package constants;

import java.util.HashMap;
import java.util.Map;

public class SystemConstants {
	public static final String SYSTEM_ERROR_MESSAGE = "systemErrorMessage";
	public static final String LOGIN_TOKEN = "login_token";
	public static final int PRODUCTS_PER_PAGE = 9;
	public static final int PRODUCTS_PER_PAGE_ADMIN = 10;
	
	public static final String TAB_INDICATOR = "tabIndicator"; 
	
	public static Map<Integer, String> priorityList;
	
	public static Map<Integer, String> getPriorityList() {
		Map<Integer, String> list = new HashMap<>();
		list.put(1, "mới nhất");
		list.put(2, "giá tăng dần");
		list.put(3, "giá giảm dần");
		return list;
	}
}
