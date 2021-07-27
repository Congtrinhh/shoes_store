package entities;

import java.sql.Blob;

public class Image {
	private int image_id;
	private int admin_id;
	private int product_line_id;
	private String img_name;
	private Blob img_file;
	private String base64Image;
	
	public Image(int admin_id, int product_line_id, String img_name, Blob img_file) {
		super();
		this.admin_id = admin_id;
		this.product_line_id = product_line_id;
		this.img_name = img_name;
		this.img_file = img_file;
	}

	public Image(int image_id, int admin_id, int product_line_id, String img_name, Blob img_file, String base64Image) {
		super();
		this.image_id = image_id;
		this.admin_id = admin_id;
		this.product_line_id = product_line_id;
		this.img_name = img_name;
		this.img_file = img_file;
		this.base64Image = base64Image;
	}

	public String getBase64Image() {
		return base64Image;
	}



	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}



	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getProduct_line_id() {
		return product_line_id;
	}

	public void setProduct_line_id(int product_line_id) {
		this.product_line_id = product_line_id;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public Blob getImg_file() {
		return img_file;
	}

	public void setImg_file(Blob img_file) {
		this.img_file = img_file;
	}

	
}
