package user_login_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Admin;
import entities.User;

/*
 * Khi nhận GET request, kiểm tra xem trong session có chứa user không? Nếu có (tức là đã đăng nhập) -> redirect về
 * trang chủ. Nếu không có -> forward request qua trang login để người dùng nhập form login.
 * Khi nhận POST request, tức là sau khi người dùng bấm nút submit(ở đây ta coi như client đã validate form đầy đủ),
 * ta nhận user data từ client, kiểm tra xem user có trong DB không, nếu không có -> forward lại trang login kèm thônng
 * báo lỗi. Nếu có -> lưu ngay user vào session, đồng thời nếu nút remember me được check, lưu user name vào Cookie cho
 * lần đăng nhập sau đồng thời redirect về trang chủ.
 * Lưu ý: ta sẽ chưa handle trạng thái đăng nhập ở đây, vì nó khá phức tạp cũng như không quá cần thiết.
 * */
@WebServlet(name = "userLogin", urlPatterns = {"/login", "/user-login"})
public class UserLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			
			// ngoại lệ: người dùng cố đăng nhập user khi đang đăng nhập là admin
			Admin logedInAdmin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
			if (logedInAdmin!=null) {
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/exception/logoutAdminFirst.jsp");
				dispatcher.forward(req, resp);		
				return;
			}
			
			
			User userInSession = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
			if (userInSession==null) {
				doPost(req, resp); // auto login
				
//				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_login/userLogin.jsp");
//				dispatcher.forward(req, resp);			
			}
			else {
				resp.sendRedirect(req.getServletContext().getContextPath() +"/");
			}
		}
		catch (Exception e) {
			System.out.println("user login servlet get, mes: "+e.getMessage());
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String u_login_name = req.getParameter("name");
		String u_password = req.getParameter("password");
		String remember_me = req.getParameter("remember-me");
		
		// auto login
		u_login_name ="adog11"; 
		u_password="abc123";
		remember_me="y";
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String errorMessage = null;
		try {
			User user = db_user_login_utils.UserQuery.findUser(u_login_name, u_password, conn);
			if (user==null) {
				errorMessage = "Tên tài khoản hoặc mật khẩu không đúng";
				req.setAttribute(User.ERROR_MESSAGE_IN_REQUEST, errorMessage);
				
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_login/userLogin.jsp");
				dispatcher.forward(req, resp);
			} 
			else {
				HttpSession session = req.getSession();
				session.setAttribute(User.LOGED_IN_USER_IN_SESSION, user);
				req.setAttribute(User.LOGED_IN_USER_IN_REQUEST, user);
				
				if (remember_me!=null) {
					Cookie c = new Cookie(User.USER_LOGIN_NAME_IN_COOKIE, user.getU_login_name());
					c.setMaxAge(5 * 60);
					resp.addCookie(c);
				} else {
					Cookie c = new Cookie(User.USER_LOGIN_NAME_IN_COOKIE, null);
					c.setMaxAge(0);
					resp.addCookie(c);
				}
				
				
				resp.sendRedirect(req.getServletContext().getContextPath() +"/");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
