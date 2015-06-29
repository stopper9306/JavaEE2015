package models;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tasks")
public class Task {
    @DatabaseField
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField
    private Date dueDate;
    @DatabaseField
    private String assignee;
    @DatabaseField
    private Status status;

    public Task() {
	// ORMLite needs a no-arg constructor
    }

    public Task(int id, String title, String description, Date dueDate, String assignee, Status status) {
	this.id = id;
	this.title = title;
	this.description = description;
	this.dueDate = dueDate;
	this.assignee = assignee;
	this.status = status;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Date getDueDate() {
	return dueDate;
    }

    public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
    }

    public String getAssignee() {
	return assignee;
    }

    public void setAssignee(String assignee) {
	this.assignee = assignee;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

}
