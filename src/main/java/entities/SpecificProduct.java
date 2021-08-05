package entities;

import java.math.BigDecimal;

public class SpecificProduct {
	private int id;
	private int product_line_id;
	private int color_id;
	private int size_id;
	private BigDecimal spr_price;
	private int spr_quantity;
	
	
	public SpecificProduct(int id, int product_line_id, int color_id, int size_id, BigDecimal spr_price,
			int spr_quantity) {
		super();
		this.id = id;
		this.product_line_id = product_line_id;
		this.color_id = color_id;
		this.size_id = size_id;
		this.spr_price = spr_price;
		this.spr_quantity = spr_quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getSize_id() {
		return size_id;
	}
	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}
	public BigDecimal getSpr_price() {
		return spr_price;
	}
	public void setSpr_price(BigDecimal spr_price) {
		this.spr_price = spr_price;
	}
	public int getSpr_quantity() {
		return spr_quantity;
	}
	public void setSpr_quantity(int spr_quantity) {
		this.spr_quantity = spr_quantity;
	}
}
