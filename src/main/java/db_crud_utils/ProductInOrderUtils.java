package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.ProductInOrder;

public class ProductInOrderUtils {
	
	public static void create(ProductInOrder pio, Connection conn) throws SQLException {
		String sql = "insert into product_in_order(product_line_id, order_id, pio_size, pio_color, pio_quantity, pio_discount_percent)\r\n"
				+ "values(?,?,?,?,?,?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, pio.getProduct_line_id());
		stm.setInt(2, pio.getOrder_id());
		stm.setByte(3, pio.getPio_size());
		stm.setString(4, pio.getPio_color());
		stm.setShort(5, pio.getPio_quantity());
		stm.setByte(6, pio.getPio_discount_percent());
		
		stm.executeUpdate();
	}
}
