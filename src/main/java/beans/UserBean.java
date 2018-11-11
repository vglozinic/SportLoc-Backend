package beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private int userId;
	private boolean gender;
	private String dob;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String salt;
	private String password;
	private String description;
	
	public UserBean () {}
	
	public UserBean(int userID,
			String name,
			String surname,
			String username,
			String email,
			String salt,
			String password,
			boolean gender, 
			String dob, 
			String description) {
		super();
		this.userId = userID;
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
		name = resolveNull(name);
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		surname = resolveNull(surname);
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		username = resolveNull(username);
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		email = resolveNull(email);
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSalt() {
		salt = resolveNull(salt);
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getPassword() {
		password = resolveNull(password);
		return password;
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
		dob = resolveNull(dob);
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getDescription() {
		description = resolveNull(description); 
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}