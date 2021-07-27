package entities;

public class ColorProductLine {
	private int color_product_line_id;
	private int product_line_id;
	private int color_id;
	private short cpl_quantity;

	public ColorProductLine(int product_line_id, int color_id, short cpl_quantity) {
		super();
		this.product_line_id = product_line_id;
		this.color_id = color_id;
		this.cpl_quantity = cpl_quantity;
	}

	public int getColor_product_line_id() {
		return color_product_line_id;
	}

	public void setColor_product_line_id(int color_product_line_id) {
		this.color_product_line_id = color_product_line_id;
	}

	public int getProduct_line_id() {
		return product_line_id;
	}

	public void setProduct_line_id(int product_line_id) {
		this.product_line_id = product_line_id;
	}

	public int getColor_id() {
		return color_id;
	}

	public void setColor_id(int color_id) {
		this.color_id = color_id;
	}

	public short getCpl_quantity() {
		return cpl_quantity;
	}

	public void setCpl_quantity(short cpl_quantity) {
		this.cpl_quantity = cpl_quantity;
	}
}
