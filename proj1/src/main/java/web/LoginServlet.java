package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RiembDAO;
import dao.UserDAO;
import model.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/start")
public class LoginServlet extends HttpServlet {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private UserDAO userDAO;
	
	public void init() {
		userDAO = new UserDAO();
	}

public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

	String action = request.getServletPath();
	
try
{	    

     UserBean user = new UserBean();
     user.setUserName(request.getParameter("un"));
     user.setPassword(request.getParameter("pw"));


     user = UserDAO.login(user);
	   		    
     if (user.isValid())
     {
    	 
      if(user.access==1) {
        	 HttpSession session = request.getSession(true);	    
             session.setAttribute("currentSessionUser",user); 
             RequestDispatcher dispatcher = request.getRequestDispatcher("success2.jsp");
     		dispatcher.forward(request, response);
        
         }
         
          HttpSession session = request.getSession(true);	    
          session.setAttribute("currentSessionUser",user); 
          response.sendRedirect("success.jsp"); //logged-in page 
      //    return;
     }
     
	        
     else 
          response.sendRedirect("invalidLogin.jsp"); //error page 
     

} 
		
		
catch (Throwable theException) 	    
{
     System.out.println(theException); 
}
       }
	}
