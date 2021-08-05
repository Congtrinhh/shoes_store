package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.Constants;

import com.google.gson.Gson;

import entities.SpecificProduct;

@WebServlet(urlPatterns = {"/specific-product-read"})
public class SpecificProductReadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		try {
			List<SpecificProduct> specificProductList = db_crud_utils.SpecificProductUtils.getSpecificProductList(conn, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN, 1);
			int itemCount = -1;
			
			if (specificProductList != null) {
				req.setAttribute("specificProductList", specificProductList);
				itemCount = db_crud_utils.SpecificProductUtils.getTotalProduct(conn);
			}
			else {
				itemCount = 0;
			}
			
			
			HttpSession session = req.getSession();
			session.setAttribute("currentPage", 1);
			session.setAttribute("itemCount", itemCount);
			session.setAttribute("itemPerPage", constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN);
			session.setAttribute("totalPage", common_utils.MyUtils.calculateTotalPage(itemCount, constants.SystemConstants.PRODUCTS_PER_PAGE_ADMIN));
			
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/specificProductRead.jsp");
			dispatcher.forward(req, resp);
		} 
		catch (SQLException e) {
			System.out.println("s product read, code: "+e.getErrorCode());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("s product read, mes: "+e.getMessage());
		}
		
	}
	
}
