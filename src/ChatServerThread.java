import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Thread
 * Created:		05/02/2016
 * Version:		1.0
 */

public class ChatServerThread extends Thread
{
	ChatServer4 server;
	Socket clientSocket;
	String loginName;
	int portNumber;
	String line;
	PrintWriter out;
	Scanner serverIn;
	int id;
	boolean done = false, firstLine = true;
	
	public ChatServerThread(Socket sock, ChatServer4 serv)
	{
		clientSocket = sock;
		server = serv;
		
		try
		{
			out = new PrintWriter(clientSocket.getOutputStream());
			serverIn = new Scanner(clientSocket.getInputStream());
			
			setLoginName(serverIn.nextLine());
			
			server.writeToConsole(loginName + ": Just connected");
		}
		catch (IOException e){
			
		}
	}
	
	@Override
	public void run()
	{
		System.out.println("Socket created: " + clientSocket);
		try {			
						
			while(!done)
			{
				line = serverIn.nextLine();
				server.chatMessage(loginName + ">> " + line);
				done = line.equals(".bye");
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		finally
		{
			try
			{
				clientSocket.close();
				System.out.println("Connection closed...");
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setLoginName(String name)
	{
		loginName = name;
	}
	
	public void getPort()
	{
		portNumber = clientSocket.getPort();
	}
	
	public void sendMessage(String mes) throws IOException
	{
		try
		{
			out = new PrintWriter(clientSocket.getOutputStream());
			out.println(mes);
			out.flush();
		}
		catch(IOException e){
			System.out.println("Nah mate, no one there");
		}
	}
}
