package db_crud_utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.entity.product_create.Brand;
import crud.entity.product_create.Category;
import entities.Product;
import homepage_servlet.ProductGetter;

public class ProductUtils {
	public static void createProduct(Connection conn, Product product) throws SQLException {
		String sql = "insert into product_line(admin_id, category_id, pr_slug, pr_name, pr_brand_id, pr_price, pr_description, created_at)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?,?);";
		PreparedStatement stm = conn.prepareStatement(sql);		
			stm.setInt(1, product.getAdmin_id());
			stm.setInt(2, product.getCategory_id());
			stm.setString(3, product.getPr_slug());
			stm.setString(4, product.getPr_name());
			stm.setInt(5, product.getPr_brand_id());
			stm.setBigDecimal(6, product.getPr_price());
			stm.setString(7, product.getPr_description());
			stm.setString(8, product.getCreated_at());
			
			stm.executeUpdate();					
	}
	
	public static ProductGetter getProduct(Connection conn, int id) {
		String sql = "select p.product_line_id, pr_name, pr_slug, pr_price, brand_name,c_name, pr_description\r\n"
				+ "from product_line p join category c on p.category_id=c.category_id\r\n"
				+ "			join brand b on b.brand_id=p.pr_brand_id\r\n"
				+ "where p.product_line_id=?;";
		try( PreparedStatement stm=conn.prepareStatement(sql) ) {
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				int p_id = rs.getInt("product_line_id");
				String p_name = rs.getString("pr_name");
				String p_slug = rs.getString("pr_slug");
				BigDecimal price = rs.getBigDecimal("pr_price");
				String brand = rs.getString("brand_name");
				String cate = rs.getString("c_name");
				String desc = rs.getString("pr_description");
				return new ProductGetter(p_slug, p_name, price, p_id, brand, cate, desc);
			}
			else {
				System.out.println("tại vì brand chưa có đấy");
			}
		}
		catch (SQLException e) {
			System.out.println("get product by id, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("get product by id, mes: "+e.getMessage());
		}
		
		return null;
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
	
	
	public static List<Brand> getBrandList(Connection conn) throws SQLException{
		String sql = "select brand_id, brand_name from brand;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		ResultSet rs = stm.executeQuery();
		
		List<Brand> list = new ArrayList<Brand>();
		while (rs.next()) {
			int id = rs.getInt("brand_id");
			String brandName = rs.getString("brand_name");
			list.add(new Brand(id, brandName));
		}
		return list;
	}
	
	public static List<Category> getCategoryList(Connection conn) throws SQLException {
		String sql = "select category_id,c_name  from category;";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		ResultSet rs = stm.executeQuery();
		
		List<Category> list = new ArrayList<Category>();
		
		while (rs.next()) {
			int cateId = rs.getInt("category_id");
			String cateName = rs.getString("c_name");
			list.add(new Category(cateId, cateName));
		}
		return list;
	}
	
	public static List<Product> getProductListForAdmin(Connection conn, int itemPerPage, int pageNo){
		String sql = "select * from product_line\r\n"
				+ "order by product_line_id\r\n"
				+ "limit ? offset ?;";
		
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, itemPerPage);
			stm.setInt(2, (pageNo-1)*itemPerPage);
			
			ResultSet rs = stm.executeQuery();
			List<Product> list = new ArrayList<>();
			
			while(rs.next()) {
				int product_line_id = rs.getInt(1);
				int ad_id = rs.getInt(2);
				int cate_id = rs.getInt(3);
				String pr_slug = rs.getString(4);
				String pr_name = rs.getString(5);
				int pr_brand_id = rs.getInt(6);
				BigDecimal price = rs.getBigDecimal(7);
				String pr_sku = rs.getString(8);
				String pr_des = rs.getString(9);
				String created = rs.getString(10);
				String updated = rs.getString(11);
				
				Product product = new Product(product_line_id, ad_id, cate_id, pr_slug, pr_name, pr_sku, pr_brand_id, price, pr_des, created, updated);
				list.add(product);
			}
			return list;
		}
		catch (SQLException e) {
			System.out.println("get all product for admin, code: "+e.getErrorCode());
		}
		catch(Exception e) {
			System.out.println("get all product for admin, code: "+e.getMessage());
		}
		
		return null;
	}
	
	public static int getNumberOfProducts(Connection conn){
		String sql = "select count(*) from product_line";
		
		try (PreparedStatement stm = conn.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch (SQLException e) {
			System.out.println("get number of product for admin, code: "+e.getErrorCode());
		}
		catch(Exception e) {
			System.out.println("get number of product for admin, code: "+e.getMessage());
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
	
	public static int deleteProduct(Connection conn, int productId) {
		String sql ="delete from product_line\r\n"
				+ "where product_line_id =?;";
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, productId);
			int row = stm.executeUpdate();
			return row;
		}
		catch (SQLException e) {
			System.out.println("Loi xoa product line, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			 System.out.println("Loi xoa product line: "+e.getMessage());
		}
		return -1;
	}
	
	/* update */
	public static boolean isDuplicatedSlug(Connection conn, String slug) {
		String sql ="select * from product_line\r\n"
				+ "where pr_slug=?; ";
		
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setString(1, slug);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return true;
			}
		}
		catch (SQLException e) {
			System.out.println("Loi check slug dup line, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			 System.out.println("Loi slug dup line: "+e.getMessage());
		}
		return false;
	}
	
	public static int deleteImage(Connection conn, int id) {
		String sql ="delete from image\r\n"
				+ "where image_id=?;  ";
		
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, id);
			return stm.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Loi check slug dup line, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			 System.out.println("Loi slug dup line: "+e.getMessage());
		}
		return -1;
	}
	
	public static void insertImage(Connection conn, int productId, InputStream fileData) {
		String sql ="insert into image(product_line_id, img_file)\r\n"
				+ "values(?,?);";
		
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setInt(1, productId);
			//stm.setBlob(2, fileData);
			stm.setBinaryStream(2, fileData);
			stm.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Loi insert image, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			 System.out.println("Loi insert image: "+e.getMessage());
		}
	}
	
	public static int updateProduct(Connection conn, Product p) {
		String sql ="update product_line\r\n"
				+ "set pr_name=?, pr_slug=?, pr_price=?,\r\n"
				+ "	pr_brand_id=?, category_id=?, pr_description=?,\r\n"
				+ "    admin_id=?, updated_at=?\r\n"
				+ "where product_line_id=?;";
		
		try(PreparedStatement stm = conn.prepareStatement(sql)){
			stm.setString(1, p.getPr_name());
			stm.setString(2, p.getPr_slug());
			stm.setBigDecimal(3, p.getPr_price());
			stm.setInt(4, p.getPr_brand_id());
			stm.setInt(5, p.getCategory_id());
			stm.setString(6, p.getPr_description());
			stm.setInt(7, p.getAdmin_id());
			stm.setString(8, p.getUpdated_at());
			stm.setInt(9, p.getProduct_line_id());
			
			int row = stm.executeUpdate();
			return row;
		}
		catch (SQLException e) {
			System.out.println("Loi update product line, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			 System.out.println("Loi update product line: "+e.getMessage());
		}
		
		return -1;
	}
}
