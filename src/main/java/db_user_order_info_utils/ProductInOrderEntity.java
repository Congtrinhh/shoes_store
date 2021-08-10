package db_user_order_info_utils;


public class ProductInOrderEntity  {
	private String name;
	private String base64Image;
	String color_name;
	byte size_number;
	
	public ProductInOrderEntity(String name, String base64Image, String color_name, byte size_number) {
		super();
		this.name = name;
		this.base64Image = base64Image;
		this.color_name = color_name;
		this.size_number = size_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public byte getSize_number() {
		return size_number;
	}
	public void setSize_number(byte size_number) {
		this.size_number = size_number;
	}
}
