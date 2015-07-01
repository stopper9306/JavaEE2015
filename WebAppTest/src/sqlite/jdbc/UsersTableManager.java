package sqlite.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Task;
import models.User;
import ormlite.utils.DatabaseUtils;

public class UsersTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<User, String> usersDao;

    public UsersTableManager() throws SQLException {
	connectionSource = new JdbcConnectionSource(DatabaseUtils.DATABASE_URL);
	usersDao = DaoManager.createDao(connectionSource, User.class);
    }

    public void initTable() throws SQLException {
	TableUtils.createTable(connectionSource, User.class);
    }

    public void addUser(User user) throws SQLException {
	usersDao.create(user);
    }

    public void removeUser(User user) throws SQLException {
	usersDao.delete(user);
    }

    public void removeUserByUserName(String userName) throws SQLException {
	DeleteBuilder<User, String> deleteBuilder = usersDao.deleteBuilder();
	deleteBuilder.where().eq(User.USER_NAME, userName);
	deleteBuilder.delete();
    }

    public void updateUserFiled(User user) throws SQLException {
	UpdateBuilder<User, String> updateBuilder = usersDao.updateBuilder();
	// set the criteria like you would a QueryBuilder
	updateBuilder.where().eq(User.USER_NAME, user.getUserName());
	// update the value of your field(s)
	updateBuilder.updateColumnValue(User.PASSWORD, user.getPassword());
	updateBuilder.updateColumnValue(User.EMAIL, user.getEmail());
	updateBuilder.updateColumnValue(User.FULL_NAME, user.getFullName());
	updateBuilder.updateColumnValue(User.USER_TYPE, user.getType());
	updateBuilder.update();
    }

    public User getUser(User user) throws SQLException {
	QueryBuilder<User, String> queryBuilder = usersDao.queryBuilder();
	List<User> usersList = new ArrayList<User>();
	usersList = queryBuilder.where().eq(User.USER_NAME, user.getUserName()).query();
	if (usersList.isEmpty()) {
	    return null;
	}
	return usersList.get(0);
    }

    public User getUserByUserName(String userName) throws SQLException {
	QueryBuilder<User, String> queryBuilder = usersDao.queryBuilder();
	List<User> usersList = new ArrayList<User>();
	usersList = queryBuilder.where().eq(User.USER_NAME, userName).query();
	if (usersList.isEmpty()) {
	    return null;
	}
	return usersList.get(0);
    }

    public User getUserByUserNameAndPassword(String userName, String password) throws SQLException {
	QueryBuilder<User, String> queryBuilder = usersDao.queryBuilder();
	List<User> usersList = new ArrayList<User>();
	usersList = queryBuilder.where().eq(User.USER_NAME, userName).and().eq(User.PASSWORD, password).query();
	if (usersList.isEmpty()) {
	    return null;
	}
	return usersList.get(0);
    }

    public List<Task> getUserTasks(String userName) throws SQLException {
	Dao<Task, String> tasksDao = DaoManager.createDao(connectionSource, Task.class);
	QueryBuilder<Task, String> queryBuilder = tasksDao.queryBuilder();
	return queryBuilder.where().eq(Task.ASSIGNEE, userName).query();
    }
}
