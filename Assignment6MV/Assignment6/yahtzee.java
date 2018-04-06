/*
 * This program creates the simplified version of Yahtzee. It is the test file
 *  for the classes. It takes in user input to decide the die that are kept.
 *  The user has three thows and then decides if he wants to play again
 *  It calculates the user's score at the end.
 * CPSC 224-02, Spring 2018
 * Programming Assignment #5
 * Mauricio Velazquez
 * version 1.0
 */

import java.util.*;
import java.io.*;


public class yahtzee {
    
   static int DICE_IN_PLAY = 100; //Number of die
   static int hand[] = new int[100];
   
	//Main function where the user plays
	public static void main(String[] args) throws FileNotFoundException {
      
        File file = new File("yahtzeeConfig.txt");
        Scanner input = new Scanner(file);
        int dieSides = input.nextInt();
        int numberOfDie = input.nextInt();
        int numberOfRolls =input.nextInt();
        

        DICE_IN_PLAY = numberOfDie;
		dice dice1 = new dice();
		scoring score = new scoring();
		Scanner in = new Scanner(System.in);
		char playAgain = 'y';
		while (playAgain == 'y'){
		    String keep = "nnnnn"; //setup to roll all dice in the first roll
		    int turn = 1;
		    while (turn < numberOfRolls && keep != "yyyyy"){
		        //roll dice not kept
		        for (int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++){
		        		char keepChar = keep.charAt(dieNumber);
		            if(keepChar != 'y')
                        hand[dieNumber] = dice1.rollDie(dieSides);
		        }
		        //output roll
		        System.out.print("Your roll was: ");
		        for (int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++)
                    System.out.print(hand[dieNumber] + " ");
                
		        System.out.println();
		        //Checks if not last roll
		        if (turn < 3){
		        		System.out.print("Enter dice to keep (y or n): ");
		        		keep = in.next();	
		        }
		        turn++;
		    }
            
		    //start scoring
		    //hand need to be sorted to check for straights
		    dice1.sortArray(hand, DICE_IN_PLAY);
		    
		    System.out.print("Here is your sorted hand : ");
		    for (int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++){
		        System.out.print(hand[dieNumber] + " ");
		    }
		    System.out.println();
		    //upper scorecard
		    for (int dieValue = 1; dieValue <=6; dieValue++){
		        int currentCount = 0;
		        for (int diePosition = 0; diePosition < 5; diePosition++)
                    if (hand[diePosition] == dieValue)
		                currentCount++;
                
		       System.out.print("Score " + dieValue * currentCount + " on the ");
		       System.out.println(dieValue + " line");
		    }
		    //lower score card
		    if (score.maxOfAKindFound(hand) >= 3){
		        System.out.print("Score " + dice1.totalAllDice(hand) + " on the ");
		        System.out.println("3 of a Kind line");
		    }
		    else System.out.println("Score 0 on the 3 of a Kind line");

		    if (score.maxOfAKindFound(hand) >= 4){
		        System.out.print("Score " + dice1.totalAllDice(hand) + " on the ");
		        System.out.println("4 of a Kind line");
		    }
		    else System.out.println("Score 0 on the 4 of a Kind line");

		    if (score.fullHouseFound(hand))
		        System.out.println("Score 25 on the Full House line");
		    else
		        System.out.println("Score 0 on the Full House line");

		    if (score.maxStraightFound(hand) >= 4)
		        System.out.println("Score 30 on the Small Straight line");
		    else
		    		System.out.println("Score 0 on the Small Straight line");

		    if (score.maxStraightFound(hand) >= 5)
		    		System.out.println("Score 40 on the Large Straight line");
		    else
		    		System.out.println("Score 0 on the Large Straight line");

		    if (score.maxOfAKindFound(hand) >= 5)
		    		System.out.println("Score 50 on the Yahtzee line");
		    else
		    		System.out.println("Score 0 on the Yahtzee line");

		    System.out.print("Score " + dice1.totalAllDice(hand) + " on the ");
		    System.out.println("Chance line");
		    System.out.print("Enter 'y' to play again: ");
		    
		    playAgain = in.next().charAt(0);
		}
			System.out.println();
			System.out.println("Program ending...");
		    return;
	}
}
