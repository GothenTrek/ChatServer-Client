import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
 * Authors:		Andrew Whitaker & George Othen
 * Title:		Chat Client
 * Created:		05/02/2016
 * Version:		1.0
 */

public class ChatClient
{
	
	public static void main(String[]args) throws IOException
	{
		Socket socket = null;
		System.out.println("Establishing connection. Please wait...");
		
		try
		{
			String server = args[0];
			int serverPort = Integer.parseInt(args[1]);
			
			socket = new Socket(server, serverPort);
			
			System.out.println("Client socket created: " + socket);
			
			String input = "";
			Scanner in = new Scanner(System.in);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			while(!input.equals(".bye"))
			{
				input = in.nextLine();
				out.println(input);
				out.flush();
			}
		}
		
		catch(UnknownHostException e){}		
		catch(IOException ioe){}
		
		finally {socket.close();}
	}
}
