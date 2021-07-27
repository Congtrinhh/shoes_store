package db_admin_login_utils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Admin;

public class AdminQuery {
	public static entities.Admin findAdmin(String login_name, String password, Connection conn) throws SQLException {
		String sql ="select * from admin where ad_login_name=? and ad_password=?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, login_name);
		stm.setString(2, password);
		
		ResultSet rs = stm.executeQuery();
		
		if (rs.next()) {
			int admin_id = rs.getInt("admin_id");
			String ad_name = rs.getString("ad_name");
			String ad_login_name = rs.getString("ad_login_name");
			String ad_password = rs.getString("ad_password");
			String ad_email = rs.getString("ad_email");
			String ad_phone_number = rs.getString("ad_phone_number");
			byte ad_state = rs.getByte("ad_state");// 1: active; 0: inactive
			
			Admin admin = new Admin(admin_id, ad_name, ad_login_name, ad_password, ad_email, ad_phone_number, ad_state);//dùng constructor đáp ứng nhu cầu dùng
			
			// admin.setAd_state((byte) 1); không có nghĩa, vì cần đồng bộ khi dùng cookie, session
			return admin;
		}
		return null;
	}
	
	public static Admin findAdminByName(String login_name, Connection conn) throws SQLException {
		String sql = "select * from admin where ad_login_name=?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, login_name);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int admin_id = rs.getInt("admin_id");
			String ad_name = rs.getString("ad_name");
			String ad_login_name = rs.getString("ad_login_name");
			String ad_password = rs.getString("ad_password");
			String ad_email = rs.getString("ad_email");
			String ad_phone_number = rs.getString("ad_phone_number");
			byte ad_state = rs.getByte("ad_state");// 1: active; 0: inactive
			
			Admin admin = new Admin(admin_id, ad_name, ad_login_name, ad_password, ad_email, ad_phone_number, ad_state);//dùng constructor đáp ứng nhu cầu dùng là đủ
			return admin;
		}
		
		return null;
	}
	
	public static int getAdminId(String login_name, Connection conn) throws SQLException {
		String sql = "select admin_id from admin where ad_login_name=?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, login_name);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			return rs.getInt("admin_id");
		}
		return 0;
	}
	
	public static void updateAdminStateOn(String login_name, Connection conn) throws SQLException {
		String sql = "update admin\r\n"
				+ "set ad_state = 1 where ad_login_name = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, login_name);
		stm.executeUpdate();	
	}
	
	public static void updateAdminStateOff(String login_name, Connection conn) throws SQLException {
		String sql = "update admin\r\n"
				+ "set ad_state = 0 where ad_login_name = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, login_name);
		stm.executeUpdate();	
	}
	 
	
}
