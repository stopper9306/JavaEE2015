package ormlite.utils;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Comment;
import models.Task;
import models.User;

public class DatabaseUtils {

    static ConnectionSource connectionSource;

    Dao<Comment, String> commentsDao;

    public static String DATABASE_URL = "jdbc:sqlite:./resources/db/taskmanager.db";

    public static void initTables() throws SQLException {
	connectionSource = new JdbcConnectionSource(DATABASE_URL);

	TableUtils.createTable(connectionSource, Task.class);
	TableUtils.createTable(connectionSource, User.class);
	TableUtils.createTable(connectionSource, Comment.class);
    }

    // public CommentsTableManager() throws SQLException {
    // String databaseUrl = DATABASE_URL;
    // connectionSource = new JdbcConnectionSource(DATABASE_URL);
    // commentsDao = DaoManager.createDao(connectionSource, Comment.class);
    // }

    public static void main(String[] args) throws SQLException {
	initTables();
	// UsersTableManager usersManager = new UsersTableManager();
	// User test = new User("adsad2", "asdas2", "asda2", "sadsa2",
	// UserType.REGULAR);
	// // usersManager.addUser(test);
	// User user2 = usersManager.getUser(test);
	// System.out.println(user2.getUserName());
	// System.out.println(user2.getPassword());
	// System.out.println(user2.getFullName());
	// System.out.println(user2.getEmail());
	// System.out.println(user2.getType());
    }
}
