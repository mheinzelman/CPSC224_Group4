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

public class RulesScreenFrame extends JFrame{
	private RulesScreenFrame currentFrame = this;
	RulesScreenFrame(){
		//create the new frame for the game
		setTitle("Super Mario Yahtzee!");
		//set the size of the frame
		setSize(1400, 800);
		//make the frame close using the x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add a MainScreenPanel
		add(new RulesScreenPanel());
		
		//set visible to true
		setVisible(true);
	}
	
	public class RulesScreenPanel extends JPanel {
		RulesScreenPanel(){
			//add the buttons
			addButtons();
			//add the TextArea with the rules on it
			addTextArea();
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("rulesScreenBackground.png").getImage(), 0, 0, 1400, 800, null);
			g.drawImage(new ImageIcon("rulesScreenTitle.png").getImage(), 590, 50, 225, 90, null);
		}
		
		private void addButtons() {
			//create the three buttons associated with the main screen
			JButton quitRulesScreen = new JButton("QUIT");
			JButton menuRulesScreen = new JButton("MENU");
			
			//reposition the buttons
			setLayout(null);
			quitRulesScreen.setBounds(100, 700, 200, 50);
			menuRulesScreen.setBounds(1100, 700, 200, 50);
			
			//change fonts
			quitRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			menuRulesScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			
			//add these buttons to the main screen
			add(quitRulesScreen);
			add(menuRulesScreen);
			
			//create action listeners
			RulesScreenAction quitAction = new RulesScreenAction(1);
			RulesScreenAction menuAction = new RulesScreenAction(2);
			
			//add action listeners to the buttons
			quitRulesScreen.addActionListener(quitAction);
			menuRulesScreen.addActionListener(menuAction);
		}
		
		private void addTextArea() {
			//create a new JTextArea
			JTextArea rulesText = new JTextArea();
			//resize and reposition the text area
			rulesText.setBounds(385, 160, 630, 480);
			//dont allow user to type in it
			rulesText.setEditable(false);
			//set background color
			rulesText.setBackground(Color.LIGHT_GRAY);
			//read the rules from the file
			
			try {
				Reader reader = new BufferedReader(new FileReader("Rules.txt"));
				rulesText.read(reader, "Rules");
			}
			catch(FileNotFoundException e) {
				System.out.print("File Not Found");
			}
			catch(IOException e) {
				System.out.print("Could Not Read");
			}
			
			add(rulesText);
		}
		
	}
	
	private class RulesScreenAction implements ActionListener{
		private int buttonNumber;
		
		RulesScreenAction(int buttonNumber){
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
			
		}
		
	}
}
