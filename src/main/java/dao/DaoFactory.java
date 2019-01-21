package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
	private static String dbUrl;
	
	public DaoFactory() {
		dbUrl = System.getenv("JDBC_DATABASE_URL");
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public UserDao getUserDao() {
		return new UserDao(this);
	}
	
	public EventDao getEventDao() {
		return new EventDao(this);
	}

}
