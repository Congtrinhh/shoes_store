package connection_utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		return MySQLConnUtils.getMySQLConnection();
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	System.out.println("From closeQuietly (ConnectionUtils)");
		}
	}
	
	public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
