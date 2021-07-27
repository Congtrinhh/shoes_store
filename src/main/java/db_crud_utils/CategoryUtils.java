package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Category;

public class CategoryUtils {
	
	public static void create(Category cat, Connection conn) throws SQLException {
		String sql = "INSERT INTO Category(admin_id, c_slug, c_name) " +
				"values(?, ?, ?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, cat.getAdmin_id());
		stm.setString(2, cat.getC_slug());
		stm.setString(3, cat.getC_name());
		
		stm.executeUpdate();
	}
	
}
