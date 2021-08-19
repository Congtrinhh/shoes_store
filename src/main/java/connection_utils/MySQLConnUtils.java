package connection_utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
	
	public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException {
	    
	     Class.forName("com.mysql.cj.jdbc.Driver");
	  
	     // URL Connection for MySQL:
	     // Example: 
	     // jdbc:mysql://localhost:3306/simplehr
	     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;
	 }
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "shoes_store";
		String userName = "root";
		String password = "";
		
		hostName = "congtrinhh.cggiop45nqom.ap-southeast-1.rds.amazonaws.com";
		userName = "admin";
		password="trinhquycong";
		return getMySQLConnection(hostName, dbName, userName, password);
	}
}
