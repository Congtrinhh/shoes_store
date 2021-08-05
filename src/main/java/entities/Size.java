package entities;

public class Size {
	private int size_id;
	private byte size_number; // số size
	private String size_description;
	
	public Size(byte size_number, String size_detail) {
		super();
		this.size_number = size_number;
		this.size_description = size_detail;
	}
	
	public Size(int size_id, byte size_number) {
		super();
		this.size_id = size_id;
		this.size_number = size_number;
	}

	public int getSize_id() {
		return size_id;
	}
	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}
	public byte getSize_number() {
		return size_number;
	}
	public void setSize_number(byte size_number) {
		this.size_number = size_number;
	}
	public String getSize_detail() {
		return size_description;
	}
	public void setSize_detail(String size_detail) {
		this.size_description = size_detail;
	}
	
	
}
