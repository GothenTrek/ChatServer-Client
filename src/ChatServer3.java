import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Server
 * Created:		05/02/2016
 * Version:		3.0
 */

public class ChatServer3
{
	private Socket socket;
	private ServerSocket server;
	static String serverInput;
	
	public static void main(String[]args) throws IOException
	{
		ChatServer3 server;
		
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		
		else
		{
			serverInput = "";
			int port = Integer.parseInt(args[0]);
			server = new ChatServer3(port);
		}
	}
	
	public ChatServer3(int port)
	{
		try
		{
			boolean stopServer = false;
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			
			while (!stopServer)
			{
				System.out.println("Waiting for connection...");
				socket = server.accept();
				
				new ChatServerThread(socket, this).start();
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public void chatMessage(String message)
	{
		System.out.println("Message sent: " + message);
	}
}
