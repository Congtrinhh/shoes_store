package db_admin_update_info_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Admin;

public class AdminUpdateBasicInfo {
	public static void updateBasicInfo(Admin admin, Connection conn) throws SQLException {
		String sql = "update admin\r\n"
				+ "set ad_name = ?, ad_login_name = ?, ad_email = ?, ad_phone_number = ?\r\n"
				+ "where admin_id = ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, admin.getAd_name());
		stm.setString(2, admin.getAd_login_name());
		stm.setString(3, admin.getAd_email());
		stm.setString(4, admin.getAd_phone_number());
		stm.setInt(5, admin.getAdmin_id());
		
		stm.executeUpdate();
	}
}
