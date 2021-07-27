package homepage_servlet;

import java.math.BigDecimal;
import java.sql.Blob;

public class ProductGetter {
	private String pr_slug;
	private String pr_name;
	private Blob img_file;
	private String base64Image;
	private String c_slug;
	private String pr_brand_name;
	private BigDecimal pr_price;
	
	private int currentPage; // 2 thuộc tính này dành cho việc lấy sản phẩm
	private int totalPages; // và gọi ajax và phân trang
	
	public String getC_slug() {
		return c_slug;
	}
	public void setC_slug(String c_slug) {
		this.c_slug = c_slug;
	}
	public ProductGetter(String pr_slug, String pr_name, BigDecimal pr_price, Blob img_file, String c_slug) {
		super();
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.img_file = img_file;
		this.c_slug = c_slug;
		this.pr_price = pr_price;
	}
	
	public ProductGetter(String pr_slug, String pr_name,BigDecimal pr_price, Blob img_file, String c_slug, String pr_brand_name) {
		super();
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.img_file = img_file;
		this.c_slug = c_slug;
		this.pr_brand_name = pr_brand_name;
		this.pr_price = pr_price;
	}
	
	
	public ProductGetter(String pr_slug, String pr_name,String base64Image, String c_slug,
			String pr_brand_name, BigDecimal pr_price) {
		super();
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.base64Image = base64Image;
		this.c_slug = c_slug;
		this.pr_brand_name = pr_brand_name;
		this.pr_price = pr_price;
	}
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public void refineProductName() {
		pr_name = pr_name.replace("-", " ");
	}
	public String getPr_slug() {
		return pr_slug;
	}
	public void setPr_slug(String pr_slug) {
		this.pr_slug = pr_slug;
	}
	public String getPr_name() {
		return pr_name;
	}
	public void setPr_name(String pr_name) {
		this.pr_name = pr_name;
	}


	public String getPr_brand_name() {
		return pr_brand_name;
	}
	public void setPr_brand_name(String pr_brand_name) {
		this.pr_brand_name = pr_brand_name;
	}
	
	public void toString2() {
		System.out.println("pr_slug"+pr_slug);
		System.out.println("pr_name"+pr_name);
		System.out.println("pr_brand_name"+pr_brand_name);
		System.out.println("pr_brand_name"+pr_brand_name);
		
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public BigDecimal getPr_price() {
		return pr_price;
	}
	public void setPr_price(BigDecimal pr_price) {
		this.pr_price = pr_price;
	}
	public Blob getImg_file() {
		return img_file;
	}
	public void setImg_file(Blob img_file) {
		this.img_file = img_file;
	}	

}
