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

import entities.Product;

@WebServlet(urlPatterns = {"/ajax-product-read"})
public class ProductRead extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * js: khi bấm vào 1 nút sang trang:
		 * -> gọi ajax method get, formdata gồm: requested page
		 * servlet: nhận được request
		 * -> kiểm tra nếu trùng trang hiện tại thì return, nếu không trùng thì lấy ra list sp mới dựa vào trang được yêu cầu
		 * -> set lại session attribute currentpage = requested page
		 * -> trả lại json cho js ( chú ý cần trả về cả currentPage ), vì đây là ajax nên không có chuyện load lại jsp, các attribute
		 * phải được trả về bằng json
		 */
		String requestedPageStr = req.getParameter("requested-page");
		int requestedPage = -1;
		if (requestedPageStr!=null) {
			requestedPage=Integer.parseInt(requestedPageStr);
		}
		HttpSession session =req.getSession();
		
		int totalPage = (int) session.getAttribute("totalPage");
		int currentPage = (int) session.getAttribute("currentPage");
		int itemPerPage = (int) session.getAttribute("itemPerPage");
		
		if (requestedPage<=0 || requestedPage>totalPage || requestedPage==currentPage) { // hoặc trùng hoặc không thỏa mãn -> dừng hàm
			return;
		}
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		List<Product> productList = db_crud_utils.ProductUtils.getProductListForAdmin(conn, itemPerPage, requestedPage);
		
		session.setAttribute("currentPage", requestedPage); // set lại session
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		String productString = new Gson().toJson(productList); // string product
		
		Map<String, Integer> pageInfo = new HashMap<>();
		pageInfo.put("totalPage", totalPage);
		pageInfo.put("currentPage", requestedPage);
		pageInfo.put("itemPerPage", itemPerPage);
		pageInfo.put("itemCount", (int)session.getAttribute("itemCount"));
		
		String pageString = new Gson().toJson(pageInfo); // string page
		
		
		List<String> genList = new ArrayList<>();
		genList.add(pageString);
		genList.add(productString);
		
		String finalString = new Gson().toJson(genList); // string cuối cùng
		
		resp.getWriter().write(finalString);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * code dopost trước doget
		 * js: ngay khi giá trị thẻ select thay đổi:
		 * -> gọi ajax với method post, param của formdata: ppp mới
		 * servlet: nhận param
		 * -> set currentPage về 1
		 * -> tính lại totalPage mới
		 * -> lấy ra số sp dựa vào ppp mới và currentPage mới
		 * -> set lại cho session 4 attribute page
		 * -> trả về 1 list theo con đường ajax gồm 2 phần: 
		 * 	+ object chứa 4 attr page
		 * 	+ array product
		 */
		
		String ippStr = req.getParameter("item-per-page");
		int ipp = -1;
		if (ippStr!=null) {
			ipp = Integer.parseInt(ippStr);
		}
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		HttpSession session = req.getSession();
		
		List<Product> productList = db_crud_utils.ProductUtils.getProductListForAdmin(conn, ipp, 1);
		
		if (productList!=null) {
			int itemCount = (int)session.getAttribute("itemCount");
			
			session.setAttribute("currentPage", 1);
			session.setAttribute("totalPage", db_crud_utils.ProductUtils.calculateTotalPages(itemCount, ipp));
			//session.setAttribute("itemCount", ...); khong can thiet
			session.setAttribute("itemPerPage", ipp);
			
			Map<String, Integer> pageInfoList = new HashMap<>();
			pageInfoList.put("itemCount", itemCount);
			pageInfoList.put("currentPage", 1);
			pageInfoList.put("itemPerPage", ipp);
			pageInfoList.put("totalPage", db_crud_utils.ProductUtils.calculateTotalPages(itemCount, ipp));
			
			String productString = new Gson().toJson(productList);
			String pageString = new Gson().toJson(pageInfoList);
			
			List<String> finalString = new ArrayList<>();
			finalString.add(pageString);
			finalString.add(productString);
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			
			resp.getWriter().write(new Gson().toJson(finalString));
		}
	}
}
