package kr.ac.kku.cs.wp.seok.aaa;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs=req.getSession();  
		User sUser=(User)hs.getAttribute("user");  
		if(sUser==null){ 
			String id=req.getParameter("id");  
			String role=req.getParameter("role");  
			User user=new User();  
			user.setId(id);  
			user.setRole(role);  
			hs.setAttribute("user",user);  
			log(user.getId());  
			log(user.getRole());  
			resp.getWriter().println("Loginsuccessful.");  
			}
		else{  
			resp.getWriter().println(sUser.getId()+"!pleaselogoutfirst");  }
	}

	

}
