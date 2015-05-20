package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.PassDAO;

public abstract class JDBCPassDAO implements PassDAO {
	
	protected String SQL_ = "";

	protected DAOFactory jdbcDAOFactory;
	
	@Override
	public Boolean checkPass(String pass){
		return null;
	}
	
	protected abstract Connection getConnection() throws SQLException;
}
