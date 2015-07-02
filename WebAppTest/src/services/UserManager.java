package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.Task;
import models.User;
import sqlite.jdbc.TasksTableManager;
import sqlite.jdbc.UsersTableManager;

public class UserManager extends HttpServlet{
	
    private UserContext context;
	private	UsersTableManager userTable;
	private	TasksTableManager taskTable;
	
    public UserManager() {
    	context = new UserContext();
		try {
			userTable = new UsersTableManager();
			taskTable = new TasksTableManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  JSONObject data = null;
		  try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  try {
			  data = new JSONObject(jb.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			int action = Integer.parseInt(data.getString("action"));
			switch (action) {
			case 1: 
				getUsers(resp,req); 
				break;
			
			
			case 2: 
				getUser(data,resp,req); 
				break;
			
			}
		} catch (NumberFormatException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
	}
	
	private void getUser(JSONObject data,HttpServletResponse resp, HttpServletRequest req) {
		try {
			User user=userTable.getUser(data.getString("userName"));
			
			List<Task> tasks=taskTable.getAllTasks(data.getString("userName"));
			JSONArray list=new JSONArray();
			for (int i = 0; i < tasks.size(); i++) {
				list.put(tasks.get(i).toJSON());
			}
			JSONObject result=new JSONObject();
			result.put("userData", user.toJSON());
			result.put("tasks", list);
			resp.setStatus(200); 
			resp.getOutputStream().write(result.toString().getBytes());
		    resp.getOutputStream().flush();
		    resp.getOutputStream().close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	private void getUsers(HttpServletResponse resp, HttpServletRequest req) {
		try {
			List<User> users=userTable.getUsers();
			JSONArray list=new JSONArray();
			for (int i = 0; i < users.size(); i++) {
				list.put(users.get(i).toJSON());
			}
			
			
			resp.setStatus(200); 
			resp.getOutputStream().write(list.toString().getBytes());
		    resp.getOutputStream().flush();
		    resp.getOutputStream().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
