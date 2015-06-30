package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

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


	public Comment(JSONObject data) {
    	try {
			this.content = data.getString("content");
			this.userId = Integer.parseInt(data.getString("userId"));
			this.taskId = Integer.parseInt(data.getString("taskId"));

			DateFormat format = new SimpleDateFormat();
			this.date = format.parse(data.getString("date"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

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
    
    public String toJSONString() {
    	return "{content:" + this.content + ",userId:" + this.userId + ",date:" + this.date + "}";

    }
}
