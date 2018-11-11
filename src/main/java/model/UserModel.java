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
		
		if(data != null) {
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
	
	public boolean registerUser(UserBean user) {
		boolean result = false;
		
		if(!user.getEmail().isEmpty()) {
			user.setSalt(Password.getSalt());
			user.setPassword(Password.getPassword(user.getPassword(), user.getSalt()));
			
			result = daoFactory.getUserDao().insertUser(user);
			if (result) {
				MailSender.sendEmail(user.getEmail(), "Registracija SportLoc", "Registracija za korisnicko ime " + user.getUsername() + " je gotova! Ovo je automatski e-mail potvrde.");
			}
		}
		return result;
	}

}
