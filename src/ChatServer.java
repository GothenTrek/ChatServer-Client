import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Server
 * Created:		05/02/2016
 * Version:		1.0
 */

public class ChatServer
{
	private Socket socket;
	private ServerSocket server;
	
	public static void main(String[]args) throws IOException
	{
		ChatServer server;
		
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		
		else
		{
			int port = Integer.parseInt(args[0]);
			server = new ChatServer(port);
		}
	}
	
	public ChatServer(int port) throws IOException
	{		
		try
		{
			server = new ServerSocket(port);
			
			System.out.println("Server started: " + server);
			System.out.println("Waiting for connection...");
			socket = server.accept();
			System.out.println("Socket created: " + socket);
			
			Scanner in = new Scanner(socket.getInputStream());
			
			boolean done = false;
			
			while(!done)
			{
				String line = in.nextLine();
				System.out.println(line);
				done = line.equals(".bye");
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
		
		finally
		{
			socket.close();
			System.out.println("Connection closed...");
		}
	}
}
