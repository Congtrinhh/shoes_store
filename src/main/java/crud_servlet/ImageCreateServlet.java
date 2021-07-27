package crud_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Image;

@WebServlet(urlPatterns = {"/create-image"})
public class ImageCreateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/imageCreateView.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int admin_id = Integer.parseInt(req.getParameter("admin_id"));
		int product_line_id = Integer.parseInt(req.getParameter("product_line_id"));
		String img_name = req.getParameter("img_name");
		String img_location = req.getParameter("img_location");
		
		Image img = new Image(admin_id, product_line_id, img_name, img_location);
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		String message = null;
		try {
			db_crud_utils.ImageUtils.create(img, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/imageCreateView.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			message=e.getMessage();
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/imageCreateView.jsp");
			dispatcher.forward(req, resp);
		}
		
		message="Thêm 1 ảnh";
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/crud/imageCreateView.jsp");
		dispatcher.forward(req, resp);
	}
}
