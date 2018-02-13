import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Client
 * Created:		05/02/2016
 * Version:		2.0
 */

public class ChatClient2
{
	private static PrintWriter out;
	private static Scanner in;
	static Socket clientSocket;	
	private String username;
	private static String clientInput = "";

	
	
	
	public static void main(String[]args) throws IOException
	{
		
		System.out.println("Establishing connection. Please wait...");
		
		try
		{
			String server = args[0];
			int serverPort = Integer.parseInt(args[1]);
			
			clientSocket = new Socket(server, serverPort);
			
			System.out.println("Client socket created: " + clientSocket);
			
			out = new PrintWriter(clientSocket.getOutputStream());
			in = new Scanner(System.in);
			
			System.out.print("Enter Login Name: ");
			ChatListenThread t = new ChatListenThread(clientSocket);
			t.start();
			
			
			
			
			while(!clientInput.equals(".bye"))
			{				
				clientInput = in.nextLine();				
				out.println(clientInput);
				out.flush();				
			}
		}
		
		catch(UnknownHostException e){}		
		catch(IOException ioe){}		
		finally {clientSocket.close();}
	}
	
	public void printMessage(String mes)
	{
		System.out.println(mes);
	}
}
