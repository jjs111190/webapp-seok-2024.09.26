package kr.ac.kku.cs.wp.seok.demo;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(  
		urlPatterns= "/demo/hello",
				initParams={  
						@WebInitParam(name="greeting",value="Hello,Worldfrom  ServletConfig!")  
						})
public class HelloServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String greeting=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	greeting = config.getInitParameter("greeting");
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log("HelloServlet");  
		String html="""  		
					<html>  
					<head> </head>  
					<body>  
					""" + greeting + """  
					<body>  
					<html>  """;  
					resp.getWriter().println(html);
		//super.service(req, resp);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		log("Hellowasdestroyed");
		super.destroy();
	}

	//@Override
	//public void init() throws ServletException {
		// TODO Auto-generated method stub
		//super.init();
	//}

}
