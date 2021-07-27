package product_detail_utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import product_detail_servlet.ProductGetter;

public class ProductDetailSQL {
	public static ProductGetter getProduct(String productSlug, Connection conn) throws SQLException {
		String sql = "select p.product_line_id, p.pr_slug, p.pr_name, p.pr_price, p.pr_description, p.pr_brand_name, i.img_location, min(spr_price) 'spr_price'\r\n"
				+ "from product_line p join image i on p.product_line_id=i.product_line_id \r\n"
				+ "join specific_product s on s.product_line_id=p.product_line_id\r\n"
				+ "where p.pr_slug=? \r\n"
				+ "group by p.product_line_id;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, productSlug);
		
		ResultSet rs = stm.executeQuery();
		
		if (rs.next()) {
			int product_line_id = rs.getInt("product_line_id");
			String pr_slug = rs.getString("pr_slug");
			String pr_name = rs.getString("pr_name");
			BigDecimal pr_price = rs.getBigDecimal("spr_price");
			String pr_description = rs.getString("pr_description");
			String pr_brand_name = rs.getString("pr_brand_name");
			String img_location = rs.getString("img_location");
			
			return new ProductGetter(product_line_id, pr_slug, pr_name, pr_price, pr_description, pr_brand_name, img_location);
		}
		return null;
	}
}
