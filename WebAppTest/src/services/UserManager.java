package services;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

public class UserManager {
	
    private UserContext context;
	
    public String getUser() {
        if (context.getCurrentUser() == null) {
            return null;
        }
        return context.getCurrentUser().getUserName();
    }
    
	public void logoutUser() {
		context.setCurrentUser(null);
	}
	
	
}
