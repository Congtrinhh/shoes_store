package product_detail_utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import product_detail_servlet.ImageGetter;
import product_detail_servlet.ProductGetter;
import product_detail_servlet.SpecificProductInfo;

public class ProductDetailSQL {
	public static ProductGetter getProduct(String productSlug, Connection conn) throws SQLException {
		String sql = "select p.product_line_id, p.pr_slug, p.pr_name, p.pr_price, p.pr_description, p.pr_brand_name, min(spr_price) 'spr_price'\r\n"
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
			
			return new ProductGetter(product_line_id, pr_slug, pr_name, pr_price, pr_description, pr_brand_name);
		}
		return null;
	}
	
	public static List<ImageGetter> getImagesOfProduct(Connection conn, String productSlug){
		String sql = "select img_file\r\n"
				+ "from product_line p join image i on p.product_line_id=i.product_line_id\r\n"
				+ "where pr_slug=?;";
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setString(1, productSlug);
			
			ResultSet rs = stm.executeQuery();
			ArrayList<ImageGetter> list = new ArrayList<>();
			while (rs.next()) {
				Blob image_file = rs.getBlob(1);
				
				InputStream iStream = image_file.getBinaryStream();
				byte[] buffer = new byte[4096];
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				int bytesRead = -1;
				
				while ( (bytesRead=iStream.read(buffer)) != -1 ) {
					outputStream.write(buffer, 0, bytesRead);
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String base64Str = Base64.getEncoder().encodeToString(imageBytes);
				
				iStream.close();
				outputStream.close();
				
				list.add(new ImageGetter(image_file, base64Str));
			}
			return list;
		}
		catch(Exception e) {
			
		}
		return null;
	}
	
	public static List<SpecificProductInfo> getSpecificProductsList(Connection conn, String productSlug){
		String sql="select size_number, color_code, spr_price from \r\n"
				+ "product_line p join specific_product s on p.product_line_id=s.product_line_id\r\n"
				+ "	join size on size.size_id=s.size_id join color on color.color_id=s.color_id\r\n"
				+ "where p.pr_slug=?;";
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setString(1, productSlug);
			
			ResultSet rs = stm.executeQuery();
			
			ArrayList<SpecificProductInfo> list = new ArrayList<>();
			while(rs.next()) {
				String color =rs.getString("color_code"); 
				byte size = rs.getByte("size_number");
				BigDecimal price = rs.getBigDecimal("spr_price");
				
				list.add(new SpecificProductInfo(color, size, price));
			}
			return list;		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
