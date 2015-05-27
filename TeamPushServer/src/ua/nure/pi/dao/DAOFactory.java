package ua.nure.pi.dao;

import ua.nure.pi.dao.jdbc.mysql.MYSQLDAOFactory;


public abstract class DAOFactory {
	public static final int MSSQL = 1;
	public static final int MYSQL = 2;
	
	public static DAOFactory getDAOFactory() {
			return MYSQLDAOFactory.getInstancce();
	}

	
	public abstract UserDAO getUserDAO();
	
	public abstract PassDAO getPassDAO();
	
	public abstract MessageDAO getMessageDAO();
}
