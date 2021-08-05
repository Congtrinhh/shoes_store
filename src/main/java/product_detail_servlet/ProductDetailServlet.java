package product_detail_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns = {"/men/*", "/women/*"})
public class ProductDetailServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pr_slug = req.getPathInfo();
		
		if (pr_slug != null) {
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			try {
				ProductGetter product = db_product_detail_utils.ProductDetailSQL.getProduct(pr_slug, conn);
				
				if (product != null) {
					req.setAttribute("product", product);
				}
				else {
					// forward sang trang không tìm thấy sp.
				}
				
				
				List<ImageGetter> imagesList = db_product_detail_utils.ProductDetailSQL.getImagesOfProduct(conn, pr_slug);
				if (imagesList!=null) {
					req.setAttribute("imagesList", imagesList);
				}
				else {
					
				}
				
				// gửi về dưới dạng string
				List<SpecificProductInfo> specificProductInfoList = db_product_detail_utils.ProductDetailSQL.getSpecificProductsList(conn, pr_slug);				
				String json = new Gson().toJson(specificProductInfoList);
				req.setAttribute("specificProductInfoList", json);
				
				req.setAttribute("categorySlug", req.getServletPath());
				req.setAttribute("productLineSlug", pr_slug);
							
				
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/product_detail/productDetail.jsp");
				dispatcher.forward(req, resp);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
