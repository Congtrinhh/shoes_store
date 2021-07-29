package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns = {"/ajax-cart"})
public class Cart extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String> paramsEnum= req.getParameterNames();
		List<String> paramsList = Collections.list(paramsEnum); 
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String errorMessage = null;
		
		for (int i=0; i<(paramsList.size()/4); i++) {
			int id = Integer.parseInt(req.getParameter(paramsList.get(i*4)));
			String color = req.getParameter(paramsList.get(i*4 +1));
			int size = Integer.parseInt(req.getParameter(paramsList.get(i*4 +2)));
			int quantity = Integer.parseInt(req.getParameter(paramsList.get(i*4 +3)));
			System.out.println("quantity: " + quantity);
			int inStock = 0;
			if ( (inStock= db_cart_utils.CartDB.getProductInStockQuantity(conn, id, color, size)) < quantity ) {
				errorMessage = "Sản phẩm thứ "+(i+1) + " trong danh sách không đủ cho bạn (còn " + inStock + ")"; 
				break;
			}
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String json = new Gson().toJson(errorMessage);
		resp.getWriter().write(json);
	}
}
