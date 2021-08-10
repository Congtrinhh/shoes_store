package entities;

public class ProductInOrder {
	private int product_in_order_id;
	private int specific_product_id;
	private int order_id;
	private short pio_quantity;


	

	public ProductInOrder(int specific_product_id, int order_id, short pio_quantity) {
		super();
		this.specific_product_id = specific_product_id;
		this.order_id = order_id;
		this.pio_quantity = pio_quantity;
	}

	public ProductInOrder(int product_in_order_id, int specific_product_id, int order_id, short pio_quantity) {
		super();
		this.product_in_order_id = product_in_order_id;
		this.specific_product_id = specific_product_id;
		this.order_id = order_id;
		this.pio_quantity = pio_quantity;
	}

	public int getSpecific_product_id() {
		return specific_product_id;
	}

	public void setSpecific_product_id(int specific_product_id) {
		this.specific_product_id = specific_product_id;
	}

	public int getProduct_in_order_id() {
		return product_in_order_id;
	}

	public void setProduct_in_order_id(int product_in_order_id) {
		this.product_in_order_id = product_in_order_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public short getPio_quantity() {
		return pio_quantity;
	}

	public void setPio_quantity(short pio_quantity) {
		this.pio_quantity = pio_quantity;
	}
}
