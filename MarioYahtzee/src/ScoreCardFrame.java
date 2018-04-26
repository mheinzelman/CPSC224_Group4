import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScoreCardFrame extends JFrame{
	private ScoreCardFrame currentFrame = this;
	private ScoreCard currentCard;
	private SuperMarioYahtzee controller;
	ScoreCardFrame(ScoreCard currentCard, SuperMarioYahtzee controller){
		this.currentCard = currentCard;
		this.controller = controller;
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
				if (i == 7 || i == 8 || currentCard.isUsed(i) == true) {
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
				if (currentCard.isUsed(i - 1) == true) {
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
			if (currentCard.cardID == 0) 
				return "MARIO.png";
			else if (currentCard.cardID == 1)
				return "LUIGI.png";
			else if(currentCard.cardID == 2)
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
					scorePanel.add(new JLabel("       " + currentCard.getScore(i - 1)));
				else if (i > 9)
					scorePanel.add(new JLabel("       " + currentCard.getScore(i - 2)));
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
					currentCard.setPicked(i, true);
					
					//increment round
					currentCard.incrementRound();
					if(currentCard.getRound() < 15) {
						//create a new instance of the roll screen
						//and create a new hand with a new roll
						controller.getGame(currentCard.cardID).getHand().createHand();
						new RerollDiceScreenFrame(controller.getGame(currentCard.cardID), currentCard, controller, 0);
					}
					else {
						//do something to show that the game is over, and go to the final screen
						//create a function determine winner in the controller that makes sure that all players have completed 
						//all 15 rounds. Then loop through all the total scores of the scorecards, determine the largest once and 
						//create an instance of the final screen passing in the winning sc
						
						if (controller.gameOver()) {
							//create a new screen of the final screen. replace this with mainscreen
							//ScoreCard winner = controller.determineWinner();
							//new finalScreenFrame(winner);
							int[] winner = controller.winnerArray();
							new LeaderboardRoundScreenFrame(winner, controller.getNumberPlayers());
						}	
							
					}
					currentFrame.dispose();
				}
			}
		}
		
	}

}
