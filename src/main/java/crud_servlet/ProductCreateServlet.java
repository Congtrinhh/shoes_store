package crud_servlet;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import common_utils.MyUtils;
import crud.entity.product_create.Brand;
import crud.entity.product_create.Category;
import entities.Product;

// multipart...
@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*2,
		maxRequestSize = 1024*1024*5*5
)
@WebServlet(urlPatterns = {"/create-product"})
public class ProductCreateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy thông tin category, brand ráp vào file jsp
		Connection conn =null;
		try {
			conn = common_utils.MyUtils.getStoredConnection(req);
			
			ArrayList<Brand> brandsList = db_crud_utils.ProductUtils.getBrandsList(conn);
			if (brandsList!=null) {
				req.setAttribute("brandsList", brandsList);
			}
			
			ArrayList<Category> categoriesList = db_crud_utils.ProductUtils.getCategoriesList(conn);
			if (categoriesList!=null) {
				req.setAttribute("categoriesList", categoriesList);
			}
			
		}
		catch(Exception e) {
			System.out.println("Lỗi khi lấy brand và category [GET] create product servlet, "+e.getMessage());
		}
		finally {
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/productCreateView.jsp");
			try {
				dispatcher.forward(req, resp);
			}
			catch(Exception e) {
				System.out.println("Lỗi khi forward: "+e.getMessage());
			}
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get dữ liệu từ request...
		String name = req.getParameter("name");
		String slug = req.getParameter("slug");
		String priceStr = req.getParameter("price");
		String brandStr = req.getParameter("brand");
		String categoryStr = req.getParameter("category");
		
		//xử lí ảnhs
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		// cần tạo product trước khi tạo anhs.s
		
		for (Part p : req.getParts()) {
				if (p.getContentType() != null) { // kiểm tra nếu đây là file
					InputStream iStream = p.getInputStream();
					
					// insert các file này vào image db
					try {
						
					}
					catch(Exception e) {
						System.out.println("Lỗi khi inset image vào image table [POST] product create servlet, "+ e.getMessage());
						return; // return về để không cần làm các bước tiếp theo
					}
					
				}			
		}
		
	}
}
