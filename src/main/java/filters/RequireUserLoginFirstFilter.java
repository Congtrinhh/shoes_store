package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

@WebFilter(filterName = "requireLoginFirstFilter",
urlPatterns = {
		"/checkout",
})
public class RequireUserLoginFirstFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//nếu người dùng chưa đăng nhập, không cho phép truy cập trang
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		User logedinUser = (User)session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
		if (logedinUser==null) {
			resp.sendRedirect(req.getContextPath()+"/access-denied");
			return;
		}
		// cho phép truy cập nếu đã đăng nhập
		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
