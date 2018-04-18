import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.*;

public class RerollDiceScreenFrame extends JFrame{
	private RerollDiceScreenFrame currentFrame = this;
	private Game curGame;
	private int rerolls;
	private ScoreCard curCard;
	private SuperMarioYahtzee controller;
	
	RerollDiceScreenFrame(Game curGame, ScoreCard curCard, SuperMarioYahtzee controller, int rerolls){
		this.rerolls = rerolls;
		this.curGame = curGame;
		this.curCard = curCard;
		this.controller = controller;
		
		if(rerolls < 3) {
			//create the new frame for the game
			setTitle("Super Mario Yahtzee!");
			//set the size of the frame
			setSize(1400, 800);
			//make the frame close using the x
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//add a MainScreenPanel
			add(new RerollDiceScreenPanel());
			
			//set visible to true
			setVisible(true);
		}
	}
	
	public class RerollDiceScreenPanel extends JOptionPane {
		RerollDiceScreenPanel(){
			//add the buttons
			addButtons();
			//add the TextArea with the title label on it
			addTextArea();
			//determine the pictures that will be used for the dice
			
			//add buttons for the dice
			addDiceButtons();
		}
		
		private String addPictures(int dieFace) {
			if (dieFace == 0)
				return "Shell.png";
			else if(dieFace == 1)
				return "Mushroom.png";
			else if(dieFace == 2)
				return "Egg.png";
			else if(dieFace == 3)
				return "Flower.png";
			else if(dieFace == 4)
				return "Coin.png";
			
			return "Star.png";
			
		}
		
