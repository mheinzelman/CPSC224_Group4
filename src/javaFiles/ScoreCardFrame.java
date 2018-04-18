import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScoreCardFrame extends JFrame{
	private ScoreCardFrame currentFrame = this;
	private ScoreCard currentCard;
	
	ScoreCardFrame(ScoreCard currentCard){
		this.currentCard = currentCard;
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
			// add the buttons
			addButtons();
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
			
			//resize the label
			setLayout(null);
			title.setBounds(575, 30, 245, 35);
			
			//change background color and font
			title.setOpaque(true);
			title.setBackground(Color.CYAN);
			title.setFont(new Font("Super Mario 256", Font.PLAIN, 38));
			add(title);
		}
		
		private void addButtons(){
			//create the buttons
			JButton backScoreCard = new JButton("BACK");
			
			//set the size and location of the button
			backScoreCard.setBounds(100, 700, 200, 50);
			
			//change the font
			backScoreCard.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
			
			//add the button to the panel
			add(backScoreCard);
			
			//create an action listener for this button and add the action 
			//listener to the button
			backScoreCard.addActionListener(new ScoreCardAction());
			
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

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// if that was the last round, show the final screen
			
			//if there are more rounds, go to the roll screen for the next player
			//
			currentFrame.dispose();
			
		}
		
	}

}
