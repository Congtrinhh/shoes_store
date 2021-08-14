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

@WebServlet(urlPatterns = "/ajax-search")
public class Search extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
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
			
			String productNameInSearch = (String) session.getAttribute("productNameForSearching");
			System.out.println("pr name sess: "+productNameInSearch );
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			List<ProductGetter> productList = search_utils.HomeSearchDB.getProductListWithSimilarName(conn, productNameInSearch, constants.SystemConstants.PRODUCTS_PER_PAGE, requestedPage);
			
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
			System.out.println("ajax get home search , mes: "+e.getMessage());
		}
	}
}
