/**
 * ScoreCard creates an instance of game and displays the scores
 * in each category on the score card
 * CPSC 224-02, Spring 2018
 * Programming Assignment 6
 * 
 * @author Max Heinzelman
 * @version v1.0 3/9/18
 */
import java.util.*;

public class ScoreCard {
	
	private Game currentGame;
	//false if the line has not been used
	private boolean[] usedLines;
	private ScoreArray[] scores;
	
ScoreCard(){
		currentGame = new Game();
		usedLines = new boolean[currentGame.getSides() + 7];
		scores = new ScoreArray[currentGame.getSides() + 7];
		initScoreArray();
		//create an instance of game and continue while until the user
		//enters something other than y
		String playAgain = "";
		Scanner input = new Scanner(System.in);
		int round = 0;
		do {
			currentGame.createHand();
			displayScoreCard();
			chooseScore();
			System.out.print("Enter 'y' to continue playing ");
			playAgain = input.next();
			round++;
		} while (playAgain.equals("y") && round < currentGame.getSides() + 7); 
		displayFinalScore();
		
	}
	
	/**
	 * displayScoreCard displays the points in each category of the scorecard
	 */
	public void displayScoreCard() {
		//display the score for each number
		for (int i = 0; i < currentGame.getSides(); i++) {
			if (usedLines[i] == false) {
				System.out.print((i + 1) + ". Score " + currentGame.totalNumbs(i + 1) + " on " + (i + 1) + " line");
				System.out.println();
				if (scores[i].getPicked() == false) {
					scores[i].setScore(currentGame.totalNumbs(i + 1));
				}
			}
			
		}
		if (usedLines[currentGame.getSides()] == false) {
			//display 3 and four of a kind
			if (currentGame.maxOfAKind() == 3) {
				System.out.print((currentGame.getSides() + 1) + ". Score " + currentGame.totalAllDice() 
				+ " on the 3 of a kind line");
				System.out.println();
				if (scores[currentGame.getSides()].getPicked() == false) {
					scores[currentGame.getSides()].setScore(currentGame.totalAllDice());
				}
			}
			else if (currentGame.maxOfAKind() < 3 || currentGame.maxOfAKind() == 5
					|| currentGame.maxOfAKind() == 4) {
				System.out.print((currentGame.getSides() + 1) + ". Score " + 0 + " on the 3 of a kind line");
				System.out.println();
				if (scores[currentGame.getSides()].getPicked() == false) {
					scores[currentGame.getSides()].setScore(0);
				}
			}
		}
		
		if (usedLines[currentGame.getSides() + 1] == false) {
			if (currentGame.maxOfAKind() == 4) {
				System.out.print((currentGame.getSides()+ 2) + ". Score " + currentGame.totalAllDice() 
				+ " on the 4 of a kind line");
				System.out.println();
				if (scores[currentGame.getSides() + 1].getPicked() == false) {
					scores[currentGame.getSides() + 1].setScore(currentGame.totalAllDice());
				}
			}
			else if (currentGame.maxOfAKind() <= 3 || currentGame.maxOfAKind() == 5) {
				System.out.print((currentGame.getSides() + 2) + ". Score " + 0 + " on the 4 of a kind line");
				System.out.println();
				if (scores[currentGame.getSides() + 1].getPicked() == false) {
					scores[currentGame.getSides() + 1].setScore(0);
				}
			}
		}
		
		if (usedLines[currentGame.getSides() + 2] == false) {
			//full house line
			if (currentGame.fullHouseFound()) {
				System.out.print((currentGame.getSides() + 3) + ". Score " + 25 + " on the Full House line");
				System.out.println();
				if (scores[currentGame.getSides() + 2].getPicked() == false) {
					scores[currentGame.getSides() + 2].setScore(25);
				}
			}
			else {
				System.out.print((currentGame.getSides() + 3) + ". Score " + 0 + " on the Full House line");
				System.out.println();
				if (scores[currentGame.getSides() + 2].getPicked() == false) {
					scores[currentGame.getSides() + 2].setScore(0);
				}
			}
		}
		
		if (usedLines[currentGame.getSides() + 3] == false) {
			//small straight and large straight
			if (currentGame.maxStraightFound() == 4) {
				System.out.print((currentGame.getSides() + 4) + ". Score " + 30 + " on the Small Straight line");
				System.out.println();
				if (scores[currentGame.getSides() + 3].getPicked() == false) {
					scores[currentGame.getSides() + 3].setScore(30);
				}
			}
			else if (currentGame.maxStraightFound() < 4) {
				System.out.print((currentGame.getSides() + 4) + ". Score " + 0 + " on the Small Straight line");
				System.out.println();
				if (scores[currentGame.getSides() + 3].getPicked() == false) {
					scores[currentGame.getSides() + 3].setScore(0);
				}
			}
		}
		
		if (usedLines[currentGame.getSides() + 4] == false) {
			if (currentGame.maxStraightFound() == 5) {
				System.out.print((currentGame.getSides() + 5) + ". Score " + 40 + " on the Large Straight line");
				System.out.println();
				if (scores[currentGame.getSides() + 4].getPicked() == false) {
					scores[currentGame.getSides() + 4].setScore(40);
				}
			}
			else if (currentGame.maxStraightFound() < 4) {
				System.out.print((currentGame.getSides() + 5) + ". Score " + 0 + " on the Large Straight line");
				System.out.println();
				if (scores[currentGame.getSides() + 5].getPicked() == false) {
					scores[currentGame.getSides() + 5].setScore(0);
				}
			}
		}
		
		if (usedLines[currentGame.getSides() + 5] == false) {
			//yahtzee line
			
			if(currentGame.maxOfAKind() == 5) {
				System.out.print((currentGame.getSides() + 6) + ". Score " + 50 + " on the Yahtzee line");
				System.out.println();
				if (scores[currentGame.getSides() + 5].getPicked() == false) {
					scores[currentGame.getSides() + 5].setScore(50);
				}
			}
			else if (currentGame.maxOfAKind() < 3) {
				System.out.print((currentGame.getSides() + 6) + ". Score " + 0 + " on the Large Straight line");
				System.out.println();
				if (scores[currentGame.getSides() + 5].getPicked() == false) {
					scores[currentGame.getSides() + 5].setScore(0);
				}
			}
		}
		
		if (usedLines[currentGame.getSides() + 6] == false) {
			//Chance line
			System.out.print((currentGame.getSides() + 7) + ". Score " + currentGame.totalAllDice() + " on the Chance line");
			System.out.println();
			System.out.println();
			if (scores[currentGame.getSides() + 6].getPicked() == false) {
				scores[currentGame.getSides() + 6].setScore(currentGame.totalAllDice());
			}
			
		}
		
	}

/**
 * choseScore asks the user to choose which line on the score card they would like to save	
 */
	public void chooseScore() {
		System.out.println("Please enter the line number you would like to add to your score card.");
		Scanner choice = new Scanner(System.in);
		int lineToScore;
		while (!choice.hasNextInt()) {
			System.out.println("Please enter a number.");
			choice.next();
		}
		lineToScore = choice.nextInt();
		choice.nextLine();
		try {
			
			while (usedLines[lineToScore - 1] == true ) {
				System.out.println("Please enter a line that has not yet been used.");
				lineToScore = choice.nextInt();
			}
			
			usedLines[lineToScore - 1] = true;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter a number that corresponds to a line.");
		}
		scores[lineToScore - 1].setPicked(true);
		
	}
	
	/**
	 * displayFinalScore displays the scores for each line chosen when the game is over
	 */
	public void displayFinalScore() {
		System.out.println("Final Score!");
		int totalOfLines = 0;
		for (int i = 0; i < currentGame.getSides() + 7; i++) {
			if (scores[i].getPicked() == true) {
				System.out.println("Score on line " + (i + 1) + ": " + scores[i].getScore());
				totalOfLines = totalOfLines + scores[i].getScore();
			}
		}
		System.out.print("Total: " + totalOfLines);
	}
	
	/**
	 * initScoreArray initializes the array scores to have objects of ScoreArray 
	 */
	private void initScoreArray() {
		for (int i = 0; i < currentGame.getSides() + 7; i++) {
			scores[i] = new ScoreArray();
		}
	}
}


