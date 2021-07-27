package entities;

public class ProductInOrder {
	private int product_in_order_id;
	private int product_line_id;
	private int order_id;
	private byte pio_size;
	private String pio_color;
	private short pio_quantity;
	private byte pio_discount_percent;

	public ProductInOrder(int product_line_id, int order_id, byte pio_size, String pio_color, short pio_quantity,
			byte pio_discount_percent) {
		super();
		this.product_line_id = product_line_id;
		this.order_id = order_id;
		this.pio_size = pio_size;
		this.pio_color = pio_color;
		this.pio_quantity = pio_quantity;
		this.pio_discount_percent = pio_discount_percent;
	}

	public byte getPio_size() {
		return pio_size;
	}

	public void setPio_size(byte pio_size) {
		this.pio_size = pio_size;
	}

	public String getPio_color() {
		return pio_color;
	}

	public void setPio_color(String pio_color) {
		this.pio_color = pio_color;
	}

	public int getProduct_in_order_id() {
		return product_in_order_id;
	}

	public void setProduct_in_order_id(int product_in_order_id) {
		this.product_in_order_id = product_in_order_id;
	}

	public int getProduct_line_id() {
		return product_line_id;
	}

	public void setProduct_line_id(int product_line_id) {
		this.product_line_id = product_line_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public byte getPio_discount_percent() {
		return pio_discount_percent;
	}

	public void setPio_discount_percent(byte pio_discount_percent) {
		this.pio_discount_percent = pio_discount_percent;
	}

	public short getPio_quantity() {
		return pio_quantity;
	}

	public void setPio_quantity(short pio_quantity) {
		this.pio_quantity = pio_quantity;
	}
}
