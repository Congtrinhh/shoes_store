package entities;

public class SizeProductLine {
	private int size_product_line_id;
	private int product_line_id;
	private int size_id;
	private short spl_quantity;
	
	public SizeProductLine(int product_line_id, int size_id, short spl_quantity) {
		super();
		this.product_line_id = product_line_id;
		this.size_id = size_id;
		this.spl_quantity = spl_quantity;
	}

	public int getSize_product_line_id() {
		return size_product_line_id;
	}

	public void setSize_product_line_id(int size_product_line_id) {
		this.size_product_line_id = size_product_line_id;
	}

	public int getProduct_line_id() {
		return product_line_id;
	}

	public void setProduct_line_id(int product_line_id) {
		this.product_line_id = product_line_id;
	}

	public int getSize_id() {
		return size_id;
	}

	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}

	public short getSpl_quantity() {
		return spl_quantity;
	}

	public void setSpl_quantity(short spl_quantity) {
		this.spl_quantity = spl_quantity;
	}
		
}
