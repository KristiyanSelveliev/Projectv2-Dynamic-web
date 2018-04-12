package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserManager;


@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		 String firstname=request.getParameter("first_name");
		 
		 String lastname=request.getParameter("last_name");
		 
		 String email=request.getParameter("email");
		
		 String username=request.getParameter("uname");
	     
	     String password=request.getParameter("psw");
	     
	     if(UserManager.getInstance().register(firstname, lastname, username, password, email)) {
	    	 System.out.println("Well done");
	    	 request.getRequestDispatcher("image.gif").forward(request, response);
	    	 
	     }else {
	    	 request.getRequestDispatcher("Register.html").forward(request, response);
	     }
	    
		
		
	}

}
