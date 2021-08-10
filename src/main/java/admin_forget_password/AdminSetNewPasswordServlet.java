package admin_forget_password;

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

@WebServlet(urlPatterns = "/admin-set-new-password")
public class AdminSetNewPasswordServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			HttpSession session = req.getSession();
			int adminId = -1;
			adminId = (int) session.getAttribute(Admin.ADMIN_ID_IN_SESSION);
			
			if ( adminId!=-1 ) {
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_forget_password/setNewPassword.jsp");
				dispatcher.forward(req, resp);
			}		
						
		}
		catch (Exception e) {
			System.out.println("sang trang nhập mk mới admin, mes: "+e.getMessage());
			
			req.setAttribute(constants.SystemConstants.SYSTEM_ERROR_MESSAGE, "Hãy nhập tên tài khoản trước");
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_forget_password/enterUserName.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhận về mật khẩu mới của người dùng từ request
		 * -> lưu mật khẩu vào db
		 * 
		 */
		try {
			String password = req.getParameter("password");
			String confirmedPassword = req.getParameter("confirmed-password");
			if ( password!=null && confirmedPassword!=null && password.equals(confirmedPassword) ) {
				HttpSession session = req.getSession();
				int adminId = -1;
				adminId = (int) session.getAttribute(Admin.ADMIN_ID_IN_SESSION);
				if ( adminId!=-1 ) {
					Connection conn = common_utils.MyUtils.getStoredConnection(req);
					
					int row = db_crud_utils.AdminUtils.changePassword(conn, adminId, confirmedPassword);
					
					if (row>0) {
						req.setAttribute("message", "Đổi mật khẩu thành công");
						RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/notice/successNotice.jsp");
						dispatcher.forward(req, resp);
					}
					else {
						throw new SQLException("Đổi mk không thành công");
					}
				}
				else {
					req.setAttribute(constants.SystemConstants.SYSTEM_ERROR_MESSAGE, "Hãy nhập tên tài khoản trước");
					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_forget_password/enterUserName.jsp");
					dispatcher.forward(req, resp);
				}
				
			}
			else {
				throw new Exception("mật khẩu trống hoặc mật khẩu xác nhận không đúng");
			}
			
			
		}
		catch (SQLException e) {
			System.out.println("đổi mk admin, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("đổi mk admin, mes: "+e.getMessage());
		}
		
		
	}
}
