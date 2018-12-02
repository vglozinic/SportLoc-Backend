package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBean;

public class UserDao {
	
	private DaoFactory daoFactory;
	
	public UserDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public ResultSet getData(String username, String sql) {
		ResultSet result = null;
		Connection connection = daoFactory.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, username);
			result = query.executeQuery();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet checkUser(String username) {
		String sql = "SELECT id_user FROM public.user WHERE username = ?";
		return getData(username, sql);
	}
	
	public ResultSet getLoginData(String username){
		String sql = "SELECT id_user, password, salt FROM public.user WHERE username = ?";
		return getData(username, sql);
	}
	
	public boolean updatePassword(String salt, String hash, String email) {
		boolean result = false;
		String sql = "UPDATE public.user SET salt = ?, password = ? WHERE email = ?";
		Connection connection = daoFactory.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, salt);
			query.setString(2, hash);
			query.setString(3, email);

			if(query.executeUpdate() != 0) {
				result = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
	
			if(query.executeUpdate() != 0) {
				result = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
