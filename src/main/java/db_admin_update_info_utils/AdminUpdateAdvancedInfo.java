package db_admin_update_info_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Admin;

public class AdminUpdateAdvancedInfo {
	public static void updatePassword(int id, String newPassword, Connection conn) throws SQLException {
		String sql = "update admin set ad_password = ?\r\n"
				+ "where admin_id = ?;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, newPassword);
		stm.setInt(2, id);
		
		stm.executeUpdate();
	}
}
