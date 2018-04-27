
public class ScoreCard {
	private ScoreArray[] currentScoreArray;
	public int cardID = 0;
	private int round;
	
	ScoreCard(int ID){
		cardID = ID;
		round = 0;
		//create an element for every line in the Score Card
		currentScoreArray = new ScoreArray[17];
		initScoreArray();
	}
	
	public boolean isUsed(int line) {
		/*if (currentScoreArray[line].getPicked() == false)
			return false;
		return true;*/
		return currentScoreArray[line].getPicked();
	}
	
	public boolean scoreLine(int line, int score) {
		//if the line the user wants to add to has already been picked, return false
		if(currentScoreArray[line].getPicked() == true)
			return false;
		
		//set the score and setPicked for that line to true
		currentScoreArray[line].setScore(score);
		
		//return true as the score was successfully added
		return true;
	}
	
	public void setPicked(int line, boolean pickedIn) {
		currentScoreArray[line].setPicked(pickedIn);
	}
	
	public int getScore(int line) {
		//if the line has not yet been scored, return -1 to show there
		// is no score on that line
		//if (currentScoreArray[line].getPicked() == false)
			//return -1;
		
		//otherwise return the score
		return currentScoreArray[line].getScore();
	}
	
	/**
	 * initScoreArray initializes the array scores to have objects of ScoreArray 
	 */
	private void initScoreArray() {
		for (int i = 0; i < 17; i++) {
			currentScoreArray[i] = new ScoreArray();
		}
	}
	
	public int getRound() {
		return round;
	}
	
	public void incrementRound() {
		round++;
	}
	
	public int finalScore() {
		return (getScore(7) + getScore(16));
	}
}
