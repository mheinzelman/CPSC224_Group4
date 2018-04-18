
/**
 * This class creates the score card and fills it with the appropriate values
 *
 * CPSC 224-02, Spring 2018
 * Programming Assignment #6
 * Mauricio Velazquez
 * version v1.0 
 * 3/21/2018
 */

import java.util.*;

public class ScoreCard {

    private int[] finalScoreCard = new int[Dice.NUMBER_OF_SIDES + 7]; // Will create an ArrayList of Strings including the line score
    private boolean[] scoreCardIsFull = new boolean[Dice.NUMBER_OF_SIDES + 7];

	//Displays the scorecard
    public static void displayScorecard(ArrayList<Dice> hand) {
        displayUpperScoreCard(hand);
        displayLowerScoreCard(hand);
    }

	//user interface to save chosen line
    public void scoreHand(ArrayList<Dice> hand) {
        System.out.print("Would you like to input your hand score in the upper or lower scorecard?");
        System.out.println("(upper = 1 and lower = 2)");
        Scanner userInput = new Scanner(System.in);
        int whichScoreCard = userInput.nextInt();
        if (whichScoreCard == 1){
            scoreUpperScoreCard(hand);
        } else if (whichScoreCard == 2){
            scoreLowerScoreCard(hand);
        } else {
            System.out.println("Please enter a valid number");
            scoreHand(hand);
        }
    }

    //Displays final score
    public void displayFinalScoreCard() {
        System.out.println();
        displayFinalUpperScoreCard();
        displayFinalLowerScoreCard();
    }

    //Fills array with booleans to know if slot empty
    public void fillArray() {
        for (int i = 0; i < Dice.NUMBER_OF_SIDES + 7; i++) 
            scoreCardIsFull[i] = false;

        for (int i = 0; i < Dice.NUMBER_OF_SIDES; i++)
            finalScoreCard[i] = 0;  
    }

	//Finds the max of a kind when more than five die
    private static int maxOfAKindFound(ArrayList<Dice> hand) {
        int maxCount = 0;
        int currentCount;
        for (int dieValue = 1; dieValue <= Dice.NUMBER_OF_SIDES; dieValue++) {
            currentCount = 0;
            for (int diePosition = 0; diePosition < Hand.HAND_SIZE; diePosition++) {
                if (hand.get(diePosition).getValue() == dieValue)
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
        }
        return maxCount;
    }

    //Add all the die for the chance line
    private static int totalAllDice(ArrayList<Dice> hand){
        int total = 0;
        for (int diePosition = 0; diePosition < Hand.HAND_SIZE; diePosition++) {
            total += hand.get(diePosition).getValue();
        }
        return total;
    }

    //Returns the max straight if found
    private static int maxStraightFound(ArrayList<Dice> hand) {
        int maxLength = 1;
        int curLength = 1;
        for (int counter = 0; counter < 4; counter++) {
            if (hand.get(counter).getValue() + 1 == hand.get(counter + 1).getValue()) //jump of 1
                curLength++;
            else if (hand.get(counter).getValue() + 1 < hand.get(counter + 1).getValue()) //jump of >= 2
                curLength = 1;
            if (curLength > maxLength)
                maxLength = curLength;
        }
        return maxLength;
    }

	//Looks for and finds full house if found
    private static boolean fullHouseFound(ArrayList<Dice> hand){
        boolean foundFH = false;
        boolean found3K = false;
        boolean found4K = false;
        boolean found2K = false;
        boolean found5K = false;

        for (int dieValue = 0; dieValue <= Dice.NUMBER_OF_SIDES; dieValue++) {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < Hand.HAND_SIZE; diePosition++) {
                if (hand.get(diePosition).getValue() == dieValue) {
                    currentCount++;
                }
            }
            if (currentCount == 2)
                found2K = true;
            else if (currentCount == 3)
                found3K = true;
            else if (currentCount == 4)
                found4K = true;
            else if (currentCount == 5) // If
                found5K = true;
        }
		// If find a triplet and pair, or >=5 same numbers found
        if ((found2K && found3K) || found5K || (found4K && found2K))
            foundFH = true;
        return foundFH;
    }
    
