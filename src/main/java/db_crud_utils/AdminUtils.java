package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.dao.DuplicateKeyException;

import entities.Admin;

public class AdminUtils {
	
	public static void create(Admin ad, Connection conn) throws Exception {
		
		String sql = "INSERT INTO ADMIN(ad_name, ad_login_name, ad_password, ad_email, ad_phone_number, ad_state, ad_remember_token) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, ad.getAd_name());
			stm.setString(2, ad.getAd_login_name());
			stm.setString(3, ad.getAd_password());
			stm.setString(4, ad.getAd_email());
			stm.setString(5, ad.getAd_phone_number());
			stm.setByte(6, ad.getAd_state());
			stm.setByte(7, ad.getAd_remember_token());
			
			stm.executeUpdate();
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connection_utils.ConnectionUtils.rollbackQuietly(conn);
			throw new DuplicateKeyException("Trùng giá trị với bản ghi trước");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			connection_utils.ConnectionUtils.rollbackQuietly(conn);
			throw new Exception(e.getMessage());
		} finally {
			connection_utils.ConnectionUtils .closeQuietly(conn);
		}
	}
	
	
	public static int findAdminIdByName(Connection conn, String userName) {
		String sql = "select admin_id from admin where ad_login_name = ?;";
		
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setString(1, userName);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		}
		catch(Exception e) {
			System.out.println("Loi "+e.getMessage());
		}
		return -1;
	}
	
}
