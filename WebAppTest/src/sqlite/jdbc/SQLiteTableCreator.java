package sqlite.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author DenisSoft
 *
 */
public class SQLiteTableCreator {
	static Connection connection = null;
	static Statement statement = null;

	public static void createCommentsTable() {	
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:comments.db");
	      System.out.println("Opened database successfully");

	      statement = connection.createStatement();
	      String sql = "CREATE TABLE COMMENTS " +
	                   "(ID INT PRIMARY KEY       NOT NULL," +
	                   " TITLE           TEXT     NOT NULL, " + 
	                   " DESCRIPTION     TEXT     NOT NULL, " + 
	                   " OWNER           TEXT     NOT NULL, " + 
	                   " DUEDATE         DATE     NOT NULL, " +
	                   " STATUS          TEXT     NOT NULL" + ")"; 
	      statement.executeUpdate(sql);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}

	public static void createTicketsTable() {
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:tickets.db");
	      System.out.println("Opened database successfully");

	      statement = connection.createStatement();
	      String sql = "CREATE TABLE TICKETS " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " CONTENT           TEXT    NOT NULL, " + 
	                   " USERID            INT     NOT NULL, " + 
	                   " TASKID            INT	   NOT NULL, " + 
	                   " DATE			   DATE	   NOT NULL" + ")"; 
	      statement.executeUpdate(sql);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}

	
	public static void createUsersTable() {
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:users.db");
	      System.out.println("Opened database successfully");

	      statement = connection.createStatement();
	      String sql = "CREATE TABLE USERS " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " USERNAME           TEXT    NOT NULL, " + 
	                   " PASSWORD            INT     NOT NULL, " + 
	                   " EMAIL         CHAR[50] " + 
	                   " USERTYPE 	   CHAR[50]		NOT NULL"+ ")"; 
	      statement.executeUpdate(sql);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}
	
	public void finalize() throws SQLException{
		connection.close();
		statement.close();
	}
}
