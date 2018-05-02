import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreArrayTest {

	@Test
	void testSetScore() {
		//create a new scoreArray instance
		//set the score equal to 500
		ScoreArray scoreNode = new ScoreArray();
		scoreNode.setScore(500);
		assert(scoreNode.getScore() == 500);
	}

	@Test
	void testSetPicked() {
		//create a new scoreArray instance
		//set picked to true
		ScoreArray scoreNode = new ScoreArray();
		scoreNode.setPicked(true);
		assert(scoreNode.getPicked() == true);
		
		//now set to false
		scoreNode.setPicked(false);
		assert(scoreNode.getPicked() == false);
	}

	@Test
	void testGetScore() {
		//create a new scoreArray instance
		//set the score equal to 500
		ScoreArray scoreNode = new ScoreArray();
		scoreNode.setScore(12);
		assert(scoreNode.getScore() == 12);
	}

	@Test
	void testGetPicked() {
		//create a new scoreArray instance
		//set picked to true
		ScoreArray scoreNode = new ScoreArray();
		scoreNode.setPicked(true);
		assert(scoreNode.getPicked() == true);
	}

}
