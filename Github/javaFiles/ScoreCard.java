
public class ScoreCard {
	private ScoreArray[] currentScoreArray;
	
	ScoreCard(int numberOfPlayers){
		//create an element for every line in the Score Card
		currentScoreArray[17] = new ScoreArray();
	}
	
	public boolean scoreLine(int line, int score) {
		//if the line the user wants to add to has already been picked, return false
		if(currentScoreArray[line].getPicked() == true)
			return false;
		
		//set the score and setPicked for that line to true
		currentScoreArray[line].setScore(score);
		currentScoreArray[line].setPicked(true);
		
		//return true as the score was successfully added
		return true;
	}
	
	public int getScore(int line) {
		//if the line has not yet been scored, return -1 to show there
		// is no score on that line
		if (currentScoreArray[line].getPicked() == false)
			return -1;
		
		//otherwise return the score
		return currentScoreArray[line].getScore();
	}
}
