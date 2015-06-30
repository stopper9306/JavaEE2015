package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "COMMENTS")
public class Comment {
	
	public static final String ID = "ID";

	public static final String CONTENT = "CONTENT";
	
	public static final String USER_ID = "USERID";

    public static final String TASK_ID = "TASKID";

    public static final String DATE = "DATE";
    
	@DatabaseField(columnName = ID, unique = true, generatedId = true, canBeNull = false)
    private int id;
	
	@DatabaseField(columnName = CONTENT, canBeNull = false)
    private String content;
    
    @DatabaseField(columnName = USER_ID, canBeNull = false)
    private int userId;
    
    @DatabaseField(columnName = TASK_ID, canBeNull = false)
    private int taskId;
    
    @DatabaseField(columnName = DATE, canBeNull = false)
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
