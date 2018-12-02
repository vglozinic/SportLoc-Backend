package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import beans.EventBean;

public class EventDao {

	private DaoFactory daoFactory;

	public EventDao(DaoFactory daoFactory) {
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

	public boolean createEvent(EventBean event) {
		boolean result = false;
		int queryResult = 0;
		int subqueryResult = 0;
		String sql = "INSERT INTO public.event (name, start_time, end_time, address, description, capacity, dates, opened, id_city, id_sport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String subsql = "INSERT INTO public.participant (id_event, id_user, owner, id_status) VALUES ((SELECT id_event FROM public.event ORDER BY id_event DESC LIMIT 1), ?, TRUE, 1)";
		Connection connection = daoFactory.getConnection();

		try {
			connection.setAutoCommit(false);
			PreparedStatement query = prepareEventStatement(event, sql, connection);
			PreparedStatement subquery = connection.prepareStatement(subsql);
			subquery.setInt(1, event.getUserId());

			queryResult = query.executeUpdate();
			subqueryResult = subquery.executeUpdate();

			if (queryResult != 0 && subqueryResult != 0) {
				connection.commit();
				result = true;
			} else {
				connection.rollback();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateEvent(EventBean event) {
		boolean result = false;
		String sql = "UPDATE public.event SET name = ?, start_time = ?, end_time = ?, address = ?, description = ?, capacity = ?, dates = ?, opened = ?, id_city = ?, id_sport = ? WHERE id_event = ?";
		Connection connection = daoFactory.getConnection();

		try {
			PreparedStatement query = prepareEventStatement(event, sql, connection);
			query.setInt(11, event.getEventId());

			if (query.executeUpdate() != 0) {
				result = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteEvent(int id) {
		boolean result = false;
		String sql = "DELETE FROM public.event WHERE id_event = ?";
		Connection connection = daoFactory.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setInt(1, id);

			if (query.executeUpdate() != 0) {
				result = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private PreparedStatement prepareEventStatement(EventBean event, String sql, Connection connection) throws SQLException {
		PreparedStatement query = connection.prepareStatement(sql);
		query.setString(1, event.getName());
		query.setObject(2, event.getStart() + ":00", Types.TIME);
		if (event.getEnd().isEmpty()) {
			query.setNull(3, Types.TIME);
		} else {
			query.setObject(3, event.getEnd() + ":00", Types.TIME);
		}
		query.setString(4, event.getAddress());
		query.setString(5, event.getDescription());
		query.setInt(6, event.getCapacity());
		query.setDate(7, Date.valueOf(event.getDate()));
		query.setBoolean(8, event.getOpen());
		query.setInt(9, event.getCityId());
		query.setInt(10, event.getSportId());
		return query;
	}

}
