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

import entities.Color;
import entities.Product;
import entities.Size;
import entities.SpecificProduct;

@WebServlet(urlPatterns = {"/specific-product-update/*"})
public class SpecificProductUpdate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * lấy id từ path info
		 * render thông tin item tương ưng 
		 */
		
		try {
			String idStr = req.getPathInfo();
			if ( idStr!=null ) {
				idStr = idStr.replace("/", "");
				int id = Integer.parseInt(idStr);
				
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				SpecificProduct specificProduct = db_crud_utils.SpecificProductUtils.getItem(conn, id);
				
				if ( specificProduct!=null ) {
					
					req.setAttribute("product", specificProduct);
					
					List<Product> productList = db_crud_utils.SpecificProductUtils.getProductList(conn);
					List<Color> colorList = db_crud_utils.SpecificProductUtils.getColorList(conn);
					List<Size> sizeList = db_crud_utils.SpecificProductUtils.getSizeList(conn);
					
					if ( productList!=null ) {
						req.setAttribute("productList", productList);
					}
					if ( colorList!=null ) {
						req.setAttribute("colorList", colorList);
					}
					if ( sizeList!=null ) {
						req.setAttribute("sizeList", sizeList);
					}
					
					
					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/specificProductUpdate.jsp");
					dispatcher.forward(req, resp);
				}
				else { //qua trang không tìm thấy sp. 
					
				}
				
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
