package ajax_call;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.protobuf.TextFormat.ParseException;

import entities.Admin;
import entities.Product;


@MultipartConfig(
		maxFileSize = 1024*1024*16*10,
		maxRequestSize = 1024*1024*16*10
)
@WebServlet(
		urlPatterns = {"/ajax-product-update"},
		initParams = {
				@WebInitParam(name="contentType", value="application/json"),
				@WebInitParam(name="id", value="-1"),
				@WebInitParam(name="name", value="chưa có"),
				@WebInitParam(name="slug", value="chưa có"),
				@WebInitParam(name="price", value="0.00"),
				@WebInitParam(name="brandId", value="-1"),
				@WebInitParam(name="categoryId", value="-1"),
				@WebInitParam(name="description", value="chưa có"),
		}
)

public class ProductUpdate extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * nhận request 
		 * -> update thông tin cơ bản (cateid, brandid,..) + admin + updated time
		 * -> update ảnh (thêm + xóa) (gom xóa param để đó)
		 * -> trả về resp là trang hiện tại để js sau đó load lại trang
		 * -> kết thúc
		 */
		
		String errorMessage = null;
		try {
			Enumeration<String> enumer = req.getParameterNames();
			List<String> paramNameList = Collections.list(enumer);
			int id = Integer.parseInt(req.getParameter(paramNameList.get(0)));
			String name = req.getParameter(paramNameList.get(1));
			String slug = req.getParameter(paramNameList.get(2));
			BigDecimal price = new BigDecimal(req.getParameter(paramNameList.get(3)));
			int brandId = Integer.parseInt(req.getParameter(paramNameList.get(4)));
			int categoryId=Integer.parseInt(req.getParameter(paramNameList.get(5)));
			String description = req.getParameter(paramNameList.get(6));
			
			if (!slug.startsWith("/")) {
				slug = "/" +slug;
			}
			
			System.out.println("Number of params: "+paramNameList.size());
			// update thông tin cơ bản 
			int adminId = -1;
			adminId = ((Admin)req.getSession().getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION)).getAdmin_id();
			if (adminId == -1) {
				return;
			}
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(req.getServletContext().getInitParameter("dateFormat"));
			String nowString = sdf.format(now);
		
			
			
			Product product = new Product(id, adminId, categoryId, slug, name, brandId, price, description, nowString); 
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			
			int row = db_crud_utils.ProductUtils.updateProduct(conn, product);
			if (row<1) {
				throw new SQLException("Lỗi update productz");
			}
			
			// thêm ảnh
			for (Part p : req.getParts()) {
				
				if ( p.getContentType()!= null) {
					InputStream iStream = p.getInputStream();
					if (iStream.available() > 0 ) {
						System.out.println("avai" +iStream.available());
						System.out.println("input stream: "+iStream);
						db_crud_utils.ProductUtils.insertImage(conn, id, iStream);
					}
					iStream.close();
				}
				
			}
			// xóa ảnh...
			for (int i=7; i<paramNameList.size(); i++) {
				int imageId = Integer.parseInt(req.getParameter(paramNameList.get(i)));
				int rs  =db_crud_utils.ProductUtils.deleteImage(conn, imageId);
				if (rs<1) {
					throw new SQLException("lỗi xóa image");
				}
			}
			
			
			// trả về địa chỉ trang để js load lại
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType(getInitParameter("contentType"));
			
			String currentPageLink = req.getServletPath()+ "/product-update" + "/" + id;
			resp.getWriter().write(new Gson().toJson(currentPageLink));
		}
		catch (ParseException e) {
			System.out.println("parse line: "+ e.getLine());
		}
		catch(Exception e) {
			errorMessage = "Có lỗi, có thể do slug trùng";
			System.out.println("error: "+e.getMessage());
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType(getInitParameter("contentType"));
			
			resp.getWriter().write(new Gson().toJson(errorMessage));
		}
		
	}

}
