package ajax_call;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.SpecificProduct;

@WebServlet(urlPatterns = {"/ajax-specific-product-update"})
public class SpecificProductUpdate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			int id = Integer.parseInt( req.getParameter("id") );
			int productLineId = Integer.parseInt( req.getParameter("product-line-id") );
			int colorId = Integer.parseInt( req.getParameter("color-id") );
			int sizeId = Integer.parseInt( req.getParameter("size-id") );
			BigDecimal price = new BigDecimal( req.getParameter("price") );
			int quantity = Integer.parseInt( req.getParameter("quantity") );
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			SpecificProduct product = new SpecificProduct(id, productLineId, colorId, sizeId, price, quantity);
			
			System.out.format("%d - %d - %d", productLineId, colorId, sizeId);
			int row = db_crud_utils.SpecificProductUtils.updateItem(conn, product);
			if ( row > 0 ) {
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html");
				
				resp.getWriter().write( req.getContextPath() + "/specific-product-update" + "/" + id );
			}
			else {
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html");
				
				resp.getWriter().write( "Có lỗi xảy ra, thử kiểm tra input, db, etc" );
			}
	
			
		}
		catch (SQLException e) {
			System.out.println("ajax update s product, code: " + e.getErrorCode());
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			
			resp.getWriter().write( "Có lỗi xảy ra, thử kiểm tra input, db, etc" );
		}
		catch (Exception e) {
			System.out.println("ajax update s product, mess: " + e.getMessage());
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			
			resp.getWriter().write( "Có lỗi xảy ra, thử kiểm tra input, db, etc" );	
		}
	}
}
