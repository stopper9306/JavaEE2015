package services;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import models.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentsManager extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*resp.setStatus(200);
	    //Here we serialize the stream to a String.
	    final String output = arr.toString();
	    resp.setContentLength(output.length());
	    //And write the string to output.
	    resp.getOutputStream().write(output.getBytes());
	    resp.getOutputStream().flush();
	    resp.getOutputStream().close();*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getParameter(arg0)
		super.doPost(req, resp);
		int action=Integer.parseInt(req.getParameter("action"));
		
		switch (action) {
		case 1: 
			addComment(); 
			break;
		case 2: 
			int taskId= Integer.parseInt(req.getParameter("taskId"));
			getComments(taskId); 
			break;
		
		
		}
			
	}
	
	private void addComment() {
		
	}
	
	private void getComments(int taskId) {
		
		
		
	}
	
}
