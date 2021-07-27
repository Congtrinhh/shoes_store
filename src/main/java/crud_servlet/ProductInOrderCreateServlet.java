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

import entities.ProductInOrder;

@WebServlet(urlPatterns = {"/create-product-in-order"})
public class ProductInOrderCreateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productInOrderCreateView.jsp");
		dispatcher.forward(req, resp);	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int product_line_id = Integer.parseInt(req.getParameter("product_line_id"));
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		byte pio_size = (byte)Integer.parseInt(req.getParameter("pio_size"));
		String pio_color = req.getParameter("pio_color");
		short pio_quantity = (short) Integer.parseInt(req.getParameter("pio_quantity"));
		byte pio_discount_percent = (byte)Integer.parseInt(req.getParameter("pio_discount_percent"));
		
		ProductInOrder pio = new ProductInOrder(product_line_id, order_id, pio_size, pio_color, pio_quantity, pio_discount_percent);
		Connection conn = common_utils.MyUtils.getStoredConnection(req); 
		String message = null;
		try {
			db_crud_utils.ProductInOrderUtils.create(pio, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productInOrderCreateView.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productInOrderCreateView.jsp");
			dispatcher.forward(req, resp);
		}
		
		message="Thêm 1 sản phẩm trong đơn đặt hàng.";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productInOrderCreateView.jsp");
		dispatcher.forward(req, resp);
	}
}
