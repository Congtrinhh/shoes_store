package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entities.SpecificProduct;

@WebServlet(
		urlPatterns = {"/ajax-specific-product-read"},
		initParams = {
				@WebInitParam(name = "requested-page", value = "1"),
		}
)
public class SpecificProductRead extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhận request từ js (requested-page)
		 * -> check requested page hợp lệ (trong khoảng từ 1 đến totalPage)
		 * -> lấy ra list item tương ứng 
		 * -> set lại current page trong session
		 * -> lấy ra 4 attr page 
		 * -> gửi 4attr page + list item về js
		 */
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
				List<SpecificProduct> specificProductList = db_crud_utils.SpecificProductUtils.getSpecificProductList(conn, itemPerPage, requestedPage); 
				
				// set attr
				session.setAttribute("currentPage", requestedPage); 
				
				
				int itemCount = (int)session.getAttribute("itemCount");
				
				Map<String, Integer> pageInfo = new HashMap<>();
				pageInfo.put("currentPage", requestedPage);
				pageInfo.put("itemCount", itemCount);
				pageInfo.put("itemPerPage", itemPerPage);
				pageInfo.put("totalPage", common_utils.MyUtils.calculateTotalPage(itemCount, itemPerPage));
				
				String pageString = new Gson().toJson(pageInfo);
				String itemString = new Gson().toJson(specificProductList);
				
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
			System.out.println("ajax get s product, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("ajax get s product, mes: "+e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhận param từ request (item-per-page) 
		 * -> kiểm tra itemperpage hợp lệ (lớn hơn 0)
		 * -> lấy ra item list tương ứng
		 * -> set page attr trong session
		 * -> trả về list item + page attr
		 */
		
		try {
			String itemPerPageStr = req.getParameter("item-per-page");
			int itemPerPage = -1;
			if (itemPerPageStr!=null ) {
				itemPerPage = Integer.parseInt(itemPerPageStr);
				if (itemPerPage<0) {
					return;
				}
				
				HttpSession session = req.getSession();
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				List<SpecificProduct> specificProductList = db_crud_utils.SpecificProductUtils.getSpecificProductList(conn, itemPerPage, 1);
				
				int itemCount = (int)session.getAttribute("itemCount");
				int totalPage = common_utils.MyUtils.calculateTotalPage(itemCount, itemPerPage);
				
				session.setAttribute("itemPerPage", itemPerPage);
				session.setAttribute("totalPage", totalPage);
				session.setAttribute("currentPage", 1);
				
				Map<String, Integer> pageInfo = new HashMap<>();
				pageInfo.put("itemCount", itemCount);
				pageInfo.put("itemPerPage",itemPerPage);
				pageInfo.put("totalPage", totalPage);
				pageInfo.put("currentPage", 1);
				
				String pageString = new Gson().toJson(pageInfo);
				String itemString = new Gson().toJson(specificProductList);
				
				List<String> finalList = new ArrayList<>();
				finalList.add(pageString);
				finalList.add(itemString);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				
				// gui response ve js
				resp.getWriter().write(new Gson().toJson(finalList));
			}
		}
		catch (SQLException e) {
			System.out.println("ajax post s product, code: "+e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("ajax post s product, mes: "+e.getMessage());
		}
		
	}
}
