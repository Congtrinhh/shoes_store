package homepage_servlet;

import java.math.BigDecimal;
import java.sql.Blob;

public class ProductGetter {
	private String pr_slug;
	private String pr_name;
	private Blob img_file;
	private String base64Image;
	private String c_slug;
	private int pr_brand_id;
	private BigDecimal pr_price;
	
	private int pr_id;
	private String brand_name;
	private String category_name;
	private String pr_description;
	
	
	
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
	
	public ProductGetter(String pr_slug, String pr_name,BigDecimal pr_price, Blob img_file, String c_slug, int pr_brand_id) {
		super();
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.img_file = img_file;
		this.c_slug = c_slug;
		this.pr_brand_id = pr_brand_id;
		this.pr_price = pr_price;
	}
	
	
	public ProductGetter(String pr_slug, String pr_name,String base64Image, String c_slug,
			int pr_brand_id, BigDecimal pr_price) {
		super();
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.base64Image = base64Image;
		this.c_slug = c_slug;
		this.pr_brand_id = pr_brand_id;
		this.pr_price = pr_price;
	}
	
	
	public ProductGetter(String pr_slug, String pr_name, BigDecimal pr_price, int pr_id, String brand_name,
			String category_name, String pr_description) {
		super();
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.pr_price = pr_price;
		this.pr_id = pr_id;
		this.brand_name = brand_name;
		this.category_name = category_name;
		this.pr_description = pr_description;
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

	
	public int getPr_brand_id() {
		return pr_brand_id;
	}
	public void setPr_brand_id(int pr_brand_id) {
		this.pr_brand_id = pr_brand_id;
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
	@Override
	public String toString() {
		return "ProductGetter [pr_name=" + pr_name + ", c_slug=" + c_slug + ", pr_price=" + pr_price + "]";
	}
	public int getPr_id() {
		return pr_id;
	}
	public void setPr_id(int pr_id) {
		this.pr_id = pr_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getPr_description() {
		return pr_description;
	}
	public void setPr_description(String pr_description) {
		this.pr_description = pr_description;
	}	
}
