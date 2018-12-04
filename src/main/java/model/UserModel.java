package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import beans.UserBean;
import dao.DaoFactory;
import helper.MailSender;
import helper.Password;

public class UserModel {
	
	private DaoFactory daoFactory;
	
	public UserModel() {
		this.daoFactory = new DaoFactory();
	}
	
	public int checkParameters(Map<String, String[]> parameters) {
		int result = 0;
		if(parameters != null && !parameters.isEmpty() && parameters.containsKey("username") && parameters.containsKey("password")) {
			result = checkLoginData(parameters.get("username")[0], parameters.get("password")[0]);
		}
		return result;
	}
	
	private int checkLoginData(String username, String password) {
		int result = 0;
		ResultSet data = daoFactory.getUserDao().getLoginData(username);
		
		if (data != null) {
			try {
				data.next();
				if(Password.checkPassword(password, data.getString(2), data.getString(3))) {
					result = data.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean checkUser(String username) {
		return daoFactory.getUserDao().checkUser(username);
	}
	
	public boolean checkEmail(String email) {
		return daoFactory.getUserDao().checkEmail(email);
	}
	
	public boolean registerUser(UserBean user) {
		boolean result = false;
		if(!user.getEmail().isEmpty() && !user.getUsername().isEmpty()) {
			user.setSalt(Password.getSalt());
			user.setPassword(Password.getPassword(user.getPassword(), user.getSalt()));
			
			result = daoFactory.getUserDao().insertUser(user);
			if (result) {
				MailSender.sendEmail(user.getEmail(), "Registracija SportLoc", "Registracija za korisnicko ime " + user.getUsername() + " je gotova! Ovo je automatski e-mail potvrde.");
			}
		}
		return result;
	}
	
	public UserBean getProfile (String id) {
		UserBean result = new UserBean();
		ResultSet data = daoFactory.getUserDao().getProfile(Integer.parseInt(id));
		if (data != null) {
			try {
				while (data.next()) {
					result.setUserId(data.getInt("id_user"));
					result.setName(data.getString("name"));
					result.setSurname(data.getString("surname"));
					result.setUsername(data.getString("username"));
					result.setEmail(data.getString("email"));
					result.setGender(data.getBoolean("gender"));
					result.setDob(data.getString("dob"));
					result.setDescription(data.getString("description"));
					result.setUpvote(data.getInt("upvote"));
					result.setDownvote(data.getInt("downvote"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public boolean updateProfile(UserBean user) {
		boolean result = false;
		if(user.getUserId() != 0) {
			user.setSalt(Password.getSalt());
			user.setPassword(Password.getPassword(user.getPassword(), user.getSalt()));
			result = daoFactory.getUserDao().updateProfile(user);
		}
		return result;
	}
	
	public boolean resetPassword(String email) {
		boolean result = false;
		if (!email.isEmpty()) {
			String password = Password.generatePassword(8);
			String salt = Password.getSalt();
			String secure = Password.getPassword(password, salt);
			
			if(daoFactory.getUserDao().updatePassword(salt, secure, email)) {
				MailSender.sendEmail(email, "Nova lozinka za SportLoc", "Vaša nova lozinka je: " + password);
				result = true;
			}
		}
		return result;
	}

}
