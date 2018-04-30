import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SuperMarioYahtzee {
	private Game[] currentGames;
	private ScoreCard[] currentCards;
	private Game curGame;
	private ScoreCard curCard;
	private SuperMarioYahtzee controller;
	private int rerolls;
	private int numberPlayers; //amount of players in the game
	private int currentPlayer; //the current player's turn
	private int currentRound; //current round out of three
	private int totalRounds;	//total amount of rounds, default is 15
	private int[] array; //placeholder array for the leaderboard screen
	
	SuperMarioYahtzee(Game curGame, ScoreCard curCard, SuperMarioYahtzee controller, int rerolls, int totalPlayers, int currentPlayer, int currentRound, int totalRounds, int[] array){
		this.curGame = curGame;
		this.curCard = curCard;
		this.controller = controller;
		this.rerolls = rerolls;
		this.numberPlayers = totalPlayers;
		this.currentPlayer = currentPlayer;
		this.currentRound = currentRound;
		this.totalRounds = totalRounds;
		this.array = array;
		
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
			//show the roll screens for each player
			new RerollDiceScreenFrame(currentGames[0],currentCards[0], this, 0, numberPlayers, currentPlayer, currentRound, totalRounds, array);
			//show the scorecard somewhere	
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
		if(curCard.isUsed(6) == false) {
			if (curGame.totalAllDice() > 20) {
				curCard.scoreLine(6, 10);
			}
			else
				curCard.scoreLine(6, 0);
		}
		
		//show the total for the upper score card
		int totalUpper = 0;
		for (int i = 0; i < 7; i++) {
			totalUpper += curCard.getScore(i);
		}
		curCard.scoreLine(7, totalUpper);
		//curCard.scoreLine(7, curGame.totalAllDice() + curCard.getScore(6));
		
		//three of a kind line
		if (curCard.isUsed(8) == false) {
			if(curGame.maxOfAKind() >= 3) {
				curCard.scoreLine(8, curGame.totalAllDice());
			}
			else
				curCard.scoreLine(8, 0);
		}
		
		//four of a kind line
		if(curCard.isUsed(9) == false) {
			if(curGame.maxOfAKind() >= 4) {
				curCard.scoreLine(9, curGame.totalAllDice() + 5);
			}
			else
				curCard.scoreLine(9, 0);
		}
		
		//fullHouse line
		if(curCard.isUsed(10) == false) {
			if (curGame.fullHouseFound() == true) {
				curCard.scoreLine(10, 25);
			}
			else
				curCard.scoreLine(10, 0);
		}
		
		//power up line
		if (curCard.isUsed(11) == false) {
			if (curGame.PowerUp())
				curCard.scoreLine(11, 30);
			else 
				curCard.scoreLine(11, 0);
		}
		
		//all different objects
		if (curCard.isUsed(12) == false) {
			if (curGame.allDifferent() == true)
				curCard.scoreLine(12, 40);
			else
				curCard.scoreLine(12, 0);
		}
		
		//all shells line
		if (curCard.isUsed(13) == false) {
			if (curGame.totalAllDice() == 0)
				curCard.scoreLine(13, 75);
			else
				curCard.scoreLine(13, 0);
		}
		
		//MARIO line! 5 of a kind
		if (curCard.isUsed(14) == false) {
			if (curGame.maxOfAKind() == 5)
				curCard.scoreLine(14, 50);
			else
				curCard.scoreLine(14, 0);
		}
		
		//chance line
		if (curCard.isUsed(15) == false) {
			curCard.scoreLine(15, curGame.totalAllDice());
		}
		
		//total of lower scoreCard
		int totalLowerScore = 0;
		for(int i = 8; i < 16; i++) {
			totalLowerScore += curCard.getScore(i);
		}
		curCard.scoreLine(16, totalLowerScore);
		
	}
	
	public boolean gameOver() {
		boolean gameover = true;
		for (int i = 0; i < numberPlayers; i++) {
			if (currentCards[i].getRound() < 15)
				gameover = false;
		}
		return gameover;
	}
	
	public ScoreCard determineWinner() {
		ScoreCard winner = currentCards[0];
		int highestScore = currentCards[0].finalScore();
		for(int i = 0; i < numberPlayers; i++) {
			if (currentCards[i].finalScore() > highestScore)
				winner = currentCards[i];
		}
		return winner;
	}
	
	public int[] winnerArray() {
		//array with the final scores
		int[] finalScores = new int[numberPlayers];
		for (int i = 0; i < numberPlayers; i ++) {
			finalScores[i] = currentCards[i].finalScore(); 
			System.out.println(currentCards[i].finalScore());
		}	
		return finalScores;
	}
	
	/*
	 * Array that contains the current scores for each player during a round
	 */
	public int[] roundLeadersArray() {
		int[] currentScores = new int[numberPlayers];
		for (int i = 0; i < numberPlayers; i ++) {
			currentScores[i] = currentCards[i].currentPlayerScore(); 
			System.out.println(currentCards[i].currentPlayerScore());
		}
		return currentScores;
	}
	
	public Game getGame(int player) {
		if (player == numberPlayers)
			return currentGames[0];
		return currentGames[player];
	}
	
	public ScoreCard getCard(int player) {
		if (player == numberPlayers)
			return currentCards[0];
		return currentCards[player];
	}
	
	public int getNumberPlayers() {
		return numberPlayers;
	}
}