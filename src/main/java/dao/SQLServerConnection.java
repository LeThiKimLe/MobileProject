package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;;

public class SQLServerConnection
{		
		public static Connection initializeDatabase()throws SQLException,ClassNotFoundException
		{
			
			String dbDriver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String dbURL ="jdbc:sqlserver://localhost:1433";
			String dbName = "OnlineLearningSystem";
			String dbUsername = "sa";
			String dbPassword = "123456";
			String connectionURL = dbURL + ";databaseName="+dbName + ";encrypt=true;trustServerCertificate=true;";
			Connection conn=null;
			try {
				Class.forName(dbDriver);
				conn=DriverManager.getConnection(connectionURL, dbUsername, dbPassword);
				System.out.print("Connection successfully");
			} catch (Exception ex){
				System.out.print("Connection failure");
				ex.printStackTrace();
			}
			return conn;
		}
}
