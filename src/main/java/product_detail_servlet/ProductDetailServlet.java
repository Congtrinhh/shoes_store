package product_detail_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/men/*", "/women/*"})
public class ProductDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pr_slug = req.getPathInfo();
		
		if (pr_slug != null) {
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			try {
				ProductGetter product = product_detail_utils.ProductDetailSQL.getProduct(pr_slug, conn);
				
				if (product != null) {
					req.setAttribute("product", product);
					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/product_detail/productDetail.jsp");
					dispatcher.forward(req, resp);
				}
				else {
					// forward sang trang không tìm thấy sp.
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
