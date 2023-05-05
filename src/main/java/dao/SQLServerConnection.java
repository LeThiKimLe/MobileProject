package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;;

public class SQLServerConnection {
	
	private static final String dbDriver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String serverName = "LAPTOP-N0HFUMP2";
	private static final String dbName = "OnlineLearningSystem";
	private static final String portNumber = "1433";
	private static final String instance = "";// MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private static final String userID = "sa";
	private static final String password = "123456";

	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
		
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName 
				+ ";encrypt=true;trustServerCertificate=true;";
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName 
			+ ";encrypt=true;trustServerCertificate=true;";
		
		Connection conn = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(url, userID, password);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return conn;
	}
}
