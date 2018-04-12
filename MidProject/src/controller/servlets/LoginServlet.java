package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String username=request.getParameter("uname");
	     
	     String password=request.getParameter("psw");
	     System.out.println(username);
	     System.out.println(password);
	     
	     
	     if(UserManager.getInstance().login(username, password)) {
	    	 System.out.println("well done");
	    	 request.getRequestDispatcher("image.gif").forward(request, response);
	     }else {
	    request.getRequestDispatcher("InvalidLogin.html").forward(request,response); 
	     }
		
	}

}