		private String determineID() {
			if (curGame.gameID == 0) 
				return "MARIO.png";
			else if (curGame.gameID == 1)
				return "LUIGI.png";
			else if(curGame.gameID == 2)
				return "YOSHI.png";
			return "TOAD.png";
		}

		
		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("rollDiceBackground.png").getImage(), 0, 0, 1400, 800, null);
			g.drawImage(new ImageIcon(determineID()).getImage(), 10, 10, 40, 40, null);
		}
		
		private void addButtons() {
			//create the three buttons associated with the main screen
			JButton quitRulesScreen = new JButton("QUIT");
			JButton nextScreen = new JButton("NEXT");
			JButton rolledDiceScreen = new JButton("ROLL!");
			
			//reposition the buttons
			setLayout(null);
			quitRulesScreen.setBounds(100, 700, 200, 50);
			nextScreen.setBounds(1100, 700, 200, 50);
			rolledDiceScreen.setBounds(550, 500, 300, 75);
			
			//change fonts
			quitRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			nextScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			rolledDiceScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			
			//add these buttons to the main screen
			add(quitRulesScreen);
			add(nextScreen);
			//dont show this button if the player has already rerolled twice
			if (rerolls < 2)
				add(rolledDiceScreen);
			
			//create action listeners
			RerollDiceScreenAction quitAction = new RerollDiceScreenAction(1);
			RerollDiceScreenAction nextAction = new RerollDiceScreenAction(2);
			RerollDiceScreenAction rolledAction = new RerollDiceScreenAction(8);
			
			//add action listeners to the buttons
			quitRulesScreen.addActionListener(quitAction);
			nextScreen.addActionListener(nextAction);
			rolledDiceScreen.addActionListener(rolledAction);
		}
		
		//Adds button functionality for the dice
		private void addDiceButtons() {
			
			//Creates five buttons to represent the die
			JButton diceSelect1 = new JButton(new ImageIcon(addPictures(curGame.getHand().intAt(0))));
			JButton diceSelect2 = new JButton(new ImageIcon(addPictures(curGame.getHand().intAt(1))));
			JButton diceSelect3 = new JButton(new ImageIcon(addPictures(curGame.getHand().intAt(2))));
			JButton diceSelect4 = new JButton(new ImageIcon(addPictures(curGame.getHand().intAt(3))));
			JButton diceSelect5 = new JButton(new ImageIcon(addPictures(curGame.getHand().intAt(4))));
			
			//reposition the buttons and resize them
			setLayout(null);
			diceSelect1.setBounds(288, 320, 100, 100);
			diceSelect2.setBounds(468, 320, 100, 100);
			diceSelect3.setBounds(648, 320, 100, 100);
			diceSelect4.setBounds(828, 320, 100, 100);
			diceSelect5.setBounds(1008, 320, 100, 100);
			
			//change the fonts of the five die
			diceSelect1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
			diceSelect2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
			diceSelect3.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
			diceSelect4.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
			diceSelect5.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
			
			//add these buttons to the RollDice screen
			add(diceSelect1);
			add(diceSelect2);
			add(diceSelect3);
			add(diceSelect4);
			add(diceSelect5);
			
			//create action listeners
			RerollDiceScreenAction diceAction1 = new RerollDiceScreenAction(3);
			RerollDiceScreenAction diceAction2 = new RerollDiceScreenAction(4);
			RerollDiceScreenAction diceAction3 = new RerollDiceScreenAction(5);
			RerollDiceScreenAction diceAction4 = new RerollDiceScreenAction(6);
			RerollDiceScreenAction diceAction5 = new RerollDiceScreenAction(7);
			
			//add action listeners to the buttons
			diceSelect1.addActionListener(diceAction1);
			diceSelect2.addActionListener(diceAction2);
			diceSelect3.addActionListener(diceAction3);
			diceSelect4.addActionListener(diceAction4);
			diceSelect5.addActionListener(diceAction5);
		}
		
		private void addTextArea() {
			//create a new JTextArea
			JTextArea keepDiceText = new JTextArea("Click on any dice to Reroll!");
			JLabel IDLabel = new JLabel("Player " + (curGame.gameID + 1));
			
			//resize and reposition the text area
			IDLabel.setBounds(5, 50, 150, 50);		
			keepDiceText.setBounds(430, 100, 540, 75);
			keepDiceText.setFont(new Font("Super Mario 256", Font.PLAIN, 32));
			//When the words are highlighted, the highlight is black. setOpaque prevents this from happening
			keepDiceText.setOpaque(false);
			//dont allow user to type in it
			keepDiceText.setEditable(false);
			//set background color
			keepDiceText.setBackground(new Color(0,0,0,0));
			add(keepDiceText);
			add(IDLabel);
		}
	}
	
	private class RerollDiceScreenAction implements ActionListener{
		private int buttonNumber;
		private boolean[] pressed = new boolean[5];
		
		RerollDiceScreenAction(int buttonNumber){
			this.buttonNumber = buttonNumber;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (buttonNumber == 1) {
				//button pressed is QUIT button
				currentFrame.dispose();
			}
			else if (buttonNumber == 2) {
				//button pressed is next
				//create the new scorecard to display and display it
				controller.createScoreCard(curCard, curGame);
				
				new ScoreCardFrame(curCard);
				
				currentFrame.dispose();
			}	
			
			//Dice button number 1
			else if(buttonNumber == 3) {
				if (pressed[0] == false) {
					curGame.getHand().reroll(0);
					pressed[0] = true;
				}

			}
			//Dice button number 2
			else if(buttonNumber == 4) {
				if(pressed[1] == false) {
					curGame.getHand().reroll(1);
					pressed[1] = true;
				}

				
			}
			//Dice button number 3
			else if(buttonNumber == 5) {
				if(pressed[2] == false) {
					curGame.getHand().reroll(2);
					pressed[2] = true;
				}

			}
			//Dice button number 4
			else if(buttonNumber == 6) {
				if(pressed[3] == false) {
					curGame.getHand().reroll(3);
					pressed[3] = true;
				}

			}
			//Dice button number 5
			else if(buttonNumber == 7) {
				if (pressed[4] == false) {
					curGame.getHand().reroll(4);
					pressed[4] = true;
				}

			}
			else if (buttonNumber == 8) {
				//roll button is pressed, rerolls selected dice
				//recursively call the rerolldicescreen untill all of the rerolls are used
				new RerollDiceScreenFrame(curGame, curCard, controller, ++rerolls);
				currentFrame.dispose();
			}
		}	
	}
}
