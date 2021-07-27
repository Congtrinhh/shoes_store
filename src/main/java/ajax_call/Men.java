package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import constants.SystemConstants;
import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/ajax-men-products"})
public class Men extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Nhiệm vụ của hàm này là trả về list sản phẩm theo từng trang, cập nhật lại currentPage,..
		 *  
		 *  */
		
		String requestedPageStr = req.getParameter("requestedPage");
		
		int requestedPage = 1; // giá trị mặc định, tránh lỗi
		try {
			requestedPage = Integer.parseInt(requestedPageStr);
		}catch(Exception e) {
			System.out.println("lỗi parse requestedPage [GET] Men.java" + e.getMessage());
		}
		
		HttpSession session = req.getSession();
		int totalPages = (int) session.getAttribute("totalPages");
		if (requestedPage > totalPages || requestedPage<1) {
			System.out.println("Trang yêu cầu không hợp lệ [out of range]");
			return;
		}
		
		// các tiêu chí đã nằm trong session, vì đã được set mặc định tại method GET của /men servlet
		// tuy nhiên cần bắt lỗi tại case người dùng bấm nút lọc sản phẩm nhưng không điền 2 ô input giá
		int brandOption = 1; // all
		int priorityOption = 1; // mới nhất
		int fromRangeOption = 0; // giá từ 0
		int toRangeOption = 99999999; // đến 99999999 dollars
		int currentPage = 1; 
		
		try {
			brandOption = (int) session.getAttribute("brandOption");
			priorityOption = (int) session.getAttribute("priorityOption");
			currentPage = (int) session.getAttribute("currentPage"); // lấy ra trang đang đứng ngay trước khi thực hiện request GET này
			
			fromRangeOption = (int) session.getAttribute("fromRangeOption"); // 2 ông dễ bị lỗi để bên dưới
			toRangeOption = (int) session.getAttribute("toRangeOption");
		}
		catch(Exception e) {
			System.out.println("lỗi parse int [GET] Men.java" + e.getMessage());
		}
		
		if (currentPage==requestedPage) {
			System.out.println("Đang ở trang này rồi, bấm đi đâu nữa.");
			return;
		}
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		try {
			session.setAttribute("currentPage", requestedPage);
			
			ArrayList<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(req, conn, brandOption, priorityOption, fromRangeOption, toRangeOption, requestedPage);
			if (productList!=null) {
				session.setAttribute("productList", productList);
				
				String json = new Gson().toJson(productList);
								
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				
				resp.getWriter().write(json);
				System.out.println("Men jax [GET] thanh cong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhiệm vụ của hàm này là cập nhật tiêu chí và trả về trang đầu tiên của list sản phẩm mới
		 * theo tiêu chí đó 
		 * */
		
		
		String brandOptionStr = req.getParameter("brand");
		String priorityOptionStr = req.getParameter("priority");
		String fromRangeOptionStr = req.getParameter("from-range");
		String toRangeOptionStr = req.getParameter("to-range");
		
				
		System.out.println("1brand: "+brandOptionStr);
		System.out.println("1priority: "+priorityOptionStr);
		System.out.println("1from: "+fromRangeOptionStr);
		System.out.println("1to: "+toRangeOptionStr);
		
		// khởi tạo giá trị (cũng là giá trị dự phòng nếu có lỗi trong khối try -parse integer
		// nếu có bất kỳ lỗi gì, thì đã có giá trị mặc định hợp lệ
		int brandOption = 1;
		int priorityOption = 1;
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
		
		// thực hiện so sánh old và new tiêu chí, nếu khác -> cho phép tiếp tục, nếu giống -> return, kết thúc hàm
		
															
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		try {
			session.setAttribute("brandOptionOld", brandOption);
			session.setAttribute("priorityOptionOld", priorityOption);
			session.setAttribute("fromRangeOptionOld", fromRangeOption);
			session.setAttribute("toRangeOptionOld", toRangeOption);
			
			session.setAttribute("currentPage", 1); // luôn trả về trang đầu tiên, nên currentPage=1
			int totalProducts = db_men_utils.MenQuery.countTotalProducts(conn, brandOption, priorityOption, fromRangeOption, toRangeOption);
			session.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts));
			ArrayList<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(req, conn, brandOption, priorityOption, fromRangeOption, toRangeOption, 1);
			
			if (productList!=null) {
				
				System.out.println("togn sp: " + totalProducts);
				String json = new Gson().toJson(productList);
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				
				resp.getWriter().write(json);
				System.out.println("Thanh cong. gui ajax ve");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
