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


@WebServlet(urlPatterns = {"/ajax-product-delete"})
public class ProductDelete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// xóa sp và 
		try {
			String idString = req.getParameter("id");
			int id = -1;
			if (idString!=null) {
				id = Integer.parseInt(idString);
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				//db_crud_utils.ProductUtils.deleteSpecificProductOfProduct(conn, id); không được xóa cái này, hãy làm như trong db, xóa hết record từ table con trước
				
				int row = db_crud_utils.ProductUtils.deleteProduct(conn, id); // xóa từ khóa ngoài hết rồi mới xóa sp được
				System.out.println("so hang bi tac dong: "+row);
				if (row>0) {
					HttpSession session = req.getSession();
					int currentPage = (int) session.getAttribute("currentPage");
					int itemCount = (int) session.getAttribute("itemCount") - 1; // số lượng sp trong db giảm 1
					int itemPerPage = (int) session.getAttribute("itemPerPage"); // vẫn vậy
					int totalPage = db_crud_utils.ProductUtils.calculateTotalPages(itemCount, itemPerPage);
					
					
					List<Product> productList = db_crud_utils.ProductUtils.getProductListForAdmin(conn, itemPerPage, currentPage);
					
					if (productList != null && productList.size()==0) {
						currentPage -= 1;
						productList = db_crud_utils.ProductUtils.getProductListForAdmin(conn, itemPerPage, currentPage);
					}
					// set session
					session.setAttribute("currentPage", currentPage);
					session.setAttribute("itemCount", itemCount);
					session.setAttribute("itemPerPage", itemPerPage);
					session.setAttribute("totalPage", totalPage);
					
					String productString = new Gson().toJson(productList);
					
					Map<String, Integer> pageInfoList = new HashMap<>();
					pageInfoList.put("currentPage", currentPage);
					pageInfoList.put("itemCount", itemCount);
					pageInfoList.put("itemPerPage", itemPerPage);
					pageInfoList.put("totalPage", totalPage);
					String pageString = new Gson().toJson(pageInfoList);
					
					List<String> finalList = new ArrayList<>();
					finalList.add(pageString);
					finalList.add(productString);
					
					
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					
					resp.getWriter().write(new Gson().toJson(finalList));
				}
			}
		}
		//catch (SQLException e) {
			//System.out.println("ajax product delete, code: "+e.getErrorCode());
		//}
		catch (Exception e) {
			System.out.println("ajax product delete, mess: "+e.getMessage());
		}
	}
}
