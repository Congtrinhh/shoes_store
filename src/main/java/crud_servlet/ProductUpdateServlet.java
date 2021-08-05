package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.entity.product_create.Brand;
import crud.entity.product_create.Category;
import homepage_servlet.ProductGetter;
import product_detail_servlet.ImageGetter;

@WebServlet(urlPatterns = {"/product-update/*"})
public class ProductUpdateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// nhận id qua pathInfo và triển
	// hiển thị thông tin sp kèm tất cả ảnh
	// có thể thêm/xóa ảnh (không có sửa)
	// cần thêm 1 servlet ajax nữa
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String idStr = req.getPathInfo();
			int id = -1;
			if (idStr!=null) {
				id = Integer.parseInt(idStr.replace("/", ""));
				
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				List<Brand> brandList = db_crud_utils.ProductUtils.getBrandList(conn);
				if (brandList!=null) {
					req.setAttribute("brandList", brandList);
				}
				
				List<Category> categoryList = db_crud_utils.ProductUtils.getCategoryList(conn);
				if (categoryList!=null) {
					req.setAttribute("categoryList", categoryList);
				}
				
				ProductGetter product = db_crud_utils.ProductUtils.getProduct(conn, id);
				if (product!=null) {
					req.setAttribute("product", product);
					
					List<ImageGetter> imageList = db_product_detail_utils.ProductDetailSQL.getImagesOfProduct(conn, product.getPr_slug());
					if (imageList!=null) {
						req.setAttribute("imageList", imageList);
					}
				}
				
				
				
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productUpdate.jsp");				
				dispatcher.forward(req, resp);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
