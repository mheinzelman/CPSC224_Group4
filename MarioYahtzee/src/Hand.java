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
	private int sides = 6;
	private int dice = 5;
	private int rolls = 2;
	
	Hand() {
		dieToRoll = new Die();
		handToUse = new ArrayList <Integer> ();
		createHand();
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
		displayHand();
	}
	
	public void reroll(int dieNumber) {
		handToUse.set(dieNumber, dieToRoll.rollDie());
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

