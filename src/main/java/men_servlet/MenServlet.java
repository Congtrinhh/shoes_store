package men_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import homepage_servlet.ProductGetter;

@WebServlet(urlPatterns = {"/men"})
public class MenServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy list sản phẩm theo một tiêu chí mặc định nào đó
		// có thể sử dụng lại class ProductGetter trong home_servlet để hứng dữ liệu từ DB
		
		// phân trang
		// bây giờ, suy nghĩ phần lọc sp ...
		
		// mình sẽ tách cách lọc sp thành các phần.
		/*
		 * 1. tiêu chí mức 1: brand (hãng): có chức năng quan trọng nhất bộ lọc: 
		 * brand: all, nike, adidas, bitis,..
		 * để biết brand hiện tại là gì, ta lưu nó vào request với giá trị mặc định là all
		 * từ brand sẽ lấy ra tất cả sp thuộc brand đó
		 * khi user vào /men với GET method, brand mặc định sẽ là 'all',
		 * đồng thời lấy ra số sp của brand để phân trang (eg: 1 trang có 8 sp, tổng sp là 16, vậy ta được 2 trang)
		 * để biết trang cần lấy là trang nào, ta lưu nó vào request với giá trị mặc định là 1
		 * trên giao diện có phần chọn brand, ngay khi chọn vào 1 brand, phương thức POST sẽ 
		 * được thực thi trên /men, với 1 parameter là brand được chọn, sau đó load lại sp
		 * và forward lại về trang danh sách sp vừa rồi (lấy số lượng sp để phân trang tương tự)
		 * hàm phân trang sẽ được viết sau (trong package utils)
		 * lấy số bản ghi theo vị trí từ DB dựa theo mệnh đề LIMIT+OFFSET
		 * 
		 * 
		 * 2. tiêu chí mức 2: ưu tiên(priority): độ quan trọng đứng ngay sau brand
		 * priority: giá từ cao đến thấp, giá từ thấp đến cao, mới nhất
		 * tương tự, priority được lưu như 1 thuộc tính trong request với giá trị mặc định là 'mới nhất'
		 * khi đang ở một brand nhất định, khi ngừi dùng click vào phần giao diện
		 * chọn tiêu chí và chọn vào 1 tiêu chí nhất định, ajax sẽ được gọi để trả về
		 * trang đầu tiên với tiêu chí đã chọn đó (vẫn lấy ra tổng số lượng sp của tiêu chí đó
		 * để phân trang như bth), từ trang thứ 2 trở đi, nó sẽ là request GET đến /men (request mang theo 
		 * brand + trang cần lấy)
		 * 
		 * 
		 * 3. tiêu chí mức 3: giá (người dùng nhập trực tiệp giá)
		 * tương tự 2 phần trên, tuy nhiên không có giá trị mặc định cho đến khi người dùng
		 * nhập.
		 * 
		 * hiểu đơn giản là các tiêu chí lọc sẽ được lưu thành các thuộc tính trong request,
		 * khi 1 tiêu chí lọc nào đó trống, thì ta không dùng nó trong câu lệnh select
		 * eg: ta có 3 tiêu chí lọc là brand, priority và giá, câu lệnh select sẽ như sau:
		 * select * from PRODUCT where (price between ... and ...) and (brand = ...) 
		 * order by price desc limit 8;->lấy ra 8 bản ghi đầu tiên trong cụm dữ liệu query được.
		 * 
		 * Các hàm lọc sẽ được viết trong class của package db_men_utils
		 * Hàm gọi AJAX sẽ được viết trong file main.js với method GET, servlet tiếp nhận
		 * request GET này sẽ được tạo trong 1 package khác có nhiệm vụ trả về trang đầu tiên 
		 * của 1 brand nào đó theo tiêu chí đã chọn, file main.js nhận được dữ liệu trả về, 
		 * thực thi hàm parse DL và hiển thị lên giao diện.
		 */
		
		// hàm này có tác dụng set các tiêu chí mặc định vào session
		// và lấy ra trang đầu tiên của list sản phẩm theo tiêu chí mặc định
		HttpSession session = req.getSession();
		
		int brandOption = 1; // all
		int priorityOption = 1; // mới nhất
		int fromRangeOption = 0; // giá từ 0
		int toRangeOption = 99999999; // đến 99999999 dollars
		int pageNo = 1; // trang này đương nhiên là trang đầu
					
		session.setAttribute("brandOption", brandOption);
		session.setAttribute("priorityOption", priorityOption);
		session.setAttribute("fromRangeOption", fromRangeOption);
		session.setAttribute("toRangeOption", toRangeOption);
		session.setAttribute("currentPage", pageNo);
		
		Connection conn = common_utils.MyUtils.getStoredConnection(req);
		
		try {
			ArrayList<ProductGetter> productList = db_men_utils.MenQuery.queryProduct(conn, brandOption, priorityOption, fromRangeOption, toRangeOption, pageNo);
			
			if (productList!=null) {
				int totalProducts = db_men_utils.MenQuery.countTotalProducts(conn, brandOption, priorityOption, fromRangeOption, toRangeOption);
				session.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts));
								
				req.setAttribute("currentPage", pageNo); // 2 cái này dành cho phân trang.
				req.setAttribute("totalPages", db_men_utils.MenQuery.calculateTotalPages(totalProducts));
				
				req.setAttribute("productList", productList);
				
				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/men/men.jsp");
				dispatcher.forward(req, resp);
			}
			else {
				System.out.println("Khogn co san pham");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String brandOptionStr = req.getParameter("brand");
		String priorityOptionStr = req.getParameter("priority");
		String fromRangeOptionStr = req.getParameter("from-range");
		String toRangeOptionStr = req.getParameter("to-range");
		
		int brandOption = 0;
		int priorityOption = 0;
		int fromRangeOption = 0;
		int toRangeOption = 0;
				
		try {
			brandOption = Integer.parseInt(brandOptionStr);
			priorityOption = Integer.parseInt(priorityOptionStr);
			fromRangeOption = Integer.parseInt(fromRangeOptionStr);
			toRangeOption = Integer.parseInt(toRangeOptionStr);
			
			
			
		}catch(Exception e) {
			System.out.println("Loi: "+e.getMessage());
		}
		
	
		if (toRangeOption==0) {
			toRangeOption = 99999999;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("brandOption", brandOption);
		session.setAttribute("priorityOption", priorityOption);
		session.setAttribute("fromRangeOption", fromRangeOption);
		session.setAttribute("toRangeOption", toRangeOption);
		
		System.out.println("men - post - done");
		System.out.println("brand: "+brandOption);
		System.out.println("priority: "+priorityOption);
		System.out.println("from: "+fromRangeOption);
		System.out.println("to: "+toRangeOption);
		doGet(req, resp);
	}
}
