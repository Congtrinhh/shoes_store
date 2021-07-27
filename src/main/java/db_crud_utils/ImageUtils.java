package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Image;

public class ImageUtils {
	public static void create(Image img, Connection conn) throws SQLException {
		String sql = "insert into image(admin_id, product_line_id, img_name, img_location)\r\n"
				+ "values(?,?,?,?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, img.getAdmin_id());
		stm.setInt(2, img.getProduct_line_id());
		
		stm.setString(3, img.getImg_name());
		stm.setString(4, img.getImg_location());
		
		stm.executeUpdate();
	}
}
