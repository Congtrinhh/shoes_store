package common_utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;

public class MyUtils {
	
	public static final String STORED_CONNECTION = "STORED_CONNECTION";
	
	
	// nếu có lỗi, thử thay kiểu req thành ServletRequest
	public static void storeConnection(ServletRequest req, Connection conn) {
		req.setAttribute(STORED_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest req) {
		return (Connection)req.getAttribute(STORED_CONNECTION);
	}
	

}
