import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlayerNumberFrame extends JFrame{
	PlayerNumberFrame currentFrame = this;
	
	PlayerNumberFrame(){
		//create the new frame for the game
		setTitle("Super Mario Yahtzee!");
		//set the size of the frame
		setSize(1400, 800);
		//make the frame close using the x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//add a MainScreenPanel
		add(new PlayerNumberScreenPanel());
				
		//set visible to true
		setVisible(true);
	}
	
	public class PlayerNumberScreenPanel extends JPanel{
		PlayerNumberScreenPanel(){
			//add the buttons
			addButtons();
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("playersScreenBackground.png").getImage(), 0, 0, 1400, 800, null);
			g.drawImage(new ImageIcon("PlayerNumberScreenTitle.png").getImage(), 390, 70, 615, 50, null);
		}
		
		private void addButtons() {
			//create the buttons to add
			JButton onePlayer = new JButton("1");
			JButton twoPlayer = new JButton("2");
			JButton threePlayer = new JButton("3");
			JButton fourPlayer = new JButton("4");
			
			JButton quitPlayerNumberScreen = new JButton("QUIT");
			JButton menuPlayerNumberScreen = new JButton("MENU");
			
			//change the location and size of the buttons
			setLayout(null);
			onePlayer.setBounds(350, 350, 100, 100);
			twoPlayer.setBounds(550, 350, 100, 100);
			threePlayer.setBounds(750, 350, 100, 100);
			fourPlayer.setBounds(950, 350, 100, 100);
			quitPlayerNumberScreen.setBounds(100, 700, 200, 50);
			menuPlayerNumberScreen.setBounds(1100, 700, 200, 50);
			
			//change the font and size of font for the 4 number buttons
			onePlayer.setFont(new Font("Super Mario 256",Font.PLAIN, 50));
			twoPlayer.setFont(new Font("Super Mario 256",Font.PLAIN, 50));
			threePlayer.setFont(new Font("Super Mario 256",Font.PLAIN, 50));
			fourPlayer.setFont(new Font("Super Mario 256",Font.PLAIN, 50));
			quitPlayerNumberScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			menuPlayerNumberScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			
			//change the background color of the number buttons
			onePlayer.setOpaque(true);
			onePlayer.setBackground(Color.RED);
			twoPlayer.setOpaque(true);
			twoPlayer.setBackground(Color.blue);
			threePlayer.setOpaque(true);
			threePlayer.setBackground(Color.green);
			fourPlayer.setOpaque(true);
			fourPlayer.setBackground(Color.cyan);
			
			
			//add these buttons to the playerNumberScreen panel
			add(onePlayer);
			add(twoPlayer);
			add(threePlayer);
			add(fourPlayer);
			add(quitPlayerNumberScreen);
			add(menuPlayerNumberScreen);
			
			//create PlayerNumberActions for each button
			PlayerNumberAction onePlayerAction = new PlayerNumberAction(1);
			PlayerNumberAction twoPlayerAction = new PlayerNumberAction(2);
			PlayerNumberAction threePlayerAction = new PlayerNumberAction(3);
			PlayerNumberAction fourPlayerAction = new PlayerNumberAction(4);
			PlayerNumberAction quitPlayerNumberAction = new PlayerNumberAction(5);
			PlayerNumberAction menuPlayerNumberAction = new PlayerNumberAction(6);
			
			//add action listeners to each button
			onePlayer.addActionListener(onePlayerAction);
			twoPlayer.addActionListener(twoPlayerAction);
			threePlayer.addActionListener(threePlayerAction);
			fourPlayer.addActionListener(fourPlayerAction);
			quitPlayerNumberScreen.addActionListener(quitPlayerNumberAction);
			menuPlayerNumberScreen.addActionListener(menuPlayerNumberAction);
		}
	}
	
	private class PlayerNumberAction implements ActionListener {
		private int buttonNumber;
		private Hand[] curHands;
		private Game[] curGames;
		private ScoreCard[] curCards;
		
		PlayerNumberAction(int buttonNumber){
			this.buttonNumber = buttonNumber;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Remove these checks and just pass in buttonNumber into the methods
			// but first check if it's 5 or 6
			if (buttonNumber == 1) {
				//one Player button pressed
				//create an instance of the next screen
				new RollDiceScreenFrame(1, 0, 1, 15); //1 player, start on player 1, round 1, 15 total rounds
				currentFrame.dispose();
			}
			else if (buttonNumber == 2) {
				//two player button pressed
				//create an instance of the next screen
				
				new RollDiceScreenFrame(2, 0, 1, 15);  //2 players, start on player 1, round 1, 15 total rounds
				currentFrame.dispose();
			}
			else if (buttonNumber == 3) {
				//three player button pressed
				//create an instance of the next screen
				
				new RollDiceScreenFrame(3, 0, 1, 15);  //3 players, start on player 1, round 1, 15 total rounds
				currentFrame.dispose();
			}
			else if (buttonNumber == 4) {
				//four player button pressed
				//create an instance of the next screen
				
				new RollDiceScreenFrame(4, 0, 1, 15);  //4 players, start on player 1, round 1, 15 total rounds
				currentFrame.dispose();
			}
			else if (buttonNumber == 5) {
				//quit button pressed
				currentFrame.dispose();
			}
			else if (buttonNumber == 6) {
				//menu button pressed
				new MainScreenFrame();
				currentFrame.dispose();
			}
			
		}
		
		
	}

}
