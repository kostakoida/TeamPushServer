package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.MessageDAO;
import ua.nure.pi.entity.Message;
import ua.nure.pi.entity.User;
import ua.nure.pi.parameter.MapperParameters;
import ua.nure.pi.security.Hashing;

public abstract class JDBCMessageDAO implements MessageDAO {
	
	protected String SQL_SELECT_MESSAGE_BY_ID = "SELECT * FROM Messages WHERE MessageID = ?";
	protected String SQL_SELECT_MESSAGE = "SELECT * FROM Messages WHERE MessageID > ?";
	protected String SQL_SELECT_MESSAGES_BY_DATE = "SELECT * FROM Messages WHERE Date = ?";
	protected String SQL_SELECT_MESSAGES_BY_ROOM = "SELECT * FROM Messages WHERE Room = ?";
	protected String SQL_SELECT_MESSAGES_BY_USER = "SELECT * FROM Messages WHERE User = ?";

	
	protected String SQL_INSERT_MESSAGE = "INSERT INTO Messages (User, Date, Room, Message) VALUES(?, ?, ?, ?)";
	//protected String SQL_UPDATE_MESSAGE = "UPDATE Messages SET ";
	protected String SQL_DELETE_MESSAGE_BY_ID = "DELETE FROM Messages WHERE MessageID = ?";
	protected String SQL_DELETE_MESSAGES_BY_DATE = "DELETE FROM Messages WHERE Date = ?";
	protected String SQL_DELETE_MESSAGES_BY_ROOM = "DELETE FROM Messages WHERE Room = ?";
	protected String SQL_DELETE_MESSAGES_BY_USER = "DELETE FROM Messages WHERE User = ?";
	
	protected DAOFactory jdbcDAOFactory;
	
	@Override
	public Collection<Message> getMessages(long id) {
		Collection<Message> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getMessages(con, id);
			
		} catch (SQLException e) {
			System.err.println("Can not get faculties." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. " + e.getMessage());
			}
		}
		return result;
	}
	
	private Collection<Message> getMessages(Connection con, long id)
			throws SQLException {
		Collection<Message> result = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SQL_SELECT_MESSAGE);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			result = new ArrayList<Message>();
			while(rs.next()){
				Message fc = unMapMessage(rs);
				result.add(fc);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}
	
	private Message unMapMessage(ResultSet rs) throws SQLException {
		Message fc = new Message();
		fc.setMeassageId((rs.getLong(MapperParameters.MESSAGE_ID)));
		fc.setRoom(rs.getString(MapperParameters.ROOM_SENDER));
		fc.setUserSender((rs.getString(MapperParameters.USER_SENDER)));
		fc.setMessage(rs.getString(MapperParameters.TEXT));
		fc.setDataSender(rs.getString(MapperParameters.DATA_SENDER));
		return fc;
	}
	@Override
	public Message getMessage(long messageId){
		return null;
		}
		
	@Override
	public boolean insertMessage(Message message){
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertMessage(message, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert user." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return result;
		}
	
	public boolean insertMessage(Message message, Connection con)throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = con.prepareStatement(SQL_INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS);
			mapMessage(message, pstmt);
			result = pstmt.executeUpdate() == 1;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
				message.setMeassageId(rs.getLong(1));
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}
	 //(UserID, Date, Room, Message)
	private void mapMessage(Message message, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, message.getUserSender());
		pstmt.setString(2, message.getDataSender());
		pstmt.setString(3, message.getRoom());
		pstmt.setString(4, message.getMessage());
		}
	
	@Override
	public boolean deleteMessage(Message message){
		return false;
		}
	
	protected abstract Connection getConnection() throws SQLException;
}
