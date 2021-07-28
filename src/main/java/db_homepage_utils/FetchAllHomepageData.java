package db_homepage_utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import homepage_servlet.CategoryGetter;
import homepage_servlet.CtaGetter;
import homepage_servlet.ProductGetter;

public class FetchAllHomepageData {
	
	public static List<CategoryGetter> fetchCategories(Connection conn) throws SQLException {
		String sql = "select c_slug, c_name from category;";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		ArrayList<CategoryGetter> list = new ArrayList<>();
		
		while(rs.next()) {
			String c_slug = rs.getString("c_slug");
			String c_name = rs.getString("c_name");
			
			list.add(new CategoryGetter(c_slug, c_name));
		}
		
		return list;
	}
	
	public static List<ProductGetter> fetchProducts(Connection conn) throws SQLException, IOException {
		String sql = "select *, min(spr_price) 'spr_price' from product_line p join specific_product s on p.product_line_id = s.product_line_id\r\n"
				+ "join image i on i.product_line_id = p.product_line_id join category c on c.category_id=p.category_id\r\n"
				+ "group by p.product_line_id;\r\n";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		ArrayList<ProductGetter> list = new ArrayList<>();
		while(rs.next()) {
			String pr_slug = rs.getString("pr_slug");
			String pr_name = rs.getString("pr_name");
			BigDecimal pr_price = rs.getBigDecimal("spr_price");
			
			// nhận và xử lí ảnh ở đây.
			Blob img_file = rs.getBlob("img_file");
			String base64Str= common_utils.MyUtils.convertBlobToString(img_file);
			// kết thúc xử lí ảnh
			
			
			String c_slug = rs.getString("c_slug");
			String pr_brand_name = rs.getString("pr_brand_name");
			
			ProductGetter prd = new ProductGetter(pr_slug, pr_name, base64Str, c_slug, pr_brand_name, pr_price);
			prd.refineProductName();
			list.add(prd);
		}
		return list;
	}
	
	public static List<CtaGetter> fetchCtas(Connection conn) throws SQLException {
		String sql ="select cta_slug, cta_banner_location, cta_title, cta_button_text, cta_description from cta;";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		ArrayList<CtaGetter> list = new ArrayList<>();
		
		while(rs.next()) {
			String cta_slug = rs.getString("cta_slug");
			String cta_banner_location = rs.getString("cta_banner_location");
			String cta_title= rs.getString("cta_title");
			String cta_button_text= rs.getString("cta_button_text");
			String cta_description= rs.getString("cta_description");
			
			list.add(new CtaGetter(cta_slug, cta_banner_location, cta_title, cta_button_text, cta_description));
		}
		return list;
	}
}
