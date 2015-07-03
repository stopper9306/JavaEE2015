package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import models.Comment;
import models.Task;
import models.User;
import models.UserType;
import sqlite.jdbc.CommentsTableManager;
import sqlite.jdbc.TasksTableManager;
import sqlite.jdbc.UsersTableManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;



public class TaskManager extends HttpServlet{  

	/**
	 * 
	 */
	private static final long serialVersionUID = 3924798675478843547L;
	private	TasksTableManager taskTable;
	private	UsersTableManager userTable;
	
	public TaskManager() {
		try {
			taskTable = new TasksTableManager();
			userTable = new UsersTableManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

			HttpSession session = req.getSession(false);
			if(session == null) {
				resp.setStatus(HttpServletResponse.SC_FOUND);
				resp.sendRedirect("index.html");
			}
		
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
				getTasks(resp,req); 
				break;
			case 2: 
				try {
					changeStatus(data,resp,req);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 3:
				try {
					getTask(data,resp,req);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 4:
				try {
					changeAssignee(data,resp,req);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 5:
				getAllTasks(resp,req);
			case 6:
				checkOpenedInProgressTasks(resp, req);
			
		}
			
		} catch (NumberFormatException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
	}

	private void changeAssignee(JSONObject data, HttpServletResponse resp, HttpServletRequest req) throws SQLException{

		try {
			Task task=taskTable.getTask(Integer.parseInt(data.getString("taskId")));
			task.setAssignee(data.getString("assignee"));
			taskTable.updateAssignee(task);
			resp.setStatus(200);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void getTask(JSONObject data, HttpServletResponse resp, HttpServletRequest req) throws SQLException{
		try {
			Task task=taskTable.getTask(Integer.parseInt(data.getString("taskId")));
			
			List<User> users=userTable.getUsers();
			JSONObject result=new JSONObject();
			result.put("taskData", task.toJSON());
			result.put("users", users);
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
		}
		
	}
	
	private void changeStatus(JSONObject data, HttpServletResponse resp, HttpServletRequest req) throws SQLException{
		HttpSession session = req.getSession(false);

		Enumeration<String> test = session.getAttributeNames();
		
		while (test.hasMoreElements())
		       System.out.println(test.nextElement());
		try {
			String selectValue = session.getAttribute("name").toString();
			
			System.out.println(selectValue);
			Task task=taskTable.getTask(Integer.parseInt(data.getString("taskId")), session.getAttribute("name").toString());
			task.setStatus(data.getString("status"));
			
			taskTable.updateStatus(task);
			//taskTable.addComment(comment);
			
			
			resp.setStatus(200);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	private void getTasks(HttpServletResponse resp, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Object username=session.getAttribute("name");
		try {
			List<Task> tasks=taskTable.getAllTasks(username.toString());
			JSONArray list=new JSONArray();
			for (int i = 0; i < tasks.size(); i++) {
				list.put(tasks.get(i).toJSON());
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
	
	private void getAllTasks(HttpServletResponse resp, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		try {
			List<Task> tasks=taskTable.getAllTasks();
			JSONArray list=new JSONArray();
			for (int i = 0; i < tasks.size(); i++) {
				list.put(tasks.get(i).toJSON());
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
	
	private void checkOpenedInProgressTasks(HttpServletResponse resp, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		try {
			List<Task> tasks=userTable.getUserTasksOpenedAndInProgress(session.getAttribute("name").toString());
			
			if (tasks.size()<=1){
			
			resp.setStatus(200); 
			}else{
			resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
