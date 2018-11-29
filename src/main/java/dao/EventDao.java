package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDao {
	
	private DaoFactory daoFactory;
	
	public EventDao(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	public ResultSet getList(String sql) {
		ResultSet result = null;
		Connection connection = daoFactory.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			result = query.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet getCities() {
		String sql = "SELECT id_city, name FROM public.city";
		return getList(sql);
	}
	
	public ResultSet getSports() {
		String sql = "SELECT id_sport, name FROM public.sport";
		return getList(sql);
	}

}
