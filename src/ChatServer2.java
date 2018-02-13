import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Server
 * Created:		05/02/2016
 * Version:		2.0
 */

public class ChatServer2
{
	private Socket socket;
	private ServerSocket server;
	static String serverInput;
	
	public static void main(String[]args) throws IOException
	{
		ChatServer2 server;
		
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		
		else
		{
			serverInput = "";
			int port = Integer.parseInt(args[0]);
			server = new ChatServer2(port);
		}
	}
	
	public ChatServer2(int port) throws IOException
	{
		try
		{
			boolean stopServer = false;
			server = new ServerSocket(port);
			
			while (!stopServer)
			{
				System.out.println("Server started: " + server);
				System.out.println("Waiting for connection...");
				socket = server.accept();
				System.out.println("Socket created: " + socket);
				
				Scanner in = new Scanner(socket.getInputStream());
				Scanner serverIn = new Scanner(System.in);
				
				boolean done = false;
				
				while(!done)
				{
					String line = in.nextLine();
					System.out.println(line);
					done = line.equals(".bye");
				}
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
