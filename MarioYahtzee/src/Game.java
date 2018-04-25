import java.io.*;
import java.util.*;

/**
 * Game creates an instance of Hand and determines if the max of a kind,
 * if there is a fullhouse or not, the max straight, the total of the dice,
 * and the total number of a certain dice in a hand.
 * CPSC 224-02, Spring 2018
 * Programming Assignment 6
 * 
 * @author Max Heinzelman
 * @version v1.0 3/9/18
 */
public class Game {
	private Hand currentHand;
	public int gameID;
	//public ScoreCard currentScoreCard;
	
	Game(int gameID) {
		this.gameID = gameID;
		//create a new hand
		currentHand = new Hand();
	}
	
	/**
	 * maxOfAKind determines the largest number of the same die roll in a hand
	 * @return maxCount (the largest number)
	 */
	public int maxOfAKind() {
		int maxCount = 0;
	    int currentCount ;
	    for (int dieValue = 0; dieValue <= 5; dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < currentHand.getDice(); diePosition++)
	        {
	            if (currentHand.intAt(diePosition).equals(dieValue))
	                currentCount++;
	        }
	        if (currentCount > maxCount)
	            maxCount = currentCount;
	    }
	    return maxCount;
	}
	
	/**
	 * maxStraightFound finds the largest straight in the hand
	 * @return maxLenght (straight length)
	 */
	int maxStraightFound()
	//this function returns the length of the longest
	//straight found in a hand
	{
	    int maxLength = 1;
	    int curLength = 1;
	    for(int counter = 0; counter < currentHand.getDice() - 1; counter++)
	    {
	        if (currentHand.intAt(counter) + 1 == currentHand.intAt(counter + 1)) //jump of 1
	            curLength++;
	        else if (currentHand.intAt(counter) + 1 < currentHand.intAt(counter + 1)) //jump of >= 2
	            curLength = 1;
	        if (curLength > maxLength)
	            maxLength = curLength;
	    }
	    return maxLength;
	}
	
	/**
	 * fullHouseFounf determines if the hand has a full house
	 * returns true if yes, and false if no
	 * @return foundFH
	 */
	boolean fullHouseFound()
	//this function returns true if the hand is a full house
	//or false if it does not
	{
	    boolean foundFH = false;
	    boolean found3K = false;
	    boolean found2K = false;
	    int currentCount ;
	    for (int dieValue = 0; dieValue <= currentHand.getSides(); dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < currentHand.getDice(); diePosition++)
	        {
	            if (currentHand.intAt(diePosition) == dieValue)
	                currentCount++;
	        }
	        if (currentCount == 2)
	            found2K = true;
	        if (currentCount == 3)
	            found3K = true;
	    }
	    if(maxOfAKind() >= 5) {
	    	return true;
	    }
	    
	    if (found2K && found3K)
	        foundFH = true;
	    return foundFH;
	}
	/**
	 * if the hand has a flower a mushroom and a star
	 * @return true
	 */
	public boolean PowerUp (){
        boolean foundPowerUp = false;
        boolean hasFlower = false;
        boolean hasMoshroom = false;
        boolean hasStar = false;
        
        for (int dieValue = 0; dieValue <= 6; dieValue++) {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (currentHand.intAt(diePosition) == 1)
                    hasMoshroom = true;
                if (currentHand.intAt(diePosition) == 3)
                    hasFlower = true;
                if (currentHand.intAt(diePosition) == 5)
                    hasStar = true;
                
            }
        }
        if (hasFlower && hasMoshroom && hasStar)
            foundPowerUp = true;
        return foundPowerUp;
    }
	
	public boolean allDifferent() {
		//initiate the flag alldifferent to true
		boolean allDifferent = true;
		//loop through each possible die value and count how many there are of each
		for(int i = 0; i < 6; i++) {
			if (totalDieFace(i) > 1) 
				allDifferent = false;
		}
		
		return allDifferent;
	}
	
	/**
	 * totalAllDice totals all the values of the dice
	 * in the hand
	 * @return total
	 */
	int totalAllDice() {
		int total = 0;
		for (int i = 0; i < currentHand.getDice(); i++) {
			total = total + currentHand.intAt(i);
		}
		return total;
	}
	
	/**
	 * totalNumbs returns how many of the same dice value there are
	 * multiplied by that value
	 * @param number
	 * @return score
	 */
	public int totalNumbs(int number) {
		int total = 0;
		for (int i = 0; i < currentHand.getDice(); i++) {
			if (currentHand.intAt(i) == number)
				total++;
		}
		int score = total * number;
		return score;
	}
	
	public int totalDieFace(int number) {
		int total = 0;
		for (int i = 0; i < currentHand.getDice(); i++) {
			if (currentHand.intAt(i) == number)
				total++;
		}
		return total;
	}
	
	public int getSides() {
		return currentHand.getSides();
	}
	
	public int getDice() {
		return currentHand.getDice()	;
	}
	
	public int getRolls() {
		return currentHand.getSides();
	}
	
	public void createHand() {
		currentHand.createHand();
	}
	
	public Hand getHand() {
		return currentHand;
	}
}
