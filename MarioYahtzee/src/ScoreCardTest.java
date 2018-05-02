import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreCardTest {

	@Test
	void testCurrentPlayerScore() {
		//create a scoreCard with a score of 2 on the first 10 lines
		//this should have a total score of 20, as the other scores should not be counted
		ScoreCard scorecard1 = new ScoreCard(0);
		for(int i = 0; i < 10; i++) {
			//you must score each line
			scorecard1.scoreLine(i, 2);
			//and also set each scorecard line isUsed to true
			//so the fuction will know to count that line in the current total score
			scorecard1.setPicked(i, true);
		}
		assert(scorecard1.currentPlayerScore() == 20);
	}

	@Test
	void testIsUsed() {
		//create a score card and set line 0 picked to true
		//set line 1 picked to false
		ScoreCard scorecard1 = new ScoreCard(0);
		scorecard1.setPicked(0, true);
		assert(scorecard1.isUsed(0));
		
		assert(!scorecard1.isUsed(1));
		
	}

	@Test
	void testScoreLine() {
		//create a scorecard and score line 0 to be 520
		ScoreCard scorecard1 = new ScoreCard(0);
		scorecard1.scoreLine(0, 520);
		assert(scorecard1.getScore(0) == 520);
	}

	@Test
	void testSetPicked() {
		//create a scorecard and set picked for linne 5 to be true
		ScoreCard scorecard1 = new ScoreCard(0);
		scorecard1.setPicked(5, true);
		assert(scorecard1.isUsed(5));
		
		//now set picked for line 5 to be false
		scorecard1.setPicked(5, false);
		assert(!scorecard1.isUsed(5));
	}

	@Test
	void testFinalScore() {
		//create a scoreCard with a score of 2 on everyline
		//this should have a total score of 34
		ScoreCard scorecard1 = new ScoreCard(0);
		for(int i = 0; i < 17; i++) {
			//you must score each line
			scorecard1.scoreLine(i, 2);
			//and also set each scorecard line isUsed to true
			//so the fuction will know to count that line in the current total score
			scorecard1.setPicked(i, true);
		}
		assert(scorecard1.currentPlayerScore() == 34);
	}

}
