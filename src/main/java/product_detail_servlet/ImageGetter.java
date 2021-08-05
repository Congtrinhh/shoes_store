package product_detail_servlet;

import java.sql.Blob;

public class ImageGetter {
	private int image_id;
	private Blob image_file;
	private String base64Image;
	
	
	public ImageGetter( String base64Image) {
		super();
		this.base64Image = base64Image;
	}
	
	public ImageGetter(int image_id, String base64Image) {
		super();
		this.image_id = image_id;
		this.base64Image = base64Image;
	}


	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public Blob getImage_file() {
		return image_file;
	}
	public void setImage_file(Blob image_file) {
		this.image_file = image_file;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
}
