package models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "comments")
public class Comment {
    @DatabaseField(id = true)
    private String contend;
    @DatabaseField
    private int userId;
    @DatabaseField
    private int taskId;
    @DatabaseField
    private Date dateAdded;

    Comment() {

    }

    Comment(String contend, int userId, int taskId, Date dateAdded) {
	this.contend = contend;
	this.userId = userId;
	this.taskId = taskId;
	this.dateAdded = dateAdded;
    }

    public String getContend() {
	return contend;
    }

    public void setContend(String contend) {
	this.contend = contend;
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

    public Date getDateAdded() {
	return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
	this.dateAdded = dateAdded;
    }
}
