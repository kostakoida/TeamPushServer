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
 * Servlet implementation class UserServelet
 */
@WebServlet("/UserServelet")
public class UserServelet extends HttpServlet {
	static MainService mainservice;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().println("Hurray !! This Servlet Works");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*int length = request.getContentLength();
         byte[] input = new byte[length];
         int c, count = 0 ;
         String login;
         String pass;
         ServletInputStream in = request.getInputStream();
         BufferedInputStream bf = new BufferedInputStream((InputStream)in);
         StringBuffer data = new StringBuffer();
         int bit;
         while((bit = bf.read()) != -1)
         {
                 data.append((char)bit);
         }

         String all_data = new String(data);
         login = all_data.split(all_data, '/')[0];
         pass =  all_data.split(all_data, '/')[1];
         in.close();

         String recievedString = new String(input);
         response.setStatus(HttpServletResponse.SC_OK);
         OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());

         Integer doubledValue = Integer.parseInt(recievedString) * 2;

     	 mainservice = new MainServiceImpl();
         writer.write(mainservice.checkLogined(login, pass).toString());
         writer.flush();
         writer.close();*/
	}

}
