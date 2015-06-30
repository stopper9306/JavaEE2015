package sqlite.jdbc;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Comment;
import ormlite.utils.DatabaseUtils;

public class CommentsTableManager {
    TableUtils tableutils;

    ConnectionSource connectionSource;

    Dao<Comment, String> commentsDao;

    public CommentsTableManager() throws SQLException {
	connectionSource = new JdbcConnectionSource(DatabaseUtils.DATABASE_URL);
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

    public void printContent() throws SQLException {
	UpdateBuilder<Comment, String> updateBuilder = commentsDao.updateBuilder();
	// commentsDao.queryForAll();
	for (Comment comment : commentsDao.queryForAll()) {
	    comment.print();
	}
    }
}
