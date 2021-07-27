package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Admin;

@WebServlet(urlPatterns = {"/create-admin"})
public class AdminCreateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// forward to jsp page
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/adminCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get data and process
		String ad_name = req.getParameter("ad_name");
		String ad_login_name = req.getParameter("ad_login_name");
		String ad_password = req.getParameter("ad_password");
		String ad_email = req.getParameter("ad_email");
		String ad_phone_number = req.getParameter("ad_phone_number");
		byte ad_state = (byte) Integer.parseInt(req.getParameter("ad_state"));
		byte ad_remember_token = (byte) Integer.parseInt(req.getParameter("ad_remember_token"));
		
		Admin admin = new Admin(ad_name, ad_login_name, ad_password, ad_email, ad_phone_number, ad_state, ad_remember_token);
		Connection conn = null;
		String message = null;
		try {
			conn = connection_utils.ConnectionUtils.getConnection();
			db_crud_utils.AdminUtils.create(admin, conn);
		}  catch(Exception e) {
			connection_utils.ConnectionUtils.rollbackQuietly(conn);
			e.printStackTrace();
			message = e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/adminCreateView.jsp");
			dispatcher.forward(req, resp);
		} 
		message = "Them 1 admin thanh cong";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/adminCreateView.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
}
