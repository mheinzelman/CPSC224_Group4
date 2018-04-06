/**
 * Hand creates an instance of Die, and creates a hand of 5 dice values
 * CPSC 224-02, Spring 2018
 * Programming Assignment 6
 * 
 * @author Max Heinzelman
 * @version v1.0 3/9/18
 */
import java.io.*;

import java.util.*;

public class Hand {
	private Die dieToRoll;
	private ArrayList <Integer> handToUse;
	private int sides = 0;
	private int dice = 0;
	private int rolls = 0;
	/*
	public static void main(String[] args) {
		Hand max = new Hand();
		max.createHand();
		//max.displayHand();	
		max.sortArray();
		max.displayHand();
		//System.out.println(max.maxOfAKind());
	}*/
	
	Hand() {
		configGame();
		dieToRoll = new Die(sides);
		handToUse = new ArrayList <Integer> ();
		//createHand();
	}
	
	/**
	 * createHand creates a hand of a predetermined number of dice values and allows 
	 * the user to reroll twice if they wish to. it then
	 * displays your sorted hand when you are done
	 */
	public void createHand() {
		handToUse.clear();
		for (int i = 0; i < dice; i++) {
			handToUse.add(dieToRoll.rollDie());
		}
		
		
		//create a new scanner to take in input from user about
		//what dice rolls to keep and to change
		Scanner console = new Scanner(System.in);
		
		//loop through and ask which dice to keep and change 2 times
		for (int i = 0; i < rolls - 1; i++) {
			//display the current hand
			System.out.print("Your roll was: ");
			displayHand();
			
			System.out.print("Enter dice to keep (y or n) ");
			String discard = console.next();
			
			//make sure the user enters the correct amount of letters and only y or n
			while ((discard.length() < dice || discard.length() > dice)) {
				System.out.print("Please enter " + dice + " letters (y or n) ");
				discard = console.next();
			}
			
			for (int z = 0; z < discard.length(); z++) {
				if (discard.charAt(z) != 'n' && discard.charAt(z) != 'y') {
					System.out.print("Please enter only y or n ");
					discard = console.next();
				}
			}
			
			//if the user wants to keep all dice, break from the loop
			int numbOfYes = 0;
			for (int j = 0; j < dice; j++) {
				if (discard.charAt(j) == 'y')
					numbOfYes++;
			}
			if (numbOfYes == dice)
				break;
			
			//replace all that the user wished to change with a new roll
			for (int j = 0; j < handToUse.size(); j++) {
				if (discard.charAt(j) == 'n') 
					handToUse.set(j, dieToRoll.rollDie());
			}
			
		}
		sortArray();
		System.out.print("Here is your sorted hand : ");
		displayHand();
	}
	
	/**
	 * sortArray sorts the final hand of die values
	 */
	public void sortArray() {
		Collections.sort(handToUse);
	}
	
	/**
	 * displayHand displays the current hand
	 */
	public void displayHand() {
		for (int i = 0; i < handToUse.size(); i++) {
			System.out.print(handToUse.get(i) + " ");
		}
		System.out.println();
	}
	
	/**
	 * retrieves the in at the certain index in the hand
	 * @param index
	 * @return value at index in the hand
	 */
	public Integer intAt(int index) {
		return handToUse.get(index);
	}
	
	/**
	 * configGame reads the contents of yahtzeeConfig.txt to set the new game rules.
	 * it also asks the user if they would like to change the current rules.
	 */
	public void configGame(){
		Scanner text;
		try {
			text = new Scanner(new File("yahtzeeConfig.txt"));
			sides = text.nextInt();
			dice = text.nextInt();
			rolls = text.nextInt();
		} catch (FileNotFoundException e) {
			System.out.println("No File Found.");
		}
		
		System.out.println("You are playing with " + dice + " " + sides + "-sided dice.");
		System.out.println("You get " + rolls + " per hand.");
		System.out.println("Enter 'y' if you would like to change the configuration");
		Scanner read = new Scanner(System.in);
		String reconfig = read.next();
		if (reconfig.equals("y")) {
			System.out.println("enter the number of sides on each die. ");
			while (!read.hasNextInt()) {
				System.out.println("please enter a number ");
				read.next();
			}
			sides = read.nextInt();
		
			System.out.println("enter the number of dice in play. ");
			while (!read.hasNextInt()) {
				System.out.println("please enter a number ");
				read.next();
			}
			dice = read.nextInt();
		
			System.out.println("enter the number of rolls per hand. ");
			while (!read.hasNextInt()) {
				System.out.println("please enter a number ");
				read.next();
			}
			rolls = read.nextInt();
			
			//rewrite yahtzeeConfig.txt with the new numbers
			try {
				PrintStream write = new PrintStream(new File("yahtzeeConfig.txt"));
				write.println(sides);
				write.println(dice);
				write.println(rolls);
			}
			catch(FileNotFoundException e) {
				System.out.println("error.");
			}
		}
	}
	
	/**
	 * getSides is a getter for the field sides
	 * @return sides
	 */
	public int getSides() {
		return sides;
	}
	
	/**
	 * getDice is a getter for the field dice
	 * @return dice
	 */
	public int getDice() {
		return dice;
	}
	
	/**
	 * getRolls is a getter for the field rolls
	 * @return rolls
	 */
	public int getRolls() {
		return rolls;
	}
}

