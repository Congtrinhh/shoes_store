package filters;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entities.Admin;
import entities.User;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// CÂU HỎI: liệu request có tồn tại đến khi tắt trình duyệt (kết thúc 1 phiên) hay không?
		// TRẢ LỜI: không, khi server nhận request và trả về response tức là request đã kết thúc
		HttpServletRequest req = (HttpServletRequest)request; 
		
		HttpSession session = req.getSession();
		
		// login_token attribute dùng để set class cho phần hiển thị html css phần login
		// nếu là 'admin' hoặc 'user', các nút login, register sẽ bị ẩn đi, thay vào đó
		// là avatar và tên người dùng(có thể là admin hoặc user)
		Admin adminInSession = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		if (adminInSession!=null) {
			session.setAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION, adminInSession);
			session.setAttribute(constants.SystemConstants.LOGIN_TOKEN, "admin");
			chain.doFilter(request, response);
			return;
		} else {
			User userInSession = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
			if (userInSession!=null) {
				session.setAttribute(User.LOGED_IN_USER_IN_SESSION, userInSession);
				session.setAttribute(constants.SystemConstants.LOGIN_TOKEN, "user");
				chain.doFilter(request, response);
				return;
			} else {
				session.setAttribute(constants.SystemConstants.LOGIN_TOKEN, "");
			}
		}
		
		
		
		
		
		Cookie[] cookies = null;
		String login_name = null;
		String login_type = null;
		String systemErrorMessage = null;
		try {
			cookies = req.getCookies();
			if (cookies !=null) {
				for (Cookie c : cookies) {
					if (c.getName().equals(Admin.ADMIN_LOGIN_NAME_IN_COOKIE)) {
						login_name = c.getValue();
						login_type = Admin.ADMIN_LOGIN_NAME_IN_COOKIE;
						break;
					} else if (c.getName().equals(User.USER_LOGIN_NAME_IN_COOKIE)) {
						login_name = c.getValue();
						login_type = User.USER_LOGIN_NAME_IN_COOKIE;
						break;
					}
				}
				
				if (login_name!=null) {
					
					Connection conn = common_utils.MyUtils.getStoredConnection(req);
					
					if (login_type.equals(Admin.ADMIN_LOGIN_NAME_IN_COOKIE)) {
						Admin admin = db_admin_login_utils.sql.AdminQuery.findAdminByName(login_name, conn);
											
						if (admin!=null) {
							session.setAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION, admin);
							session.setAttribute(constants.SystemConstants.LOGIN_TOKEN, "admin");
						}
						
					} else if (login_type.equals(User.USER_LOGIN_NAME_IN_COOKIE)) {
						User user = db_user_login_utils.UserQuery.findUserByName(login_name, conn);
						if (user!=null) {
							session.setAttribute(User.LOGED_IN_USER_IN_SESSION, user);
							session.setAttribute(constants.SystemConstants.LOGIN_TOKEN, "user");
						}
					}
					
				}
			}
						
		}
		catch(Exception e) {
			systemErrorMessage = e.getMessage();
			req.setAttribute(constants.SystemConstants.SYSTEM_ERROR_MESSAGE, systemErrorMessage);			
		}
				
		chain.doFilter(request, response);
		// qua filter.
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
