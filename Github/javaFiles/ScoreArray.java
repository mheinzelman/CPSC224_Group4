import java.util.ArrayList;

/**
 * ScoreArray keeps track of what lines on the Score
 * Card have been used, and what the scores for those
 * lines are
 * CPSC 224-02, Spring 2018
 * Super Mario Yahtzee
 * 
 * @author Max Heinzelman
 * @version v1.1 4/12/18
 */
public class ScoreArray {
	private int score;
	private boolean picked;
	
	ScoreArray(){
		score = 0;
		picked = false;
	}
	
	/**
	 * setScore sets the score
	 * @param scoreIn
	 */
	public void setScore(int scoreIn) {
		score = scoreIn;
	}
	
	/**
	 * setPicked sets the value of picked to true or false
	 * @param pickedIn
	 */
	public void setPicked(boolean pickedIn) {
		picked = pickedIn;
	}
	
	/**
	 * getScore returns the score for that line
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * getPicked tells whether the line has been picked
	 * or not
	 * @return picked
	 */
	public boolean getPicked() {
		return picked;
	}
}
