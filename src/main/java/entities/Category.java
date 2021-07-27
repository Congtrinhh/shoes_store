package entities;

import java.util.Date;

public class Category {
	private int category_id;
	private int admin_id;
	private String c_slug;
	private String c_name;
	private Date created_at;
	private Date updated_at;
	
	
	public Category(int admin_id, String c_slug, String c_name, Date created_at, Date updated_at) {
		super();
		this.admin_id = admin_id;
		this.c_slug = c_slug;
		this.c_name = c_name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public Category(int  admin_id, String c_slug, String c_name) {
		super();
		this.admin_id = admin_id;
		this.c_slug = c_slug;
		this.c_name = c_name;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getC_slug() {
		return c_slug;
	}
	public void setC_slug(String c_slug) {
		this.c_slug = c_slug;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
