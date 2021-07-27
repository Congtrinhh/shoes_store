package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.SizeProductLine;

public class SizeProductLineUtils {
	public static void create(SizeProductLine spl, Connection conn) throws SQLException {
		String sql = "insert into size_product_line(product_line_id, size_id, spl_quantity)\r\n"
				+ "values(?, ?, ?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, spl.getProduct_line_id());
		stm.setInt(2, spl.getSize_id());
		stm.setShort(3, spl.getSpl_quantity());
		
		stm.executeUpdate();
	}
}
