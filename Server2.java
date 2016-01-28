/**
 * Server2.java

 * 
 * Version:
 *   $1.1$
 *   
 * Revisions:
 *   $1.2$
 */
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
/**
 * Server2 class is used for creating the Hangman Game for multi palyers 
 
 *  
 * @author Amruta Deshpande
 * @author Shreya Joshi
 */
public class Server2 {
	public static int servBadGuess;
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	/*
	 * myMethod function calls the functionality to implements the controller logic
	 * @param model object for HangmanModel
	 * @param view object for HangmanView
	 * @param randomWordFinal the random word selected by the model
	 * @param servArray character array to store returned value for result word
	 */
	public void myMethod(ObjectOutputStream oost,ObjectInputStream oist) throws IOException, Exception {
		this.oos=oost;
		this.ois=oist;
		HangmanModel model=new HangmanModel();
		HangmanView view=new HangmanView();
		HangmanController controller=new HangmanController(model,view);
		System.out.println("connection estalished in server2!");
		String s1=controller.getAgainName();		//calling the controller method
		//String s1 = new String("Enter the name of the player!");
		oos.writeObject(s1);
		
		
		String s=null;
		try{
			s = (String) ois.readObject();
		}
		catch(SocketException se){
				System.out.println("Game Over");
		}
		System.out.println("The player name is:"+s);
		model.addName(s);
		String randomWordFinal=new String();
		randomWordFinal=model.chooseWord(); 
		System.out.println(randomWordFinal);
		String s2=new String("The word to guess is:");
		oos.writeObject(s2);
		oos.writeObject(randomWordFinal);
		while(model.badGuess> 0 && model.goodGuess< 8){
			System.out.println(model.badGuess+"------");
			System.out.println(model.goodGuess+"------");
			Integer servBad=model.badGuess;
			Integer servGood=model.goodGuess;
			char ch=(char)ois.readObject();
			System.out.println("The character is"+ch);
			char[] servArray=model.guessWord(ch,randomWordFinal);
					
					
			String toPass=Arrays.toString(servArray);
			
			if(model.guessValue==false){
					String badguess=new String("Bad guess, The number of guesses remaining are :: "+Integer.toString(model.badGuess)+"\n");
					oos.writeObject(servBad);
					oos.writeObject(servGood);
					System.out.println("servbad in bad"+servBad+"servGood in bad"+servGood);
					oos.writeObject(badguess);
					System.out.println(toPass);
					oos.writeObject(toPass);
			}
			else{
					String goodguess=new String("Good guess, The number of guesses remaining are :: "+Integer.toString(model.badGuess)+"\n");
					oos.writeObject(servBad);
					oos.writeObject(servGood);
					System.out.println("servbad in good"+servBad+"servGood in good"+servGood);
					oos.writeObject(goodguess);
					System.out.println(toPass);
					oos.writeObject(toPass);
			}
	  }
	 System.out.println("After while");
    	if(model.badGuess==0){
    		System.out.println("in if loop");
			int totalpoints=model.checkGuess();
			String tp=new String("\n Sorry wrong guess!!! Your points are "+Integer.toString(totalpoints));
			oos.writeObject(tp);
	    }
		else{
			System.out.println("in if loop");
			int totalpoints=model.checkGuess();
			String tp=new String("\n Good guess!!!Your points are "+Integer.toString(totalpoints));
			oos.writeObject(tp);
		}
					
	}//endofMethod
		
}//endOfclass
