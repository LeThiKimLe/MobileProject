package dao;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDataBase {
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException
	{
		return SQLServerConnection.initializeDatabase();
	}

}
