package women_servlet;

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

import crud.entity.product_create.Brand;
import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/women"})
public class WomenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * không phân trang nhưng load item ajax khi scroll vẫn cần totalPage và currentPage
		 * hàm này:
		 * -> set default totalPage, currentPage vào session
		 * -> set tiêu chí mặc định vào session
		 * -> lấy ra list brand để hiển thị trên thanh filter
		 * -> lấy ra list item đầu tiên 
		 * -> gửi về jsp, kết thúc
		 */
		try {
			String categorySlug = "/women";
			
			HttpSession session= req.getSession();
			int currentPage = 1;
			
			int brandOption = 0; // all
			int priorityOption = 1; // mới nhất
			int fromRangeOption = 0; // giá từ 0
			int toRangeOption = 99999999; // đến 99999999 dollars
			
			session.setAttribute("brandOption", brandOption);
			session.setAttribute("priorityOption", priorityOption);
			session.setAttribute("fromRangeOption", fromRangeOption);
			session.setAttribute("toRangeOption", toRangeOption);
			session.setAttribute("currentPage", currentPage);
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			List<Brand> brandList = db_men_utils.MenQuery.getBrandList(conn);
			
			List<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(conn, categorySlug, brandOption, priorityOption, fromRangeOption, toRangeOption, currentPage);
			
			int itemCount = db_men_utils.MenQuery.countTotalProducts(conn, categorySlug, brandOption, fromRangeOption, toRangeOption);
			int totalPage = common_utils.MyUtils.calculateTotalPage(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE);
			
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currentPage", currentPage);
			
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("productCount", itemCount);
			
			req.setAttribute("productList", productList);
			req.setAttribute("brandList", brandList);
			
			req.setAttribute("tabIndicator", req.getServletContext().getContextPath() + "/women");
		
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/women/women.jsp");
			dispatcher.forward(req, resp);
		}
		catch (Exception e) {
			System.out.println("women servlet, mes: "+e.getMessage());
		}
		
		
	}
}
