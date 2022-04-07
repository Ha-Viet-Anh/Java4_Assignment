package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

@WebFilter(urlPatterns = 
	"/admin/*"
)

public class AuthenticationFilter extends HttpFilter implements Filter {
	public AuthenticationFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq= (HttpServletRequest) request;
		HttpServletResponse httpRes= (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();
		User user =(User) session.getAttribute("user");
		String uri=httpReq.getRequestURI();
		if(user==null) {
			httpRes.sendRedirect("/PH14045_HaVietAnh_Assignment/user/flogin");
			return;
		}else if(user!=null&&user.getRole()==0&&uri.contains("/admin/")) {
			httpRes.sendRedirect("/PH14045_HaVietAnh_Assignment/user/flogin");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
