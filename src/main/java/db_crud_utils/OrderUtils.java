package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Order;

public class OrderUtils {
	
	public static void create(Order od, Connection conn) throws SQLException {
		String sql = "INSERT INTO purchase_order(admin_id,user_id, or_total_cost, or_shipping_cost, or_status) "
				+ "VALUES(?,?,?,?,?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, od.getAdmin_id());
		stm.setInt(2, od.getUser_id());
		stm.setBigDecimal(3, od.getOr_total_cost());
		stm.setBigDecimal(4, od.getOr_shipping_cost());
		stm.setByte(5, od.getOr_status()); // 0: chưa thanh toán; 1: đã thanh toán; 3: đang giao hàng; 4. đã giao hàng
		
		stm.executeUpdate();
	}
}
