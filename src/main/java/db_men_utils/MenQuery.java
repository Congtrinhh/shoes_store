package db_men_utils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import homepage_servlet.ProductGetter;

public class MenQuery {
	public static List<ProductGetter> queryProduct(Connection conn, int brand, int priority, int fromRange, int toRange, int pageNo) throws SQLException {
		String sql = "select *, min(spr_price) 'spr_price' from product_line p join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
				+ "join specific_product s on s.product_line_id=p.product_line_id\r\n"
				+ "where pr_brand_name like ? and (pr_price between ? and ?) \r\n"
				+ "group by p.product_line_id\r\n"
				+ "order by ? limit ? offset ?;";
		
		String brandStr = null;
		String priorityStr = null;
		
		switch(brand) {
		case 1:
			brandStr = "%";
			break;
		case 2:
			brandStr = "adidas";
			break;
		case 3:
			brandStr = "bitis";
			break;
		case 4:
			brandStr = "nike";
			break;
		case 5:
			brandStr = "ananas";
			break;
		case 6:
			brandStr = "new balance";
			break;
		default:
			brandStr = "all";
		}
		
		switch(priority) {
		case 1:
			priorityStr = "product_line.created_at DESC";
			break;
		case 2:
			priorityStr = "pr_price ASC";
			break;
		case 3:
			priorityStr = "pr_price DESC";
			break;
		default:
			priorityStr = "product_line.created_at DESC";
		}
				
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, brandStr);
		stm.setInt(2, fromRange);
		stm.setInt(3, toRange);
		stm.setString(4, priorityStr);
		stm.setInt(5, constants.SystemConstants.PRODUCTS_PER_PAGE);
		stm.setInt(6, (pageNo-1) * constants.SystemConstants.PRODUCTS_PER_PAGE);
		
		ResultSet rs = stm.executeQuery();
		List<ProductGetter> list = new ArrayList<ProductGetter>();
		while (rs.next()) {
			int id = rs.getInt("product_line_id");
			String name = rs.getString("pr_name");
			BigDecimal price = rs.getBigDecimal("spr_price");
			String brandName = rs.getString("pr_brand_name");
			String slug = rs.getString("pr_slug");
			String c_slug = rs.getString("c_slug");
			
			Blob img_file = rs.getBlob("img_file");
			String base64String = common_utils.MyUtils.convertBlobToString(img_file);
			
			ProductGetter product = new ProductGetter(slug, name, base64String, c_slug, brandName, price);
			list.add(product);
		}
		return (ArrayList<ProductGetter>) list;
	}
	
	public static List<ProductGetter> queryProduct(HttpServletRequest req, Connection conn, int brand, int priority, int fromRange, int toRange, int pageNo) throws SQLException {
		String sql = "select *, min(spr_price) 'spr_price' from product_line p join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
				+ "join specific_product s on s.product_line_id=p.product_line_id\r\n"
				+ "where pr_brand_name like ? and (pr_price between ? and ?) \r\n"
				+ "group by p.product_line_id\r\n"
				+ "order by ? limit ? offset ?;";
		
		String brandStr = null;
		String priorityStr = null;
		
		switch(brand) {
		case 1:
			brandStr = "%";
			break;
		case 2:
			brandStr = "adidas";
			break;
		case 3:
			brandStr = "bitis";
			break;
		case 4:
			brandStr = "nike";
			break;
		case 5:
			brandStr = "ananas";
			break;
		case 6:
			brandStr = "new balance";
			break;
		default:
			brandStr = "all";
		}
		
		switch(priority) {
		case 1:
			priorityStr = "product_line.created_at DESC";
			break;
		case 2:
			priorityStr = "pr_price ASC";
			break;
		case 3:
			priorityStr = "pr_price DESC";
			break;
		default:
			priorityStr = "product_line.created_at DESC";
		}
				
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, brandStr);
		stm.setInt(2, fromRange);
		stm.setInt(3, toRange);
		stm.setString(4, priorityStr);
		stm.setInt(5, constants.SystemConstants.PRODUCTS_PER_PAGE);
		stm.setInt(6, (pageNo-1) * constants.SystemConstants.PRODUCTS_PER_PAGE);
		
		ResultSet rs = stm.executeQuery();
		List<ProductGetter> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("product_line_id");
			String name = rs.getString("pr_name");
			BigDecimal price = rs.getBigDecimal("spr_price");
			String brandName = rs.getString("pr_brand_name");
			String slug = rs.getString("pr_slug");
			String c_slug = rs.getString("c_slug");
					
			Blob img_file= rs.getBlob("img_file");
			String base64String = common_utils.MyUtils.convertBlobToString(img_file);
			
			
			ProductGetter product = new ProductGetter(slug, name, base64String, c_slug, brandName, price);
			
			if (rs.isFirst()) {
				System.out.println("Đang ở đầu row product đây nè");
			HttpSession session= req.getSession();
			product.setCurrentPage((int)session.getAttribute("currentPage"));
			product.setTotalPages((int)session.getAttribute("totalPages"));
			}
			
			list.add(product);
		}
		return (ArrayList<ProductGetter>) list;
	}
	
	public static int countTotalProducts(Connection conn, int brand, int priority, int fromRange, int toRange) throws SQLException {
		String sql = "select *, min(spr_price) 'spr_price' from product_line p join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
				+ "join specific_product s on s.product_line_id=p.product_line_id\r\n"
				+ "where pr_brand_name like ? and (pr_price between ? and ?) \r\n"
				+ "group by p.product_line_id\r\n"
				+ "order by ?;";
		
		String brandStr = null;
		String priorityStr = null;
		
		switch(brand) {
		case 1:
			brandStr = "%";
			break;
		case 2:
			brandStr = "adidas";
			break;
		case 3:
			brandStr = "bitis";
			break;
		case 4:
			brandStr = "nike";
			break;
		case 5:
			brandStr = "ananas";
			break;
		case 6:
			brandStr = "new balance";
			break;
		default:
			brandStr = "all";
		}
		
		switch(priority) {
		case 1:
			priorityStr = "created_at desc";
			break;
		case 2:
			priorityStr = "pr_price asc";
			break;
		case 3:
			priorityStr = "pr_price desc";
			break;
		default:
			priorityStr = "created_at desc";
		}
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, brandStr);
		stm.setInt(2, fromRange);
		stm.setInt(3, toRange);
		stm.setString(4, priorityStr);
		
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}
	
	public static int calculateTotalPages(int totalProducts) {
		int total = totalProducts / constants.SystemConstants.PRODUCTS_PER_PAGE;
		if (totalProducts % constants.SystemConstants.PRODUCTS_PER_PAGE > 0) {
			total++;
		}
		return total;
	}
}
