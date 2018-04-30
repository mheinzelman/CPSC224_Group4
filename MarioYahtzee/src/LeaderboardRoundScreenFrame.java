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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class LeaderboardRoundScreenFrame extends JFrame{
	private LeaderboardRoundScreenFrame currentFrame = this;
	private int[] leaderboardScore; //holds the scores from highest to lowest
	private int[] playerScore; //holds the score for each player
	private int players; //holds the total number of players
	private int size; //holds the size of the playerScore array
	private Game curGame;
	private ScoreCard curCard;
	private SuperMarioYahtzee controller;
	private int totalPlayers; //amount of players in the game
	private int currentPlayer; //the current player's turn
	private int currentRound; //current round out of three
	private int totalRounds;	//total amount of rounds, default is 15
	private int[] array; //placeholder array for the leaderboard screen
	
	LeaderboardRoundScreenFrame(Game curGame, ScoreCard curCard, SuperMarioYahtzee controller, int totalPlayers, int currentPlayer, int currentRound, int totalRounds, int[] array){
		
		this.curGame = curGame;
		this.curCard = curCard;
		this.controller = controller;
		this.totalPlayers = totalPlayers;
		leaderboardScore = new int [totalPlayers];
		this.currentPlayer = currentPlayer;
		this.currentRound = currentRound;
		this.totalRounds = totalRounds;
		this.array = array;
		
		playerScore = Arrays.copyOf(array, array.length); //copy the array from the parameter into playerScore
		players = totalPlayers; //set amount of players
		//create the new frame for the game
		setTitle("Super Mario Yahtzee!");
		//set the size of the frame
		setSize(1400, 800);
		//make the frame close using the x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add a MainScreenPanel
		add(new LeaderboardRoundScreenPanel());
		
		//set visible to true
		setVisible(true);
	}
	
	/*
	 * This function sorts the player's scores from highest to lowest and stores them in leaderboardScore array
	 */
	private void determineHighScore() {
		size = playerScore.length;
		//copy elements into leaderboard score
		for(int i = 0; i < size; i++)
		{
			leaderboardScore[i] = playerScore[i];
			System.out.println(leaderboardScore[i]);
		}
		//sort the array
		Arrays.sort(leaderboardScore);
		
		/*
		* 	Since using Arrays.sort sorts the numbers from least to greatest, we have to sort it from greatest to least. Unfortunately using 
		*	Collections.reverseOrder() does not work with primitives so I have to make the code look disgusting
		*/
		int[] reverseOrderArray = new int[4];
		for(int i = 0; i < size; i++) {
			reverseOrderArray[i] = leaderboardScore[size-1-i];
		}
		leaderboardScore = Arrays.copyOf(reverseOrderArray, reverseOrderArray.length);
		for(int i = 0; i < size; i++)
		{
			System.out.println(leaderboardScore[i]);
		}
	}
	
	public class LeaderboardRoundScreenPanel extends JPanel {
		LeaderboardRoundScreenPanel(){
			//add the buttons
			addButtons();
			//add the TextArea with the rules on it
			addTextArea();
			determineHighScore();
			addTextPlacement();
		}
		
		/*
		 * This function establishes the background for the screen
		 */
		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("LeaderboardRound.png").getImage(), 0, 0, 1400, 800, null);
		}
		
		/*
		 * This function creates two buttons that will quit the program or bring the user to the main menu
		 */
		private void addButtons() {
			//create the three buttons associated with the main screen
			JButton quitRulesScreen = new JButton("QUIT");
			JButton menuRulesScreen = new JButton("MENU");
			JButton rerollScreen = new JButton("NEXT");
			 
			//reposition the buttons
			setLayout(null);
			quitRulesScreen.setBounds(100, 700, 200, 50);
			menuRulesScreen.setBounds(600, 700, 200, 50);
			rerollScreen.setBounds(1100, 700, 200, 50);
			
			//change fonts
			quitRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			menuRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			rerollScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			
			//add these buttons to the main screen
			add(quitRulesScreen);
			add(menuRulesScreen);
			add(rerollScreen);
			 
			//create action listeners
			LeaderboardRoundScreenAction quitAction = new LeaderboardRoundScreenAction(1);
			LeaderboardRoundScreenAction menuAction = new LeaderboardRoundScreenAction(2);
			 LeaderboardRoundScreenAction rerollAction = new LeaderboardRoundScreenAction(3);
			  
			//add action listeners to the buttons
			quitRulesScreen.addActionListener(quitAction);
			menuRulesScreen.addActionListener(menuAction);
			rerollScreen.addActionListener(rerollAction);
		}
		
		/*
		 * This function creates the physical leaderboard to be displayed on the screen
		 */
		private void addTextPlacement() {		
			int place = 0;			
			//First place text 
			//Marks the scores from highest to lowest
			for(int i = 0; i < size; i++) {
				if(leaderboardScore[0] == playerScore[i]) {
					place = i;
				}	
			}
			
			if(place == 0) {
				JTextArea placementText1 = new JTextArea("1st: Player 1 " + "     "+ playerScore[0] + " pts");
				//resize and reposition the text area
				placementText1.setBounds(400, 260, 600, 50);
				placementText1.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				//dont allow user to type in it
				placementText1.setEditable(false);
				//set background color to gold
				placementText1.setBackground(new Color(255, 215, 0));
				add(placementText1);
			}
			else if(place == 1) {
				JTextArea placementText1 = new JTextArea("1st: Player 2 " + "     "+playerScore[1]+ " pts" );
				//resize and reposition the text area
				placementText1.setBounds(400, 260, 600, 50);
				placementText1.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				//dont allow user to type in it
				placementText1.setEditable(false);
				//set background color to gold
				placementText1.setBackground(new Color(255, 215, 0));
				add(placementText1);
			}
			else if(place == 2) {
				JTextArea placementText1 = new JTextArea("1st: Player 3 " + "     "+ playerScore[2]+ " pts" );
				//resize and reposition the text area
				placementText1.setBounds(400, 260, 600, 50);
				placementText1.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				//dont allow user to type in it
				placementText1.setEditable(false);
				//set background color to gold
				placementText1.setBackground(new Color(255, 215, 0));
				add(placementText1);
			}
			else {
				JTextArea placementText1 = new JTextArea("1st: Player 4 " + "     "+ playerScore[3]+ " pts" );
				//resize and reposition the text area
				placementText1.setBounds(400, 260, 600, 50);
				placementText1.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				//dont allow user to type in it
				placementText1.setEditable(false);
				//set background color to gold
				placementText1.setBackground(new Color(255, 215, 0));
				add(placementText1);
			}
			
			//If there are at least two players
			if(players >= 2) {		
				//second place text 
				place = 0; //reset place to 0
				
				//Marks the scores from highest to lowest
				for(int i = 0; i < size; i++) {
					if(leaderboardScore[1] == playerScore[i]) {
						place = i;
					}	
				}
				if(place == 0) {
					JTextArea placementText2 = new JTextArea("2nd: Player 1 " + "     "+ playerScore[0]+ " pts");
					//resize and reposition the text area
					placementText2.setBounds(400, 360, 600, 50);
					placementText2.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText2.setEditable(false);
					//set background color to gold
					placementText2.setBackground(new Color(255, 215, 0));
					add(placementText2);
				}
				
				else if(place == 1) {
					JTextArea placementText2 = new JTextArea("2nd: Player 2 " + "     "+ playerScore[1]+ " pts");
					//resize and reposition the text area
					placementText2.setBounds(400, 360, 600, 50);
					placementText2.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText2.setEditable(false);
					//set background color to gold
					placementText2.setBackground(new Color(255, 215, 0));
					add(placementText2);
				}
				else if(place == 2) {
					JTextArea placementText2 = new JTextArea("2nd: Player 3 " + "     "+ playerScore[2]+ " pts");
					//resize and reposition the text area
					placementText2.setBounds(400, 360, 600, 50);
					placementText2.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText2.setEditable(false);
					//set background color to gold
					placementText2.setBackground(new Color(255, 215, 0));
					add(placementText2);
				}
				else if(place == 3) {
					JTextArea placementText2 = new JTextArea("2nd: Player 4 " + "     "+ playerScore[3]+ " pts");
					//resize and reposition the text area
					placementText2.setBounds(400, 360, 600, 50);
					placementText2.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText2.setEditable(false);
					//set background color to gold
					placementText2.setBackground(new Color(255, 215, 0));
					add(placementText2);
				}
			}
			//When there is at least three players
			if(players >= 3) {
				//text when there is three players
				place = 0; //reset place to 0
				
				//Marks the scores from highest to lowest
				for(int i = 0; i < size; i++) {
					if(leaderboardScore[2] == playerScore[i]) {
						place = i;
					}	
				}
				if(place == 0) {
					JTextArea placementText3 = new JTextArea("3rd: Player 1 " + "     "+ playerScore[0]+ " pts");
					//resize and reposition the text area
					placementText3.setBounds(400, 460, 600, 50);
					placementText3.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText3.setEditable(false);
					//set background color to gold
					placementText3.setBackground(new Color(255, 215, 0));
					add(placementText3);
				}
				
				else if(place == 1) {
					JTextArea placementText3 = new JTextArea("3rd: Player 2 " + "     "+ playerScore[1]+ " pts");
					//resize and reposition the text area
					placementText3.setBounds(400, 460, 600, 50);
					placementText3.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText3.setEditable(false);
					//set background color to gold
					placementText3.setBackground(new Color(255, 215, 0));
					add(placementText3);
				}
				else if(place == 2) {
					JTextArea placementText3 = new JTextArea("3rd: Player 3 " + "     "+ playerScore[2]+ " pts");
					//resize and reposition the text area
					placementText3.setBounds(400, 460, 600, 50);
					placementText3.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText3.setEditable(false);
					//set background color to gold
					placementText3.setBackground(new Color(255, 215, 0));
					add(placementText3);
				}
				else if(place == 3) {
					JTextArea placementText3 = new JTextArea("3rd: Player 4 " + "     "+ playerScore[3]+ " pts");
					//resize and reposition the text area
					placementText3.setBounds(400, 460, 600, 50);
					placementText3.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText3.setEditable(false);
					//set background color to gold
					placementText3.setBackground(new Color(255, 215, 0));
					add(placementText3);
				}
			}
			
			//When there is at least four players
			if(players == 4) {
				//text for four players
				place = 0; //reset place to 0
				
				//Marks the scores from highest to lowest
				for(int i = 0; i < size; i++) {
					if(leaderboardScore[3] == playerScore[i]) {
						place = i;
					}	
				}
				if(place == 0) {
					JTextArea placementText4 = new JTextArea("4th: Player 1 " + "     "+ playerScore[0]+ " pts");
					//resize and reposition the text area
					placementText4.setBounds(400, 560, 600, 50);
					placementText4.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText4.setEditable(false);
					//set background color to gold
					placementText4.setBackground(new Color(255, 215, 0));
					add(placementText4);	
				}
				
				else if(place == 1) {
					JTextArea placementText4 = new JTextArea("4th: Player 2 " + "     "+ playerScore[1]+ " pts");
					//resize and reposition the text area
					placementText4.setBounds(400, 560, 600, 50);
					placementText4.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText4.setEditable(false);
					//set background color to gold
					placementText4.setBackground(new Color(255, 215, 0));
					add(placementText4);	
				}
				else if(place == 2) {
					JTextArea placementText4 = new JTextArea("4th: Player 3 " + "     "+ playerScore[2]+ " pts");
					//resize and reposition the text area
					placementText4.setBounds(400, 560, 600, 50);
					placementText4.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText4.setEditable(false);
					//set background color to gold
					placementText4.setBackground(new Color(255, 215, 0));
					add(placementText4);	
				}
				else if(place == 3) {
					JTextArea placementText4 = new JTextArea("4th: Player 4 " + "     "+ playerScore[3]+ " pts");
					//resize and reposition the text area
					placementText4.setBounds(400, 560, 600, 50);
					placementText4.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
					//dont allow user to type in it
					placementText4.setEditable(false);
					//set background color to gold
					placementText4.setBackground(new Color(255, 215, 0));
					add(placementText4);	
				}
			}
		}
		
		/*
		 * This function adds text "Leaderboard" to the top-middle part of the screen
		 */
		private void addTextArea() {
			//create a new JTextArea
			JTextArea rollDiceText = new JTextArea("Round " + currentRound + "/" + totalRounds + " Results");
			//resize and reposition the text area
			rollDiceText.setBounds(450, 50, 500, 100);
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
	
	private class LeaderboardRoundScreenAction implements ActionListener{
		private int buttonNumber;
		
		LeaderboardRoundScreenAction(int buttonNumber){
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
				//button pressed is NEXT
                //display reroll screen
				//increment the currentRound and reset the current player to 0
				new RerollDiceScreenFrame(controller.getGame(0), controller.getCard(0), controller, 0, totalPlayers, 0, ++currentRound, totalRounds, array);
				currentFrame.dispose();
			}
				
		}	
	}
}