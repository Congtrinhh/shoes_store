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

import entities.SizeProductLine;

@WebServlet(urlPatterns = {"/create-size-product-line"})
public class SizeProductLineCreateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeProductLineCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int product_line_id = Integer.parseInt(req.getParameter("product_line_id"));
		int size_id = Integer.parseInt(req.getParameter("size_id"));
		short spl_quantity = (short) Integer.parseInt(req.getParameter("spl_quantity"));
		
		SizeProductLine spl = new SizeProductLine(product_line_id, size_id, spl_quantity);
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String message = null;
		try {
			db_crud_utils.SizeProductLineUtils.create(spl, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeProductLineCreateView.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeProductLineCreateView.jsp");
			dispatcher.forward(req, resp);
		}
		
		message="Thêm 1 size ứng với 1 product line";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/sizeProductLineCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}
