package crud_servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common_utils.MyUtils;
import entities.Order;

@WebServlet(urlPatterns = {"/create-order"})
public class OrderCreateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/orderCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get data from form of client
		int admin_id = Integer.parseInt(req.getParameter("admin_id"));
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		String or_total_cost_str = req.getParameter("or_total_cost");
		String or_shipping_cost_str = req.getParameter("or_shipping_cost");
		BigDecimal or_total_cost = new BigDecimal(or_total_cost_str);
		BigDecimal or_shipping_cost = new BigDecimal(or_shipping_cost_str);
		byte or_status = (byte)Integer.parseInt(req.getParameter("or_status"));
		
		Order od = new Order(admin_id, user_id, or_total_cost, or_shipping_cost, or_status);
		Connection conn = MyUtils.getStoredConnection(req);
		String message = null;
		
		try {
			db_crud_utils.OrderUtils.create(od, conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/orderCreateView.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/orderCreateView.jsp");
			dispatcher.forward(req, resp);
		}
		
		message = "Thêm 1 đơn hàng.";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/orderCreateView.jsp");
		dispatcher.forward(req, resp);
		
	}
		
}
