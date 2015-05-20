package ua.nure.pi.dao;

import ua.nure.pi.dao.jdbc.mysql.MYSQLDAOFactory;


public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory() {

		return MYSQLDAOFactory.getInstancce();
	}
	public abstract PassDAO getPassDAO();
	
	public abstract RoomDAO getRoomDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract TeamLeadDAO getTeamLeadDAO();
	
}
