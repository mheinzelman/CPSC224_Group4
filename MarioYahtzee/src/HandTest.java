import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandTest {

	@Test
	void testCreateHand() {
		//create a new hand
		Hand hand1 = new Hand();
		// transfer the current hand into an array to compare with
		int[] compareHand = new int[5];
		for(int i = 0; i < 5; i++) {
			compareHand[i] = hand1.intAt(i);
		}
		
		//create hand should create a new hand with 5 new dice rolls, 
		//this does not mean that every dice will change however, so 
		//we will test if at least one dice changes. If this fails then
		//we will call createHand again and retest
		hand1.createHand();
		boolean oneChange = false;
		
		for (int i = 0; i < 5; i++) {
			if (compareHand[i] != hand1.intAt(i)) {
				oneChange = true;
				break;
			}
		}
		//if oneChange is equal to true, the test passes
		assert(oneChange);
	}

	@Test
	void testReroll() {
		//create a new hand
		Hand hand1 = new Hand();
		// transfer the current hand into an array to compare with
		int[] compareHand = new int[5];
		for(int i = 0; i < 5; i++) {
			compareHand[i] = hand1.intAt(i);
		}
		
		//this tests to see if a certain die value has changed, it is possible that you will 
		//get the same die value so we will test this three times
		boolean changed = false;
		for (int i = 0; i < 3; i++) {
			hand1.reroll(0);
			if (compareHand[0] != hand1.intAt(0))
				changed = true;
		}
		
		if (changed = true)
			assert(true);
		else assert(false);
			
	}

	@Test
	void testGetSides() {
		//create a new hand
		Hand hand1 = new Hand();
		//see if 6 is returned
		assert(hand1.getSides() == 6);
	}

	@Test
	void testGetDice() {
		//create a new hand
		Hand hand1 = new Hand();
		//see if 6 is returned
		assert(hand1.getDice() == 5);
	}

	@Test
	void testGetRolls() {
		//create a new hand
		Hand hand1 = new Hand();
		//see if 6 is returned
		assert(hand1.getRolls() == 2);
	}

}
