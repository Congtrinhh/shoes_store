package db_user_order_info_utils;

import java.math.BigDecimal;

public class OrderEntity {
	private int id;
	private int user_id;
	private BigDecimal total_price;
	private String address;
	private String status;
	private int itemCount;
	
	private String specificBase64String;
	private String specificItemName;
	private int specificItemQuantity;
	private String specificItemColor;
	private byte specificItemSize;
	
	private String created_at;
	
	
	
	public OrderEntity(int id, int user_id, BigDecimal total_price, String address, String status, int itemCount,
			String specificBase64String, String specificItemName, int specificItemQuantity, String specificItemColor,
			byte specificItemSize) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.total_price = total_price;
		this.address = address;
		this.status = status;
		this.itemCount = itemCount;
		this.specificBase64String = specificBase64String;
		this.specificItemName = specificItemName;
		this.specificItemQuantity = specificItemQuantity;
		this.specificItemColor = specificItemColor;
		this.specificItemSize = specificItemSize;
	}
	
	
	public OrderEntity(BigDecimal total_price, String address, String status, int itemCount,
			String specificBase64String, String specificItemName, int specificItemQuantity, String specificItemColor,
			byte specificItemSize, String created_at) {
		super();
		this.total_price = total_price;
		this.address = address;
		this.status = status;
		this.itemCount = itemCount;
		this.specificBase64String = specificBase64String;
		this.specificItemName = specificItemName;
		this.specificItemQuantity = specificItemQuantity;
		this.specificItemColor = specificItemColor;
		this.specificItemSize = specificItemSize;
		this.created_at = created_at;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public BigDecimal getTotal_price() {
		return total_price;
	}


	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getItemCount() {
		return itemCount;
	}


	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}


	public String getSpecificBase64String() {
		return specificBase64String;
	}


	public void setSpecificBase64String(String specificBase64String) {
		this.specificBase64String = specificBase64String;
	}


	public String getSpecificItemName() {
		return specificItemName;
	}


	public void setSpecificItemName(String specificItemName) {
		this.specificItemName = specificItemName;
	}


	public int getSpecificItemQuantity() {
		return specificItemQuantity;
	}


	public void setSpecificItemQuantity(int specificItemQuantity) {
		this.specificItemQuantity = specificItemQuantity;
	}


	public String getSpecificItemColor() {
		return specificItemColor;
	}


	public void setSpecificItemColor(String specificItemColor) {
		this.specificItemColor = specificItemColor;
	}


	public byte getSpecificItemSize() {
		return specificItemSize;
	}


	public void setSpecificItemSize(byte specificItemSize) {
		this.specificItemSize = specificItemSize;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	
	
}
