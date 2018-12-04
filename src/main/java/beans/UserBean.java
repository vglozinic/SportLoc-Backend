package beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private int userId;
	private int upvote;
	private int downvote;
	private boolean gender;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String salt;
	private String password;
	private String description;
	private String dob;
	
	public UserBean () {}
	
	public UserBean(int userId,
			String name,
			String surname,
			String username,
			String email,
			String salt,
			String password,
			boolean gender, 
			String dob, 
			String description,
			int upvote,
			int downvote) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.salt = salt;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.description = description;
	}
	
	private String resolveNull(String string) {
		if(string == null) {
			string = "";
		}
		return string;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return resolveNull(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return resolveNull(surname);
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		return resolveNull(username);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return resolveNull(email);
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSalt() {
		return resolveNull(salt);
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getPassword() {
		return resolveNull(password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getGender() {
		return gender;
	}
	
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	public String getDob() {
		return resolveNull(dob);
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getDescription() {
		return resolveNull(description); 
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getUpvote() {
		return upvote;
	}
	
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	
	public int getDownvote() {
		return downvote;
	}
	
	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}

}
