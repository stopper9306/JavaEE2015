package models;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import models.UserType;

@DatabaseTable(tableName = "USERS")
public class User {

    public static final String USER_NAME = "USERNAME";

    public static final String FULL_NAME = "FULLNAME";

    public static final String PASSWORD = "PASSWORD";

    public static final String EMAIL = "EMAIL";

    public static final String USER_TYPE = "USERTYPE";

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
    
    @Override
    public String toString() {
    	return String.format("username: %s, password: %s, fullName: %s, email: %s, type: %s",
    			this.userName, this.password, this.fullName, this.email, this.type);
    }
}
