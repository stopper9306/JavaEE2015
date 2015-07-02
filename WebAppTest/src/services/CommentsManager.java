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
import models.User;
import models.UserType;
import sqlite.jdbc.CommentsTableManager;
import sqlite.jdbc.UsersTableManager;
import sun.org.mozilla.javascript.internal.json.JsonParser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;



public class CommentsManager extends HttpServlet{  

	/**
	 * 
	 */
	private static final long serialVersionUID = 3924798675478843547L;
	private	CommentsTableManager commentsTable;
	
	public CommentsManager() {
		try {
			commentsTable = new CommentsTableManager();
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
				try {
					addComment(data,resp,req);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				break;
			case 2: 
				int taskId= Integer.parseInt(data.getString("taskId"));
				getComments(taskId,resp); 
				break;
			
			
			}
			
		} catch (NumberFormatException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
	}
	
	private void addComment(JSONObject data, HttpServletResponse resp, HttpServletRequest req) throws SQLException{

		try {
			HttpSession session = req.getSession(false);
			data.put("userId", session.getAttribute("name"));
			Comment comment=new Comment(data);
			commentsTable.addComment(comment);
			
			
			try {
				resp.setStatus(200);
				resp.getOutputStream().write(comment.toJSON().toString().getBytes());
			    resp.getOutputStream().flush();
			    resp.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	private void getComments(int taskId, HttpServletResponse resp) {
		try {
			List<Comment> comments=commentsTable.getAllTaskComments(taskId);
			JSONArray list=new JSONArray();
			for (int i = 0; i < comments.size(); i++) {
				list.put(comments.get(i).toJSON());
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
