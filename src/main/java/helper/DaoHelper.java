package helper;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoHelper {
	
	public DaoHelper() {};
	
	public void closeConnection (Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
