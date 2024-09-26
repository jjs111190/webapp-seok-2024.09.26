package kr.ac.kku.cs.wp.seok.aaa;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.seok.suport.servlet.BaseFilter;

@WebFilter(  urlPatterns={"/jsp/user/*","/jsp/admin/*","/logout"},  initParams={  @WebInitParam(name="valve",value="on") })
public class AuthenticationFilter extends BaseFilter{
	private boolean valve = false;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(valve){
			HttpServletRequest req=(HttpServletRequest)request;  
			HttpSession hs=req.getSession();  
			User user=(User)hs.getAttribute("user");  
			if(user!=null){
				log(user.getId()+"hasloggedin");  
				chain.doFilter(request,response);  
				}
			else{  
				HttpServletResponse res = (HttpServletResponse)response;  
				res.getWriter().println("Pleaselogin");  
				}  
			}
		else{
			chain.doFilter(request,response);  
			}
	
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
		String sValve=filterConfig.getInitParameter("valve");  
		if(sValve!=null){  
			if(sValve.equals("on")){
				valve = true;  
				}
			else if(sValve.equals("off")){
				valve = false;  
				}  
			}  log("initAuthenticationFilter("+sValve+")");
	}

}
