package admin_login_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Admin;

@WebServlet(urlPatterns = {"/admin-logout"})
public class AdminLogoutServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// xóa cookie admin
		db_admin_login_utils.cookie_session.AdminCookieSession.deleteAdminNameInCookie(resp);
		
		// xóa session admin
		HttpSession session = req.getSession();
		Admin admin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		String admin_login_name = admin.getAd_login_name();
		session.removeAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		
		// set trang thai admin
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		try {
			db_admin_login_utils.sql.AdminQuery.updateAdminStateOff(admin_login_name, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// redirect ve trang chu
		resp.sendRedirect(req.getServletContext().getContextPath() + "/");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
