package db_cart_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDB {
	public static int getProductInStockQuantity(Connection conn, int id, String colorCode, int size) {
		String sql = "select spr_quantity from specific_product s join color on color.color_id=s.color_id \r\n"
				+ "	join size on size.size_id=s.size_id\r\n"
				+ "where product_line_id=? and color_code=? and size_number=?;";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, id);
			stm.setString(2, colorCode);
			stm.setInt(3, size);
			
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			else {
				throw new Exception("sản phẩm không tồn tại");
			}
		}
		catch(Exception e) {
			System.out.println("cart db, " + e.getMessage());
		}
		return -1;
	}
}
