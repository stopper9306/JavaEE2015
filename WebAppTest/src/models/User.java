package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(id = true)
    private String userName;

    @DatabaseField
    private String fullName;

    @DatabaseField
    private String password;

    @DatabaseField
    private String email;

    @DatabaseField
    private UserType type;

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

}
