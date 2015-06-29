package sqlite.jdbc;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.User;
import ormlite.utils.DatabaseUtils;

public class UsersTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<User, String> usersDao;

    private static String DATABASE_URL = "jdbc:sqlite:users.db";

    public UsersTableManager() throws SQLException {
		String databaseUrl = DATABASE_URL;
		connectionSource = new JdbcConnectionSource(databaseUrl);
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

    public void updateUser(User user) throws SQLException {
    	usersDao.update(user);
    }

    public void getUser(User user) throws SQLException {
    	usersDao.queryForId(user.getUserName());
    }

    public static void main(String[] args) throws Exception {
    	DatabaseUtils.initTables();
    }
}
