package user_login_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

@WebServlet(name = "userLogout", urlPatterns = {"/logout", "/user-logout"})
public class UserLogoutServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// xóa cookie
		Cookie c = new Cookie(User.USER_LOGIN_NAME_IN_COOKIE, null);
		c.setMaxAge(0);
		resp.addCookie(c);
		
		// xóa session attr
		HttpSession session = req.getSession();
		session.removeAttribute(User.LOGED_IN_USER_IN_SESSION);
		
		// xóa request attr
		req.removeAttribute(User.LOGED_IN_USER_IN_REQUEST);		
		
		resp.sendRedirect(req.getContextPath() + "/");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
