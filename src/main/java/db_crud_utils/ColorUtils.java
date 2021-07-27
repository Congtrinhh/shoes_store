package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Color;

public class ColorUtils {
	public static void create(Color color, Connection conn) throws SQLException {
		String sql = "insert into color(color_name, color_code)\r\n"
				+ "values(?, ?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, color.getColor_name());
		stm.setString(2, color.getColor_code());
		
		stm.executeUpdate();
	}
}
