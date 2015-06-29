package sqlite.jdbc;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Task;

public class TasksTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<Task, String> usersDao;

    private static String DATABASE_URL = "jdbc:sqlite:tasks.db";

    public TasksTableManager() throws SQLException {
	String databaseUrl = DATABASE_URL;
	connectionSource = new JdbcConnectionSource(databaseUrl);
	usersDao = DaoManager.createDao(connectionSource, Task.class);
    }

    public void initTable() throws SQLException {
	TableUtils.createTable(connectionSource, Task.class);
    }

    public void addTask(Task user) throws SQLException {
	usersDao.create(user);
    }

    public void removeTask(Task user) throws SQLException {
	usersDao.delete(user);
    }

    public void updateTask(Task user) throws SQLException {
	usersDao.update(user);
    }

    public void getTask(Task user) throws SQLException {
	usersDao.queryForId(user.getTitle());
    }
}
