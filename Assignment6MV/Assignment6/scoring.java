/*
 * This class that the scoring methods of the game. It takes in the array that
 * represents the die and calculates the score, then returns the score.
 * CPSC 224-02, Spring 2018
 * Programming Assignment #5
 * Mauricio Velazque
 * version 1.0
 */

import java.util.*;

public class scoring {

	//This function counts the values that are repeated
    //  the most and returns an int
	public int maxOfAKindFound(int hand[]){
	    int maxCount = 0;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <=6; dieValue++){
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < 5; diePosition++){
	            if (hand[diePosition] == dieValue)
	                currentCount++;
	        }
	        if (currentCount > maxCount)
	            maxCount = currentCount;
	    }
	    return maxCount;
	}
	
	//This function finds the largest straight in the hand
    //  dealt.
	public int maxStraightFound(int hand[]){
	    int maxLength = 1;
	    int curLength = 1;
	    for(int counter = 0; counter < 4; counter++){
	        if (hand[counter] + 1 == hand[counter + 1] ) //jump of 1
	            curLength++;
	        else if (hand[counter] + 1 < hand[counter + 1]) //jump of >= 2
	            curLength = 1;
	        if (curLength > maxLength)
	            maxLength = curLength;
	    }
	    return maxLength;
	}
	
	//This function finds wheather or not there is
    //  a fullhouse in the hand dealt, a pair
    //  and three of a kind, returns a boolean
	public boolean fullHouseFound(int hand[]){
	    boolean foundFullHouse = false;
	    boolean found3ofaKind = false;
	    boolean foundpair = false;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <=6; dieValue++){
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < 5; diePosition++){
	            if (hand[diePosition] == dieValue)
	                currentCount++;
	        }
	        if (currentCount == 2)
	            foundpair = true;
	        if (currentCount == 3)
	            found3ofaKind = true;
	    }
	    if (foundpair && found3ofaKind)
	        foundFullHouse = true;
	    return foundFullHouse;
	}
}
