/**
 * HangmanView.java

 * 
 * Version:
 *   $1.1$
 *   
 * Revisions:
 *   $1.2$
 */
import java.util.Scanner;
/**
 * HangmanView class is used for creating the Hangman Game View
 
 *  
 * @author Amruta Deshpande
 * @author Shreya Joshi
 */
public class HangmanView {
	public String getName() {
		String name=new String("Enter the name of the Player:") ;
		return name;
	}
	
	public char getAnswer() {
		char ans=' ';
		System.out.println("Do you want to continue the game:");
		Scanner scan = new Scanner(System.in);
		ans = scan.next().charAt(0);
		return ans;
	}
	public String guessLetter(){
		System.out.println("\nGuess a letter");
		Scanner inp= new Scanner(System.in);
		String inpletter=inp.next();
		return inpletter;
	}

}
