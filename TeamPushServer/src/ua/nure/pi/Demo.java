package ua.nure.pi;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.GWT;

import ua.nure.pi.MainService;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.jdbc.JDBCUserDAO;
import ua.nure.pi.entity.Message;
import ua.nure.pi.entity.User;
import ua.nure.pi.server.MainServiceImpl;
public class Demo {
	static MainService mainservice;
	public static void main(String[] args) {
		
		Message message = new Message();
		message.setDataSender(new Date(1996, 21, 04));
		message.setMessage("Message");
		message.setRoom("Room 1");
		message.setUserSender("kostya");
		mainservice = new MainServiceImpl();
		
		//mainservice.insertUser(user);
	//	System.out.print(mainservice.insertMessage(message));
		Collection<Message> messages = mainservice.getMessages(2);
		if (messages != null)
			for (Message i : messages)
				System.out.println(i.getMessage());
		else
			System.out.println("Нет новых сообщений");
	}

}
