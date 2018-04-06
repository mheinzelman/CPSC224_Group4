/*
 * This class  contains all of the functions related to the die
 * in the game.
 * 
 * CPSC 224-02, Spring 2018
 * Programming Assignment #5
 * 
 * Mauricio Velazquez
 * version 1.0
 */

import java.util.*;
import java.util.Random; //for the random function

public class dice {
	
    //This function selects a random integer
    //  to simulate rolling of a die
	public int rollDie(int dieSides) {
		Random rand = new Random();
		int roll = rand.nextInt(dieSides) + 1;
		return roll;
	}
	
	//This function returns the total of
    //  all the die togeter
	public int totalAllDice(int hand[]){
	    int total = 0;
	    for (int diePosition = 0; diePosition < 5; diePosition++){
	        total += hand[diePosition];
	    }
	    return total;
	}
	
	//This function sorts the die in an array
    //  for the ouput by using bubble sort
	public void sortArray(int array[], int size){
	   boolean swap;
	   int temp;
	   do{
	      swap = false;
	      for (int count = 0; count < (size - 1); count++){
	         if (array[count] > array[count + 1]){
	            temp = array[count];
	            array[count] = array[count + 1];
	            array[count + 1] = temp;
	            swap = true;
	         }
	      }
	   }
        while (swap);
	}

}

