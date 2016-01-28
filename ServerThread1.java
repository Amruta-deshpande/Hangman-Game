/**
 * ServerThread1.java

 * 
 * Version:
 *   $1.1$
 *   
 * Revisions:
 *   $1.2$
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Serverthread1 class is used for creating the Hangman Game for multi palyers 
 
 *  
 * @author Amruta Deshpande
 * @author Shreya Joshi
 */



public class ServerThread1 implements Runnable {
	private ServerSocket server;
	
	public ServerThread1(int port) throws IOException {
		server = new ServerSocket(port);
	}
	/**
	 * run function accepts multiple requests & starts client thread.
	 * @param count to keep count of number of clients
	 */
	public void run() {
		int count=0;
		while(count<4) {
			try {
				System.out.println("waiting for connections...");
				Socket client = server.accept();
				System.out.println("connection estalished! with"+count);
				new Thread(new ClientHandler(client)).start();
				count++;
				
			} 
			catch (IOException e) {
			} 
		}
	}
	/**
	 * main function starts the server thread.
	 */
	public static void main(String[] args) throws Exception {
		new Thread(new ServerThread1(2245)).start();
	}
	/*
	 * ClientHandler class handles the client server interactions
	 */
	private class ClientHandler implements Runnable {
		private Socket sock;
		public ObjectOutputStream oos;
		public ObjectInputStream ois;
		
		
		ClientHandler(Socket sock) {
			this.sock = sock;
		}
		
		
		public void run() {
			try {
				System.out.println("In clientHandler");
				oos = new ObjectOutputStream(sock.getOutputStream());
				ois=new ObjectInputStream(sock.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				new Server2().myMethod(oos,ois);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}//end of run
	}

}
