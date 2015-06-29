package models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "comments")
public class Comment {
    
	@DatabaseField(id = true)
    private String content;
    
    @DatabaseField
    private int userId;
    
    @DatabaseField
    private int taskId;
    
    @DatabaseField
    private Date date;

    Comment() {
    }

    Comment(String content, int userId, int taskId, Date date) {
		this.content = content;
		this.userId = userId;
		this.taskId = taskId;
		this.date = date;
    }

    public String getContent() {
    	return content;
    }

    public void setContent(String contend) {
    	this.content = contend;
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

    public Date getDate() {
    	return date;
    }
}
