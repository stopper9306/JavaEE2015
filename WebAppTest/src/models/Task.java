package models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TASKS")
public class Task {

	public static final String ID = "ID";

	public static final String TITLE = "TITLE";

	public static final String DESCRIPTION = "DESCRIPTION";

	public static final String DUE_DATE = "DUEDATE";

	public static final String ASSIGNEE = "ASSIGNEE";

	public static final String STATUS = "STATUS";

	@DatabaseField(columnName = ID, unique = true, generatedId = true, canBeNull = false)
	private int id;

	@DatabaseField(columnName = TITLE, canBeNull = false)
	private String title;

	@DatabaseField(columnName = DESCRIPTION, canBeNull = false)
	private String description;

	@DatabaseField(columnName = DUE_DATE, canBeNull = false)
	private Date dueDate;

	@DatabaseField(columnName = ASSIGNEE, canBeNull = false)
	private String assignee;

	@DatabaseField(columnName = STATUS, canBeNull = false)
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

	public Task(String title, String description, Date dueDate, String assignee, Status status) {
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

	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JSONObject toJSON() {

		JSONObject result = new JSONObject();
		try {
			result.put("id", this.id);
			result.put("title", this.title);
			result.put("description", this.description);
			result.put("dueDate", this.dueDate);
			result.put("assignee", this.assignee);
			result.put("status", this.status);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;


	}
	@Override
	public String toString() {
		return String.format("title: %s, description: %s, due date: %s, assignee: %s, status: %s",
				this.title, this.description, this.dueDate, this.assignee, this.status);
	}

}
