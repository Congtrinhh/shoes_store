package homepage_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(urlPatterns = {"", "/home"}) // dấu "/" có nghĩa là khi một trang không tồn tại thì servlet này sẽ được map.
public class HomeServlet extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// dùng thẻ select để sửa form nào..
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
//		
		try {
			
			/* hiện tại không dùng
			List<CategoryGetter> cateList = db_homepage_utils.FetchAllHomepageData.fetchCategories(conn);
			// need to check if null first, and have a message variable to inform with jsp
			if(cateList!=null) {
				req.setAttribute("categoryList", cateList);
			} 
			*/
			
			List<ProductGetter> productList = new ArrayList<ProductGetter>();
			productList = db_homepage_utils.FetchAllHomepageData.fetchProducts(conn);
			
			// liệu có cần biến đổi hình ảnh để hiển thị được trên jsp file?
			if (productList!=null) {
				req.setAttribute("productList", productList);
			}
			
			List<CtaGetter> ctaList = new ArrayList<CtaGetter>();
			ctaList = db_homepage_utils.FetchAllHomepageData.fetchCtas(conn);
			if (ctaList!=null) {
				req.setAttribute("ctaList", ctaList);
			}
						
		} catch (Exception e) {
			connection_utils.ConnectionUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		
		req.setAttribute(constants.SystemConstants.TAB_INDICATOR, "/home"); // đang ở trang home thì tab home trên navbar được highlight
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/homepage/homepage.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}