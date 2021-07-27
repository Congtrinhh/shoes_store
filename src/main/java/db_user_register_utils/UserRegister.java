package db_user_register_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class UserRegister {
	public static void createUser(User user, Connection conn) throws SQLException {
		String sql = "insert into user(u_login_name, u_email, u_password, u_avatar)\r\n"
				+ "values(?,?,?,?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, user.getU_login_name());
		stm.setString(2, user.getU_email());
		stm.setString(3, user.getU_password());
		stm.setString(4, user.getU_avatar());
		
		stm.executeUpdate();
	}
	
	
	public static boolean isUserNameDuplicated(Connection conn, String userName) throws SQLException {
		String sql = "select * from user where u_login_name = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, userName);
		
		ResultSet rs = stm.executeQuery();
		
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmailDuplicated(Connection conn, String email) throws SQLException {
		String sql = "select * from user where u_email = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, email);
		
		ResultSet rs = stm.executeQuery();
		
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
