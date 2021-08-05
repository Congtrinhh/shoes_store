package admin_login_servlet;

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
import entities.User;

@WebServlet(urlPatterns = { "/admin-login" })
public class AdminLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		User logedInUser = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
		if (logedInUser!=null) {
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/exception/logoutUserFirst.jsp");
			dispatcher.forward(req, resp);		
			return;
		}
		
		
		Admin logedInAdmin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		// nếu có admin trong session, qua luôn trang quản trị
		if (logedInAdmin != null) {
			
			// set trạng thái admin đang hoạt động
			// phần này xem xét bỏ, vì không quá quan trọng.
			boolean isStateUpdated = false;
			if (!isStateUpdated) {
				Connection connForUpdateStatus = common_utils.MyUtils.getStoredConnection(req);
				try {
					db_admin_login_utils.sql.AdminQuery.updateAdminStateOn(logedInAdmin.getAd_login_name(), connForUpdateStatus);
					logedInAdmin.setAd_state((byte) 1);
					isStateUpdated = true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			req.setAttribute(Admin.LOGED_IN_ADMIN_IN_REQUEST, logedInAdmin);
			
			// qua luôn trang quản trị
			RequestDispatcher dispatcher = req.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/admin_login/dashboard.jsp");
			dispatcher.forward(req, resp);
			System.out.println("case session");
			// kết thúc
		} 
		else {
			// còn nếu chưa có admin, yêu cầu đăng nhập
			RequestDispatcher dispatcher = req.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/admin_login/login.jsp");
			dispatcher.forward(req, resp);
			System.out.println("case login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy admin từ form và kiểm tra
		String login_name = req.getParameter("name");
		String password = req.getParameter("password");
		String remember_token_str = req.getParameter("remember-me");
		byte remember_token = 0; // cần rất cẩn thận khi lấy value về ở dạng string này
		if (remember_token_str != null) {
			remember_token = (byte) Integer.parseInt(remember_token_str);
		} else {
			remember_token = 0;
		}

		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String errorMessage = null;

		try {
			Admin admin = db_admin_login_utils.sql.AdminQuery.findAdmin(login_name, password, conn);

			// nếu tên tk/mk sai, forward
			if (admin == null) {
				errorMessage = "Tên tk hoặc mk không chính xác";
				req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
				RequestDispatcher dispatcher = req.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/admin_login/login.jsp");
				dispatcher.forward(req, resp);
			}
			// nếu admin tồn tại
			else {
				
				// cập nhật trạng thái admin hoạt động
				boolean isStateUpdated = false;
				if (!isStateUpdated) {
					db_admin_login_utils.sql.AdminQuery.updateAdminStateOn(admin.getAd_login_name(), conn);
					admin.setAd_state((byte) 1);
					isStateUpdated = true;
				}
				
				// thêm admin vào session để dùng cho nhiều nơi
				HttpSession session = req.getSession();
				session.setAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION, admin);
				
				// thêm admin cookie nếu có
				if (remember_token == 1) {
					db_admin_login_utils.cookie_session.AdminCookieSession.storeAdminNameIntoCookie(resp, admin.getAd_login_name(), 5*60);
				} else if (remember_token == 0) {
					db_admin_login_utils.cookie_session.AdminCookieSession.deleteAdminNameInCookie(resp);
				}

				req.setAttribute(Admin.LOGED_IN_ADMIN_IN_REQUEST, admin);
				RequestDispatcher dispatcher = req.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/admin_login/dashboard.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
