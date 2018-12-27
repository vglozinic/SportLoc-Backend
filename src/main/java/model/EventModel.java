package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import beans.EventBean;
import beans.FilterBean;
import beans.ParticipantBean;
import dao.DaoFactory;
import helper.ActionEnum;
import helper.StatusEnum;

public class EventModel {
	
	private DaoFactory daoFactory;
	
	public EventModel() {
		this.daoFactory = new DaoFactory();
	}
	
	public boolean checkInteger(String number) {
		String regex = "^[0-9]+$";
		return number.matches(regex);
	}
	
	public ArrayList<HashMap<String, Object>> getCitiesList() {
		ResultSet list = daoFactory.getEventDao().getCities();	
		ArrayList<HashMap<String, Object>> result = getResultList(list);
		return result;
	}
	
	public ArrayList<HashMap<String, Object>> getSportsList() {
		ResultSet list = daoFactory.getEventDao().getSports();
		ArrayList<HashMap<String, Object>> result = getResultList(list);
		return result;
	}
	
	public ArrayList<EventBean> getEventList(FilterBean filter) {
		ArrayList<EventBean> result = new ArrayList<EventBean>();
		ResultSet data = daoFactory.getEventDao().getEvents(filter);
		if(data != null) {
			try {
				while(data.next()) {
					EventBean event = new EventBean();
					event.setEventId(data.getInt("id_event"));
					event.setSportId(data.getInt("id_sport"));
					event.setCityId(data.getInt("id_city"));
					event.setUserId(data.getInt("id_user"));
					event.setCapacity(data.getInt("capacity"));
					event.setCurrent(data.getInt("current"));
					event.setOpen(data.getBoolean("open"));
					event.setName(data.getString("name"));
					event.setStart(data.getString("start"));
					event.setEnd(data.getString("end"));
					event.setAddress(data.getString("address"));
					event.setDescription(data.getString("description"));
					event.setSport(data.getString("sport"));
					event.setCity(data.getString("city"));
					event.setUsername(data.getString("username"));
					result.add(event);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		return result;
	}
	
	public boolean createEvent(EventBean event) {
		return daoFactory.getEventDao().createEvent(event);
	}
	
	public boolean updateEvent(EventBean event) {
		return daoFactory.getEventDao().updateEvent(event);
	}
	
	public boolean deleteEvent(String id) {
		if(checkInteger(id)) {
			return daoFactory.getEventDao().deleteEvent(Integer.parseInt(id));
		}
		else {
			return false;
		}
	}
	
	public ArrayList<ParticipantBean> getParticipantList(String id) {
		ArrayList<ParticipantBean> result = new ArrayList<ParticipantBean>();
		if(checkInteger(id)) {
			ResultSet data = daoFactory.getEventDao().getParticipants(Integer.parseInt(id));
			if(data != null) {
				try {
					while(data.next()) {
						ParticipantBean participant = new ParticipantBean();
						participant.setEventId(data.getInt("id_event"));
						participant.setUserId(data.getInt("id_user"));
						participant.setUsername(data.getString("username"));
						participant.setStatusId(data.getInt("id_status"));
						participant.setStatus(data.getString("status"));
						result.add(participant);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public boolean resolveParticipant(ParticipantBean participant) {
		boolean result = false;
		if(participant.getEventId() != 0 && participant.getUserId() != 0) {
			int eventId = participant.getEventId();
			int userId = participant.getUserId();
			switch (participant.getAction()) {
				case ENTER_EVENT:
					result = daoFactory.getEventDao().joinEvent(eventId, userId, StatusEnum.getValue(StatusEnum.APPROVED));
					break;
				case SEND_REQUEST: 
					result = daoFactory.getEventDao().joinEvent(eventId, userId, StatusEnum.getValue(StatusEnum.PENDING)); 
					break;
				case APPROVE_USER: 
					result = daoFactory.getEventDao().changeStatus(eventId, userId, StatusEnum.getValue(StatusEnum.APPROVED));
					break;
				case BLOCK_USER: 
					result = daoFactory.getEventDao().changeStatus(eventId, userId, StatusEnum.getValue(StatusEnum.BLOCKED));
					break;
				case REMOVE_USER: 
					result = daoFactory.getEventDao().removeUser(eventId, userId); 
					break;
				default: return result;
			}
		}
		return result;
	}
	
	private ArrayList<HashMap<String, Object>> getResultList(ResultSet data) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		if (data != null) {
			try {
				while(data.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id" , data.getInt(1));
					map.put("name", data.getString(2));
					result.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
