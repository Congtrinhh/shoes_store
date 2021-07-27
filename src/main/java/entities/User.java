package entities;

import java.sql.Blob;
import java.util.Date;

public class User {
	public static final String LOGED_IN_USER_IN_SESSION = "logedInUser";
	public static final String LOGED_IN_USER_IN_REQUEST = "logedInUser";
	public static final String ERROR_MESSAGE_IN_REQUEST = "errorMessage";
	public static final String USER_LOGIN_NAME_IN_COOKIE = "logedInUserName";
	private int user_id;
	private int admin_id;
	private String u_name;
	private String u_login_name;
	private String u_password;
	private String u_email;
	private String u_phone_number;
	private String u_address;
	private String u_avatar; // đường dẫn đên avatar. vd: /hinh-anh
	private int u_purchased_order_id; // bỏ trống vì chưa có
	private byte u_remember_token;
	private byte u_state;
	private Date created_at;
	private Date updated_at;
	
	public User(int admin_id, String u_name, String u_login_name, String u_password, String u_email,
			String u_phone_number, String u_address) {
		super();
		this.admin_id = admin_id;
		this.u_name = u_name;
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_phone_number = u_phone_number;
		this.u_address = u_address;
	}
	

	
	public User(String u_login_name, String u_email,String u_password, String u_avatar) {
		super();
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_avatar = u_avatar;
	}



	public User(int user_id, String u_name, String u_login_name, String u_password, String u_email,
			String u_phone_number, String u_address, String u_avatar) {
		super();
		this.user_id = user_id;
		this.u_name = u_name;
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_phone_number = u_phone_number;
		this.u_address = u_address;
		this.u_avatar = u_avatar;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_login_name() {
		return u_login_name;
	}

	public void setU_login_name(String u_login_name) {
		this.u_login_name = u_login_name;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_phone_number() {
		return u_phone_number;
	}

	public void setU_phone_number(String u_phone_number) {
		this.u_phone_number = u_phone_number;
	}

	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	public String getU_avatar() {
		return u_avatar;
	}

	public void setU_avatar(String u_avatar) {
		this.u_avatar = u_avatar;
	}

	public int getU_purchased_order_id() {
		return u_purchased_order_id;
	}

	public void setU_purchased_order_id(int u_purchased_order_id) {
		this.u_purchased_order_id = u_purchased_order_id;
	}

	public byte getU_remember_token() {
		return u_remember_token;
	}

	public void setU_remember_token(byte u_remember_token) {
		this.u_remember_token = u_remember_token;
	}

	public byte getU_state() {
		return u_state;
	}

	public void setU_state(byte u_state) {
		this.u_state = u_state;
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
