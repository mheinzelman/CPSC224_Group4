import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SuperMarioYahtzee {
	private int numberPlayers;
	private Game[] currentGames;
	private ScoreCard[] currentCards;
	
	//initializes all arrays of games and scorecards
	SuperMarioYahtzee(int numberPlayers) {
		this.numberPlayers = numberPlayers;
		currentGames = new Game[numberPlayers];
		currentCards = new ScoreCard[numberPlayers];
		initGamesArray();
		play();
	}
	
	private void initGamesArray() {
		for (int i = 0; i < numberPlayers; i++) {
			currentGames[i] = new Game(i);
			currentCards[i] = new ScoreCard(i);
		}
	}
	
	private void play() {
		for (int j = 0; j < numberPlayers; j++) {
			//show the roll screens for each player
			new RerollDiceScreenFrame(currentGames[j],currentCards[j], this, 0);
			//show the scorecard somewhere
			
		}
	}
	
	public void createScoreCard(ScoreCard curCard, Game curGame) {
		//scores the first 5 lines
		for (int i = 0; i < 6; i++) {
			if (curCard.isUsed(i) == false) {
				int total = curGame.totalNumbs(i);
				curCard.scoreLine(i, total);
			}
		}
		
		//scores the bonus line, if the total dice is greater than 20
		if (curCard.isUsed(6) == false && curGame.totalAllDice() > 20) {
			curCard.scoreLine(6, 10);
		}
		else
			curCard.scoreLine(6, 0);
		
		//show the total for the upper score card
		curCard.scoreLine(7, curGame.totalAllDice() + curCard.getScore(6));
		
		//three of a kind line
		if(curCard.isUsed(8) == false && curGame.maxOfAKind() >= 3) {
			curCard.scoreLine(8, curGame.totalAllDice());
		}
		else
			curCard.scoreLine(8, 0);
		
		//four of a kind line
		if(curCard.isUsed(9) == false && curGame.maxOfAKind() >= 4) {
			curCard.scoreLine(9, curGame.totalAllDice() + 5);
		}
		else
			curCard.scoreLine(9, 0);
		
		
		
	}
	
	
	public Game getGame(int player) {
		return currentGames[player];
	}
	
}
