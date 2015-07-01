package sqlite.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
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

    public void removeCommentById(int commentId) throws SQLException {
	DeleteBuilder<Comment, String> deleteBuilder = commentsDao.deleteBuilder();
	deleteBuilder.where().eq(Comment.ID, commentId);
	deleteBuilder.delete();
    }

    public void updateComment(Comment comment) throws SQLException {
	commentsDao.update(comment);
    }

    public Comment getComment(Comment comment) throws SQLException {
	QueryBuilder<Comment, String> queryBuilder = commentsDao.queryBuilder();
	return queryBuilder.where().eq(Comment.ID, comment.getId()).query().get(0);
    }

    public List<Comment> getAllTaskComments(int taskId) throws SQLException {
	QueryBuilder<Comment, String> queryBuilder = commentsDao.queryBuilder();
	return queryBuilder.orderBy(Comment.DATE, false).where().eq(Comment.TASK_ID, taskId).query();
    }

    public void pritntContent() throws SQLException {
	UpdateBuilder<Comment, String> updateBuilder = commentsDao.updateBuilder();
	// commentsDao.queryForAll();
	for (Comment comment : commentsDao.queryForAll()) {
	    // comment.print();
	}
    }
}
