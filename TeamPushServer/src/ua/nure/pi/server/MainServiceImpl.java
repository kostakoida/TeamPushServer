package ua.nure.pi.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ua.nure.pi.MainService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.pi.Path;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.RoomDAO;
import ua.nure.pi.dao.TeamLeadDAO;
import ua.nure.pi.dao.MessageDAO;
import ua.nure.pi.dao.PassDAO;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.jdbc.JDBCUserDAO;
import ua.nure.pi.dao.jdbc.mysql.MYSQLMessageDAO;
import ua.nure.pi.dao.jdbc.mysql.MYSQLUserDAO;
import ua.nure.pi.entity.Message;
import ua.nure.pi.entity.Room;
import ua.nure.pi.entity.User;
import ua.nure.pi.parameter.AppConstants;
import ua.nure.pi.security.Hashing;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.BitmapEncoder.BitmapFormat;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.StyleManager.ChartType;
import com.xeiam.xchart.StyleManager.LegendPosition;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements
		MainService {

	private PassDAO passDAO;

	private UserDAO userDAO;
	
	private MessageDAO messageDAO;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * if (log.isDebugEnabled()) { log.debug("GET method starts"); }
		 */
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.PAGE__MAIN);
		dispatcher.forward(request, response);
		/*
		 * if (log.isDebugEnabled()) { log.debug("Response was sent"); }
		 */
	}

	public MainServiceImpl()
	{
		userDAO = MYSQLUserDAO.getInstancce();
		messageDAO = MYSQLMessageDAO.getInstancce();
	}
	@Override
	public void init() {
		ServletContext servletContext = getServletContext();

		passDAO = (PassDAO) servletContext.getAttribute(AppConstants.PASS_DAO);
		userDAO = (UserDAO) servletContext.getAttribute(AppConstants.USER_DAO);
		messageDAO = (MessageDAO) servletContext.getAttribute(AppConstants.MESSAGE_DAO);
		

		if (passDAO == null) {
			throw new IllegalStateException("PassDAO attribute is not exists.");
		}

		if (userDAO == null) {
			throw new IllegalStateException("UserDAO attribute is not exists.");
		}
		if (messageDAO == null) {
			throw new IllegalStateException("UserDAO attribute is not exists.");
		}
		
	}

	@Override
	public Boolean insertUser(User user) throws IllegalArgumentException {
		
		if (!userDAO.insertUser(user)) {
			 return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Boolean checkLogined(String login) throws IllegalArgumentException {
		if (userDAO.containsUser(login)) {
			 return true;
		}
		return false;
	}

	@Override
	public Boolean checkLogined(String login, String pass)
			throws IllegalArgumentException {
		if (userDAO.containsUser(login, pass)) {
			 return true;
		}
		return false;
	}

	@Override
	public Boolean insertMessage(Message message) throws IllegalArgumentException {
		if (messageDAO.insertMessage(message)) {
			 return true;
		}
		return false;
	}

	@Override
	public Collection<Message> getMessages(long id) throws IllegalArgumentException {
		if (messageDAO.getMessages(id).size() > 0) {
			return messageDAO.getMessages(id);
		}
		return null;
	}
	

}
