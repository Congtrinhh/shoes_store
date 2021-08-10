package ajax_call;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;

@WebServlet(urlPatterns = "/ajax-user-forget-password")
public class UserForgetPassword extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String userName = req.getParameter("name");
			String message = null;
			if ( userName!=null ) {
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				int userId = -1;
				userId = db_crud_utils.UserUtils.getUserId(conn, userName);
				
				if ( userId != -1 ) {
					message = req.getContextPath() + "/set-new-password";
					req.getSession().setAttribute(User.USER_ID_IN_SESSION, userId);
				}
				else {
					message = "Tên tài khoản không tồn tại";
				}
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html");
				resp.getWriter().write(message);
				
			}
			else {
				throw new Exception("user name param trống");
				
			}
			
		}
		catch (Exception e) {
			System.out.println("ajax user forget password, mess: "+e.getMessage());
		}
	}
}
