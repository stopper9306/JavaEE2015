package sqlite.jdbc;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Comment;

public class CommentsTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<Comment, String> usersDao;

    private static String DATABASE_URL = "jdbc:sqlite:comments.db";

    public CommentsTableManager() throws SQLException {
	String databaseUrl = DATABASE_URL;
	connectionSource = new JdbcConnectionSource(databaseUrl);
	usersDao = DaoManager.createDao(connectionSource, Comment.class);
    }

    public void initTable() throws SQLException {
	TableUtils.createTable(connectionSource, Comment.class);
    }

    public void addUser(Comment user) throws SQLException {
	usersDao.create(user);
    }

    public void removeUser(Comment user) throws SQLException {
	usersDao.delete(user);
    }

    public void updateUser(Comment user) throws SQLException {
	usersDao.update(user);
    }

    public void getUser(Comment user) throws SQLException {
	usersDao.queryForId(user.getContend());
    }
}
