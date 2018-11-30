package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.DaoFactory;

public class EventModel {
	
	private DaoFactory daoFactory;
	
	public EventModel() {
		this.daoFactory = new DaoFactory();
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
