package beans;

import java.io.Serializable;

import helper.ActionEnum;
import helper.BeanHelper;

public class ParticipantBean extends BeanHelper implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private int eventId;
	private int userId;
	private int statusId;
	private ActionEnum action;
	private String username;
	private String status;
	
	public ParticipantBean() {}
	
	public ParticipantBean(int eventId,
			int userId,
			String username,
			int statusId,
			String status,
			ActionEnum action) {
		super();
		this.eventId = eventId;
		this.userId = userId;
		this.username = username;
		this.statusId = statusId;
		this.status = status;
		this.action = action;
	}
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return resolveNull(username);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public String getStatus() {
		return resolveNull(status);
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public ActionEnum getAction() {
		return action;
	}
	
	public void setAction(ActionEnum action) {
		this.action = action;
	}

}
