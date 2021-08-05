package entities;

import java.math.BigDecimal;

public class Product {
	private int product_line_id;
	private int admin_id;
	private int category_id;
	private String pr_slug;
	private String pr_name;
	private String pr_sku;
	private int pr_brand_id;
	private BigDecimal pr_price; 
	private String pr_description;
	private String  created_at;
	private String updated_at;
	
	public Product(int admin_id, int category_id, String pr_slug, String pr_name, String pr_sku, BigDecimal pr_price, int pr_brand_id,String pr_description) {
		super();
		this.admin_id = admin_id;
		this.category_id = category_id;
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.pr_sku = pr_sku;
		this.pr_brand_id = pr_brand_id;
		this.pr_description = pr_description;
		this.pr_price = pr_price;
	}
	
	public Product(int admin_id, int category_id, String pr_slug, String pr_name, int pr_brand_id,
			BigDecimal pr_price, String pr_description, String created_at) {
		super();
		this.admin_id = admin_id;
		this.category_id = category_id;
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.pr_brand_id = pr_brand_id;
		this.pr_price = pr_price;
		this.pr_description = pr_description;
		this.created_at = created_at;
	}

	public Product(int product_line_id, int admin_id, int category_id, String pr_slug, String pr_name, int pr_brand_id,
			BigDecimal pr_price, String pr_description, String updated_at) {
		super();
		this.product_line_id = product_line_id;
		this.admin_id = admin_id;
		this.category_id = category_id;
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.pr_brand_id = pr_brand_id;
		this.pr_price = pr_price;
		this.pr_description = pr_description;
		this.updated_at = updated_at;
	}

	public Product(int product_line_id, String pr_name) {
		super();
		this.product_line_id = product_line_id;
		this.pr_name = pr_name;
	}
	
	
	
	public Product(int product_line_id, int admin_id, int category_id, String pr_slug, String pr_name, String pr_sku,
			int pr_brand_id, BigDecimal pr_price, String pr_description, String created_at, String updated_at) {
		super();
		this.product_line_id = product_line_id;
		this.admin_id = admin_id;
		this.category_id = category_id;
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.pr_sku = pr_sku;
		this.pr_brand_id = pr_brand_id;
		this.pr_price = pr_price;
		this.pr_description = pr_description;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	

	public int getPr_brand_id() {
		return pr_brand_id;
	}

	public void setPr_brand_id(int pr_brand_id) {
		this.pr_brand_id = pr_brand_id;
	}

	public int getProduct_line_id() {
		return product_line_id;
	}

	public void setProduct_line_id(int product_line_id) {
		this.product_line_id = product_line_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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

	public String getPr_sku() {
		return pr_sku;
	}

	public void setPr_sku(String pr_sku) {
		this.pr_sku = pr_sku;
	}

	public String getPr_description() {
		return pr_description;
	}

	public void setPr_description(String pr_description) {
		this.pr_description = pr_description;
	}

	

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public BigDecimal getPr_price() {
		return pr_price;
	}

	public void setPr_price(BigDecimal pr_price) {
		this.pr_price = pr_price;
	}
	
}
