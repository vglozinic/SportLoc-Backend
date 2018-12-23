package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import beans.EventBean;
import helper.DaoHelper;

public class EventDao extends DaoHelper {

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
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	private boolean resolveParticipant(int eventId, int userId, String sql) {
		boolean result = false;
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setInt(1, eventId);
			query.setInt(2, userId);
			
			if(query.executeUpdate() != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
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
		String sql = "INSERT INTO public.event (name, start_time, end_time, address, description, capacity, opened, id_city, id_sport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public ResultSet getEvents() {
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ON (E.id_event) ");
		sql.append("E.id_event, E.id_sport, E.id_city, U.id_user, E.capacity, ");
		sql.append("(SELECT COUNT(*) FROM public.participant JOIN public.event ");
		sql.append("ON public.event.id_event = public.participant.id_event ");
		sql.append("WHERE public.participant.id_status = 1 ");
		sql.append("AND public.participant.id_event = E.id_event) AS current, ");
		sql.append("E.opened AS open, E.name, E.start_time AS start, E.end_time AS end, ");
		sql.append("E.address, E.description, S.name AS sport, C.name AS city, U.username ");
		sql.append("FROM public.event E ");
		sql.append("JOIN public.sport S ON E.id_sport = S.id_sport ");
		sql.append("JOIN public.city C ON E.id_city = C.id_city ");
		sql.append("JOIN public.participant P ON E.id_event = P.id_event ");
		sql.append("JOIN public.user U ON P.id_user = U.id_user ");
		sql.append("WHERE P.owner = TRUE ");
		sql.append("ORDER BY E.id_event DESC");
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql.toString());
			result = query.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public boolean updateEvent(EventBean event) {
		boolean result = false;
		String sql = "UPDATE public.event SET name = ?, start_time = ?, end_time = ?, address = ?, description = ?, capacity = ?, opened = ?, id_city = ?, id_sport = ? WHERE id_event = ?";
		Connection connection = daoFactory.getConnection();

		try {
			PreparedStatement query = prepareEventStatement(event, sql, connection);
			query.setInt(10, event.getEventId());

			if (query.executeUpdate() != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public ResultSet getParticipants(int id) {
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT P.id_event, P.id_user, U.username, P.id_status, S.name AS status ");
		sql.append("FROM public.participant P ");
		sql.append("JOIN public.user U ON P.id_user = U.id_user ");
		sql.append("JOIN public.status S ON P.id_status = S.id_status ");
		sql.append("WHERE id_event = ? ");
		sql.append("ORDER BY id_status");
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql.toString());
			query.setInt(1, id);
			result = query.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public boolean joinEvent(int eventId, int userId, int status) {
		String sql = "INSERT INTO public.participant (id_event, id_user, owner, id_status) VALUES (?, ?, FALSE, " + status + ")";
		return resolveParticipant(eventId, userId, sql);
	}
		
	public boolean changeStatus (int eventId, int userId, int status) {
		String sql = "UPDATE public.participant SET id_status = " + status + " WHERE id_event = ? AND id_user = ?";
		return resolveParticipant(eventId, userId, sql);
	}
	
	public boolean removeUser(int eventId, int userId) {
		String sql = "DELETE FROM public.participant WHERE id_event = ? AND id_user = ?";
		return resolveParticipant(eventId, userId, sql);
	}
	
	private PreparedStatement prepareEventStatement(EventBean event, String sql, Connection connection) throws SQLException {
		PreparedStatement query = connection.prepareStatement(sql);
		query.setString(1, event.getName());
		query.setString(2, event.getStart());
		if (event.getEnd().isEmpty()) {
			query.setNull(3, Types.VARCHAR);
		} else {
			query.setString(3, event.getEnd());
		}
		query.setString(4, event.getAddress());
		query.setString(5, event.getDescription());
		query.setInt(6, event.getCapacity());
		query.setBoolean(7, event.isOpen());
		query.setInt(8, event.getCityId());
		query.setInt(9, event.getSportId());
		return query;
	}

}
