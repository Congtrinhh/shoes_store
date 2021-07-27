package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crud.entity.product_create.Brand;
import crud.entity.product_create.Category;
import entities.Product;

public class ProductUtils {
	public static void create(Product prd, Connection conn) throws SQLException {
		String sql = "insert into product_line (admin_id, category_id, pr_slug, pr_name, pr_sku, pr_brand_name, pr_description) "
				+ "values(?,?,?,?,?,?,?);";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, prd.getAdmin_id());
		stm.setInt(2, prd.getCategory_id());
		
		stm.setString(3, prd.getPr_slug());
		stm.setString(4, prd.getPr_name());
		stm.setString(5, prd.getPr_sku());
		stm.setString(6, prd.getPr_brand_name());
		stm.setString(7, prd.getPr_description());
		
		stm.executeUpdate();
	}
	
	public static int getHighestId(Connection conn) throws SQLException {
		String sql ="select max(product_line_id) from product_line;";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}
	
	
	public static ArrayList<Brand> getBrandsList(Connection conn) throws SQLException{
		String sql = "select distinct(pr_brand_name) from product_line;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		ResultSet rs = stm.executeQuery();
		
		ArrayList<Brand> list = new ArrayList<Brand>();
		int i =0;
		while (rs.next()) {
			i++;
			String brandName = rs.getString("pr_brand_name");
			list.add(new Brand(i, brandName));
		}
		return list;
	}
	
	public static ArrayList<Category> getCategoriesList(Connection conn) throws SQLException {
		String sql = "select category_id,c_name  from category;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		ResultSet rs = stm.executeQuery();
		
		ArrayList<Category> list = new ArrayList<Category>();
		
		while (rs.next()) {
			int cateId = rs.getInt("category_id");
			String cateName = rs.getString("c_name");
			list.add(new Category(cateId, cateName));
		}
		return list;
	}
}
