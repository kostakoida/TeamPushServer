package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Message;

public interface MessageDAO {
	
	public Collection<Message> getMessages();
	
	public Message getMessage(long messageId);
		
	public boolean insertMessage(Message message);
	
	public boolean deleteMessage(Message message);
}
