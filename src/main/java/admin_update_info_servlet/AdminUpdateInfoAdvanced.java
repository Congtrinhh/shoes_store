package admin_update_info_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Admin;

@WebServlet(urlPatterns = {"/admin-update-advanced"})
public class AdminUpdateInfoAdvanced extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateAdvancedInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ad_old_password = req.getParameter("ad_old_password");
		String ad_new_password = req.getParameter("ad_new_password");
		String ad_confirmed_password = req.getParameter("ad_confirmed_password");
		
		// kiểm tra mật khẩu cũ hợp lệ
		HttpSession session = req.getSession();
		Admin adminInSession = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		String errorMessage = null;
		
		if (!adminInSession.getAd_password().equals(ad_old_password)) {
			errorMessage = "mật khẩu cũ không đúng";
			req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateAdvancedInfo.jsp");
			dispatcher.forward(req, resp);
		} else {
			// verify nhè nhẹ
			if (ad_new_password.equals(ad_confirmed_password)) {
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				try {
					db_admin_update_info_utils.AdminUpdateAdvancedInfo.updatePassword(adminInSession.getAdmin_id(), ad_confirmed_password, conn);
					
					// nếu cập nhật mật khẩu thành công, update lại session của admin
					adminInSession.setAd_password(ad_confirmed_password);
					session.setAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION, adminInSession);
					
					errorMessage = "Cập nhật mật khẩu thành công";
					req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateAdvancedInfo.jsp");
					dispatcher.forward(req, resp);
				} catch (SQLException e) {
					e.printStackTrace();
					errorMessage = e.getMessage();
					req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateAdvancedInfo.jsp");
					dispatcher.forward(req, resp);
					return;
				}
			}
			else {
				errorMessage = "mật khẩu xác nhận không khớp";
				req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateAdvancedInfo.jsp");
				dispatcher.forward(req, resp);
			}
			
		}
		
		
		
		
		
		
		
	}
}