    private static boolean PowerUp (ArrayList<Dice> hand){
        boolean foundPowerUp = false;
        boolean hasFlower = false;
        boolean hasMoshroom = false;
        boolean hasStar = false;
        
        for (int dieValue = 0; dieValue <= Dice.NUMBER_OF_SIDES; dieValue++) {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < Hand.HAND_SIZE; diePosition++) {
                if (hand.get(diePosition).getValue() == 1)
                    hasMoshroom = true;
                if (hand.get(diePosition).getValue() == 3)
                    hasFlower = true;
                if (hand.get(diePosition).getValue() == 5)
                    hasStar = true;
                
            }
        }
        if (hasFlower && hasMoshroom && hasStar)
            foundPowerUp = true;
        return foundPowerUp;
    }
    
    
	//Displays upper score card
    private static void displayUpperScoreCard(ArrayList<Dice> hand) {
        for (int dieValue = 0; dieValue <Dice.NUMBER_OF_SIDES; dieValue++) {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < Hand.HAND_SIZE; diePosition++) {
                if (hand.get(diePosition).getValue() == dieValue)
                    currentCount++;
            }
            System.out.print("Score " + dieValue * currentCount + " on the ");
            System.out.print(dieValue + " line");
            System.out.println();
        }
    }

    //Displays upper score card-
    private static void displayLowerScoreCard(ArrayList<Dice> hand) {
        if (maxOfAKindFound(hand) >= 3) {
            System.out.print("Score " + totalAllDice(hand) + " on the ");
            System.out.print("3 of a Kind line");

        } else
            System.out.print("Score 0 on the 3 of a Kind line");

        System.out.println();

        if (maxOfAKindFound(hand) >= 4) {
            System.out.print("Score " + totalAllDice(hand) + " on the ");
            System.out.print("4 of a Kind line");
        } else
            System.out.print("Score 0 on the 4 of a Kind line");

        System.out.println();

        if (fullHouseFound(hand))
            System.out.print("Score 25 on the Full House line");
        else
            System.out.print("Score 0 on the Full House line");

        System.out.println();

        if (PowerUp(hand))
            System.out.print("Score 30 on the Power Up line");
        else
            System.out.print("Score 0 on the Power Up line");

        System.out.println();

        if (maxStraightFound(hand) >= 5)
            System.out.print("Score 40 on the All Objects line");
        else
            System.out.print("Score 0 on the All Objects line");

        System.out.println();
        
        if (totalAllDice(hand) == 0)
            System.out.print("Score 75 on the All Shells line");
        else
            System.out.print("Score 0 on the All Shells line");
        
        System.out.println();

        if (maxOfAKindFound(hand) >= Hand.HAND_SIZE && !(totalAllDice(hand) == 0))
            System.out.print("Score 50 on the Mario!! line");
        else
            System.out.print("Score 0 on the Mario!! line");
        System.out.println();

        System.out.print("Score " + totalAllDice(hand) + " on the ");
        System.out.println("Chance line");
    }

    /**
     * will score the upper ScoreCard, and put the value in an array of ints finalScoreCard
     * @param hand, the ArrayList of dice
     * @returns nothing
     */
    private void scoreUpperScoreCard(ArrayList<Dice> hand) {
        System.out.println(
                "Please enter the line number (1-" + Dice.NUMBER_OF_SIDES + ") you would like to score your hand to:");
        Scanner userInput = new Scanner(System.in);
        int whichLine = userInput.nextInt();

        if (whichLine < 1 || (whichLine > (Dice.NUMBER_OF_SIDES))) {
            System.out.println("Please enter a number between 1 and " + (Dice.NUMBER_OF_SIDES));
            scoreUpperScoreCard(hand);
        }

        if (scoreCardIsFull[whichLine - 1]) {
            System.out.println("You have already scored this line, Please try again");
            scoreHand(hand);
        }

        int score = 0;
        for (int dieValue = 1; dieValue <= Dice.NUMBER_OF_SIDES; dieValue++) {
            if (dieValue == whichLine) {
                System.out.println("You have decided to score line " + whichLine);
                for (int diePosition = 0; diePosition < Hand.HAND_SIZE; diePosition++) {
                    if (hand.get(diePosition).getValue() == whichLine) {
                        score += whichLine;
                    }

                }
                finalScoreCard[whichLine - 1] = score;
                scoreCardIsFull[whichLine - 1] = true;
            }
        }
    }

    //Saves line user chose
    private void scoreLowerScoreCard(ArrayList<Dice> hand) {
        System.out.println("Options for scoring");
        System.out.println("3 of a kind........1");
        System.out.println("4 of a kind........2");
        System.out.println("Full House.........3");
        System.out.println("Small Straight.....4");
        System.out.println("Large Straight.....5");
        System.out.println("All Shells.........6");
        System.out.println("Yahtzee............7");
        System.out.println("Chance.............8");
        System.out.print("Please enter the line number you would like to score your hand in: ");
        Scanner userInput = new Scanner(System.in);
        int whichLine = userInput.nextInt();

        if (scoreCardIsFull[(Dice.NUMBER_OF_SIDES + whichLine)+4]){
            System.out.println("You have already scored this line, Please try again");
            scoreHand(hand);
        }

        switch (whichLine) {
        case 1:
            if (maxOfAKindFound(hand) >= 3) {
                finalScoreCard[Dice.NUMBER_OF_SIDES] = totalAllDice(hand);

            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES] = 0;

            System.out.println("You have decided to score the 3 of a kind line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES] = true; 
            break;
        case 2:
            if (maxOfAKindFound(hand) >= 4) { 
                finalScoreCard[Dice.NUMBER_OF_SIDES + 1] = totalAllDice(hand);
            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES + 1] = 0;

            System.out.println("You have decided to score the 4 of a kind line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 1] = true;
            break;
        case 3:
            if (fullHouseFound(hand)) { 
                finalScoreCard[Dice.NUMBER_OF_SIDES + 2] = 25;
            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES + 2] = 0;

            System.out.println("You have decided to score the Full House line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 2] = true; 
            break;
        case 4:
            if (PowerUp(hand)) {
                finalScoreCard[Dice.NUMBER_OF_SIDES + 3] = 30;
            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES + 4] = 0;

            System.out.println("You have decided to score the Power Up line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 3] = true; 
            break;
        case 5:
            if (maxStraightFound(hand) >= 5) {
                finalScoreCard[Dice.NUMBER_OF_SIDES + 4] = 40;
            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES + 4] = 0;

            System.out.println("You have decided to score the All objects line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 4] = true; 
            break;
        case 6:
                if (totalAllDice(hand) == 0) {
                finalScoreCard[Dice.NUMBER_OF_SIDES + 5] = 75;
            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES + 5] = 0;

            System.out.println("You have decided to score the All Shells line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 5] = true;
            break;
        case 7: //Score a Yahtzee
            if (maxOfAKindFound(hand) >= Hand.HAND_SIZE) {
                finalScoreCard[Dice.NUMBER_OF_SIDES + 5] = 50;
            } else
                finalScoreCard[Dice.NUMBER_OF_SIDES + 5] = 0;
                
            System.out.println("You have decided to score the Mario!! line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 5] = true;
            break;
        case 8:
            finalScoreCard[Dice.NUMBER_OF_SIDES + 6] = totalAllDice(hand);
            System.out.println("You have decided to score the chance line");
            scoreCardIsFull[Dice.NUMBER_OF_SIDES + 6] = true;
            break;
        default:
            System.out.println("Please enter a valid line number between 1 and 8");
            scoreLowerScoreCard(hand);
            break;
        }
    }

    //Adds up total score
    private int findTotalScore() {
        int total = 0;
        for (int i = 0; i < (Dice.NUMBER_OF_SIDES + 6); i++) {
            total += finalScoreCard[i];
        }
        return total;
    }

	//displays the score card
    private void displayFinalUpperScoreCard() {
        for (int i = 0; i < Dice.NUMBER_OF_SIDES; i++) {
            System.out.println("The score of line " + (i) + " is " + finalScoreCard[i]);
        }
    }

	//displays the score card
    private void displayFinalLowerScoreCard() {
        System.out.println("The score of the 3 of a kind line is " + finalScoreCard[Dice.NUMBER_OF_SIDES]);
        System.out.println("The score of the 4 of a kind line is " + finalScoreCard[Dice.NUMBER_OF_SIDES ]);
        System.out.println("The score of the 5 of a kind line is " + finalScoreCard[Dice.NUMBER_OF_SIDES]);
        System.out.println("The score of the Full House line is " + finalScoreCard[Dice.NUMBER_OF_SIDES + 1]);
        System.out.println("The score of the Small Straight line is " + finalScoreCard[Dice.NUMBER_OF_SIDES + 2]);
        System.out.println("The score of the All Objects line is " + finalScoreCard[Dice.NUMBER_OF_SIDES + 3]);
        System.out.println("The score of the Yahtzee line is " + finalScoreCard[Dice.NUMBER_OF_SIDES + 4]);
        System.out.println("The score of the Chance line is " + finalScoreCard[Dice.NUMBER_OF_SIDES + 5]);
        System.out.println("The total score of this Yahtzee game is " + findTotalScore());

    }
}
