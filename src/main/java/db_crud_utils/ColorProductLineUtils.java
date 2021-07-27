package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.ColorProductLine;

public class ColorProductLineUtils {
	public static void create(ColorProductLine cpl, Connection conn) throws SQLException {
		String sql = "insert into color_product_line(product_line_id, color_id, cpl_quantity) \r\n"
				+ "values(?, ?, ?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, cpl.getProduct_line_id());
		stm.setInt(2, cpl.getColor_id());
		stm.setShort(3, cpl.getCpl_quantity());
		
		stm.executeUpdate();
	}
}
