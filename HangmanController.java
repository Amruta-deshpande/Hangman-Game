/**
 * HangmanController.java

 * 
 * Version:
 *   $1.1$
 *   
 * Revisions:
 *   $1.2$
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * HangmanController class is used for creating the Hangman Game Controller
 
 *  
 * @author Amruta Deshpande
 * @author Shreya Joshi
 */
public class HangmanController {
	public HangmanModel model;
	public HangmanView view;
	HangmanView view1=new HangmanView();
	public static ArrayList<String> playerName;
	
	
	String contName=new String();
	char ansCont;
	String contGuessLetter;
	public HangmanController(HangmanModel model,HangmanView view){
	      this.model = model;
	      this.view = view;
	     // this.playerName=playerName;
	  }
	public HangmanController(){}
	/*
	 * getAgainName() method prompts the user to enter his name from view and passes it to model
	 * @return contName2 Name of the user
	 */
	public String getAgainName() {
		contName=view1.getName();
		return contName;
	}
	public char getAgainAns() {
		ansCont=view1.getAnswer();
		return ansCont;
	}
	public String contGuessLetter(){
		contGuessLetter=view1.guessLetter();
		return contGuessLetter;
	}
	
	

}
