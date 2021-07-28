package product_detail_servlet;

import java.sql.Blob;

public class ImageGetter {
	private Blob image_file;
	private String base64Image;
	
	
	public ImageGetter(Blob image_file, String base64Image) {
		super();
		this.image_file = image_file;
		this.base64Image = base64Image;
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
