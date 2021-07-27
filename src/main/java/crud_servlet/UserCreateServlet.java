package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common_utils.MyUtils;
import entities.User;

@WebServlet(urlPatterns = {"/create-user"})
public class UserCreateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/userCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get info from form of client
		int admin_id = (int)Integer.parseInt(req.getParameter("admin_id"));
		String u_name = req.getParameter("u_name");
		String u_login_name = req.getParameter("u_login_name");
		String u_password = req.getParameter("u_password");
		String u_email = req.getParameter("u_email");
		String u_phone_number = req.getParameter("u_phone_number");
		String u_address = req.getParameter("u_address");
		
		User user = new User(admin_id, u_name, u_login_name, u_password, u_email, u_phone_number, u_address);
		Connection conn = MyUtils.getStoredConnection(req);
		String message = null;
		
		try {
			db_crud_utils.UserUtils.create(user, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/userCreateView.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/userCreateView.jsp");
			dispatcher.forward(req, resp);
		}
		
		message="Thêm 1 user.";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/userCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	
}
