package user_register_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entities.Admin;
import entities.User;

@WebServlet(name = "userRegister", urlPatterns = { "/register", "/user-register" })
public class UserRegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// ngoại lệ: người dùng cố đăng ky user khi đang đăng nhập là admin
		Admin logedInAdmin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		if (logedInAdmin != null) {
			RequestDispatcher dispatcher = req.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/exception/logoutAdminFirst.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
		User logedInUser = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
		if (logedInUser!=null) {
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/exception/logoutUserFirst.jsp");
			dispatcher.forward(req, resp);		
			return;
		}

		RequestDispatcher dispatcher = req.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/user_register/userRegister.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* nút submit được click -> main.js gom dữ liệu gửi về đây
		 * hàm này kiểm tra điều kiện các dữ liệu sau đây không được trùng với
		 * trên DB: tên đăng nhập, email, sđt
		 * nếu thỏa mãn yêu cầu -> ghi dữ liệu lên DB và forward sang trang đăng ký thành công (là 1 trang jsp trong thư mục views/notification)
		 * nếu không thỏa mãn -> đính kèm thông báo lỗi của trường bị lỗi kèm theo thông tin các trường trong danh sách param
		 * được gửi lên lúc nãy (thông tin tên đăng nhập, email,..) để người dùng có thể tùy sửa theo ý, sau đó
		 * trả về cho main.js, main.js hiển thị thông báo lỗi kèm những trường liên quan.
		 * ngay khi người dùng sửa bất kỳ trường nào (keyup), xóa dòng thông báo lỗi của server.		
		*/
		
		String u_login_name = req.getParameter("name");
		String u_email = req.getParameter("email");
		String u_password = req.getParameter("password");
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		try {
			
			if ( db_user_register_utils.UserRegister.isUserNameDuplicated(conn, u_login_name) ) {
							
				String errorMessage = "Tên người dùng đã tồn tại";
								
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("text/html");
				
				resp.getWriter().write(errorMessage);
				
			}
			else {
				
				if (db_user_register_utils.UserRegister.isEmailDuplicated(conn, u_email)) {
					
					String errorMessage = "Email đã tồn tại";
					
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("text/html");
					
					resp.getWriter().write(errorMessage);
				}
				else {
					// thêm người dùng vào DB và forward về trang đăng ký thành công.
					int avatarIndex = (int) ThreadLocalRandom.current().nextDouble(1, 15);
					String avatarPath = "/" + avatarIndex + ".jpg";
					
					User user = new User(u_login_name, u_email, u_password, avatarPath);
					
					db_user_register_utils.UserRegister.createUser(user, conn);
					
					HttpSession session = req.getSession();
					session.setAttribute(User.LOGED_IN_USER_IN_SESSION, user);
					
					
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi [POST] user register servlet, " + e.getMessage());
		}

			/*		
		String errorMessage = null;
		User newUser = new User(u_login_name, u_password, u_phone_number);
		try {
			db_user_register_utils.UserRegister.createUser(newUser, conn);

//			newUser.set
			// nếu đăng ký thành công, lưu user vào session, đồng thời remove admin session
			HttpSession session = req.getSession();
			session.removeAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
			session.setAttribute(User.LOGED_IN_USER_IN_SESSION, newUser);
			req.setAttribute(User.LOGED_IN_USER_IN_REQUEST, newUser);

			errorMessage = "Đăng ký tài khoản thành công";
			req.setAttribute(User.ERROR_MESSAGE_IN_REQUEST, errorMessage);
//			

			RequestDispatcher dispatcher = req.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/user_register/userRegister.jsp");
			dispatcher.forward(req, resp);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMessage = "Đã có lỗi khi đăng ký";
			req.setAttribute(User.ERROR_MESSAGE_IN_REQUEST, errorMessage);

			RequestDispatcher dispatcher = req.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/user_register/userRegister.jsp");
			dispatcher.forward(req, resp);
		} */
	}
}
