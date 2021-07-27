package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Size;

public class SizeUtils {
	public static void create(Size size, Connection conn) throws SQLException {
		String sql = "insert into size(size_number, size_detail)\r\n"
				+ "values(?, ?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setByte(1, size.getSize_number());
		stm.setString(2, size.getSize_detail());
		
		stm.executeUpdate();
	}
}
