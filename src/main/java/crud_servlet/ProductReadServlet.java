package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entities.Product;

@WebServlet(urlPatterns = {"/product-read"}) 
public class ProductReadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// set pageNo mặc định=1, item mỗi page mặc định=10 vào session
		// nhận về tất cả dòng sp và trả về jsp
		HttpSession session =req.getSession();
		int pageNo = 1;
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		List<Product> productList = db_crud_utils.ProductUtils.getProductListForAdmin(conn, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN, pageNo);
		
		if (productList!=null) {
			req.setAttribute("productList", productList);
			
			int itemCount=db_crud_utils.ProductUtils.getNumberOfProducts(conn);
			
			session.setAttribute("itemCount", itemCount);
			session.setAttribute("currentPage", pageNo); //  cẩn thận lẫn lộn với attribute này của sản phẩm bên user
			session.setAttribute("totalPage", db_crud_utils.ProductUtils.calculateTotalPages(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN)); // tính ra tổng số trang dựa vào tổng số sp
			session.setAttribute("itemPerPage", constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN);
			
			Map<String, Integer> pageInfo = new HashMap<>();
			pageInfo.put("itemCount", itemCount);
			pageInfo.put("currentPage", pageNo);
			pageInfo.put("totalPage", db_crud_utils.ProductUtils.calculateTotalPages(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN));
			String json = new Gson().toJson(pageInfo);
		}
		
		
		
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productRead.jsp");
		try {
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			System.out.println("Lỗi khi forward: "+e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// có thể để phân trang hoặc làm nhiều nv dựa vào request (if có param này thì làm gì, param kia thì làm gì)
	}
}
