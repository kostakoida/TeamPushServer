package ua.nure.pi;

import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.jdbc.JDBCUserDAO;
import ua.nure.pi.entity.User;
public class Demo {

	public static void main(String[] args) {
		User user = new User();
		user.setLogin("login");
		user.setPassword("pass");

	}

}
