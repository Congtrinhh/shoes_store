package ajax_first_step;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/ajax/products"})
public class GetProducts extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// có thể Connection to DB phải được lấy trực tiếp, thay vì lấy từ request.
		Connection conn = null;
		try {
			conn = connection_utils.ConnectionUtils.getConnection();
			
			ArrayList<ProductGetter> productList = new ArrayList<>(); // 
			
			String json = null;
			
			json = new Gson().toJson(productList);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			resp.getWriter().write(json);
			System.out.println("ajax thanh cong");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
