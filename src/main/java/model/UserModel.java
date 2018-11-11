package model;

import beans.UserBean;
import dao.DaoFactory;
import helper.MailSender;
import helper.Password;

public class UserModel {
	
	private DaoFactory daoFactory;
	
	public UserModel() {
		this.daoFactory = new DaoFactory();
	}
	
	public boolean registerUser(UserBean user) {
		boolean result = false;
		
		if(user.getEmail() != null) {
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
