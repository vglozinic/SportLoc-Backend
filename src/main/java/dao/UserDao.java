package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.CommentBean;
import beans.UserBean;
import helper.DaoHelper;

public class UserDao extends DaoHelper {
	
	private DaoFactory daoFactory;
	
	public UserDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public boolean getData(String attribute, String sql) {
		boolean result = false;
		ResultSet data = null;
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, attribute);
			data = query.executeQuery();
			
			if(data.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public boolean checkUser(String username) {
		String sql = "SELECT id_user FROM public.user WHERE username = ?";
		return getData(username, sql);
	}
	
	public boolean checkEmail(String email) {
		String sql = "SELECT id_user FROM public.user WHERE email = ?";
		return getData(email, sql);
	}
	
	public ResultSet getLoginData(String username){
		ResultSet result = null;
		String sql = "SELECT id_user, password, salt FROM public.user WHERE username = ?";
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query  = connection.prepareStatement(sql);
			query.setString(1, username);
			result = query.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public boolean writeComment(CommentBean comment) {
		boolean result = false;
		String sql = "INSERT INTO public.comment (id_user, id_commentator, comment, vote) VALUES (?, ?, ?, ?)";
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setInt(1, comment.getUserId());
			query.setInt(2, comment.getCommentatorId());
			query.setString(3, comment.getComment());
			query.setBoolean(4, comment.isVote());
			
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
	
	public ResultSet getComments(int id) {
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT C.id_user, C.id_commentator, ");
		sql.append("(SELECT username FROM public.user ");
		sql.append(" WHERE id_user = C.id_commentator) ");
		sql.append("AS commentator, C.comment, C.vote ");
		sql.append("FROM public.comment C WHERE C.id_user = ?");
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
	
	public boolean checkComment(int userId, int commentatorId) {
		boolean result = false;
		ResultSet data = null;
		String sql = "SELECT vote FROM public.comment WHERE id_user = ? AND id_commentator = ?";
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setInt(1, userId);
			query.setInt(2, commentatorId);
			data = query.executeQuery();
			
			if(data.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public boolean deleteComment(int userId, int commentatorId) {
		boolean result = false;
		String sql = "DELETE FROM public.comment WHERE id_user = ? AND id_commentator = ?";
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setInt(1, userId);
			query.setInt(2, commentatorId);
			
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
	
	public ResultSet getProfile (int id) {
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ON (U.id_user) U.id_user, U.name, U.surname, U.username, U.email, U.gender, U.dob, U.description, ");
		sql.append("(SELECT COUNT(*) FROM public.comment WHERE public.comment.id_user = U.id_user AND public.comment.vote = TRUE) AS upvote, ");
		sql.append("(SELECT COUNT(*) FROM public.comment WHERE public.comment.id_user = U.id_user AND public.comment.vote = FALSE) AS downvote ");
		sql.append("FROM public.user U LEFT JOIN public.comment C ON U.id_user = C.id_user WHERE U.id_user = ?");
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

	public boolean updateProfile (UserBean user) {
		boolean result = false;
		String sql = "UPDATE public.user SET name = ?, surname = ?, email = ?, salt = ?, password = ?, description = ? WHERE id_user = ?";
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, user.getName());
			query.setString(2, user.getSurname());
			query.setString(3, user.getEmail());
			query.setString(4, user.getSalt());
			query.setString(5, user.getPassword());
			query.setString(6, user.getDescription());
			query.setInt(7, user.getUserId());
			
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

	public boolean updatePassword(String salt, String hash, String email) {
		boolean result = false;
		String sql = "UPDATE public.user SET salt = ?, password = ? WHERE email = ?";
		Connection connection = daoFactory.getConnection();
		
		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, salt);
			query.setString(2, hash);
			query.setString(3, email);

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
	
	public boolean registerUser(UserBean user) {
		boolean result = false;
		String sql = "INSERT INTO public.user (name, surname, username, email, salt, password, gender, description, dob) VALUES (?, ?, ?, ?, ?, ?, ?, NULL, ?)";
		Connection connection = daoFactory.getConnection();

		try {
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, user.getName());
			query.setString(2, user.getSurname());
			query.setString(3, user.getUsername());
			query.setString(4, user.getEmail());
			query.setString(5, user.getSalt());
			query.setString(6, user.getPassword());
			query.setBoolean(7, user.isGender());
			query.setDate(8, Date.valueOf(user.getDob()));
	
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

}
