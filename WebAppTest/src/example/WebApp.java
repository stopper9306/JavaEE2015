package example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserType;
import sqlite.jdbc.UsersTableManager;

public class WebApp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setStatus(200);
	final ArrayList arr = new ArrayList();
	arr.add("asdsaas");
	arr.add("asdsaas2");
	arr.add("asdsaas3");
	// Here we serialize the stream to a String.
	final String output = arr.toString();
	resp.setContentLength(output.length());
	// And write the string to output.
	resp.getOutputStream().write(output.getBytes());
	resp.getOutputStream().flush();
	resp.getOutputStream().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	User newUser = new User(req.getParameter("username"), req.getParameter("password"),
		req.getParameter("fullName"), req.getParameter("email"), UserType.REGULAR);

	System.out.println(req.getParameter("username"));
	System.out.println(req.getParameter("password"));
	System.out.println(req.getParameter("fullName"));
	System.out.println(req.getParameter("email"));
	System.out.println(UserType.REGULAR);
	try {
	    User test = new User("adsad", "asdas", "asda", "sadsa", UserType.REGULAR);
	    UsersTableManager aaa = new UsersTableManager();
	    aaa.addUser(test);

	    User user21 = aaa.getUser(newUser);
	    System.out.println(user21.getUserName());
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}