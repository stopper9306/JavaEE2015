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

    Dao<Task, String> tasksDao;

    private static String DATABASE_URL = "jdbc:sqlite:tasks.db";

    public TasksTableManager() throws SQLException {
		String databaseUrl = DATABASE_URL;
		connectionSource = new JdbcConnectionSource(databaseUrl);
		tasksDao = DaoManager.createDao(connectionSource, Task.class);
    }

    public void initTable() throws SQLException {
    	TableUtils.createTable(connectionSource, Task.class);
    }

    public void addTask(Task task) throws SQLException {
    	tasksDao.create(task);
    }

    public void removeTask(Task task) throws SQLException {
    	tasksDao.delete(task);
    }

    public void updateTask(Task task) throws SQLException {
    	tasksDao.update(task);
    }

    public void getTask(Task task) throws SQLException {
    	tasksDao.queryForId(task.getTitle());
    }
}
