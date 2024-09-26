package kr.ac.kku.cs.wp.seok.aaa;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.seok.demo.tools.demo.data.UserData;
@WebServlet(urlPatterns= {"/login","/logout"})
public class LoginControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doPost(req, resp);
		String context = req.getServletContext().getContextPath();
		String uriStr =req.getRequestURI().replaceAll(context, "");
		
		log("in service : "+uriStr);
		
if(uriStr.equals("/login")) {
	
	Map<String, kr.ac.kku.cs.wp.seok.demo.user.User> users = UserData.getInstance().getData(); 
	String id = req.getParameter("username");
	String password = req.getParameter("password"); 
	kr.ac.kku.cs.wp.seok.demo.user.User user = users.get(id);
	
	if (user == null) {
		req.setAttribute("error", "login_fail");
		req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req,resp);
	} else {
		log(password);
		log(user.getPassword());
		
		if (!password.equals(user.getPassword())) {
		req.setAttribute("error", "login_fail"); req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
		}
		else {
			HttpSession session = req.getSession(); Account ac = new Account();
			ac.setId(id);
			ac.setName(user.getName());
			ac.setRoles (user.getRoles()); 
			ac.setEmail (user.getEmail());
			session.setAttribute("user", ac); 
			resp.sendRedirect(context);
		}
	}
}else if (uriStr.equals("/logout")) { //logout HttpSession session = req.getSession(); if (session != null) {
	HttpSession session = req.getSession(false);
	if(session!=null) {
		session.invalidate();
	}
		
}		
resp.sendRedirect(context + "/login");
return;


}
}
