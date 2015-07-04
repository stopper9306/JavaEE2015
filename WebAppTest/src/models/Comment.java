package models;

import java.sql.Timestamp;
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

	public static final String USER_NAME = "USERNAME";

	public static final String TASK_ID = "TASKID";

	public static final String DATE = "DATE";

	@DatabaseField(columnName = ID, unique = true, generatedId = true, canBeNull = false)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DatabaseField(columnName = CONTENT, canBeNull = false)
	private String content;

	@DatabaseField(columnName = USER_NAME, canBeNull = false)
	private String userName;

	@DatabaseField(columnName = TASK_ID, canBeNull = false)
	private int taskId;

	@DatabaseField(columnName = DATE, canBeNull = false)
	private Date date;

	Comment() {
	}

	Comment(String content, String userId, int taskId, Date date) {
		this.content = content;
		this.userName = userId;
		this.taskId = taskId;
		this.date = date;
	}

	public Comment(JSONObject data) {
		try {
			this.content = data.getString("content");
			this.userName = data.getString("userName");
			this.taskId = Integer.parseInt(data.getString("taskId"));
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);
			try {
				long time = sdfDate.parse(strDate).getTime();
				this.date = new Timestamp(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public String getContent() {
		return content;
	}

	public void setContent(String contend) {
		this.content = contend;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		try {
			result.put("content", this.content);
			result.put("userName", this.userName);
			result.put("date", this.date);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;

	}
}
