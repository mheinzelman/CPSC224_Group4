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

public class RollDiceScreenFrame extends JFrame{
	private RollDiceScreenFrame currentFrame = this;
	
	private Game curGame;
	private ScoreCard curCard;
	private SuperMarioYahtzee controller;
	private int rerolls;
	private int totalPlayers; //amount of players in the game
	private int currentPlayer; //the current player's turn
	private int currentRound; //current round out of three
	private int totalRounds;	//total amount of rounds, default is 15
	private int[] array; //placeholder array for the leaderboard screen
	
	RollDiceScreenFrame(int totalPlayers, int currentPlayer, int currentRound, int totalRounds){
		this.totalPlayers = totalPlayers;
		this.currentPlayer = currentPlayer;
		this.currentRound = currentRound;
		this.totalRounds = totalRounds;
			
		//create the new frame for the game
		setTitle("Super Mario Yahtzee!");
		//set the size of the frame
		setSize(1400, 800);
		//make the frame close using the x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set visible to true
		setVisible(true);
		
		//add a RollDiceScreenPanel
		add(new RollDiceScreenPanel());				
	}
	
	public class RollDiceScreenPanel extends JPanel {
		RollDiceScreenPanel(){
			//add the buttons
			addButtons();
			//add the TextArea with the rules on it
			addTextArea();
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("rollDiceBackground.png").getImage(), 0, 0, 1400, 800, null);
			g.drawImage(new ImageIcon("MysteryBox.png").getImage(), 288, 300, 155, 145, null);
			g.drawImage(new ImageIcon("MysteryBox.png").getImage(), 468, 300, 155, 145, null);
			g.drawImage(new ImageIcon("MysteryBox.png").getImage(), 648, 300, 155, 145, null);
			g.drawImage(new ImageIcon("MysteryBox.png").getImage(), 828, 300, 155, 145, null);
			g.drawImage(new ImageIcon("MysteryBox.png").getImage(), 1008, 300, 155, 145, null);
		}
		
		private void addButtons() {
			//create the three buttons associated with the main screen
			JButton quitRulesScreen = new JButton("QUIT");
			JButton menuRulesScreen = new JButton("MENU");
			JButton rollDiceScreen = new JButton("BEGIN!");
			
			//reposition the buttons
			setLayout(null);
			quitRulesScreen.setBounds(100, 700, 200, 50);
			menuRulesScreen.setBounds(1100, 700, 200, 50);
			rollDiceScreen.setBounds(550, 500, 300, 75);
			
			//change fonts
			quitRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			menuRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			rollDiceScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 50));
			
			//add these buttons to the main screen
			add(quitRulesScreen);
			add(menuRulesScreen);
			add(rollDiceScreen);
			
			//create action listeners
			RollDiceScreenAction quitAction = new RollDiceScreenAction(1);
			RollDiceScreenAction menuAction = new RollDiceScreenAction(2);
			RollDiceScreenAction rolledAction = new RollDiceScreenAction(3);
			
			//add action listeners to the buttons
			quitRulesScreen.addActionListener(quitAction);
			menuRulesScreen.addActionListener(menuAction);
			rollDiceScreen.addActionListener(rolledAction);
		}
		
		private void addTextArea() {
			//create a new JTextArea
			JTextArea rollDiceText = new JTextArea("Click on 'BEGIN' to start!");
			//resize and reposition the text area
			rollDiceText.setBounds(425, 50, 550, 100);
			rollDiceText.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			//When the words are highlighted, the highlight is black. setOpaque prevents this from happening
			rollDiceText.setOpaque(false);
			//dont allow user to type in it
			rollDiceText.setEditable(false);
			//set background color
			rollDiceText.setBackground(new Color(0,0,0,0));
			add(rollDiceText);
		}	
	}
	
	private class RollDiceScreenAction implements ActionListener{
		private int buttonNumber;
		
		RollDiceScreenAction(int buttonNumber){
			this.buttonNumber = buttonNumber;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (buttonNumber == 1) {
				//button pressed is QUIT button
				currentFrame.dispose();
			}
			else if (buttonNumber == 2) {
				//button pressed is MENU
				//Display main menu
				new MainScreenFrame();
				currentFrame.dispose();
			}	
			else if(buttonNumber == 3) {
				//button pressed is ROLL
				//Display reroll menu
				new SuperMarioYahtzee(curGame, curCard, controller, 3, totalPlayers, currentPlayer, currentRound, totalRounds, array);
				currentFrame.dispose();
			}			
		}	
	}
}
