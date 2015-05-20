package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.TeamLeadDAO;
import ua.nure.pi.entity.Room;
import ua.nure.pi.entity.User;

public abstract class JDBCTeamLeadDAO implements TeamLeadDAO {
	
	protected String SQL_ = "";
	
	protected DAOFactory jdbcDAOFactory;
	
	@Override
	public boolean addBanList(User user){
		return false;		
	}
	
	@Override
	public boolean deleteRomm(Room room) {
		return false;
	}
	
	protected abstract Connection getConnection() throws SQLException;
}
