package services;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

public class UserManager {
	
    private UserContext context;
	
    public UserManager() {
    	context = new UserContext();
    }
    
    public String getUser() {
        if (context.getCurrentUser() == null) {
            return null;
        }
        return context.getCurrentUser().getUserName();
    }
    
	public void logoutUser() {
		context.setCurrentUser(null);
	}
	
	public void loginUser(User user, HttpServletResponse response, HttpServletRequest request) {
		context.setCurrentUser(user);
		HttpSession session = request.getSession();
        session.setAttribute("name", user.getUserName());
		response.setStatus(HttpServletResponse.SC_FOUND);
		try {
			if (user == null) {
				response.sendRedirect("index.html");
			}
			else {
				response.sendRedirect("tasks.html");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	
}
