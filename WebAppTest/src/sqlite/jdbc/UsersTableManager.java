package sqlite.jdbc;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.User;
import models.UserType;
import ormlite.utils.DatabaseUtils;

public class UsersTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<User, String> usersDao;

    public UsersTableManager() throws SQLException {
	connectionSource = new JdbcConnectionSource(DatabaseUtils.DATABASE_URL);
	usersDao = DaoManager.createDao(connectionSource, User.class);
    }

    public void createUpdateBulder() throws SQLException {
	UpdateBuilder<User, String> updateBuilder = usersDao.updateBuilder();
	// set the criteria like you would a QueryBuilder
	updateBuilder.where().eq(User.USER_TYPE, UserType.REGULAR);
	// update the value of your field(s)
	updateBuilder.updateColumnValue(User.USER_TYPE, UserType.ADMIN);
	updateBuilder.update();
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

    public void updateUser(User user) throws SQLException {
	usersDao.update(user);
    }

    public User getUser(User user) throws SQLException {
	return usersDao.queryForId(user.getUserName());
    }

    public static void main(String[] args) throws Exception {
	DatabaseUtils.initTables();
    }
}
