package crud_servlet;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import common_utils.MyUtils;
import crud.entity.product_create.Brand;
import crud.entity.product_create.Category;
import entities.Admin;
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
		String description = req.getParameter("description");
		
		int category = 0;
		BigDecimal price = null;
		
		String errorMessage = null;
		
		try {
			price = new BigDecimal(priceStr);
			category = Integer.parseInt(categoryStr);
		}
		catch(Exception e) {
			System.out.println("error parsing bigdecimal");
			errorMessage = "Giá sản phẩm không hợp lệ";
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			
			String json = new Gson().toJson(errorMessage);
			resp.getWriter().write(json);
			return;
		}
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		// cần tạo product trước khi tạo anhs.s
		HttpSession session = req.getSession();
		Admin logedinAdmin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		int adminId = db_crud_utils.AdminUtils.findAdminIdByName(conn, logedinAdmin.getAd_login_name());
		
		Product product = new Product(adminId, category, slug, name, brandStr, price, description);
		
		try {
			db_crud_utils.ProductUtils.createProduct(conn, product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorMessage = "Lỗi insert sản phẩm vào table, thử đổi tên slug";

			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			
			String json = new Gson().toJson(errorMessage);
			resp.getWriter().write(json);
			return;
		}
		
		
		int highestProductId = db_crud_utils.ProductUtils.getHighestId(conn);
		
		for (Part p : req.getParts()) {
			if (p.getContentType() != null) { // kiểm tra nếu đây là file				
				// insert các file này vào image db
				String sql = "insert into image(product_line_id, img_file,admin_id)\r\n"
						+ "values (?, ?, ?);";
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					InputStream iStream = p.getInputStream();
					stm.setInt(1, highestProductId);
					if (iStream!=null) {
						stm.setBlob(2, iStream);							
					}
					stm.setInt(3, adminId);
					int row = stm.executeUpdate();
					if (row>0) {
						System.out.println("them "+row+" image vao db");
					}
				}
				catch(Exception e) {
					System.out.println("Lỗi khi inset image vào image table [POST] product create servlet, "+ e.getMessage());
					errorMessage = "Lỗi khi insert ảnh vào table";
					
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					
					String json = new Gson().toJson(errorMessage);
					resp.getWriter().write(json);
				}
			}			
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		System.out.println("errorm: "+errorMessage);
		String json = new Gson().toJson(errorMessage);
		resp.getWriter().write(json);
		
	}
}
