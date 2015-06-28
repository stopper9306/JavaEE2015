package models;

import java.sql.Date;

public class Comment {
	private
		int id;
		String text;
		int userId;
		int taskId;
		Date taskDate;
	
	public
		Comment(int _id, String _text,int _userId, int _taskId, Date _taskDate) {
			this.id=_id;
			this.text=_text;
			this.userId=_userId;
			this.taskId=_taskId;
			this.taskDate=_taskDate;
		}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public int getId() {
		return id;
	}
}
