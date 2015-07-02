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
import models.UserType;
import services.UserContext;
import services.UserManager;
import sqlite.jdbc.UsersTableManager;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UsersTableManager usersTable;
    private UserContext context;
	
    public RegisterServlet() {
    	try {
			usersTable = new UsersTableManager();
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
        String confirmPassword = request.getParameter("confirm-password");
        String fullName = request.getParameter("full-name");
        String email = request.getParameter("email");
        UserType userType = UserType.REGULAR;

        try {
        	User user = new User(userName, fullName, password, email, userType);
			usersTable.addUser(user);
			//login
			UserManager userManager= new UserManager();
			userManager.loginUser(user, response,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}