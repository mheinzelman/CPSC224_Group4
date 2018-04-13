/**
 * Die creates a random number from one to five 
 * and returns it.
 * @author Max Heinzelman
 * Super Mario Yahtzee
 * Version V.1 
 * O4/05/2018
 */

import java.util.Random;

public class Die {
	//Mario dice will have 5 sides
	private int sides = 5;
	
	public int rollDie() {
		Random rand = new Random();
		int currentRoll = rand.nextInt(sides + 1);
		
		//if the current roll = 0, re-roll until it is not
		while (currentRoll == 0) {
			currentRoll = rand.nextInt(sides + 1);
		}
		return currentRoll;
	}
}