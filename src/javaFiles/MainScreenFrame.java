import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainScreenFrame extends JFrame{
	MainScreenFrame currentFrame = this;
		
		MainScreenFrame(){
			//create the new frame for the game
			setTitle("Super Mario Yahtzee!");
			//set the size of the frame
			setSize(1400, 800);
			//make the frame close using the x
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//add a MainScreenPanel
			add(new MainScreen());
			
			//set visible to true
			setVisible(true);
			
			
			
		}
		public class MainScreen extends JPanel{
			MainScreen(){
				//add the buttons
				addButtons();
			}
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				//g.drawImage(image, posX, posY, sizeX, sizeY, null);
				g.drawImage(new ImageIcon("mainScreenBackground.png").getImage(), 0, 0, 1400, 800, null);
				g.drawImage(new ImageIcon("mainScreenTitle.png").getImage(), 430, 75, 550, 145, null);
				g.drawImage(new ImageIcon("mainScreenMario.png").getImage(), 460, 170, 500, 556, null);
				
				
			}
			
			public Dimension getPreferredSize() {
				return new Dimension(2000,1000);
			}
			
			private void addButtons() {
				//create the three buttons associated with the main screen
				JButton quitMainScreen = new JButton("QUIT");
				JButton playMainScreen = new JButton("PLAY");
				JButton rulesMainScreen = new JButton("RULES");
				
				//reposition the buttons
				setLayout(null);
				quitMainScreen.setBounds(100, 700, 200, 50);
				playMainScreen.setBounds(600, 700, 200, 50);
				rulesMainScreen.setBounds(1100, 700, 200, 50);
				
				//change fonts 
				quitMainScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				playMainScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				rulesMainScreen.setFont(new Font("Super Mario 256", Font.PLAIN, 35));
				
				//add these buttons to the main screen
				add(quitMainScreen);
				add(playMainScreen);
				add(rulesMainScreen);
				
				//create action listeners
				MainScreenAction quitAction = new MainScreenAction(1);
				MainScreenAction playAction = new MainScreenAction(2);
				MainScreenAction rulesAction = new MainScreenAction(3);
				
				//add action listeners to the buttons
				quitMainScreen.addActionListener(quitAction);
				playMainScreen.addActionListener(playAction);
				rulesMainScreen.addActionListener(rulesAction);
				
			}
			
			
			private class MainScreenAction implements ActionListener{
				private int buttonNumber;
				
				MainScreenAction(int buttonNumber){
					this.buttonNumber = buttonNumber;
					
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (buttonNumber == 1) {
						//button pressed is QUIT button
						currentFrame.dispose();
						
					}
					else if(buttonNumber == 2) {
						//button pressed is PLAY
						//create an instance of the next screen
						new PlayerNumberFrame();
						currentFrame.dispose();
						
					}
					else if (buttonNumber == 3) {
						//button pressed is RULES
						//Display rules
						new RulesScreenFrame();
						currentFrame.dispose();
					}
					
				}
				
			}
		}
		
	}