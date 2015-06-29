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

    Dao<Comment, String> commentsDao;

    private static String DATABASE_URL = "jdbc:sqlite:comments.db";

    public CommentsTableManager() throws SQLException {
		String databaseUrl = DATABASE_URL;
		connectionSource = new JdbcConnectionSource(databaseUrl);
		commentsDao = DaoManager.createDao(connectionSource, Comment.class);
    }

    public void initTable() throws SQLException {
    	TableUtils.createTable(connectionSource, Comment.class);
    }

    public void addComment(Comment comment) throws SQLException {
    	commentsDao.create(comment);
    }

    public void removeComment(Comment comment) throws SQLException {
    	commentsDao.delete(comment);
    }

    public void updateComment(Comment comment) throws SQLException {
    	commentsDao.update(comment);
    }

    public void getComment(Comment comment) throws SQLException {
    	commentsDao.queryForId(comment.getContent());
    }
}
