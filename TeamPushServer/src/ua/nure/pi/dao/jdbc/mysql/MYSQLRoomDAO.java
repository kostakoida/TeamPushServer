package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCRoomDAO;

public class MYSQLRoomDAO extends JDBCRoomDAO {
	
private static volatile MYSQLRoomDAO instance;
	
	private MYSQLRoomDAO() {
	}
	
	public static MYSQLRoomDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLRoomDAO.class){
				if(instance == null)
					instance = new MYSQLRoomDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
