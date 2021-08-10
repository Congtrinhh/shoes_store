package user_forget_password;

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

import entities.User;

@WebServlet(urlPatterns = "/set-new-password")
public class UserSetNewPassword extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			int userId = -1;
			userId = (int) session.getAttribute(User.USER_ID_IN_SESSION);
			System.out.println("user id: "+userId);
			if ( userId!=-1 ) {
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_forget_password/setNewPassword.jsp");
				dispatcher.forward(req, resp);
			}
		}
		catch (Exception e) {
			System.out.println("user set new password servlet, ms: " + e.getMessage());
			//forward
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_forget_password/enterUserName.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String password = req.getParameter("password");
			String confirmedPassword = req.getParameter("confirmed-password");
			
			if ( password!=null && confirmedPassword!=null && password.equals(confirmedPassword) ) {
				HttpSession session = req.getSession();
				int userId = (int) session.getAttribute(User.USER_ID_IN_SESSION);
				
				Connection conn= common_utils.MyUtils.getStoredConnection(req);
				int row = db_crud_utils.UserUtils.changePassword(conn, userId, password);
				
				if ( row>0 ) {
					req.setAttribute("message", "Đổi mật khẩu thành công");
					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/notice/successNotice.jsp");
					dispatcher.forward(req, resp);
				}
				
			}
			else {
				throw new Exception("Lỗi đầu vào (mật khẩu chẳng hạn)");
			}
			
		}
		catch (SQLException e) {
			System.out.println("user set new pass, code: "+e.getErrorCode());
			req.setAttribute(constants.SystemConstants.SYSTEM_ERROR_MESSAGE, "Đổi mật khẩu không thành công, vui lòng thử lại");
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_forget_password/setNewPassword.jsp");
			dispatcher.forward(req, resp);
		}
		catch (Exception e) {
			System.out.println("user set new pass, mess: "+e.getMessage());
			req.setAttribute(constants.SystemConstants.SYSTEM_ERROR_MESSAGE, "Đổi mật khẩu không thành công, vui lòng thử lại");
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_forget_password/setNewPassword.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
}
