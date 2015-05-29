package ua.nure.pi.servelet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.pi.MainService;
import ua.nure.pi.entity.Message;
import ua.nure.pi.entity.User;
import ua.nure.pi.server.MainServiceImpl;

/**
 * Servlet implementation class AddMessage
 */
@WebServlet("/AddMessage")
public class AddMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static MainService mainservice;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().println("Hurray !! This Servlet Works");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int length = request.getContentLength();
		byte[] input = new byte[length];
		int c, count = 0;
		String login;
		String pass;
		String name;
		ServletInputStream in = request.getInputStream();
		BufferedInputStream bf = new BufferedInputStream((InputStream) in);
		StringBuffer data = new StringBuffer();
		int bit;
		while ((bit = bf.read()) != -1) {
			data.append((char) bit);
		}
		System.out.println(data);
		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(
				response.getOutputStream());

		mainservice = new MainServiceImpl();
		Message message = new Message();
		Pattern pattern = Pattern
				.compile("<!([^#]+)#, ([^#]+)#, ([^#]+)#, ([^#]+)#, ([^#]+)#>");
		Matcher result = pattern.matcher(data);
		// message.setMeassageId(Long.parseLong(result.group(1)));
        while (result.find()) {

			message.setMessage(result.group(2));
			message.setUserSender(result.group(3));
			message.setDataSender(result.group(4));
			message.setRoom(result.group(5));
		}
		writer.write((mainservice.insertMessage(message)).toString());
		writer.flush();
		writer.close();
	}

}