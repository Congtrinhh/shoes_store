package ajax_call;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Admin;

@WebServlet(urlPatterns = "/ajax-admin-forget-password")
public class AdminForgetPassword extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhận về admin user name từ request
		 * -> kiểm tra user name tồn tại
		 * + nếu có, trả về link đến trang jsp về js
		 * + nếu không, trả thông báo lỗi về js
		 * */
		try {
			String userName = req.getParameter("name");
			
			if ( userName != null ) {
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				String message = null;
				int adminId = db_crud_utils.AdminUtils.findAdminIdByName(conn, userName);
				
				if ( adminId != -1 ) {
					message = req.getContextPath() + "/admin-set-new-password";
					req.getSession().setAttribute(Admin.ADMIN_ID_IN_SESSION, adminId);
				}
				else {
					message = "Tên tài khoản không tồn tại";
				}
				
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html");
				resp.getWriter().write(message);
			}
		}
		catch (Exception e) {
			System.out.println("ajax admin forget pass, mes: " +e.getMessage());
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.getWriter().write("Đã có lỗi từ server");
		}
	}
}
