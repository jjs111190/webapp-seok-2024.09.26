package kr.ac.kku.cs.wp.seok.aaa;

import jakarta.servlet.http.HttpServlet;

public class User extends HttpServlet{
	
	    private static final long serialVersionUID = 1L;
	    private String id;
	    private String role;

	    // Getter for id
	    public String getId() {
	        return id;
	    }

	    // Setter for id
	    public void setId(String id) {
	        this.id = id;
	    }

	    // Getter for role
	    public String getRole() {
	        return role;
	    }

	    // Setter for role
	    public void setRole(String role) {
	        this.role = role;
	    }
	
}
