package beans;

public class ParticipantBean {
	
	public static final long serialVersionUID = 1L;
	
	private int eventId;
	private int userId;
	private int statusId;
	private String username;
	private String status;
	
	public ParticipantBean() {}
	
	public ParticipantBean(int eventId,
			int userId,
			String username,
			int statusId,
			String status) {
		super();
		this.eventId = eventId;
		this.userId = userId;
		this.username = username;
		this.statusId = statusId;
		this.status = status;
	}

	private String resolveNull(String string) {
		if(string == null) {
			string = "";
		}
		return string;
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

}
