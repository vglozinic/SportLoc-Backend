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
	
	public ArrayList<HashMap<String, Object>> getSportsCitiesList(String object) {
		ResultSet list = null;
		if(object == "city") {
			list = daoFactory.getEventDao().getCities();		
		}
		if(object == "sport") {
			list = daoFactory.getEventDao().getSports();
		}
		ArrayList<HashMap<String, Object>> result = getResultList(list, object);
		return result;
	}
	
	private ArrayList<HashMap<String, Object>> getResultList(ResultSet data, String object) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		if (data != null) {
			try {
				while(data.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id_" + object, data.getInt(1));
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
