package product_detail_servlet;

import java.math.BigDecimal;

public class SpecificProductInfo {
	private String color;
	private int size;
	private BigDecimal price;
	public SpecificProductInfo(String color, int size, BigDecimal price) {
		super();
		this.color = color;
		this.size = size;
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
