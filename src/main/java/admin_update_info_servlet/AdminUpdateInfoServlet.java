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

@WebServlet(urlPatterns = {"/admin-update"})
public class AdminUpdateInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateBasicInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ad_name = req.getParameter("ad_name");
		String ad_login_name = req.getParameter("ad_login_name");
		String ad_email = req.getParameter("ad_email");
		String ad_phone_number = req.getParameter("ad_phone_number");
		String ad_password = req.getParameter("ad_password");
		
		// từ password check user.
		Admin logedInAdmin = (Admin) req.getSession().getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String errorMessage = null;
		try {
			Admin adminFromServer = db_admin_login_utils.sql.AdminQuery.findAdmin(logedInAdmin.getAd_login_name(), ad_password, conn);
			if (adminFromServer!=null) {
				int admin_id = logedInAdmin.getAdmin_id();
				Admin newAdmin = new Admin(admin_id, ad_name, ad_login_name, ad_password, ad_email, ad_phone_number, (byte)1);
				db_admin_update_info_utils.AdminUpdateBasicInfo.updateBasicInfo(newAdmin, conn);
				
				// cập nhật lại thong tin admin trong session
				HttpSession session = req.getSession();
				session.setAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION, newAdmin);
				
				// chuyển về trang cũ với thông báo đã cập nhật thành công
				errorMessage = "Cập nhật thông tin thành công";
				req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateBasicInfo.jsp");
				dispatcher.forward(req, resp);
			}
			// khi nhập mk không chính xác, forward về trang 
			else {
				errorMessage = "Mật khẩu không đúng";
				req.setAttribute(Admin.ERROR_MESSAGE_IN_REQUEST, errorMessage);
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/admin_update_info/updateBasicInfo.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
