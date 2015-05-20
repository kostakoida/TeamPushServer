package ua.nure.pi;

import com.google.gwt.core.client.GWT;

import ua.nure.pi.MainService;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.jdbc.JDBCUserDAO;
import ua.nure.pi.entity.User;
import ua.nure.pi.server.MainServiceImpl;
public class Demo {
	static MainService mainservice;
	public static void main(String[] args) {
		
		User user = new User();
		user.setLogin("login");
		user.setPassword("pass");
		user.setBan(false);
		user.setAdmin(false);
		mainservice = new MainServiceImpl();
		
		mainservice.insertUser(user);
	}

}
