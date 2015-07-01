package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import services.UserContext;
import sqlite.jdbc.UsersTableManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
    
	private UsersTableManager usersTable;
	private UserContext context;
	
    public LoginServlet() {
    	try {
    		usersTable = new UsersTableManager();
    		context = new UserContext();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
			User user = usersTable.getUserByUserNameAndPassword(userName, password);
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.sendRedirect("index.html");
			}
	    	context.setCurrentUser(user);
			HttpSession session = request.getSession(false);
	        session.setAttribute("name", user.getUserName());  
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("tasks.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}

}
