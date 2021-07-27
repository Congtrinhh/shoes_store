package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.User;

public class UserUtils {

	public static void create(User user, Connection conn) throws SQLException {
		String sql = "INSERT INTO USER(admin_id, u_name, u_login_name, u_password, u_email, "
				+ "u_phone_number, u_address) VALUES(?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, user.getAdmin_id());
		stm.setString(2, user.getU_name());
		stm.setString(3, user.getU_login_name());
		stm.setString(4, user.getU_password());
		stm.setString(5, user.getU_email());
		stm.setString(6, user.getU_phone_number());
		stm.setString(7, user.getU_address());
		
		stm.executeUpdate();
	}
	
}
