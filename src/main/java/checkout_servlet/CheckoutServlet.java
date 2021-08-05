package checkout_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		List<AddressEntity> provincesList = db_checkout_utils.CheckoutDB.getAllProvinces(conn);
		if (provincesList!=null) {			
			req.setAttribute("provincesList", provincesList);
		}
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/checkout/checkout.jsp");
		dispatcher.forward(req, resp);
	}
}
