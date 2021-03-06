package db_checkout_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.District;
import entities.Province;
import entities.Ward;

public class CheckoutDB {
	
	public static int decreaseInStockQuantity(Connection conn, int id, String colorCode, int size, int quantity) {
		String sql = "update specific_product s join product_line p on p.product_line_id=s.product_line_id\r\n"
				+ "	join color on color.color_id=s.color_id join size on size.size_id=s.size_id\r\n"
				+ "set s.spr_quantity= s.spr_quantity-?\r\n"
				+ "where p.product_line_id=? and color_code=? and size_number=?;";
		
		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setInt(1, quantity);
			stm.setInt(2, id);
			stm.setString(3, colorCode);
			stm.setInt(4, size);
			
			int row = stm.executeUpdate(); // = 1 neu thanh cong
			return row;
		}
		catch(Exception e) {
			System.out.println("Lỗi trừ lượng sp trong kho Checkout db, "+e.getMessage());
		}
		
		return -1;
	}
	
	public static int getHighestOrderId(Connection conn) { // lấy ra id cao nhất, để có thể có id chèn vào product_in_order
		String sql="select max(order_id) from purchase_order;";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println("Lỗi get highest order id Checkout db, "+e.getMessage());
		}
		return -1;
	}
	
	public static void createOrder(Connection conn, int userNameId, int wardId, String specificAddress, String created_at) { // them params ward,..
		String sql ="insert into purchase_order(user_id, ward_id, specific_address, created_at) \r\n"
				+ "values(?, ?, ?, ?);";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, userNameId);
			stm.setInt(2, wardId);
			stm.setString(3, specificAddress);
			stm.setString(4, created_at);
			
			stm.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Lỗi tạo Order mới Checkout db, "+e.getMessage());
		}
		
	}
	
	public static void createProductInOrder(Connection conn, int orderId, int specificProductId, int quantity)  {
		String sql="insert into product_in_order(order_id,specific_product_id,pio_quantity)\r\n"
				+ "values(?,?,?);";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, orderId);
			stm.setInt(2, specificProductId);
			stm.setInt(3, quantity);
			
			stm.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Lỗi create product in order Checkout db, "+e.getMessage());
		}
	}
	
	public static int getSpecificProductId(Connection conn, int productLineId, String colorCode, int size) {
		String sql="select specific_product_id from specific_product s\r\n"
				+ "	join color on color.color_id=s.color_id join size on size.size_id=s.size_id\r\n"
				+ "where s.product_line_id=? and color_code=? and size_number=?;";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, productLineId);
			stm.setString(2, colorCode);
			stm.setInt(3, size);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println("Lỗi get specific product id Checkout db, "+e.getMessage());
		}
		
		return -1;
	}
	
	public static List<Province> getAllProvinces(Connection conn){
		String sql ="select id, _name from province;";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			
			List<Province> list = new ArrayList<>();
			while(rs.next()) {
				int id=rs.getInt(1);
				String name= rs.getString(2);
				list.add(new Province(id, name));
			}
			return list;
		}
		catch(Exception e) {
			System.out.println("Lỗi get all provinces Checkout db, "+e.getMessage());
		}
		return null;
	}
	
	public static List<District> getAllDistricts(Connection conn, int provinceId) {
		String sql ="select id , _name from district\r\n"
				+ "where _province_id=?;";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, provinceId);
			
			ResultSet rs = stm.executeQuery();
			
			List<District> list = new ArrayList<>();
			while(rs.next()) {
				int id=rs.getInt(1);
				String name= rs.getString(2);
				list.add( new District(id, name) );
			}
			return list;
		}
		catch(Exception e) {
			System.out.println("Lỗi get all districts Checkout db, "+e.getMessage());
		}
		return null;
	}
	
	public static List<Ward> getAllWards(Connection conn, int districtId) {
		String sql ="select id , _name from ward\r\n"
				+ "where _district_id=?;";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, districtId);
			
			ResultSet rs = stm.executeQuery();
			
			List<Ward> list = new ArrayList<>();
			while(rs.next()) {
				int id=rs.getInt(1);
				String name= rs.getString(2);
				list.add( new Ward(id, name) );
			}
			return list;
		}
		catch(Exception e) {
			System.out.println("Lỗi get all wards Checkout db, "+e.getMessage());
		}
		return null;
	}
	
}
