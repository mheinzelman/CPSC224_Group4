/**
 * Die creates a random number from one to six 
 * and returns it.
 * CPSC 224-02, Spring 2018
 * Programming Assignment 6
 * 
 * @author Max Heinzelman
 * @version v1.0 3/9/18
 */
import java.util.*;
public class Die {
	private int sides = 0;
	Die(int numbOfSides){
		sides = numbOfSides;
	}
	public int rollDie() {
		Random rand = new Random();
		int currentRoll = rand.nextInt(sides + 1);
		while (currentRoll == 0) {
			currentRoll = rand.nextInt(sides + 1);
		}
		return currentRoll;
	}
}
