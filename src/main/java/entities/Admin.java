package entities;

public class Admin {
	public static final String LOGED_IN_ADMIN_IN_SESSION= "logedInAdmin";
	public static final String LOGED_IN_ADMIN_IN_REQUEST = "logedInAdmin";
	public static final String ADMIN_LOGIN_NAME_IN_COOKIE = "ad_login_name";
	public static final String ADMIN_LOGIN_STATUS = "adminLoginStatus"; // set cho session. 1: đang đăng nhập, 0: đang đăng xuất
	public static final String ERROR_MESSAGE_IN_REQUEST = "errorMessage";
	public static final String ADMIN_ID_IN_SESSION = "adminId";
	
	private int admin_id;
	private String ad_name;
	private String ad_login_name;
	private String ad_password;
	private String ad_email;
	private String ad_phone_number;
	private byte ad_state; //  1: active; 0: inactive
	private byte ad_remember_token;
	
	
	public Admin(String ad_name, String ad_login_name, String ad_password, String ad_email, String ad_phone_number,
			byte ad_state, byte ad_remember_token) {
		super();
		this.ad_name = ad_name;
		this.ad_login_name = ad_login_name;
		this.ad_password = ad_password;
		this.ad_email = ad_email;
		this.ad_phone_number = ad_phone_number;
		this.ad_state = ad_state;
		this.ad_remember_token = ad_remember_token;
	}
		

	public Admin(int admin_id, String ad_name, String ad_login_name, String ad_email, String ad_phone_number) {
		super();
		this.admin_id = admin_id;
		this.ad_name = ad_name;
		this.ad_login_name = ad_login_name;
		this.ad_email = ad_email;
		this.ad_phone_number = ad_phone_number;
	}


	public Admin(int admin_id, String ad_name, String ad_login_name, String ad_password, String ad_email,
			String ad_phone_number, byte ad_state) {
		super();
		this.admin_id = admin_id;
		this.ad_name = ad_name;
		this.ad_login_name = ad_login_name;
		this.ad_password = ad_password;
		this.ad_email = ad_email;
		this.ad_phone_number = ad_phone_number;
		this.ad_state = ad_state;
	}



	public int getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}


	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getAd_login_name() {
		return ad_login_name;
	}

	public void setAd_login_name(String ad_login_name) {
		this.ad_login_name = ad_login_name;
	}

	public String getAd_password() {
		return ad_password;
	}

	public void setAd_password(String ad_password) {
		this.ad_password = ad_password;
	}

	public String getAd_email() {
		return ad_email;
	}

	public void setAd_email(String ad_email) {
		this.ad_email = ad_email;
	}

	public String getAd_phone_number() {
		return ad_phone_number;
	}

	public void setAd_phone_number(String ad_phone_number) {
		this.ad_phone_number = ad_phone_number;
	}

	public byte getAd_state() {
		return ad_state;
	}

	public void setAd_state(byte i) {
		this.ad_state = i;
	}

	public byte getAd_remember_token() {
		return ad_remember_token;
	}

	public void setAd_remember_token(byte ad_remember_token) {
		this.ad_remember_token = ad_remember_token;
	}
	
}
