package ua.nure.pi.servelet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.pi.MainService;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		String all_data = new String(data);
		System.out.print(all_data);
		all_data = all_data.split(" ")[0];
		System.out.print(all_data);
		login = all_data.split("/")[0];
		pass = all_data.split("/")[1];
		//name = all_data.split("/")[2];
		in.close();
		System.out.println("\n");
		System.out.print(login + "/ " + pass + "/");
		String recievedString = new String(input);
		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(
				response.getOutputStream());

		mainservice = new MainServiceImpl();
		User user = new User();
		user.setLogin(login);
		user.setPassword(pass);

		user.setBan(false);
		user.setAdmin(false);
		writer.write(mainservice.insertUser(user).toString());
		writer.flush();
		writer.close();
	}

}
