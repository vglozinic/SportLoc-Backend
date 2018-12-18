package beans;

import java.io.Serializable;

public class EventBean {
	
	public static final long serialVersionUID = 1L;
	
	private int eventId;
	private int sportId;
	private int cityId;
	private int userId;
	private int capacity;
	private int current;
	private boolean open;
	private String name;
	private String start;
	private String end;
	private String address;
	private String description;
	private String sport;
	private String city;
	private String username;
	
	public EventBean () {}
	
	public EventBean(int eventId,
			String name,
			String start,
			String end,
			String address,
			String description,
			boolean open,
			int capacity,
			int current,
			int sportId,
			String sport,
			int cityId,
			String city,
			int userId,
			String username) {
		super ();
		this.eventId = eventId;
		this.name = name;
		this.start = start;
		this.end = end;
		this.address = address;
		this.description = description;
		this.open = open;
		this.capacity = capacity;
		this.current = current;
		this.sportId = sportId;
		this.sport = sport;
		this.cityId = cityId;
		this.city = city;
		this.userId = userId;
		this.username = username;
	}
	
	private String resolveNull(String string) {
		if (string == null) {
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
	
	public String getName() {
		return resolveNull(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStart() {
		return resolveNull(start);
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return resolveNull(end);
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getAddress() {
		return resolveNull(address);
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return resolveNull(description);
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCurrent() {
		return current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public int getSportId() {
		return sportId;
	}
	
	public void setSportId(int sportId) {
		this.sportId = sportId;
	}
	
	public String getSport() {
		return resolveNull(sport);
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public String getCity() {
		return resolveNull(city);
	}
	
	public void setCity(String city) {
		this.city = city;
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
	
}
