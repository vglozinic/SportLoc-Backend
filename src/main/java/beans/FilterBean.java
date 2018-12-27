package beans;

import java.io.Serializable;

public class FilterBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private int userId;
	private int sportId;
	private int cityId;
	private boolean owner;
	private boolean full;
	private Boolean participant;
	private Boolean open;
	
	public FilterBean () {}
	
	public FilterBean(int userId,
			int sportId,
			int cityId,
			boolean owner,
			boolean full,
			Boolean participant,
			Boolean open) {
		super();
		this.userId = userId;
		this.sportId = sportId;
		this.cityId = cityId;
		this.owner = owner;
		this.full = full;
		this.participant = participant;
		this.open = open;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getSportId() {
		return sportId;
	}
	
	public void setSportId(int sportId) {
		this.sportId = sportId;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public boolean isOwner() {
		return owner;
	}
	
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	
	public boolean isFull() {
		return full;
	}
	
	public void setFull(boolean full) {
		this.full = full;
	}
	
	public Boolean isParticipant() {
		return participant;
	}
	
	public void setParticipant(Boolean participant) {
		this.participant = participant;
	}
	
	public Boolean isOpen() {
		return open;
	}
	
	public void setOpen(Boolean open) {
		this.open = open;
	}

}
