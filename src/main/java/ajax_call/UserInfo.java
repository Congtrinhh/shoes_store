package ajax_call;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entities.District;
import entities.User;
import entities.Ward;

@WebServlet(urlPatterns = {"/ajax-info", "/ajax-user-info"})
public class UserInfo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* nhận 1 trong 2 param từ request ajax (district id | province id)
		 * -> tùy vào param, lấy ra district list hoặc ward list
		 * -> kiểm tra ngoại lệ (param="") trước khi lấy ra các list trên
		 * -> trả list về js ajax hoặc không trả về nếu list=null
		 */
		try {
			int provinceId = -1;
			int districtId = -1;
			String provinceIdStr = req.getParameter("province");
			String districtIdStr = req.getParameter("district");

			// ajax gửi province id để lấy district list
			if ( provinceIdStr!=null && provinceIdStr.length()>0 ) {
				provinceId = Integer.parseInt(provinceIdStr);
				if ( provinceId==0 ) {
					return;
				}
				
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				List<District> districtList = db_checkout_utils.CheckoutDB.getAllDistricts(conn, provinceId);
				if ( districtList!=null ) {
					resp.setCharacterEncoding("utf-8");
					resp.setContentType("application/json");
					resp.getWriter().write( new Gson().toJson(districtList) );
				}
			}
			else if ( districtIdStr!=null && districtIdStr.length()>0 ) {
				districtId = Integer.parseInt(districtIdStr);
				if ( districtId==0 ) {
					return;
				}
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				List<Ward> wardList = db_checkout_utils.CheckoutDB.getAllWards(conn, districtId);
				if ( wardList!=null ) {
					resp.setCharacterEncoding("utf-8");
					resp.setContentType("application/json");
					resp.getWriter().write( new Gson().toJson(wardList) );
				}
			}else {
				throw new Exception("Không có param từ ajax");
			}
			
		}
		catch (Exception e) {
			System.out.println("ajax get district provice user info, mes: " + e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* nhận tất cả param từ ajax js (bao gồm field đổi mật khẩu)
		 * -> kiểm tra trùng: user name, sđt, email
		 * 	+ nếu 1 trong 3 trùng, trả về ajax thông báo lỗi
		 * 	+ nếu không trường nào trùng, tiếp tục
		 * -> update các trường khác
		 * -> nếu cả 3 trường mật khẩu đều không rỗng -> kiểm tra mật khẩu đúng với mk trong db
		 * 	+ nếu không khớp, trả về ajax tb lỗi
		 * 	+ nếu khớp, đổi mk và tiếp tục
		 * -> trả về tb cập nhật tất cả thành công, dùng js reload trang
		 */
		
		try {
			HttpSession session = req.getSession();
			User logedInUser = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
			
			int userId = logedInUser.getUser_id();
			
			String userName = req.getParameter("user-name"); //*
			String fullName = req.getParameter("full-name");
			String phone = req.getParameter("phone-number"); //*
			String email = req.getParameter("email"); //*
			String address = req.getParameter("specific-address");
			
			String currentPassword = req.getParameter("current-password");
			String newPassword = req.getParameter("new-password");
			String confirmedPassword = req.getParameter("confirmed-password");
			
			String wardIdStr = req.getParameter("ward");
			int wardId = -1;
			
			if ( wardIdStr!=null ) {
				wardId = Integer.parseInt(wardIdStr);
			}
			
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			
			//System.out.format("old mk: %s, new mk: %s, cf mk: %s", currentPassword, newPassword, confirmedPassword);
			
			Connection conn = common_utils.MyUtils.getStoredConnection(req);
			
			String duplicatedErrorMessage = db_user_info_utils.UserInfoDB.getDuplicatedMessage(conn, userId, userName, phone, email);
			//System.out.println("errm: "+duplicatedErrorMessage);
			
			if ( duplicatedErrorMessage!=null ) {
				resp.getWriter().write(duplicatedErrorMessage);
				return;
			}
			
			boolean isNotNull = currentPassword!=null && newPassword!=null && confirmedPassword!=null;
			boolean isAllEmpty = currentPassword.length()==0 && newPassword.length()==0 && confirmedPassword.length()==0;
			
			// khi có ít nhất 1 trường không rỗng, thực hiện check mật khẩu
			if ( isNotNull && !isAllEmpty ) {
				// check mk
				if ( !db_user_info_utils.UserInfoDB.isPasswordTrue(conn, userId, currentPassword) ) {
					resp.getWriter().write("Mật khẩu cũ không chính xác");
					return;
				}
				// check confirm pass giong pass moi
				if ( !newPassword.equals(confirmedPassword) ) {
					resp.getWriter().write("Mật khẩu xác nhận không khớp mật khẩu mới");
					return;
				}
				
				// đến đây, cập nhật mọi thứ thôi =)
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(req.getServletContext().getInitParameter("dateFormat"));
				String nowStr = sdf.format(now);
				
				User userToUpdate = new User(userId ,fullName, userName, newPassword, email, phone, address, nowStr, wardId);
				int row = db_user_info_utils.UserInfoDB.updateUser(conn, userToUpdate);
				if ( row>0 ) {
					System.out.println("update user thanh cong");
					logedInUser.setWard_id(wardId);
					resp.getWriter().write("/");
				}
				else {
					throw new SQLException("Cập nhật user thất bại");
				}
			}
			// khi 3 trường mk không hề được nhập -> dùng mk cũ để update -> mk không đổi
			else if ( isNotNull && isAllEmpty ) {
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(req.getServletContext().getInitParameter("dateFormat"));
				String nowStr = sdf.format(now);
				
				User userToUpdate = new User(userId, fullName, userName, logedInUser.getU_password(), email, phone, address, nowStr, wardId);
				int row = db_user_info_utils.UserInfoDB.updateUser(conn, userToUpdate);
				if ( row>0 ) {
					System.out.println("update user thanh cong");
					logedInUser.setWard_id(wardId);
					resp.getWriter().write("/");
				}
				else {
					throw new SQLException("Cập nhật user thất bại");
				}
			}
			
		}
		catch (SQLException e) {
			System.out.println("post ajax user info update info, code: "+e.getErrorCode());
			resp.getWriter().write(e.getErrorCode());
		}
		catch (Exception e) {
			System.out.println("post ajax user info update info, mes: "+e.getMessage());
			resp.getWriter().write(e.getMessage());
		}
	}
}
