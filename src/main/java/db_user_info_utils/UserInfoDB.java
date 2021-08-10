package db_user_info_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class UserInfoDB {
	
	// nếu 1 trong 3 trường dưới mà trùng trong db, trả về tên trường bị trùng, nếu không có lỗi, trả vê null
	public static String getDuplicatedMessage(Connection conn, int id, String userName, String phone, String email) {
		
		String sql = "update user\r\n"
				+ "set u_login_name=?, u_phone_number=?, u_email=?\r\n"
				+ "where user_id=?;";
		try {
			conn.setAutoCommit(false); //
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, userName);
			stm.setString(2, phone);
			stm.setString(3, email);
			stm.setInt(4, id);
			
			int row = stm.executeUpdate();
			
			if ( row>0 ) {
				System.out.println("Thanh cong nhung chua commit");
				conn.rollback(); // để không update thật
				return null;
			}
			else {
//				System.out.println("Rollback vi that bai");
			}
			
		}
		catch (SQLException e) {
			System.out.println("code: " +e.getErrorCode());
			System.out.println("message: " +e.getMessage());
			System.out.println("sqlstate: " +e.getSQLState());
			System.out.println("cause: " +e.getCause());
			
			if ( e.getErrorCode()==1062 ) {
				String message = e.getMessage();
				if ( message.contains("u_login_name") ) {
					return "Tên đăng nhập đã tồn tại";
				}
				else if ( message.contains("u_phone_number") ) {
					return "Số điện thoại đã tồn tại";
				}
				else if ( message.contains("u_email") ) {
					return "Email đã tồn tại";
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("mes: " +e.getMessage());
			return e.getMessage();
		}
		
		return null;
	}
	
	public static boolean isPasswordTrue(Connection conn, int id, String password) throws SQLException {
		String sql = "select * from user\r\n"
				+ "where u_password=? and user_id=?;";
		
		PreparedStatement stm= conn.prepareStatement(sql);
		stm.setString(1, password);
		stm.setInt(2, id);
		
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
		
	}
	
	// ----------- update user -----------------
	public static int updateUser(Connection conn, User u) throws SQLException {
		String sql = "update user\r\n"
				+ "set u_login_name=?, u_name=?, u_phone_number=?,\r\n"
				+ "	u_email=?,ward_id=?,specific_address=?,\r\n"
				+ "	u_password=?,updated_at=?\r\n"
				+ "where user_id=?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, u.getU_login_name());
		stm.setString(2, u.getU_name());
		stm.setString(3, u.getU_phone_number());
		stm.setString(4, u.getU_email());
		
		stm.setInt(5, u.getWard_id());
		
		stm.setString(6, u.getSpecific_address());
		stm.setString(7, u.getU_password());
		stm.setString(8, u.getUpdated_at());
		stm.setInt(9, u.getUser_id());
		
		return stm.executeUpdate();
		
	}
}
