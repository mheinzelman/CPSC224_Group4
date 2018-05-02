import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DieTest {

	@Test
	void testRollDie() {
		Die die1 = new Die();
		int roll = die1.rollDie();
		//determine if the roll is a value from 0 to 5
		assert(roll >= 0 && roll <= 5);
	}

}
