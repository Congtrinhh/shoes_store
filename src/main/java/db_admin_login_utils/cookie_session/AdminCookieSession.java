package db_admin_login_utils.cookie_session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Admin;

public class AdminCookieSession {
	public static String getAdminNameInCookie(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		String ad_login_name = null;
		for (Cookie c : cookies) {
			if (c.getName().equals(Admin.ADMIN_LOGIN_NAME_IN_COOKIE)) {
				ad_login_name = c.getValue();
				break;
			}
		}
		return ad_login_name;
	}
	public static void storeAdminNameIntoCookie(HttpServletResponse resp, String admin_login_name, int duration) {
		Cookie cookie = new Cookie(Admin.ADMIN_LOGIN_NAME_IN_COOKIE, admin_login_name);
		cookie.setMaxAge(duration);
		resp.addCookie(cookie);
	}
	public static void deleteAdminNameInCookie(HttpServletResponse resp) {
		Cookie cookie = new Cookie(Admin.ADMIN_LOGIN_NAME_IN_COOKIE, null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
	}
}
