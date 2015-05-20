package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jfork.nproperty.Cfg;
import jfork.nproperty.ConfigParser;
import ua.nure.pi.dao.*;

@Cfg(prefix = "db.")
public class MYSQLDAOFactory extends DAOFactory {

	private static volatile MYSQLDAOFactory instance;
	
	protected static String sqlLink;
	
	protected static String user;
	
	protected static String password;
	
	protected static String host;
	
	protected static String port;
	
	protected static String database;
	
	protected static String DRIVER;
	
	private static boolean first = true;
	
	private MYSQLDAOFactory() {
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			ConfigParser.parse(MYSQLDAOFactory.class, 
					loader.getResource("properties.ini").getPath().replaceAll("%20", " "));
			DRIVER = "com.mysql.jdbc.Driver";
			//sqlLink = "jdbc:mysql://localhost:3306/cvsystem";
			sqlLink = "jdbc:mysql://"
					+host + ":"+ port + "/"
					+database;//user="+user+";password="+password;
			first = false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public static MYSQLDAOFactory getInstancce(){
		if(instance == null)
			synchronized (MYSQLDAOFactory.class){
				if(instance == null)
					instance = new MYSQLDAOFactory();
			}
		return instance;
	}
	
	//private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	/**
	 * method to create MYSQL connections
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected static synchronized Connection getConnection()
			throws SQLException {
		Connection con = null;
		if(first){
			MYSQLDAOFactory.getInstancce();
			first = false;
		}
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(sqlLink, user, password);
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (SQLException e) {
			System.err.println("Can not get connection. # " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Can not load driver. # " + e.getMessage());
		}
		return con;
	}

	@Override
	public UserDAO getUserDAO() {
		return MYSQLUserDAO.getInstancce();
	}

	

	@Override
	public PassDAO getPassDAO() {
		return MYSQLPassDAO.getInstancce();
	}

	@Override
	public RoomDAO getRoomDAO() {
		// TODO Auto-generated method stub
		return MYSQLRoomDAO.getInstancce();
	}


	@Override
	public TeamLeadDAO getTeamLeadDAO() {
		// TODO Auto-generated method stub
		return MYSQLTeamLeadDAO.getInstancce();
	}
	


}
