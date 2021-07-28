package db_crud_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import crud.entity.product_create.Brand;
import crud.entity.product_create.Category;
import entities.Admin;
import entities.Product;

public class ProductUtils {
	public static void createProduct(Connection conn, Product product) throws SQLException {
		String sql = "insert into product_line(admin_id, category_id, pr_slug, pr_name, pr_brand_name, pr_price, pr_description)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement stm = conn.prepareStatement(sql);
//			HttpSession session = req.getSession();
//			Admin admin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
//			int adminId = db_crud_utils.AdminUtils.findAdminIdByName(conn, admin.getAd_login_name());
			
			stm.setInt(1, product.getAdmin_id());
			stm.setInt(2, product.getCategory_id());
			stm.setString(3, product.getPr_slug());
			stm.setString(4, product.getPr_name());
			stm.setString(5, product.getPr_brand_name());
			stm.setBigDecimal(6, product.getPr_price());
			stm.setString(7, product.getPr_description());
			
			stm.executeUpdate();					
	}
	
	
	public static int getHighestId(Connection conn){
		String sql ="select max(product_line_id) from product_line;";
		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
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
