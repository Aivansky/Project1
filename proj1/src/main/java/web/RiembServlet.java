package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RiembDAO;
import dao.UserDAO;
import model.Riembursements;
import model.UserBean;

import javax.servlet.http.HttpSession;
@WebServlet("/")
public class RiembServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RiembDAO riembDAO;
	
	public void init() {
		riembDAO = new RiembDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
/*
		     UserBean user = new UserBean();
		     user.setUserName(request.getParameter("un"));
		     user.setPassword(request.getParameter("pw"));

		     user = UserDAO.login(user);
			   		    
		     if (user.isValid())
		     {
			        
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          response.sendRedirect("success.jsp"); //logged-in page      		
		     }
			        
		     else 
		          response.sendRedirect("invalidLogin.jsp"); //error page 
		*/
		
		
		
		
		
		String action = request.getServletPath();

		try {
					     
			
			
			
			switch (action) {
			case "/login":
				login(request, response);
				break;
			
			case "/menu":
				listUser(request, response);
				break;
			case "/deny":
				Deny(request, response);
				break;
			case "/approve":
				Approve(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/approved":
				showApproved(request, response);
				break;
			case "/denied":
				showDenied(request, response);
				break;
			case "/manager":
				showManager(request, response);
				break;
			case "/success":
				success(request, response);
				break;
			case "/start":
				start(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserBean user = new UserBean();
	     user.setUserName(request.getParameter("un"));
	     user.setPassword(request.getParameter("pw"));


	     user = UserDAO.login(user);
		   		    
	     if (user.isValid())
	     {
	         HttpSession session = request.getSession(true);	   
	      if(user.access==1) {
	      
	             session.setAttribute("currentSessionUser",user); 
	             RequestDispatcher dispatcher = request.getRequestDispatcher("success2.jsp");
	     		dispatcher.forward(request, response);
	        
	         }
	         
	      

	          response.sendRedirect("success.jsp"); //logged-in page 
	      //    return;
	     }
	     
		        
	     else 
	          response.sendRedirect("invalidLogin.jsp"); //error page 
		
	}

	private void start(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
	}

	private void success(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
		dispatcher.forward(request, response);
	}

	private void showManager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Riembursements> listRiem = riembDAO.selectAllUsers();
		request.setAttribute("listRiem", listRiem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("financeM.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showDenied(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Riembursements> listRiem = riembDAO.selectDenied();
		request.setAttribute("listRiem", listRiem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Denied.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showApproved(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Riembursements> listRiem = riembDAO.selectApproved();
		request.setAttribute("listRiem", listRiem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Approved.jsp");
		dispatcher.forward(request, response);
	}
	
/*
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        users loginBean = new users();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
 //       riembDAO.validate(loginBean);
        /*
		try {
			if(riembDAO.validate(loginBean)) {
			    response.sendRedirect("success.jsp");
			}else {
              //  HttpSession session = request.getSession();
                //session.setAttribute("user", username);
             //   response.sendRedirect("Login.jsp");
            }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	/*
		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}
	*/
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Riembursements> listRiem = riembDAO.selectAllUsers();
		request.setAttribute("listRiem", listRiem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("mainMenu.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addRiem.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Riembursements existingUser = riembDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addRiem.jsp");
		request.setAttribute("riem", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		Riembursements newRiem = new Riembursements(amount, description, type);
		riembDAO.insertUser(newRiem);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		String type = request.getParameter("type");

		Riembursements book = new Riembursements(id, amount, description, type, null);
		riembDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		riembDAO.deleteUser(id);
		response.sendRedirect("list");
	}
	
	private void Approve(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		riembDAO.ApproveUser(id);
		response.sendRedirect("list");
	}
	
	private void Deny(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		riembDAO.DenyUser(id);
		response.sendRedirect("list");
	}

}