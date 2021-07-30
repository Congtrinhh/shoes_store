package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import checkout_servlet.AddressEntity;
import entities.User;

@WebServlet(urlPatterns = {"/ajax-checkout"})
public class Checkout extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Hàm này: 
		 * kiểm tra xem các sp trong db có đủ đáp ứng không
		 * - nếu không, hiển thị thông báo lỗi (tương tự bên cart ajax)
		 * - nếu có, giảm số lượng sp trong db tương ứng số lượng sp khách mua và
		 * tạo ra đơn hàng và
		 * tạo ra những product_in_cart tương ứng với sp khách mua.
		 */
		
		// lấy dữ liệu các sp và so sánh với số lượng trong kho
		Enumeration<String> paramNamesEnum = req.getParameterNames();
		List<String> paramNamesList = Collections.list(paramNamesEnum);
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String errorMessage = null;
		
		// kiểm tra sp trong kho có đủ đáp ứng, yêu cầu tất cả sp trong giỏ phải pass, nếu không, return
		for (int i=0; i<((paramNamesList.size()-7)/4); i++) { // trừ đi 7 params cuối của địa chỉ nhận hàng
			int id = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 0)));
			String colorCode = req.getParameter(paramNamesList.get(i*4 + 1));
			int size = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 2)));
			int quantity = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 3)));
			
			// work with db here and set errormessage
			// if error, set json and return immediately
			int inStockQuantity = db_cart_utils.CartDB.getProductInStockQuantity(conn, id, colorCode, size); // dùng luôn hàm của cart db
			if (inStockQuantity < quantity) {
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				
				errorMessage = "Số lượng sản phẩm số " + (i+1) + " hiện tại không đủ cho bạn (còn " + inStockQuantity + " )";
				
				String json = new Gson().toJson(errorMessage);
				resp.getWriter().write(json);
				return;
			}
		}
		
		// if no error, continue..
		
		// cập nhật lại số sp trong kho cho mỗi sp (số lượng sẽ giảm)
		for (int i=0; i<((paramNamesList.size()-7)/4); i++) {
			int id = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 0)));
			String colorCode = req.getParameter(paramNamesList.get(i*4 + 1));
			int size = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 2)));
			int quantity = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 3)));
			
			int row = db_checkout_utils.CheckoutDB.decreaseInStockQuantity(conn, id, colorCode, size, quantity);
			//System.out.println("(update quantity)1 -> 1 row effected; 0 -> failed: "+row);
		}
		
		// tạo đơn hàng mới
		HttpSession session = req.getSession();
		User logedinUser = (User)session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
		
		String specificAddress = req.getParameter("specific-address");
		int wardId = Integer.parseInt(req.getParameter("ward"));
	
		db_checkout_utils.CheckoutDB.createOrder(conn, logedinUser.getUser_id(), wardId, specificAddress);
		//System.out.println("Tạo 1 Order");
		
		int highestOrderId = db_checkout_utils.CheckoutDB.getHighestOrderId(conn);
		// tạo các sản phẩm trong đơn hàng 
		for (int i=0; i<((paramNamesList.size()-7)/4); i++) {
			int id = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 0)));
			String colorCode = req.getParameter(paramNamesList.get(i*4 + 1));
			int size = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 2)));
			int quantity = Integer.parseInt(req.getParameter(paramNamesList.get(i*4 + 3)));
			
			int specificProductId = db_checkout_utils.CheckoutDB.getSpecificProductId(conn, id, colorCode, size);
			db_checkout_utils.CheckoutDB.createProductInOrder(conn, highestOrderId, specificProductId, quantity);
			//System.out.println("Tạo 1 product in order");
		}
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		String json = new Gson().toJson(errorMessage);
		resp.getWriter().write(json);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Hàm này:
		 * update DOM cho địa chỉ (tỉnh/tp, huyện, xã) 
		 * lưu ý rằng ds tỉnh/tp cần được lấy ra từ request thường trước
		 */
		String type = req.getParameter("needed-type"); // loại địa chỉ cần lấy
		if (type.equals("district")) {
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			int id = Integer.parseInt(req.getParameter("id"));
			
			List<AddressEntity> districtsList = db_checkout_utils.CheckoutDB.getAllDistricts(conn, id);
			if (districtsList!=null) {
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				String json = new Gson().toJson(districtsList);
				resp.getWriter().write(json);
			}
			else {
				System.out.println("khong co district");
			}
		}
		else if (type.equals("ward")) {
			System.out.println("trong if ward");
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			int id = Integer.parseInt(req.getParameter("id"));
			
			List<AddressEntity> wardsList = db_checkout_utils.CheckoutDB.getAllWards(conn, id);
			if (wardsList!=null) {
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				String json = new Gson().toJson(wardsList);
				resp.getWriter().write(json);
			}
			else {
				System.out.println("khong co ward");
			}
		}
		
	}
}
