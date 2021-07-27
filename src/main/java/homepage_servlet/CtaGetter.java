package homepage_servlet;

public class CtaGetter {
	private String cta_slug;
	private String cta_banner_location;
	private String cta_title;
	private String cta_button_text;
	private String cta_description;

	public CtaGetter(String cta_slug, String cta_banner_location, String cta_title, String cta_button_text,
			String cta_description) {
		super();
		this.cta_slug = cta_slug;
		this.cta_banner_location = cta_banner_location;
		this.cta_title = cta_title;
		this.cta_button_text = cta_button_text;
		this.cta_description = cta_description;
	}
	public String getCta_slug() {
		return cta_slug;
	}
	public void setCta_slug(String cta_slug) {
		this.cta_slug = cta_slug;
	}
	public String getCta_banner_location() {
		return cta_banner_location;
	}
	public void setCta_banner_location(String cta_banner_location) {
		this.cta_banner_location = cta_banner_location;
	}
	public String getCta_title() {
		return cta_title;
	}
	public void setCta_title(String cta_title) {
		this.cta_title = cta_title;
	}
	public String getCta_button_text() {
		return cta_button_text;
	}
	public void setCta_button_text(String cta_button_text) {
		this.cta_button_text = cta_button_text;
	}
	public String getCta_description() {
		return cta_description;
	}
	public void setCta_description(String cta_description) {
		this.cta_description = cta_description;
	}
}
