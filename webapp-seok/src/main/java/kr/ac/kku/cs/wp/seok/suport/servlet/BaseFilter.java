package kr.ac.kku.cs.wp.seok.suport.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;


public abstract class BaseFilter implements Filter{
	private String filterName=null;  
	private ServletContext servletContext;  
	protected void log(String message){
		String sm=filterName+":"+message;  
		servletContext.log(sm);  

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterName=filterConfig.getFilterName();  
		servletContext=filterConfig.getServletContext();
		Filter.super.init(filterConfig);
	}
	
	
}
