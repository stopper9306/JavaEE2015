package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import services.UserManager;
import sqlite.jdbc.UsersTableManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	private UsersTableManager usersTable;

	public LoginServlet() {
		try {
			usersTable = new UsersTableManager();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			User user = usersTable.getUserByUserNameAndPassword(userName, password);
			UserManager userManager= new UserManager();
			userManager.loginUser(user, response,request);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
