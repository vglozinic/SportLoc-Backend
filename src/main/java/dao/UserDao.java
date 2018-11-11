package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.UserBean;

public class UserDao {
	
	private DaoFactory daoFactory;
	
	public UserDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public boolean insertUser(UserBean user) {
		boolean result = false;
		String sql = "INSERT INTO public.user (name, surname, username, email, salt, password, gender, description, dob) VALUES (?, ?, ?, ?, ?, ?, ?, NULL, ?)";
		Connection connection = daoFactory.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement(sql);
			
			query.setString(1, user.getName());
			query.setString(2, user.getSurname());
			query.setString(3, user.getUsername());
			query.setString(4, user.getEmail());
			query.setString(5, user.getSalt());
			query.setString(6, user.getPassword());
			query.setBoolean(7, user.getGender());
			query.setDate(8, Date.valueOf(user.getDob()));
	
			query.execute();
			connection.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
