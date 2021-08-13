package ajax_call;

import java.io.IOException;
import java.sql.Connection;
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

import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/ajax-women"})
public class Women extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * load list item mới khi scroll đến đáy trang
		 * nhận request: requestedPage
		 * -> lấy ra item list tương ứng với trang được load (coi trang ajax đầu tiên được load là trang số 2)
		 * -> set lại currentPage, totalPage vào session
		 * -> gửi currentPage, totalPage và list item về js
		 */
		
		try {
			String categorySlug = "/women";
			
			HttpSession session= req.getSession();
			
			int totalPage = (int) session.getAttribute("totalPage");
			
			
			String requestedPageStr = req.getParameter("requested-page");
			int requestedPage = -1;
			if ( requestedPageStr!=null ) {
				requestedPage= Integer.parseInt(requestedPageStr);
			}
			if ( requestedPage<1 || requestedPage>totalPage) {
				return;
			}
			
			int brandOption = (int) session.getAttribute("brandOption");
			int priorityOption = (int) session.getAttribute("priorityOption");
			int fromRangeOption = (int) session.getAttribute("fromRangeOption");
			int toRangeOption = (int) session.getAttribute("toRangeOption");
			
			int currentPage = (int) session.getAttribute("currentPage"); 
			
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			List<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(conn, categorySlug, brandOption, priorityOption, fromRangeOption, toRangeOption, requestedPage);
			
			if ( productList==null ) {
				return;
			}
			
			session.setAttribute("currentPage", requestedPage);
			
			Map<String, Integer> pageInfo = new HashMap<>();
			pageInfo.put("currentPage", requestedPage);
			pageInfo.put("totalPage", totalPage);
			
			
			String pageString = new Gson().toJson(pageInfo);
			String itemString = new Gson().toJson(productList);
			
			List<String> finalString = new ArrayList<>();
			finalString.add(pageString);
			finalString.add(itemString);
			
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().write(new Gson().toJson(finalString));
			
		}
		catch (Exception e) {
			System.out.println("ajax get women , mes: "+e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhận tất cả param là tiêu chí lọc từ request
		 * -> xử lý param
		 * -> get list item theo các param trên
		 * -> set các tiêu chí lọc, thông tin trang vào session
		 * -> gửi về js list item + page info
		 */
		try {
			String categorySlug = "/women";
			
			String brandOptionStr = req.getParameter("brand");
			String priorityStr = req.getParameter("priority");
			String fromRangeStr=req.getParameter("from-range");
			String toRangeStr = req.getParameter("to-range");
			
			int brandOption = 0;
			int priorityOption = 1;
			int fromRange = 0;
			int toRange = 99999999;
			
			try {
				brandOption = Integer.parseInt(brandOptionStr);
				priorityOption = Integer.parseInt(priorityStr);
				fromRange = Integer.parseInt(fromRangeStr);
				toRange = Integer.parseInt(toRangeStr);
			}catch (Exception e) {
				System.out.println("ajax post women parsing integer, mes: "+e.getMessage());
			}
			
			System.out.format("brand: %d, pri: %d, from: %d, to: %d\n", brandOption, priorityOption, fromRange, toRange);
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			HttpSession session = req.getSession();
			
			
			List<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(conn, categorySlug, brandOption, priorityOption, fromRange, toRange, 1);
			
			if ( productList==null ) {
				return;
			}else if ( productList!=null && productList.size()==0 ) {
				return;
			}
			
			int itemCount = db_men_utils.MenQuery.countTotalProducts(conn, categorySlug, brandOption, fromRange, toRange); // số item theo tiêu chí lọc đã thay đổi
			int totalPage = common_utils.MyUtils.calculateTotalPage(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE);
			
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currentPage", 1);
			session.setAttribute("itemCount", itemCount);
			
			session.setAttribute("", brandOption);
			session.setAttribute("", priorityOption);
			session.setAttribute("", fromRange);
			session.setAttribute("", toRange);
			
			Map<String, Integer> pageInfo = new HashMap<>();
			pageInfo.put("currentPage", 1);
			pageInfo.put("totalPage", totalPage);
			
			String pageString = new Gson().toJson(pageInfo);
			String itemString = new Gson().toJson(productList);
			
			List<String> finalList = new ArrayList<>();
			finalList.add(pageString);
			finalList.add(itemString);
			
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().write(new Gson().toJson(finalList));
		}
		catch (Exception e) {
			System.out.println("ajax post women, mes: "+e.getMessage());
		}
		
	}
}
