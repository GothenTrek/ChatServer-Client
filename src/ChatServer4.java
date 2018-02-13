import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Server
 * Created:		05/02/2016
 * Version:		3.0
 */

public class ChatServer4
{
	
	private Socket clientSocket;
	private ServerSocket server;
	
	static String serverInput;
	int port;
	
	private ArrayList<ChatServerThread> clientThreadList;
	
	public static void main(String[]args) throws IOException
	{
		ChatServer4 server;
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
			
		
		else
		{
			serverInput = "";
			int port = Integer.parseInt(args[0]);
			server = new ChatServer4(port);
			server.startServer();
		}
	}
	
	public ChatServer4(int port)
	{
		this.port = port;
	}
	
	private void startServer() {
		boolean stopServer = false;
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			
			while (!stopServer)
			{
				System.out.println("Waiting for connection...");
				clientSocket = server.accept();
				//Create arraylist of threads to keep track of each client
				//this will then be used to get and send the messages from each client
				clientThreadList = new ArrayList<ChatServerThread>();
				ChatServerThread thread = new ChatServerThread(clientSocket, this);
				thread.start();
				clientThreadList.add(thread);				
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}

	
	
	public void chatMessage(String message) throws IOException
	{
		System.out.println("Message sent: " + message);
		for(ChatServerThread thread : clientThreadList)
		{
			thread.sendMessage(message);
		}
	}

	public void writeToConsole(String string) {
		System.out.println(string);
		
	}
}
