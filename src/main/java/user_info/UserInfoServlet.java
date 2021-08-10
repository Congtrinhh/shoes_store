package user_info;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.District;
import entities.Province;
import entities.User;
import entities.Ward;

@WebServlet(urlPatterns = {"/info", "/user-info"})
public class UserInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * lấy ra các thông tin cần thiết cả user
		 * forward sang jsp
		 */
	
		try {
			HttpSession session = req.getSession();
			User logedInUser = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION); 
			if ( logedInUser != null ) {
				req.setAttribute("user", logedInUser);
				
				int wardId =-1;
				wardId = logedInUser.getWard_id();
				//System.out.println("warid id: "+wardId);
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				if ( wardId!=0 && wardId != -1 ) {
					
					Ward ward = db_crud_utils.UserUtils.getWard(conn, wardId);
					Province province = db_crud_utils.UserUtils.getProvince(conn, ward.getProvince_id());
					District district = db_crud_utils.UserUtils.getDistrict(conn, ward.getDistrict_id());
					
					
					req.setAttribute("ward", ward);
					req.setAttribute("district", district);
					req.setAttribute("province", province);
				}
				// có ward hay không vẫn cần list province để gọi ajax chọn tỉnh/tp, xã/phường,..
				List<Province> provinceList = db_crud_utils.UserUtils.getProvinceList(conn);
				req.setAttribute("provinceList", provinceList);
			}
			
			req.getSession().setAttribute( "userNavTabIndicator" , req.getServletContext().getContextPath() + "/info" );
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_info/userInfo.jsp");
			dispatcher.forward(req, resp);
		}
		catch (Exception e) {
			System.out.println("user info get, mess: "+e.getMessage());
		}
		
	}
}
