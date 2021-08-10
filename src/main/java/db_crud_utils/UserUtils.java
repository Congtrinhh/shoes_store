package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.District;
import entities.Province;
import entities.Ward;


public class UserUtils {
	public static int getUserId(Connection conn, String userName) {
		String sql ="select user_id from user where u_login_name=?";
		try(PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setString(1, userName);
			
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("Lỗi get user id by name, crud util, code: " + e.getErrorCode());
		}
		return -1;
	}
	public static int changePassword(Connection conn, int id, String password) throws SQLException {
		String sql = "update user\r\n"
				+ "set u_password=?\r\n"
				+ "where user_id=?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, password);
		stm.setInt(2, id);
		
		return stm.executeUpdate();
	}
//	----------------- User info -----------------------
	public static Ward getWard(Connection conn, int id) throws SQLException {
		String sql = "select * from ward where id=?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int ward_id = rs.getInt("id");
			String name = rs.getString("_name");
			int p_id = rs.getInt("_province_id");
			int d_id = rs.getInt("_district_id");
			String prefix = rs.getString("_prefix");
			return new Ward(ward_id, name, prefix, p_id, d_id);
		}
		return null;
	}
	
	public static District getDistrict(Connection conn, int id) throws SQLException {
		String sql = "select * from district where id=?";
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
		String sql = "select * from province where id=?";
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
	
	public static List<Province> getProvinceList (Connection conn) throws SQLException{
		String sql = "select * from province";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		ResultSet rs = stm.executeQuery();
		
		List<Province> list = new ArrayList<>();
		
		while (rs.next()) {
			int p_id = rs.getInt("id");
			String name = rs.getString("_name");
			list.add( new Province(p_id, name) );
		}
		return list;
	}
}
