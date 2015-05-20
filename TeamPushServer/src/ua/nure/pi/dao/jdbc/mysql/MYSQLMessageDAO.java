package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCMessageDAO;

public class MYSQLMessageDAO extends JDBCMessageDAO {
	
private static volatile MYSQLMessageDAO instance;
	
	private MYSQLMessageDAO() {
	}
	
	public static MYSQLMessageDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLMessageDAO.class){
				if(instance == null)
					instance = new MYSQLMessageDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
