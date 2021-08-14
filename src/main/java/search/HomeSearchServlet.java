package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = "/search")
public class HomeSearchServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* nhận param từ request (tên sp được nhập trong thẻ input)
		 * xử lí param (tối ưu string: toLowerCase, trim,..)
		 * lấy ra list item tương ứng với tên
		 * set page attr vào session tương tự men/women servlet
		 * gửi về list item, page attr sang jsp thông qua req
		 */
		try {
			String productName = req.getParameter("product-name");
			
			req.getSession().setAttribute("productNameForSearching", productName);
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			List<ProductGetter> productList = search_utils.HomeSearchDB.getProductListWithSimilarName(conn, productName, constants.SystemConstants.PRODUCTS_PER_PAGE, 1);
						
			int itemCount = search_utils.HomeSearchDB.getItemCount(conn, productName);
			System.out.println("itemcount: "+itemCount);
			int totalPage = common_utils.MyUtils.calculateTotalPage(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE); 
			
			HttpSession session = req.getSession();
			session.setAttribute("currentPage", 1);
			session.setAttribute("totalPage", totalPage);
			
			req.setAttribute("currentPage", 1);
			req.setAttribute("totalPage", totalPage);
			
			req.setAttribute("productList", productList);
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/search/search.jsp");
			dispatcher.forward(req, resp);
		}
		catch (SQLException e) {
			System.out.println("search servlet get, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("search servlet get, mess: "+e.getLocalizedMessage());
		}
	}
}
