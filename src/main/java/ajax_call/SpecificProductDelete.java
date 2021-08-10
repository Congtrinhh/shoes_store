package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

import entities.SpecificProduct;

@WebServlet(urlPatterns = {"/ajax-specific-product-delete"})
public class SpecificProductDelete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * lấy param ừ request (specific product id)
		 * -> xóa item tương ứng. nếu
		 * + xóa thất bại -> gửi response thông báo lỗi về js
		 * + xóa thành công:
		 * -> từ session, lấy ra itemCount, itemPerPage
		 * -> itemCount = itemCount - 1,..
		 * -> lấy ra list item của trang hiện tại (trang hiện tại là trang bao nhiêu còn tùy)
		 * -> set page attr trong session
		 * -> trả về page attr + list item
		 */
		try {
			String idStr = req.getParameter("id");
			int id = -1;
			if ( idStr!=null ) {
				id = Integer.parseInt(idStr);
				
				HttpSession session =req.getSession();
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				int row = db_crud_utils.SpecificProductUtils.deleteItem(conn, id);
				
				if ( row > 0 ) { // thanh cong
					int itemCount = (int)session.getAttribute("itemCount");
					int itemPerPage = (int)session.getAttribute("itemPerPage");
					int currentPage = (int)session.getAttribute("currentPage");
					int totalPage = (int)session.getAttribute("totalPage");
					
					itemCount = itemCount -1; // vì vừa xóa 1 item mà
					
					if ( itemCount % itemPerPage == 0 ) { // tức là sau khi xóa item thì 1 trang sẽ biến mất (item vừa xóa là item cuối của trang đó)
						totalPage = totalPage -1; // tổng số trang giảm 1
						
						if ( totalPage < currentPage ) { // trang hiện tại xuống cho đúng
							currentPage = currentPage -1;
						}
					}
					
					List<SpecificProduct> specificProductList = db_crud_utils.SpecificProductUtils.getSpecificProductList(conn, itemPerPage, currentPage);
					
					session.setAttribute("itemCount", itemCount);
					session.setAttribute("itemPerPage", itemPerPage);
					session.setAttribute("currentPage", currentPage);
					session.setAttribute("totalPage", totalPage);
					
					// tra data ve js
					Map<String, Integer> pageInfo = new HashMap<>();
					pageInfo.put("itemCount", itemCount);
					pageInfo.put("itemPerPage", itemPerPage);
					pageInfo.put("currentPage", currentPage);
					pageInfo.put("totalPage", totalPage);
					
					String pageString = new Gson().toJson(pageInfo);
					String itemString = new Gson().toJson(specificProductList);
					
					List<String> finalList = new ArrayList<>();
					finalList.add(pageString);
					finalList.add(itemString);
					
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					
					resp.getWriter().write(new Gson().toJson(finalList));
				}
				else { // that bai
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					
					resp.getWriter().write("Đã có lỗi, có vẻ item này có ràng buộc khóa ngoài với table khác");
				}
				
				
			}
			
		}
		catch (SQLException e) {
			System.out.println("delete s product ajax, code" + e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("delete s product ajax, mes" + e.getMessage());
		}
	}
}
