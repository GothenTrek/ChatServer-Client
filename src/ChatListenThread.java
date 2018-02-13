import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatListenThread extends Thread{
	
	Socket socket;
	Scanner servIn;
	public ChatListenThread(Socket socket) throws IOException
	{
		this.socket = socket;
		servIn = new Scanner(socket.getInputStream());
	}
	
	public void run()
	{
		while(true)
		{
			String serverResponse = servIn.nextLine();
			System.out.println(serverResponse);
		}
	}
}
