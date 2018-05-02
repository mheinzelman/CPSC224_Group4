import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {
	private Game game1;
	GameTest(){
		//create a new game with gameID = 0
		game1 = new Game(0);
	}

	@Test
	void testMaxOfAKind() {
		//set the games currentHand to {1,2,3,4,5}
		//max of a kind should return 1
		int[] testHand = {1,2,3,4,5};
		game1.getHand().setHand(testHand);
		assert(game1.maxOfAKind() == 1);
		
		//set the games currentHand to {1,1,3,4,5}
		//max of a kind should return 2
		testHand[1] = 1;
		game1.getHand().setHand(testHand);
		assert(game1.maxOfAKind() == 2);
		
		//set the games currentHand to {1,1,1,4,5}
		//max of a kind should return 3
		testHand[2] = 1;
		game1.getHand().setHand(testHand);
		assert(game1.maxOfAKind() == 3);
		
		//set the games currentHand to {1,1,1,1,5}
		//max of a kind should return 4
		testHand[3] = 1;
		game1.getHand().setHand(testHand);
		assert(game1.maxOfAKind() == 4);
		
		//set the games currentHand to {1,1,1,1,1}
		//max of a kind should return 5
		testHand[4] = 1;
		game1.getHand().setHand(testHand);
		assert(game1.maxOfAKind() == 5);
	}

	@Test
	void testMaxStraightFound() {
		//set the games currentHand to {1,2,3,4,5}
		//max straight found should return 5
		int[] testHand = {1,2,3,4,5};
		game1.getHand().setHand(testHand);
		assert(game1.maxStraightFound() == 5);
		
		//set the games currentHand to {0,2,3,4,5}
		//max straight found should return 4
		testHand[0] = 0;
		game1.getHand().setHand(testHand);
		assert(game1.maxStraightFound() == 4);
		
		//set the games currentHand to {0,0,3,4,5}
		//max straight found should return 3
		testHand[1] = 0;
		game1.getHand().setHand(testHand);
		assert(game1.maxStraightFound() == 3);
		
		//set the games currentHand to {0,0,0,4,5}
		//max straight found should return 2
		testHand[2] = 0;
		game1.getHand().setHand(testHand);
		assert(game1.maxStraightFound() == 2);
		
		//set the games currentHand to {0,0,0,0,1}
		//max straight found should return 1
		testHand[3] = 0;
		game1.getHand().setHand(testHand);
		assert(game1.maxStraightFound() == 1);
	}

	@Test
	void testFullHouseFound() {
		//create a hand with a full house
		//fullHouseFound should return true
		int[] testHand = {1,1,1,2,2};
		game1.getHand().setHand(testHand);
		assert(game1.fullHouseFound());
		
		//change the hand so it no longer has a fullhouse
		//should return false
		testHand[0] = 3;
		game1.getHand().setHand(testHand);
		assert(!game1.fullHouseFound());
	}

	@Test
	void testPowerUp() {
		//create a hand that has a 1, 3, and 5
		//this should return true
		int[] testHand = {1,3,5,0,0};
		game1.getHand().setHand(testHand);
		assert(game1.PowerUp());
		
		//change the hand so it no longer
		//has either a 1, 3, and 5
		//this should return false
		testHand[0] = 0;
		game1.getHand().setHand(testHand);
		assert(!game1.PowerUp());
	}

	@Test
	void testAllDifferent() {
		//create a hand that has no repeated die values
		//this should make testALlDifferent return true
		int[] testHand = {1,2,3,4,5};
		game1.getHand().setHand(testHand);
		assert(game1.allDifferent());
		
		//create a hand that has a repeated die value
		//this should make testALlDifferent return false
		testHand[0] = 2;
		game1.getHand().setHand(testHand);
		assert(!game1.allDifferent());
		
	}

	@Test
	void testTotalAllDice() {
		//create a hand with a total of 25
		//this should make totalAllDice return 25
		int[] testHand = {5,5,5,5,5};
		game1.getHand().setHand(testHand);
		assert(game1.totalAllDice() == 25);
	}

	@Test
	void testTotalNumbs() {
		//create a hand that has {4,4,4,2,2}
		//test to see that totalNumbs returns 4*3 when passed 1
		//test to see that totalNumbs returns 2*2 when asked to count 2's
		//test to see that totalNumbs returns 0 when asked to count 0's
		int[] testHand = {4,4,4,2,2};
		game1.getHand().setHand(testHand);
		assert(game1.totalNumbs(4) == 12);
		assert(game1.totalNumbs(2) == 4);
		assert(game1.totalNumbs(0) == 0);
	}

	@Test
	void testTotalDieFace() {
		//create a hand that has {1,1,1,2,2}
		//test to see that totalDieFace returns 3 when asked to count 1's
		//test to see that totalDieFace returns 2 when asked to count 2's
		//test to see that totalDieFace returns 0 when asked to count 0's
		int[] testHand = {1,1,1,2,2};
		game1.getHand().setHand(testHand);
		assert(game1.totalDieFace(1) == 3);
		assert(game1.totalDieFace(2) == 2);
		assert(game1.totalDieFace(0) == 0);
	}

}
