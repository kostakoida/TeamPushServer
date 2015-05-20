package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.MessageDAO;
import ua.nure.pi.entity.Message;

public abstract class JDBCMessageDAO implements MessageDAO {
	
	protected String SQL_SELECT_MESSAGE_BY_ID = "SELECT * FROM Messages WHERE MessageID = ?";
	protected String SQL_SELECT_MESSAGES_BY_DATE = "SELECT * FROM Messages WHERE Date = ?";
	protected String SQL_SELECT_MESSAGES_BY_ROOM = "SELECT * FROM Messages WHERE Room = ?";
	protected String SQL_SELECT_MESSAGES_BY_USER = "SELECT * FROM Messages WHERE UserID = ?";

	
	protected String SQL_INSERT_MESSAGE = "INSERT INTO Messages (UserID, Date, Room) VALUES(?, ?, ?)";
	//protected String SQL_UPDATE_MESSAGE = "UPDATE Messages SET ";
	protected String SQL_DELETE_MESSAGE_BY_ID = "DELETE FROM Messages WHERE MessageID = ?";
	protected String SQL_DELETE_MESSAGES_BY_DATE = "DELETE FROM Messages WHERE Date = ?";
	protected String SQL_DELETE_MESSAGES_BY_ROOM = "DELETE FROM Messages WHERE Room = ?";
	protected String SQL_DELETE_MESSAGES_BY_USER = "DELETE FROM Messages WHERE UserID = ?";
	
	protected DAOFactory jdbcDAOFactory;
	
	@Override
	public Collection<Message> getMessages(){
		return null;
	}
	
	@Override
	public Message getMessage(long messageId){
		return null;
		}
		
	@Override
	public boolean insertMessage(Message message){
		return false;
		}
	
	@Override
	public boolean deleteMessage(Message message){
		return false;
		}
	
	protected abstract Connection getConnection() throws SQLException;
}
