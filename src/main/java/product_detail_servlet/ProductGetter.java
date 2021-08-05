package product_detail_servlet;

import java.math.BigDecimal;

public class ProductGetter {
	private int product_line_id;
	private String pr_slug;
	private String pr_name;
	private BigDecimal pr_price;
	private String pr_description;
	private String pr_brand_name;
	
	
	public ProductGetter(int product_line_id, String pr_slug, String pr_name, BigDecimal pr_price, String pr_description) {
		super();
		this.product_line_id = product_line_id;
		this.pr_slug = pr_slug;
		this.pr_name = pr_name;
		this.pr_price = pr_price;
		this.pr_description = pr_description;
	}


	public int getProduct_line_id() {
		return product_line_id;
	}


	public void setProduct_line_id(int product_line_id) {
		this.product_line_id = product_line_id;
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

	public BigDecimal getPr_price() {
		return pr_price;
	}


	public void setPr_price(BigDecimal pr_price) {
		this.pr_price = pr_price;
	}


	public String getPr_description() {
		return pr_description;
	}


	public void setPr_description(String pr_description) {
		this.pr_description = pr_description;
	}


	public String getPr_brand_name() {
		return pr_brand_name;
	}


	public void setPr_brand_name(String pr_brand_name) {
		this.pr_brand_name = pr_brand_name;
	}
	
}
