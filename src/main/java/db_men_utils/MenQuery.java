package db_men_utils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import crud.entity.product_create.Brand;
import homepage_servlet.ProductGetter;

public class MenQuery {
	
	public static List<Brand> getBrandList(Connection conn) {
		String sql = "select * from brand";
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			
			List<Brand> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("brand_id");
				String name = rs.getString("brand_name");
				list.add(new Brand(id, name));
			}
			return list;
		}
		catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static List<ProductGetter> queryProduct(Connection conn, int brandId, int priority, int fromRange, int toRange, int pageNo) throws SQLException {		
		String sql = "";
		
		String brandStr = null;
		
		if (brandId==0) {
			brandStr = "%"; // lấy ra tất cả brand
		}
		else {
			brandStr = String.valueOf(brandId);
		}
		
		switch(priority) {
		case 1:
			sql = "select *, min(spr_price) 'spr_price' from product_line p left join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
					+ "left join specific_product s on s.product_line_id=p.product_line_id\r\n"
					+ "where pr_brand_id like ? and (pr_price between ? and ?) \r\n"
					+ "group by p.product_line_id\r\n"
					+ "order by p.created_at DESC limit ? offset ?;";
			
			break;
		case 2:
			sql = "select *, min(spr_price) 'spr_price' from product_line p left join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
					+ "left join specific_product s on s.product_line_id=p.product_line_id\r\n"
					+ "where pr_brand_id like ? and (pr_price between ? and ?) \r\n"
					+ "group by p.product_line_id\r\n"
					+ "order by pr_price ASC "
					+ "limit ? offset ?;";
			//priorityStr = "pr_price ASC";
			break;
		case 3:
			sql = "select *, min(spr_price) 'spr_price' from product_line p left join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
					+ "left join specific_product s on s.product_line_id=p.product_line_id\r\n"
					+ "where pr_brand_id like ? and (pr_price between ? and ?) \r\n"
					+ "group by p.product_line_id\r\n"
					+ "order by pr_price DESC "
					+ "limit ? offset ?;";
			//priorityStr = "pr_price DESC";
			break;
		default:
			sql = "select *, min(spr_price) 'spr_price' from product_line p left join image i on p.product_line_id=i.product_line_id join category c on c.category_id=p.category_id\r\n"
					+ "left join specific_product s on s.product_line_id=p.product_line_id\r\n"
					+ "where pr_brand_id like ? and (pr_price between ? and ?) \r\n"
					+ "group by p.product_line_id\r\n"
					+ "order by p.created_at DESC limit ? offset ?;";
		}
				
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, brandStr);
		stm.setInt(2, fromRange);
		stm.setInt(3, toRange);
		stm.setInt(4, constants.SystemConstants.PRODUCTS_PER_PAGE);
		stm.setInt(5, (pageNo-1) * constants.SystemConstants.PRODUCTS_PER_PAGE);
		
		ResultSet rs = stm.executeQuery();
		List<ProductGetter> list = new ArrayList<ProductGetter>();
		while (rs.next()) {
			String name = rs.getString("pr_name");
			BigDecimal price = rs.getBigDecimal("spr_price");
			int brand_id = rs.getInt("pr_brand_id");
			String slug = rs.getString("pr_slug");
			String c_slug = rs.getString("c_slug");
			
			Blob img_file = rs.getBlob("img_file");
			String base64String = common_utils.MyUtils.convertBlobToString(img_file);
			
			ProductGetter product = new ProductGetter(slug, name, base64String, c_slug, brand_id , price);
			list.add(product);
		}
		return list;
	}
	
	public static int countTotalProducts(Connection conn, int brandId, int fromRange, int toRange) throws SQLException {
		String sql = "select count(*) from product_line\r\n"
				+ "where pr_brand_id like ? and (pr_price between ? and ?);";
		
		String brandStr = null;
		
		// nếu = 0 -> all, còn không thì lặp qua tất cả id trong brand table và so sánh
		if (brandId==0) {
			brandStr = "%"; // lấy ra tất cả brand
		}
		else {
			brandStr = String.valueOf(brandId);
		}
		
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, brandStr);
		stm.setInt(2, fromRange);
		stm.setInt(3, toRange);
		
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}
	
	public static int calculateTotalPages(int totalProducts, int productPerPage) {
		int total = totalProducts / productPerPage;
		if (totalProducts % productPerPage > 0) {
			total++;
		}
		return total;
	}
}
