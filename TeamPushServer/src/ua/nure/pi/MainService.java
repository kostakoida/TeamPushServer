package ua.nure.pi;

import java.util.Collection;

import ua.nure.pi.entity.Message;
import ua.nure.pi.entity.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("")
public interface MainService extends RemoteService {
	
	Boolean checkLogined(String login) throws IllegalArgumentException;
	
	Boolean checkLogined(String login, String pass) throws IllegalArgumentException;
	
	Boolean insertUser(User User) throws IllegalArgumentException;
	
	Boolean insertMessage(Message message) throws IllegalArgumentException;
	
	Collection<Message> getMessages(long id) throws IllegalArgumentException;
	
}
