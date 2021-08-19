package entities;

import java.math.BigDecimal;

public class Order {
	private int order_id;
	private int admin_id;
	private int user_id;
	private BigDecimal or_shipping_cost;
	private byte or_status; // 0: đang xử lí; 2: đang giao hàng; 3. đã giao hàng
	private String specific_address;
	private int ward_id;
	private String created_at;
	private String updated_at;
	
		
	public Order(int order_id, int admin_id, int user_id, BigDecimal or_shipping_cost, byte or_status,
			String specific_address, int ward_id, String created_at, String updated_at) {
		super();
		this.order_id = order_id;
		this.admin_id = admin_id;
		this.user_id = user_id;
		this.or_shipping_cost = or_shipping_cost;
		this.or_status = or_status;
		this.specific_address = specific_address;
		this.ward_id = ward_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}



	public Order(int admin_id, int user_id,BigDecimal or_shipping_cost, byte or_status) {
		super();
		this.admin_id = admin_id;
		this.user_id = user_id;
		this.or_shipping_cost = or_shipping_cost;
		this.or_status = or_status;
	}

	
	
	public Order(int order_id, byte or_status, String specific_address, int ward_id, String created_at) {
		super();
		this.order_id = order_id;
		this.or_status = or_status;
		this.specific_address = specific_address;
		this.ward_id = ward_id;
		this.created_at = created_at;
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



	public BigDecimal getOr_shipping_cost() {
		return or_shipping_cost;
	}



	public void setOr_shipping_cost(BigDecimal or_shipping_cost) {
		this.or_shipping_cost = or_shipping_cost;
	}



	public byte getOr_status() {
		return or_status;
	}



	public void setOr_status(byte or_status) {
		this.or_status = or_status;
	}



	public String getSpecific_address() {
		return specific_address;
	}



	public void setSpecific_address(String specific_address) {
		this.specific_address = specific_address;
	}



	public int getWard_id() {
		return ward_id;
	}



	public void setWard_id(int ward_id) {
		this.ward_id = ward_id;
	}



	public String getCreated_at() {
		return created_at;
	}



	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}



	public String getUpdated_at() {
		return updated_at;
	}



	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	
}
