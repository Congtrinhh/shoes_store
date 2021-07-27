package db_user_login_utils;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class UserQuery {
	public static User findUser(String login_name, String password, Connection conn) throws SQLException {
		String sql = "select user_id, u_name, u_login_name,u_password,u_email, u_phone_number,u_address,u_avatar from user where u_login_name = ? and u_password = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, login_name);
		stm.setString(2, password);
		
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int user_id = rs.getInt("user_id");
			String u_name = rs.getString("u_name");
			String u_login_name = rs.getString("u_login_name");
			String u_password = rs.getString("u_password");
			String u_email = rs.getString("u_email");
			String u_phone_number = rs.getString("u_phone_number");
			String u_address = rs.getString("u_address");
			String u_avatar = rs.getString("u_avatar");
			
			return new User(user_id, u_name, u_login_name, u_password, u_email, u_phone_number, u_address, u_avatar);
		}
		
		return null;
	}
	public static User findUserByName(String login_name, Connection conn) throws SQLException {
		String sql = "select user_id, u_name, u_login_name,u_password,u_email, u_phone_number,u_address,u_avatar from user where u_login_name = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, login_name);
		
		ResultSet rs = stm.executeQuery();
		if(rs.next()) {
			int user_id = rs.getInt("user_id");
			String u_name = rs.getString("u_name");
			String u_login_name = rs.getString("u_login_name");
			String u_password = rs.getString("u_password");
			String u_email = rs.getString("u_email");
			String u_phone_number = rs.getString("u_phone_number");
			String u_address = rs.getString("u_address");
			String u_avatar = rs.getString("u_avatar");
			
			return new User(user_id, u_name, u_login_name, u_password, u_email, u_phone_number, u_address, u_avatar);
		}
		return null;
	}
	
	public static void updateUserStateOn(String login_name, Connection conn) throws SQLException {
		String sql = "update user set u_state = 1 where u_login_name = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, login_name);
		stm.executeUpdate();	
	}
	
	public static void updateUserStateOff(String login_name, Connection conn) throws SQLException {
		String sql = "update user set u_state = 0 where u_login_name = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, login_name);
		stm.executeUpdate();	
	}
}
