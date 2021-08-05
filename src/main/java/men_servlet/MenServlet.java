package men_servlet;

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

import crud.entity.product_create.Brand;
import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/men"})
public class MenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// hàm này có tác dụng set các tiêu chí mặc định vào session
		// và lấy ra trang đầu tiên của list sản phẩm theo tiêu chí mặc định
		HttpSession session = req.getSession();
		
		int brandOption = 0; // all
		int priorityOption = 1; // mới nhất
		int fromRangeOption = 0; // giá từ 0
		int toRangeOption = 99999999; // đến 99999999 dollars
		int pageNo = 1; // trang này đương nhiên là trang đầu
					
		session.setAttribute("brandOption", brandOption);
		session.setAttribute("priorityOption", priorityOption);
		session.setAttribute("fromRangeOption", fromRangeOption);
		session.setAttribute("toRangeOption", toRangeOption);
		session.setAttribute("currentPage", pageNo);
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		try {
			List<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(conn, brandOption, priorityOption, fromRangeOption, toRangeOption, pageNo);
			
			if (productList!=null) {
				int totalProducts = db_men_utils.MenQuery.countTotalProducts(conn, brandOption, fromRangeOption, toRangeOption);

				session.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts, constants.SystemConstants.PRODUCTS_PER_PAGE)); // set để ajax [get] lấy mà không cần query lại
				session.setAttribute("productCount", totalProducts);
				
				req.setAttribute("currentPage", pageNo); // 2 cái này dành cho phân trang.
				req.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts, constants.SystemConstants.PRODUCTS_PER_PAGE));
				req.setAttribute("productCount", totalProducts);
				
				req.setAttribute("productList", productList);				
			}
			else {
				System.out.println("Khogn co san pham");
			}
			
			List<Brand> brandList = db_men_utils.MenQuery.getBrandList(conn);
			if (brandList!=null) {
				req.setAttribute("brandList", brandList);
			}
			
			req.setAttribute(constants.SystemConstants.TAB_INDICATOR, req.getServletPath()); // tab indicator
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/men/men.jsp");
			dispatcher.forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Trong [POST] men servlet");
	}
}
