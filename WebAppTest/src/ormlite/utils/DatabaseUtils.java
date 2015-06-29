package ormlite.utils;

import java.sql.SQLException;

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
}
