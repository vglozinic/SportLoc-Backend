package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.CommentBean;
import beans.UserBean;
import dao.DaoFactory;
import helper.MailSender;
import helper.Message;
import helper.Password;

public class UserModel {
	
	private DaoFactory daoFactory;
	
	public UserModel() {
		this.daoFactory = new DaoFactory();
	}
	
	public boolean checkInteger(String number) {
		String regex = "^[0-9]+$";
		return number.matches(regex);
	}
	
	public UserBean loginUser(Map<String, String[]> parameters) {
		UserBean result = new UserBean();
		if(parameters != null && !parameters.isEmpty() && parameters.containsKey("username") && parameters.containsKey("password")) {
			UserBean profile = getProfile(parameters.get("username")[0], true);
			if(Password.checkPassword(parameters.get("password")[0], profile.getPassword(), profile.getSalt())) {
				profile.setSalt(null);
				profile.setPassword(null);
				result = profile;
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
	
	public HashMap<String, Object> registerUser(UserBean user) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("message", Message.REGISTRATION_FAIL);
		if(!user.getEmail().isEmpty() && !user.getUsername().isEmpty()) {
			boolean username = checkUser(user.getUsername());
			boolean email = checkEmail(user.getEmail());
			if(!username && !email) {
				user.setSalt(Password.getSalt());
				user.setPassword(Password.getPassword(user.getPassword(), user.getSalt()));
				if(daoFactory.getUserDao().registerUser(user)) {
					result.replace("success", true);
					result.replace("message", Message.REGISTRATION_SUCCESS);
					MailSender.sendEmail(user.getEmail(), "Registracija SportLoc", "Registracija za korisnicko ime " + user.getUsername() + " je gotova! Ovo je automatski e-mail potvrde.");
				}
				else {
					result.replace("message", Message.REGISTRATION_ERROR);
				}
			}
			else {
				if(username && email) {
					result.replace("message", Message.USERNAME_EMAIL_EXIST);
				}
				else {
					if(username) {
						result.replace("message", Message.USERNAME_EXIST);
					}
					else {
						result.replace("message", Message.EMAIL_EXIST);
					}
				}
			}
		}
		return result;
	}
	
	public UserBean getProfile(String username, boolean login) {
		UserBean result = new UserBean();
		ResultSet data = daoFactory.getUserDao().getProfile(username);
		if (data != null) {
			try {
				while (data.next()) {
					result.setUserId(data.getInt("id_user"));
					result.setName(data.getString("name"));
					result.setSurname(data.getString("surname"));
					result.setUsername(data.getString("username"));
					result.setEmail(data.getString("email"));
					if(login) {
						result.setSalt(data.getString("salt"));
						result.setPassword(data.getString("password"));
					}
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
	
	public ArrayList<CommentBean> getCommentList(String id) {
		ArrayList<CommentBean> result = new ArrayList<CommentBean>();
		if(checkInteger(id)) {
			ResultSet data = daoFactory.getUserDao().getComments(Integer.parseInt(id));
			if(data != null) {
				try {
					while(data.next()) {
						CommentBean comment = new CommentBean();
						comment.setUserId(data.getInt("id_user"));
						comment.setCommentatorId(data.getInt("id_commentator"));
						comment.setCommentator(data.getString("commentator"));
						comment.setComment(data.getString("comment"));
						comment.setVote(data.getBoolean("vote"));
						result.add(comment);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public boolean resolveComment(CommentBean comment) {
		boolean result = false;
		if(comment.getUserId() != 0 && comment.getCommentatorId() != 0) {
			if(comment.isAction()) {
				result = daoFactory.getUserDao().deleteComment(comment.getUserId(), comment.getCommentatorId());
			}
			else {
				result = daoFactory.getUserDao().checkComment(comment.getUserId(), comment.getCommentatorId());
			}	
		}
		return result;
	}

	public boolean writeComment(CommentBean comment) {
		return daoFactory.getUserDao().writeComment(comment);
	}
}
