package crud_servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entities.Color;
import entities.Product;
import entities.Size;

@WebServlet(urlPatterns = {"/specific-product-create"})
public class SpecificProductCreateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * lấy ra các trường cần thiết để hiển thị ra file jsp:
		 * . tên dòng sp (select)
		 * . tên màu (select)
		 * . số size (select)
		 * . giá
		 * . số lượng ban đầu
		 */
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		List<Product> productsList = db_crud_utils.SpecificProductUtils.getProductsList(conn);
		List<Color> colorsList = db_crud_utils.SpecificProductUtils.getColorsList(conn);
		List<Size> sizesList = db_crud_utils.SpecificProductUtils.getSizesList(conn);
		
		if (productsList!=null) {
			req.setAttribute("productsList", productsList);
		}
		if (colorsList!=null) {
			req.setAttribute("colorsList", colorsList);
		}
		if (sizesList!=null) {
			req.setAttribute("sizesList", sizesList);
		}
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/specificProductCreate.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get params
		int productLineId =-1;
		int colorId =-1;
		int sizeId =-1;
		String priceStr = req.getParameter("price");
		BigDecimal price = new BigDecimal(-1);
		int quantity = -1;
		
		String errorMessage = null;
		try {
			productLineId = Integer.parseInt(req.getParameter("product-line-id"));
			colorId = Integer.parseInt(req.getParameter("color-id"));
			sizeId = Integer.parseInt(req.getParameter("size-id"));
			price = new BigDecimal(priceStr);
			quantity = Integer.parseInt(req.getParameter("quantity"));
			
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			db_crud_utils.SpecificProductUtils.createSpecificProduct(conn, productLineId, colorId, sizeId, price, quantity);
		}
		catch (SQLException e) {
			if (e.getErrorCode()==1062) {
				errorMessage = "Sản phẩm này đã tồn tại trong database";
			}
			else {
				errorMessage ="Đã có lỗi gì đó khi thao tác với database";
			}
		}
		catch (Exception e) {
			errorMessage = "Đã có lỗi gì đó";
		}
		
		resp.setCharacterEncoding("UTF-8");		
		resp.setContentType("application/json");
		
		
		if (errorMessage!=null) {
			String json = new Gson().toJson(errorMessage);
			resp.getWriter().write(json);			
		}
		else {
			Map<String, String> forwardMessage = new HashMap<>();
			forwardMessage.put("redirect", req.getContextPath()+"/cart");
			String json = new Gson().toJson(forwardMessage);
			resp.getWriter().write(json);
		}
	}
	
}
