package models;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {

    public static final String USER_NAME = "UserName";

    public static final String FULL_NAME = "FullName";

    public static final String PASSWORD = "Password";

    public static final String EMAIL = "Email";

    public static final String USER_TYPE = "UserType";

    @DatabaseField(columnName = USER_NAME, unique = true, canBeNull = false)
    private String userName;

    @DatabaseField(columnName = FULL_NAME, canBeNull = false)
    private String fullName;

    @DatabaseField(columnName = PASSWORD, canBeNull = false)
    private String password;

    @DatabaseField(columnName = EMAIL, canBeNull = false)
    private String email;

    @DatabaseField(columnName = USER_TYPE, canBeNull = false)
    private UserType type;

    private List<Task> tasks;

    public User() {
	// ORMLite needs a no-arg constructor
    }

    public User(String userName, String fullName, String password, String email, UserType type) {
	this.userName = userName;
	this.fullName = fullName;
	this.password = password;
	this.email = email;
	this.type = type;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public UserType getType() {
	return type;
    }

    public void setType(UserType type) {
	this.type = type;
    }

    public List<Task> getTasks() {
	return tasks;
    }
}
