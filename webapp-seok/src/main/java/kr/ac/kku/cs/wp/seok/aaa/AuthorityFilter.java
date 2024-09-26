package kr.ac.kku.cs.wp.seok.aaa;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.seok.suport.servlet.BaseFilter;

@WebFilter("/jsp/admin/*")
public class AuthorityFilter extends BaseFilter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;  
		HttpSession hs=req.getSession(false);  
		User user=(User)hs.getAttribute("user");  
		String role=user.getRole();  
		if(!role.equals("admin")){
			HttpServletResponse res=(HttpServletResponse)response;  
			res.getWriter().print("Adminaccessonly");  
			}
		else{ 
			chain.doFilter(request,response); 
			}
		
	}

}
