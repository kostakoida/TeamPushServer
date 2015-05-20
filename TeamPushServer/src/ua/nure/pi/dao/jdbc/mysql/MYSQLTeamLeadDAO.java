package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.dao.jdbc.JDBCTeamLeadDAO;
import ua.nure.pi.entity.User;

public class MYSQLTeamLeadDAO extends JDBCTeamLeadDAO {
	
private static volatile MYSQLTeamLeadDAO instance;
	
	private MYSQLTeamLeadDAO() {
	}
	
	public static MYSQLTeamLeadDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLTeamLeadDAO.class){
				if(instance == null)
					instance = new MYSQLTeamLeadDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}

	@Override
	public boolean containsUser(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertUser(User user, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(long id, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setSettings() {
		// TODO Auto-generated method stub
		return false;
	}

}
