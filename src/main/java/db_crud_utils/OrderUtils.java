package db_crud_utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Order;

public class OrderUtils {
	
	//query order list bởi order id và user name, nếu muốn lấy tất cả order list, đặt order id = "%" và user name= "%" (dùng sql wildcard)
	public static List<Order> getOrderList(Connection conn, String orderId, String userName, int ipp, int pageNo ) throws SQLException {
		String sql ="select * \r\n"
				+ "from purchase_order o join user u on o.user_id=u.user_id\r\n"
				+ "where order_id like ? and u.u_login_name like ? \r\n"
				+ "limit ? offset ?";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, orderId);
		stm.setString(2, userName);
		stm.setInt(3, ipp);
		stm.setInt(4, (pageNo-1)*ipp);
		
		ResultSet rs = stm.executeQuery();
		List<Order> list = new ArrayList<>();
		while ( rs.next() ) {
			int order_id = rs.getInt("order_id");
			int admin_id = rs.getInt("admin_id");
			int user_id = rs.getInt("user_id");
			BigDecimal shipping_cost = rs.getBigDecimal("or_shipping_cost");
			byte status_code = rs.getByte("or_status");
			int ward_id = rs.getInt("ward_id");
			String s_address = rs.getString("specific_address");
			String created_at = rs.getString("created_at");
			String updated_at = rs.getString("updated_at");
			
			Order o = new Order(order_id, admin_id, user_id, shipping_cost, status_code, s_address, ward_id, created_at, updated_at);
			list.add(o);
		}
		return list;
	}
	
	public static int getItemCount(Connection conn, String orderId, String userName) throws SQLException {
		String sql ="select count(*) \r\n"
				+ "from purchase_order o join user u on o.user_id=u.user_id\r\n"
				+ "where order_id like ? and u.u_login_name like ?;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, orderId);
		stm.setString(2, userName);
		
		ResultSet rs = stm.executeQuery();
		
		if ( rs.next() ) {
			return rs.getInt(1);
		}
		
		return -1;
	}
}
