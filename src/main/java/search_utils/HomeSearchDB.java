package search_utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import homepage_servlet.ProductGetter;

public class HomeSearchDB {
	public static List<ProductGetter> getProductListWithSimilarName(Connection conn, String name, int ipp, int pageNo) throws SQLException {
		String sql ="select *\r\n"
				+ "	from product_line p join image i on p.product_line_id=i.product_line_id\r\n"
				+ "	join category c on c.category_id=p.category_id\r\n"
				+ "	join specific_product s on s.product_line_id=p.product_line_id\r\n"
				+ "where p.pr_name like ? \r\n"
				+ "group by p.product_line_id\r\n"
				+ "having count(s.specific_product_id) > 0\r\n"
				+ "limit ? offset ?;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, "%"+ name +"%");
		stm.setInt(2, ipp);
		stm.setInt(3, (pageNo-1)* constants.SystemConstants.PRODUCTS_PER_PAGE );
		
		ResultSet rs = stm.executeQuery();
		List<ProductGetter> list = new ArrayList<>();
		while ( rs.next() ) {
			String pName = rs.getString("pr_name");
			BigDecimal price = rs.getBigDecimal("pr_price");
			java.sql.Blob img_file = rs.getBlob("img_file");
			String pSlug = rs.getString("pr_slug");
			String cSlug = rs.getString("c_slug");
			
			String base64Image = common_utils.MyUtils.convertBlobToString(img_file);
			
			ProductGetter p = new ProductGetter(pSlug, pName, base64Image, cSlug, price);
			list.add(p);
		}
		return list;
	}
	
	public static int getItemCount(Connection conn, String name) throws SQLException {
		String sql ="select count(*) from\r\n"
				+ "(select count(*)\r\n"
				+ "	from product_line p join image i on p.product_line_id=i.product_line_id\r\n"
				+ "	join category c on c.category_id=p.category_id\r\n"
				+ "	join specific_product s on s.product_line_id=p.product_line_id\r\n"
				+ "where p.pr_name like ? \r\n"
				+ "group by p.product_line_id\r\n"
				+ "having count(s.specific_product_id) > 0) as rs;";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, "%"+name+"%");
		ResultSet rs = stm.executeQuery();

		if ( rs.next() ) {
			return rs.getInt(1);
		}
		return -1;
	}
}
