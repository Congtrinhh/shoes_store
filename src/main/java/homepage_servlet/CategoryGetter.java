package homepage_servlet;

public class CategoryGetter {
	private String c_slug;
	private String c_name;
	public CategoryGetter(String c_slug, String c_name) {
		super();
		this.c_slug = c_slug;
		this.c_name = c_name;
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
}
