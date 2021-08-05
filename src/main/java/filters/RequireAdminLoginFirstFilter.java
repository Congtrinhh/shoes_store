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

import entities.Admin;


@WebFilter(filterName = "crudFilter", 
urlPatterns = {
		"/admin-logout",
		"/admin-update",
		"/admin-update-advanced",
		"/admin-manage",
		"/create-specific-product", // sua lai sau specific-product-create 
		"/product-create",
		"/product-read",
		"/product-update/*",
		"/ajax-product-update",
		"/ajax-product-delete",
		"/admin-dashboard",
		
})
public class RequireAdminLoginFirstFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		
		// kiểm tra xem có admin(có quyền admin) trong c, s không, nếu không, redirect sang trang access denied.
		// nếu 1 trong 2 tồn tại, doChain().
		HttpSession session = req.getSession();
		Admin admin = (Admin) session.getAttribute(Admin.LOGED_IN_ADMIN_IN_SESSION);
		
		if (admin==null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/access-denied");
		} else {
			chain.doFilter(req, resp);
		}	
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
