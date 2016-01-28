/**
 * Client3.java
 * 
 * Version:
 *   $1.0$
 *   
 * Revisions:
 *   $1.0$
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Client1 class is a client class which is connected to a socket 
 * in order to interact with the server for playing the hangman Game. 
 *  
 * @author Amruta Deshpande
 * @author Shreya Joshi
 */

public class Client3 {
	static String cliArray=new String() ;
	public static void main(String[] args) throws UnknownHostException, IOException, Exception {
		Socket sock = new Socket("localhost", 2245);
		
			ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());         //to read from socket
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());    //to write to socket
		  	byte[] buffer = new byte[1024];
		   	int n,count=0;
			System.out.println("In Client1");
			//System.out.println("here I am");
			String s = (String)ois.readObject();
			System.out.println(s);
			String name =new String() ;
			Scanner inpname = new Scanner(System.in);
	    	name = inpname.next();
			oos.writeObject(name);
		
			System.out.println(""+(String)ois.readObject());
			String random=new String();
			
			try {
					random = (String)ois.readObject();
			}
			catch (ClassNotFoundException e1) {	
					e1.printStackTrace();
			}
			for(int iterator=0;iterator<random.length();iterator++){
					System.out.print("_ ");
			}
				
				String guessLetter =new String() ;
				Integer cliBad=9;
				Integer cliGood=0;
				while(cliBad>1 && cliGood<7){
					System.out.println("Enter the character to be guessed!");	
					guessLetter = inpname.next();
					oos.writeObject(guessLetter.charAt(0));
					count++;
				try{
					cliBad=(Integer)ois.readObject();
					cliGood=(Integer)ois.readObject();
					//System.out.println("bad and good are::"+cliBad+""+cliGood);
					String toCli=new String();
					toCli=(String)ois.readObject();
					//System.out.println(""+toCli);
					cliArray= (String) ois.readObject();
					System.out.println("Result word is::"+cliArray.toString()); //if word is guessed incorrect display the result word
					} 
		    	catch (ClassNotFoundException e) {
					   e.printStackTrace();
				    } 
		    	catch(SocketException e){
						System.out.println(" Game over !!");
						System.exit(0);
				    }
		    	
				}//while ends
				String pts=new String();
				pts=(String)ois.readObject();
				System.out.println("The word is ::"+random+pts);
				}
}
