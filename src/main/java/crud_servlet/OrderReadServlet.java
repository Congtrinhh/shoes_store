package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Order;

@WebServlet(urlPatterns = {"/order-read", "/order-read/*"})
public class OrderReadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* lấy ra trang 1 của order list
		 * set page attr vào session
		 * gửi sang jsp order list + page attr thông qua req
		 */
		try {
			
			// chức năng search theo order id và user name, trường nào bị bỏ trống sẽ được gán gt mặc định = "%"
			String orderIdStr = req.getParameter("order-id");
			String userName = req.getParameter("user-name");
			
			if ( orderIdStr==null || orderIdStr.length()==0 ) {
				orderIdStr = "%";
			}
			if ( userName==null || userName.length()==0 ) {
				userName="%";
			}
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			HttpSession session = req.getSession();
			
			List<Order> orderList = db_crud_utils.OrderUtils.getOrderList(conn, orderIdStr, userName, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN, 1);
			
			int itemCount = -1;
			if ( orderList==null || orderList.size()==0 ) {
				itemCount=0;
			}
			else {
				itemCount = db_crud_utils.OrderUtils.getItemCount(conn, orderIdStr, userName);
			}
			
			int totalPage = common_utils.MyUtils.calculateTotalPage(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN);
			
			session.setAttribute("currentPage", 1);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("itemCount", itemCount);
			session.setAttribute("itemPerPage", constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN);
			
			req.setAttribute("orderList", orderList);
			req.setAttribute("currentPage", 1);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("itemCount", itemCount);
			req.setAttribute("itemPerPage", constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN);
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/orderRead.jsp");
			dispatcher.forward(req, resp);
		}
		catch (Exception e) {
			System.out.println("order servlet get, mes: "+e.getMessage());
		}
	}
}
