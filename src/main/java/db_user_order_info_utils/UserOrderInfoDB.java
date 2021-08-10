package db_user_order_info_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entities.District;
import entities.Order;
import entities.ProductInOrder;
import entities.Province;
import entities.Ward;

public class UserOrderInfoDB {
	
	public static List<Order> getOrderList(Connection conn, int userId) throws SQLException{
		String sql ="select * from purchase_order\r\n"
				+ "where user_id=?;";
		PreparedStatement stm =conn.prepareStatement(sql);
		stm.setInt(1, userId);
		
		List<Order> list = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while ( rs.next() ) {
			int id = rs.getInt("order_id");
			byte status = rs.getByte("or_status");
			int ward_id = rs.getInt("ward_id");
			String address = rs.getString("specific_address");
			String created_at = rs.getString("created_at");
			
			list.add( new Order(id, status, address, ward_id, created_at) );
		}
		
		return list;
	}
	
	public static Ward getWard(Connection conn, int id) throws SQLException {
		String sql ="select * from ward \r\n"
				+ "where id = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int w_id = rs.getInt("id");
			String name = rs.getString("_name");
			int d_id = rs.getInt("_district_id");
			int p_id = rs.getInt("_province_id");
			return new Ward(w_id, name, "undefined", p_id, d_id);
		}
		return null;
	}
	
	public static District getDistrict(Connection conn, int id) throws SQLException {
		String sql ="select * from district \r\n"
				+ "where id = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int d_id = rs.getInt("id");
			String name = rs.getString("_name");
			return new District(d_id, name);
		}
		return null;
	}
	
	public static Province getProvince(Connection conn, int id) throws SQLException {
		String sql ="select * from province \r\n"
				+ "where id = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int p_id = rs.getInt("id");
			String name = rs.getString("_name");
			return new Province(p_id, name);
		}
		return null;
	}
	
	public static List<ProductInOrder> getProductInOrderList(Connection conn, int orderId) throws SQLException{
		String sql ="select * from product_in_order\r\n"
				+ "where order_id = ?;";
		PreparedStatement stm =conn.prepareStatement(sql);
		stm.setInt(1, orderId);
		
		List<ProductInOrder> list = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while ( rs.next() ) {
			int pio_id = rs.getInt("product_in_order_id");
			int specific_product_id= rs.getInt("specific_product_id");
			short quantity = rs.getShort("pio_quantity");
			list.add( new ProductInOrder(pio_id, specific_product_id, orderId, quantity) );
		}
		
		return list;
	}
	
	public static ProductInOrderEntity getProductInOrder(Connection conn, int specificProductId) throws SQLException {
		String sql = "select i.img_file,  p.pr_name, c.color_name, size.size_number\r\n"
				+ "from product_in_order pio join specific_product sp on pio.specific_product_id=sp.specific_product_id\r\n"
				+ "	join product_line p on p.product_line_id=sp.product_line_id\r\n"
				+ "    join image i on i.product_line_id=p.product_line_id\r\n"
				+ "    join color c on c.color_id=sp.color_id\r\n"
				+ "    join size on size.size_id=sp.size_id\r\n"
				+ "where sp.specific_product_id=?\r\n"
				+ "group by p.product_line_id;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, specificProductId);
		ResultSet rs = stm.executeQuery();
		
		if ( rs.next() ) {
			java.sql.Blob img_file = rs.getBlob("img_file");
			String name = rs.getString("pr_name");
			String color_name = rs.getString("color_name");
			byte size = rs.getByte("size_number");
			
			String base64String = common_utils.MyUtils.convertBlobToString(img_file);
			
			return new ProductInOrderEntity(name, base64String, color_name, size);
		}
		
		return null;
	}
	
	public static int getItemPrice(Connection conn, int productInOrderId) throws SQLException {
		String sql ="select spr_price\r\n"
				+ "from specific_product sp join product_in_order pio \r\n"
				+ "	on pio.specific_product_id=sp.specific_product_id\r\n"
				+ "where product_in_order_id=?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, productInOrderId);
		ResultSet rs = stm.executeQuery();
		
		if ( rs.next() ) {
			System.out.println("Co gia, db");
			return rs.getInt(1);
		}
		
		return -1;
	}
}
