package web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Status;
import models.Task;
import sqlite.jdbc.TasksTableManager;

@WebServlet("/newtask")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TasksTableManager tasksTable;

    public TaskServlet() {
    	try {
			tasksTable = new TasksTableManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
        String description = request.getParameter("description");
        String strDate = request.getParameter("due-date");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate= null;
       
		try {
			long time = sdfDate.parse(strDate).getTime();
			dueDate=new Timestamp(time);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String assignee = request.getParameter("assignee");
        Status status = Status.OPEN;
        
        Task task = new Task(title, description, dueDate, assignee, status);
        
        try {   
			tasksTable.addTask(task);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}

