package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entities.Order;

@WebServlet(urlPatterns = "/ajax-order-read")
public class OrderRead extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String requestedPageStr = req.getParameter("requested-page");
			int requestedPage = -1;
			if ( requestedPageStr!=null ) {
				requestedPage = Integer.parseInt(requestedPageStr);
				
				HttpSession session = req.getSession();
				int totalPage =(int) session.getAttribute("totalPage");
				
				if ( requestedPage<1 || requestedPage>totalPage) {
					throw new Exception("trang yêu cầu không hợp lệ");
				}
				
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				int itemPerPage = (int) session.getAttribute("itemPerPage");
				
				// lay ra list item
				List<Order> ordertList = db_crud_utils.OrderUtils.getOrderList(conn, "%", "%", constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN, requestedPage); 
				
				// set attr
				session.setAttribute("currentPage", requestedPage); 
				
				
				int itemCount = (int)session.getAttribute("itemCount");
				
				Map<String, Integer> pageInfo = new HashMap<>();
				pageInfo.put("currentPage", requestedPage);
				pageInfo.put("itemCount", itemCount);
				pageInfo.put("itemPerPage", itemPerPage);
				pageInfo.put("totalPage", common_utils.MyUtils.calculateTotalPage(itemCount, itemPerPage));
				
				String pageString = new Gson().toJson(pageInfo);
				String itemString = new Gson().toJson(ordertList);
				
				List<String> finalList = new ArrayList<>();
				finalList.add(pageString);
				finalList.add(itemString);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				
				// gui response ve js
				resp.getWriter().write(new Gson().toJson(finalList));
			}
			else {
				System.out.println("param is null");
			}
		}
		catch (SQLException e) {
			System.out.println("ajax get order, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("ajax get order, mes: "+e.getMessage());
		}
	}
}
