/**
 * Hangman.java

 * 
 * Version:
 *   $1.0$
 *   
 * Revisions:
 *   $1.0$
 */

import java.io.File;
import java.util.Timer;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import java.lang.*;


/**
 * Hangman class is used for creating the Hangman Game for multi palyers 
 
 *  
 * @author Amruta Deshpande
 * @author Shreya Joshi
 */


public class HangmanModel{
	
	String randomWord;
	static String randomWordFinal;
	//static boolean[] foundletter;
	int badGuess=9;
	int goodGuess=0;
	public static int totalPoints=0;
	static ArrayList<Integer> pointsNew=new ArrayList<>();
	
	static int numOfPlayer=0;
	static ArrayList<String> playerName=new ArrayList<>();
	static File fileName;
	static HangmanController hcont=new HangmanController();
	
	public static Object o=new Object();
	static int totalPlayer=2;
	static ArrayList<Integer> points=new ArrayList<>();
	static int	totalPointValue;
	boolean guessValue=false;
	public char[] foundletter = new char[8];


public void addName(String name){
	playerName.add(name);	//player names are added to ArrayList playerName
	System.out.println(playerName);
}

/**
 * chooseWord function randomly chooses a word for the game 
 * 
 * @param wordArray  ArrayList to store words from file
 * @param yourRandom Random word chosen by the Random() function
 * @param randomWord 
 * 
 */

public String chooseWord() throws FileNotFoundException{
	String temp = "words.txt";
	
	fileName=new File(temp);
	ArrayList<String> wordArray=new ArrayList<>();
	
	Scanner fileScanner=new Scanner(fileName);
	while(fileScanner.hasNext()){
		wordArray.add(fileScanner.next());
	}
	
	Random yourRandom = new Random();		// pick a random word for the game
	String randomWordNew = wordArray.get(yourRandom.nextInt(wordArray.size()));
	randomWord=randomWordNew.toLowerCase();
	//System.out.println("Random word is:"+randomWord);

	for(int iterator=0;iterator<randomWord.length();iterator++)
	{
	System.out.print("_ ");
	
	}
	for(int i=0;i<foundletter.length;i++){
		foundletter[i]='_';
		
	}
	
	return randomWord;
}

/**
 * winnerOfGame function calls getMax function and prints the maximum score
 * 
 * @param pointsNew          Its an arraylist used to store the total points of the players 
 * 
 */

public static void winnerOfGame(){
	for(int iteratork=0;iteratork<totalPlayer;iteratork++){
		totalPointValue=0;
	    for(int iterator=iteratork;iterator<points.size();iterator=iterator+totalPlayer){
	    	totalPointValue=totalPointValue+points.get(iterator);
		}//endofInnerFor	
	    pointsNew.add(totalPointValue);
	}//EndofOuterFor
	//System.out.println("New array"+pointsNew);
	
	System.out.println("\n\nThe Final ScoreBoard is ");
	
	int MaxVal=getMax(pointsNew);
	
	//System.out.println("Max val is"+MaxVal);	
}

/**
 * getMax function returns the maximum score of the players
 * 
 * @param max     contains the maximum score 
 * @param newval  it contains player score
 * 
 */
public static int getMax(ArrayList pointsNew){
	int max =Integer.MIN_VALUE ;
	//HangmanModel.pointsNew=pointsNew1;
	int kiterator,iterator;
	for(iterator=0;iterator<totalPlayer;iterator++)
	for(kiterator=iterator;kiterator<pointsNew.size();kiterator++){
		int newval;
	    newval= (int) pointsNew.get(kiterator);
	    if(newval > max){
	    	max = newval;					//The values in the pointsNew array is compared and the maximum is calculated
	    }
	}//endOfFor
	int removePlayer=pointsNew.indexOf(max);
	
	System.out.println("\n\nPlayer  "+playerName.get(removePlayer)+"  Score is "+max);
	pointsNew.remove(pointsNew.indexOf(max));		//The max value is removed and
	playerName.remove(removePlayer);
	if(pointsNew.size()!=0)
		getMax(pointsNew);				 //function is recursively called on the remaining array
	return max;
}

/**
 * guessWord function calculates badGuesses of the user and tells how many guesses are left.
 * If the character is already present in the goodguess then it just replaces the character in the
 * guess word else if the character is badguesses the player miss one chance for guessing the 
 * character.
 * 
 * @param badGuess  calculates the no. of incorrect guessed letter
 * @param goodGuess calculates the no. of correct guessed letter
 * @param totalPoints calculates the total score of the player
 * 
 */


public char[] guessWord(char ch,String randomWord){
	
	
	boolean[] boolArray=new boolean[8];
	
	
	//badGues
	totalPoints=0;
		
	guessValue=false;
		
		char putletter  ;
		char inputletter=ch;
		
		for(int iterator=0;iterator<randomWord.length();iterator++){
			if(randomWord.charAt(iterator)==inputletter)
			{
				putletter= randomWord.charAt(iterator);
				System.out.print(putletter);
				foundletter[iterator]=putletter;
				boolArray[iterator]=true;
				goodGuess++;
				guessValue=true;
				//	System.out.println("foundletterarray when char match first time"+foundletter);
			}	
			
				
		}
		if(guessValue==false)
		{
			badGuess=badGuess-1;
	    }
		
		return foundletter;
}


/**
 * checkGuess function calculates the totalPoints
 * 
 * @return returns the totalpoints of the player
 * 
 */
public int checkGuess(){
	if(badGuess==0){
		totalPoints=totalPoints-5;
	}
	else
	{
		totalPoints=totalPoints+10;
	}
	return totalPoints;
 }


}
