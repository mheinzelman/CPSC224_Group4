import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScoreCardFrame extends JFrame{
	private ScoreCardFrame currentFrame = this;
	private Game curGame;
	private ScoreCard curCard;
	private SuperMarioYahtzee controller;
	private int totalPlayers; //amount of players in the game
	private int currentPlayer; //the current player's turn
	private int currentRound; //current round out of three
	private int totalRounds;	//total amount of rounds, default is 15
	private int[] array; //placeholder array for the leaderboard screen
	
	ScoreCardFrame(Game curGame, ScoreCard curCard, SuperMarioYahtzee controller, int totalPlayers, int currentPlayer, int currentRound, int totalRounds, int[] array){
		this.curGame = curGame;
		this.curCard = curCard;
		this.controller = controller;
		this.totalPlayers = totalPlayers;
		this.currentPlayer = currentPlayer;
		this.currentRound = currentRound;
		this.totalRounds = totalRounds;
		this.array = array;
		
		//create the new frame for the game
		setTitle("Super Mario Yahtzee!");
		//set the size of the frame
		setSize(1400, 800);
		//make the frame close using the x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//add a MainScreenPanel
		add(new ScoreCardPanel());
						
		//set visible to true
		setVisible(true);
				
	}
	

	
	public class ScoreCardPanel extends JPanel {
		ScoreCardPanel(){
			//add the title Label
			addTitle();
			//set the background color
			setBackground(Color.CYAN);
			//chose score to record buttons
			scoreChooseButtons();
			//display scores
			displayScores();
			
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(new ImageIcon("Scorecard_oneperson.png").getImage(), 563, 70, 275, 690, null);
			g.drawImage(new ImageIcon("PlantRight.png").getImage(), 82, 120, 300, 570, null);
			g.drawImage(new ImageIcon("PlantLeft.png").getImage(), 1012, 120, 300, 570, null);
		}
		
		private void addTitle() {
			//create the JLabel
			JLabel title = new JLabel("SCORECARD!");
			JLabel instructions = new JLabel("Press the button to keep the score!");
			
			//resize the label
			setLayout(null);
			title.setBounds(575, 30, 245, 35);
			instructions.setBounds(845, 80, 400, 20);
			
			//change background color and font
			title.setOpaque(true);
			title.setBackground(Color.CYAN);
			title.setFont(new Font("Super Mario 256", Font.PLAIN, 38));
			add(title);
			add(instructions);
		}
		
		private void scoreChooseButtons() {
			//create a new jpanel for the buttons to go on
			JPanel chooseButtonPanel = new JPanel();
			chooseButtonPanel.setBounds(850, 105, 40, 617);
			chooseButtonPanel.setLayout(new GridLayout(17,1));
			chooseButtonPanel.setOpaque(false);
			
			for (int i = 0; i < 9; i++) {
				//if we are at any of these positions, add a label instead
				if (i == 7 || i == 8 || curCard.isUsed(i) == true) {
					JLabel space = new JLabel();
					chooseButtonPanel.add(space);
				}
				else {
					//else add a button
					JButton temp = new JButton("");
					temp.addActionListener(new ScoreCardAction(i));
					chooseButtonPanel.add(temp);
				}
			
			}
			for (int i = 9; i < 17; i++) {
				//if we are at any of these positions, add a label instead
				if (curCard.isUsed(i - 1) == true) {
					JLabel space = new JLabel();
					chooseButtonPanel.add(space);
				}
				else {
					//else add a button
					JButton temp = new JButton("");
					temp.addActionListener(new ScoreCardAction(i - 1));
					chooseButtonPanel.add(temp);
				}	
			}
			
			add(chooseButtonPanel);
		}
		
		private String determineID() {
			if (curCard.cardID == 0) 
				return "MARIO.png";
			else if (curCard.cardID == 1)
				return "LUIGI.png";
			else if(curCard.cardID == 2)
				return "YOSHI.png";
			return "TOAD.png";
		}
		
		public void displayScores() {
			//create the two new score panels
			JPanel scorePanel = new JPanel();
			
			//set the size and location
			scorePanel.setBounds(773, 70, 65, 690);
			
			//set the layout manager to be GridLayout
			//makes the labels fit nicely into the scorecard
			scorePanel.setLayout(new GridLayout(19,1));
			
			//make the panel see through
			scorePanel.setOpaque(false);
			

			//set the labels
			for (int i = 0; i < 19; i++ ) {
				if (i == 0 || i == 9)
					scorePanel.add(new JLabel(new ImageIcon(determineID())));
				else if (i > 0 && i < 9)
					scorePanel.add(new JLabel("       " + curCard.getScore(i - 1)));
				else if (i > 9)
					scorePanel.add(new JLabel("       " + curCard.getScore(i - 2)));
			}			
			add(scorePanel);
		}
	}
	
	private class ScoreCardAction implements ActionListener {
		private int buttonNumb;
		ScoreCardAction(int buttonNumb){
			this.buttonNumb = buttonNumb;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			for (int i = 0; i < 17; i++) {
				if (buttonNumb == i) {
					curCard.setPicked(i, true);		
					//increment round
					curCard.incrementRound();
				}
			}
			
			//If not all the players have played that round yet, move on to the next player
			if(currentPlayer < (totalPlayers-1)) {
				controller.getGame(currentPlayer).getHand().createHand();
				new RerollDiceScreenFrame(controller.getGame(curCard.cardID + 1), controller.getCard(currentPlayer + 1), controller, 0, totalPlayers, ++currentPlayer, currentRound, totalRounds, array);
				currentFrame.dispose();
			}
			//If all the players have gone, display the leaderboard for that round
            else if(currentPlayer == (totalPlayers-1)) {
                //If it is the last round, display the winner
                if(currentRound == totalRounds) {
                		//create a new screen of the final screen. replace this with mainscreen
					//ScoreCard winner = controller.determineWinner();
					//new finalScreenFrame(winner);
                		int[] winner = controller.winnerArray();
                		controller.getGame(currentPlayer).getHand().createHand();
					new LeaderboardFinalFrame(curGame, curCard, controller, controller.getNumberPlayers(), currentPlayer, currentRound, totalRounds, winner);
					currentFrame.dispose();
                }
                //display the round leaderboard to show who is in the lead
                else {
                		int[] winner = controller.roundLeadersArray();
                		controller.getGame(currentPlayer).getHand().createHand();
					new LeaderboardRoundScreenFrame(curGame, curCard, controller, controller.getNumberPlayers(), currentPlayer, currentRound, totalRounds, winner);
                		currentFrame.dispose();
                }
            }
		}
		
	}

}