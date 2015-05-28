package ua.nure.pi.servelet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;

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
 * Servlet implementation class GetMessages
 */
@WebServlet("/GetMessages")
public class GetMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static MainService mainservice;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessages() {
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
		//int length = request.getContentLength();
		//byte[] input = new byte[length];
		//int c, count = 0;
		String id;
		ServletInputStream in = request.getInputStream();
		BufferedInputStream bf = new BufferedInputStream((InputStream) in);
		StringBuffer data = new StringBuffer();
		int bit;
		while ((bit = bf.read()) != -1) {
			data.append((char) bit);
		}
		String all_data = new String(data);
		System.out.print(all_data);
		id = all_data.split(" ")[0];
		//name = all_data.split("/")[2];
		in.close();
		System.out.println("\n");
		System.out.print(id);
		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(
				response.getOutputStream());

		mainservice = new MainServiceImpl();
		Collection<Message> messages = mainservice.getMessages(Long.valueOf(id));
		if (messages != null)
			for (Message i : messages){
				writer.write(i.getUserSender()+"+/+"+i.getMeassageId()+"+/+"+i.getRoom()+"+/+"+i.getDataSender().toString()+"+/+"+i.getMessage());
			}
		else
			writer.write("Нет новых сообщений");
		
		writer.flush();
		writer.close();
	}

}
