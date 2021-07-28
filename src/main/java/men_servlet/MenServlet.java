package men_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/men"})
public class MenServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// hàm này có tác dụng set các tiêu chí mặc định vào session
		// và lấy ra trang đầu tiên của list sản phẩm theo tiêu chí mặc định
		HttpSession session = req.getSession();
		
		int brandOption = 1; // all
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
				int totalProducts = db_men_utils.MenQuery.countTotalProducts(conn, brandOption, priorityOption, fromRangeOption, toRangeOption);
				session.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts));
								
				req.setAttribute("currentPage", pageNo); // 2 cái này dành cho phân trang.
				req.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts));
				
				req.setAttribute("productList", productList);				
			}
			else {
				System.out.println("Khogn co san pham");
			}
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/men/men.jsp");
			dispatcher.forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String brandOptionStr = req.getParameter("brand");
		String priorityOptionStr = req.getParameter("priority");
		String fromRangeOptionStr = req.getParameter("from-range");
		String toRangeOptionStr = req.getParameter("to-range");
		
		int brandOption = 0;
		int priorityOption = 0;
		int fromRangeOption = 0;
		int toRangeOption = 0;
				
		try {
			brandOption = Integer.parseInt(brandOptionStr);
			priorityOption = Integer.parseInt(priorityOptionStr);
			fromRangeOption = Integer.parseInt(fromRangeOptionStr);
			toRangeOption = Integer.parseInt(toRangeOptionStr);
			
			
			
		}catch(Exception e) {
			System.out.println("Loi: "+e.getMessage());
		}
		
	
		if (toRangeOption==0) {
			toRangeOption = 99999999;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("brandOption", brandOption);
		session.setAttribute("priorityOption", priorityOption);
		session.setAttribute("fromRangeOption", fromRangeOption);
		session.setAttribute("toRangeOption", toRangeOption);
		
		System.out.println("men - post - done");
		System.out.println("brand: "+brandOption);
		System.out.println("priority: "+priorityOption);
		System.out.println("from: "+fromRangeOption);
		System.out.println("to: "+toRangeOption);
		doGet(req, resp);
	}
}
