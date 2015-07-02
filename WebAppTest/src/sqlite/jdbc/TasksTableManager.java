package sqlite.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Comment;
import models.Task;
import ormlite.utils.DatabaseUtils;

public class TasksTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<Task, String> tasksDao;

    public TasksTableManager() throws SQLException {
	connectionSource = new JdbcConnectionSource(DatabaseUtils.DATABASE_URL);
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

    public void updateAssignee(Task task) throws SQLException {
	UpdateBuilder<Task, String> updateBuilder = tasksDao.updateBuilder();
	// set the criteria like you would a QueryBuilder
	updateBuilder.where().eq(Task.ID, task.getId());
	// update the value of your field(s)
	updateBuilder.updateColumnValue(Task.ASSIGNEE, task.getAssignee());
	updateBuilder.update();
    }

    public void updateStatus(Task task) throws SQLException {
	UpdateBuilder<Task, String> updateBuilder = tasksDao.updateBuilder();
	// set the criteria like you would a QueryBuilder
	updateBuilder.where().eq(Task.ID, task.getId());
	// update the value of your field(s)
	updateBuilder.updateColumnValue(Task.STATUS, task.getStatus());
	updateBuilder.update();
    }


    public void updateDueDate(Task task) throws SQLException {
	UpdateBuilder<Task, String> updateBuilder = tasksDao.updateBuilder();
	// set the criteria like you would a QueryBuilder
	updateBuilder.where().eq(Task.ID, task.getId());
	// update the value of your field(s)
	updateBuilder.updateColumnValue(Task.DUE_DATE, task.getDueDate());
	updateBuilder.update();
    }

    public Task getTask(Task task) throws SQLException {
	QueryBuilder<Task, String> queryBuilder = tasksDao.queryBuilder();
	return queryBuilder.where().eq(Task.ID, task.getId()).query().get(0);
    }
    
    public Task getTask(int taskId) throws SQLException {
	QueryBuilder<Task, String> queryBuilder = tasksDao.queryBuilder();
	return queryBuilder.where().eq(Task.ID, taskId).query().get(0);
    }
    public List<Task> getAllTasks(String username) throws SQLException {
	QueryBuilder<Task, String> queryBuilder = tasksDao.queryBuilder();
	return queryBuilder.orderBy(Task.DUE_DATE, false).where().eq(Task.ASSIGNEE, username).query();
    }

}
