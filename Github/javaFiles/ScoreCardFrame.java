import java.awt.*;

import javax.swing.*;

public class ScoreCardFrame extends JFrame{
	private ScoreCardFrame currentFrame = this;
	
	ScoreCardFrame(){
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
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(new ImageIcon("SuperMarioScoreCard.png").getImage(), 465, 90, 465, 670, null);
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
			
		}
		
		
	}

}
