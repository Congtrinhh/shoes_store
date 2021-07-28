package common_utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.util.Base64;

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
	
	public static String convertBlobToString(Blob imageBlog) {
		try(InputStream iStream = imageBlog.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while( (bytesRead = iStream.read(buffer)) != -1 ) {
				outputStream.write(buffer, 0, bytesRead);
			}
			byte[] imageBytes = outputStream.toByteArray();
			
			String base64 = Base64.getEncoder().encodeToString(imageBytes);
			return base64;
			
		} catch(Exception e) {
			System.out.println("lỗi khi convert blob ảnh sang string, "+e.getMessage());
		}
		
		
		return null;
	}
}
