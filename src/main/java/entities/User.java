package entities;


public class User {
	public static final String LOGED_IN_USER_IN_SESSION = "logedInUser";
	public static final String LOGED_IN_USER_IN_REQUEST = "logedInUser";
	public static final String ERROR_MESSAGE_IN_REQUEST = "errorMessage";
	public static final String USER_LOGIN_NAME_IN_COOKIE = "logedInUserName";
	public static final String USER_ID_IN_SESSION = "userId";
	
	private int user_id;
	private int admin_id;
	private String u_name;
	private String u_login_name;
	private String u_password;
	private String u_email;
	private String u_phone_number;
	private String specific_address;
	private String u_avatar; // đường dẫn đên avatar. vd: /hinh-anh
	private int u_purchased_order_id; // bỏ trống vì chưa có
	private byte u_remember_token;
	private byte u_state;
	private String created_at;
	private String updated_at;
	private int ward_id;
	
	public User(int admin_id, String u_name, String u_login_name, String u_password, String u_email,
			String u_phone_number, String specific_address) {
		super();
		this.admin_id = admin_id;
		this.u_name = u_name;
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_phone_number = u_phone_number;
		this.specific_address = specific_address;
	}
	

	
	public User(String u_login_name, String u_email,String u_password, String u_avatar) {
		super();
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_avatar = u_avatar;
	}


	public User(int user_id, String u_name, String u_login_name, String u_password, String u_email,
			String u_phone_number, String specific_address, String u_avatar) {
		super();
		this.user_id = user_id;
		this.u_name = u_name;
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_phone_number = u_phone_number;
		this.specific_address = specific_address;
		this.u_avatar = u_avatar;
	}

	public User(int user_id, int admin_id, String u_name, String u_login_name, String u_password, String u_email,
			String u_phone_number, String specific_address, String u_avatar, int ward_id, String created_at, String updated_at) {
		super();
		this.user_id = user_id;
		this.admin_id = admin_id;
		this.u_name = u_name;
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_phone_number = u_phone_number;
		this.specific_address = specific_address;
		this.u_avatar = u_avatar;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.ward_id = ward_id;
	}

	public User(int user_id,  String u_name, String u_login_name, String u_password, String u_email, String u_phone_number,
			String specific_address, String updated_at, int ward_id) {
		super();
		this.user_id = user_id;
		this.u_name = u_name;
		this.u_login_name = u_login_name;
		this.u_password = u_password;
		this.u_email = u_email;
		this.u_phone_number = u_phone_number;
		this.specific_address = specific_address;
		this.updated_at = updated_at;
		this.ward_id = ward_id;
	}



	public int getWard_id() {
		return ward_id;
	}

	public void setWard_id(int ward_id) {
		this.ward_id = ward_id;
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

	

	public String getSpecific_address() {
		return specific_address;
	}



	public void setSpecific_address(String specific_address) {
		this.specific_address = specific_address;
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
