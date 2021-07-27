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

import entities.Size;

@WebServlet(urlPatterns = {"/create-size"})
public class SizeCreateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		byte size_number = (byte) Integer.parseInt(req.getParameter("size_number"));
		String size_detail = req.getParameter("size_detail");
		
		Size size = new Size(size_number, size_detail);
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String message = null;
		try {
			db_crud_utils.SizeUtils.create(size, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeCreateView.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeCreateView.jsp");
			dispatcher.forward(req, resp);
		}
		
		message="Thêm 1 size";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeCreateView.jsp");
		dispatcher.forward(req, resp);
	}
}
