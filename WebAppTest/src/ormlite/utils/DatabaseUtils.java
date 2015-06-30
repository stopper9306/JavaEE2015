package ormlite.utils;

import java.sql.SQLException;

import models.User;
import models.UserType;
import sqlite.jdbc.CommentsTableManager;
import sqlite.jdbc.TasksTableManager;
import sqlite.jdbc.UsersTableManager;

public class DatabaseUtils {

    public static void initTables() throws SQLException {
	TasksTableManager tasksManager = new TasksTableManager();
	CommentsTableManager commentsManager = new CommentsTableManager();
	UsersTableManager usersManager = new UsersTableManager();

	tasksManager.initTable();
	commentsManager.initTable();
	usersManager.initTable();
    }

    public static void main(String[] args) throws SQLException {
	// initTables();
	UsersTableManager usersManager = new UsersTableManager();
	User test = new User("adsad2", "asdas2", "asda2", "sadsa2", UserType.REGULAR);
	// usersManager.addUser(test);
	User user2 = usersManager.getUser(test);
	System.out.println(user2.getUserName());
	System.out.println(user2.getPassword());
	System.out.println(user2.getFullName());
	System.out.println(user2.getEmail());
	System.out.println(user2.getType());
    }
}
