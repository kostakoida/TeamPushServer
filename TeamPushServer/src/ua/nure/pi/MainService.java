package ua.nure.pi;

import java.util.Collection;

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
	
	
	void insertUser(User User) throws IllegalArgumentException;
	
}
