package entities;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private int order_id;
	private int admin_id;
	private int user_id;
	private BigDecimal or_total_cost; // datatype mapping
	private BigDecimal or_shipping_cost;
	private byte or_status; // 0: chưa thanh toán; 1: đã thanh toán; 3: đang giao hàng; 4. đã giao hàng
	private Date created_at;
	private Date updated_at;
	
	public Order(int admin_id, int user_id, BigDecimal or_total_cost, BigDecimal or_shipping_cost, byte or_status) {
		super();
		this.admin_id = admin_id;
		this.user_id = user_id;
		this.or_total_cost = or_total_cost;
		this.or_shipping_cost = or_shipping_cost;
		this.or_status = or_status;
	}

	
	public byte getOr_status() {
		return or_status;
	}


	public void setOr_status(byte or_status) {
		this.or_status = or_status;
	}


	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getOr_total_cost() {
		return or_total_cost;
	}

	public void setOr_total_cost(BigDecimal or_total_cost) {
		this.or_total_cost = or_total_cost;
	}

	public BigDecimal getOr_shipping_cost() {
		return or_shipping_cost;
	}

	public void setOr_shipping_cost(BigDecimal or_shipping_cost) {
		this.or_shipping_cost = or_shipping_cost;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
}
