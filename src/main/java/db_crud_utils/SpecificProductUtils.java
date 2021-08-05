package db_crud_utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entities.Color;
import entities.Product;
import entities.Size;
import entities.SpecificProduct;

public class SpecificProductUtils {
	
	/* ----------------- CREATE ---------------------- */
	public static List<Product> getProductsList(Connection conn) {
		String sql = "select product_line_id, pr_name from product_line\r\n" + "order by pr_name;";
		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			List<Product> list = new ArrayList<>();

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getInt(1), rs.getString(2));
				list.add(p);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Color> getColorsList(Connection conn) {
		String sql = "select color_id,color_name from color;";

		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			ResultSet rs = stm.executeQuery();

			List<Color> list = new ArrayList<>();
			while (rs.next()) {
				Color c = new Color(rs.getInt(1), rs.getString(2));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi get all color name, db specific, " + e.getMessage());
		}

		return null;
	}

	public static List<Size> getSizesList(Connection conn) {
		String sql = "select size_id, size_number from size\r\n" + "order by size_number;";

		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			ResultSet rs = stm.executeQuery();

			List<Size> list = new ArrayList<>();
			while (rs.next()) {
				Size s = new Size(rs.getInt(1), rs.getByte(2));
				list.add(s);
			}
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi get all size name, db specific, " + e.getMessage());
		}

		return null;
	}

	public static void createSpecificProduct(Connection conn, int productLineId, int colorId, int sizeId,
			BigDecimal price, int quantity) throws SQLException {
		String sql = "insert into specific_product(product_line_id,color_id,size_id,spr_price,spr_quantity)\r\n"
				+ "values(?,?,?,?,?);";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, productLineId);
		stm.setInt(2, colorId);
		stm.setInt(3, sizeId);
		stm.setBigDecimal(4, price);
		stm.setInt(5, quantity);

		stm.executeUpdate();
		System.out.println("created");

	}
	
	/* ----------------- READ --------------------*/
	public static List<SpecificProduct> getSpecificProductList(Connection conn, int ipp, int pageNo) throws SQLException {
		String sql = "select * from specific_product\r\n"
				+ "limit ? offset ?;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, ipp);
		stm.setInt(2, (pageNo-1)*ipp);
		
		ResultSet rs = stm.executeQuery();
		
		List<SpecificProduct> list = new ArrayList<>();
		
		while (rs.next()) {
			int id = rs.getInt("specific_product_id");
			int p_id = rs.getInt("product_line_id");
			int c_id = rs.getInt("color_id");
			int s_id = rs.getInt("size_id");
			BigDecimal price = rs.getBigDecimal("spr_price");
			int quantity = rs.getInt("spr_quantity");
			
			list.add( new SpecificProduct(id, p_id, c_id, s_id, price, quantity) );
		}
		stm.close();
		
		return list;
	}
	
	public static int getTotalProduct(Connection conn) throws SQLException {
		String sql ="select count(*) from specific_product;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 1;
	}
	
	/* ----------------- DELETE --------------------*/
	
}
