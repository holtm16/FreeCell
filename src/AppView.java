/**
 * This class serves as the main window for displaying the FreeCell game.
 * @author Ryan Grant and Michael Holt
 */

import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;



public class AppView extends JFrame{
	
	// Regular Stuff
	private FreeCellGame game;	

	private SingleCellPanel[] freePanels;
	private SingleCellPanel[] homePanels;
	private TableauPanel[] tableauPanels;
	private AbstractPanel fromPanel;
	
	public static Color GREEN_COLOR = new Color(0, 170, 51);
	
	
	// Extra Stuff
	// Sound
	private boolean soundOn;
	private AudioClip music;

	
	// Timer
	private JLabel timerLabel;
	private static int seconds;
	private static int minutes;
	
	// Clicks
	private JLabel clickLabel;
	private int clicks;
	
	// Undo
	private ArrayList<AbstractPanel> undoFroms;
	private ArrayList<AbstractPanel> undoTos;
	private ArrayList<Integer> undoSizes;

	/**
	 * Sets up the game of FreeCell in GUI Form.
	 */
    public AppView(){

    	// Extra Stuff
        // Sound
		music = Applet.newAudioClip(getClass().getResource("Elevator.wav"));

        soundOn = false;
        
        // Timer
    	timerLabel = new JLabel("Total Time: 00:00", JLabel.CENTER);
        seconds = 0;
        minutes = 0;
        // Custom Clock
        ActionListener actListner = new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			// Increases timer
        		    seconds += 1;
        		    
        		    // Formats time into minutes and seconds
        		    if (seconds == 60){
        		    	seconds = 0;
        		    	minutes += 1;
        		    }
        		    String result = "Total Time: ";
        		    if (minutes < 10)
            		    result += "0";
        		    result += minutes + ":";
        		    if (seconds < 10) 
        		    	result += "0";
        		    result += seconds;
        		    
        		    // Sets the label to be the new time
        		    timerLabel.setText(result);
        		}	 
        };
        // Starting the timer
        // Calls actListner every 1000 milliseconds
        Timer timer = new Timer(1000, actListner);
        timer.start();
        
        // Clicks
    	clickLabel = new JLabel("Total Clicks: 0", JLabel.CENTER);
        clicks = 0;
        
        // Undo
        undoFroms = new ArrayList<AbstractPanel>();
    	undoTos = new ArrayList<AbstractPanel>();
    	undoSizes = new ArrayList<Integer>();
        
    	// Regular stuff
		game = new FreeCellGame();
		fromPanel = null;
		AppViewInformer informer = new AppViewInformer();
		freePanels = new SingleCellPanel[4];
		homePanels = new SingleCellPanel[4];
		tableauPanels = new TableauPanel[8];
		for (int i = 0; i < freePanels.length; i++)
			freePanels[i] = new SingleCellPanel(game.getFreeCell(i), informer);
		for (int i = 0; i < homePanels.length; i++)
			homePanels[i] = new SingleCellPanel(game.getHomeCell(i), informer);
		for (int i = 0; i < tableauPanels.length; i++)
			tableauPanels[i] = new TableauPanel(game.getTableau(i), informer);
		
		Container mainWindow = getContentPane();
		
		mainWindow.add(getAllLabelPanels(), BorderLayout.NORTH);
		mainWindow.add(getAllCellPanels(), BorderLayout.CENTER);
		mainWindow.add(getButtonPanel(), BorderLayout.SOUTH);
	}
    
	/**
	 * Creates a JPanel for the new game button.
 	 * @return panel - a panel containing the new game button
	 */
	private JPanel getButtonPanel(){
		
		// Regular Stuff
		JPanel panel = new JPanel();
		panel.setBackground(GREEN_COLOR);
		panel.setLayout(new GridLayout(1, 3, 0, 0));
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener(){ 
           		public void actionPerformed(ActionEvent e){
					game.resetGame();
					seconds = 0;
					clicks = 0;
					minutes = 0;
					clickLabel.setText("Total Clicks: 0");
			        undoFroms = new ArrayList<AbstractPanel>();
			    	undoTos = new ArrayList<AbstractPanel>();
			    	undoSizes = new ArrayList<Integer>();
					repaint();
            	}
		});
		
		// Extra Stuff
		// Sound Button
		JButton musicButton = new JButton("Music");
		musicButton.addActionListener(new ActionListener(){ 
           		public void actionPerformed(ActionEvent e){

           			// If sound is on, turns it off
           			// Otherwise, begins looping song
           			if (!(soundOn)){
           				music.loop();
           				soundOn = true;
           			}
           			else{
           				music.stop();
           				soundOn = false;
           			}
           		}
		});
		
		// Undo Button
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener(){ 
           		public void actionPerformed(ActionEvent e){
           			
           			// If there are moves to be undone, undoes one move
           			if (undoFroms.size() != 0){
               			AbstractPanel fromPanel = undoFroms.remove(0);
               			AbstractPanel toPanel = undoTos.remove(0);
               			int size = undoSizes.remove(0);
               			
               			// Moves the cards back one by one from the toPanel back to the fromPanel
           				for (int i = 0; i < size; i++){
               				fromPanel.getCell().add(toPanel.getCell().remove(toPanel.getCell().size() - size + i));
           				}
           				
           				// Takes back one click and resets the label
           				clicks -= 1;
           				clickLabel.setText("Total Clicks: " + clicks);
           				fromPanel.repaint();
           				toPanel.repaint();
           				
           			}
           			
           			// Pops up an error message if there are no more moves to be undone
           			else{           			
           				JOptionPane.showMessageDialog(AppView.this, 
        						"Cannot Undo", 
        						"Error", 
        						JOptionPane.INFORMATION_MESSAGE, 
        						new ImageIcon(getClass().getResource("DECK/1S.GIF")));
           			}
            	}
		});
		panel.add(newGameButton);
		panel.add(musicButton);
		panel.add(undoButton);

		return panel;
	}
		
	/**
	 * Creates a JPanel of all of the cells.
 	 * @return cellPanel - the panel of all of the cells
	 */
	private JPanel getAllCellPanels(){
		GridBagLayout layout = new GridBagLayout();
		JPanel cellPanel = new JPanel();
		cellPanel.setBackground(GREEN_COLOR);
		cellPanel.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = .8;
		constraints.insets = new Insets(0,20,0,20);
		JPanel singleCellPanels = getSingleCellPanels();
		layout.setConstraints(singleCellPanels, constraints);        
		cellPanel.add(singleCellPanels);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 4;
		constraints.insets = new Insets(0,20,0,20);
		JPanel tableauPanels = getTableauPanels();
		layout.setConstraints(tableauPanels, constraints);        
		cellPanel.add(tableauPanels);

		return cellPanel;
	}
	
	/**
	 * Creates a JPanel of the labels for free and home cells.
 	 * @return panel - the panel of labels
	 */
	private JPanel getLabelPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(GREEN_COLOR);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel freeCellLabel = new JLabel("Free Cells", JLabel.CENTER);
		JLabel homeCellLabel = new JLabel("Home Cells", JLabel.CENTER);
		freeCellLabel.setForeground(Color.white);
		homeCellLabel.setForeground(Color.white);
		
		
		panel.add(freeCellLabel);
		panel.add(homeCellLabel);
		
		return panel;
	}
	
	/**
	 * Creates a JPanel of all of the labels.
 	 * @return panel - the panel of labels
	 */
	private JPanel getAllLabelPanels(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		panel.setBackground(GREEN_COLOR);

		panel.add(getLabelPanel());
		panel.add(timerLabel);
		panel.add(clickLabel);
		
		return panel;
	}
	
	/**
	 * Creates a JPanel of the free and home cells.
 	 * @return panel - the panel of free and home cells
	 */
	private JPanel getSingleCellPanels(){
		JPanel panel = new JPanel();
		panel.setBackground(GREEN_COLOR);
		panel.setLayout(new GridLayout(1, 2, 50, 0));	
		
		panel.add(getFreePanels());
		panel.add(getHomePanels());
		
		return panel;
	}

	/**
	 * Creates a JPanel of the free cells.
 	 * @return panel - the panel of free cells
	 */
	private JPanel getFreePanels(){
		JPanel panel = new JPanel();
		panel.setBackground(GREEN_COLOR);
		panel.setLayout(new GridLayout(1, 4, 50, 0));
		for (int i = 0; i < freePanels.length; i++)
			panel.add(freePanels[i]);
		
		return panel;
	}

	/**
	 * Creates a JPanel of the home cells.
 	 * @return panel - the panel of home cells
	 */
	private JPanel getHomePanels(){
		JPanel panel = new JPanel();
		panel.setBackground(GREEN_COLOR);
		panel.setLayout(new GridLayout(1, 4, 50, 0));	
		for (int i = 0; i < homePanels.length; i++)
			panel.add(homePanels[i]);
		
		return panel;
	}

	/**
	 * Creates a JPanel of the tableaux.
 	 * @return panel - the panel of the tableaux
	 */
	private JPanel getTableauPanels(){
		JPanel panel = new JPanel();
		panel.setBackground(GREEN_COLOR);
		panel.setLayout(new GridLayout(1, 8, 50, 0));	
		for (int i = 0; i < tableauPanels.length; i++)
			panel.add(tableauPanels[i]);
		return panel;
	}

	/**
	 * This class responds to a mouse press in a panel.
	 * @author Ryan Grant and Michael Holt
	 */
	private class AppViewInformer implements ViewInformer{
		
		/**
	   	 * Makes a move and repaints the panels if the conditions warrant.
	  	 * @param panel - the panel being pressed
         */
		public void panelPressed(AbstractPanel panel){
			
			int tempSize = panel.getCell().size();
			if (fromPanel == null)
				fromPanel = panel;
			else if (fromPanel == panel)
				fromPanel = null;
			else
			{
				if (panel.getCell().transfer(fromPanel.getCell())){
					panel.repaint();
					fromPanel.repaint();
					
					// Update click counter
					clicks += 1;
					clickLabel.setText("Total Clicks: " + clicks);
					
					// Update undo list
					undoFroms.add(0, fromPanel);
					undoTos.add(0, panel);
					undoSizes.add(0, panel.getCell().size() - tempSize);
				}
				else{
					JOptionPane.showMessageDialog(AppView.this, 
							"Illegal Move", 
							"Nice Try", 
							JOptionPane.INFORMATION_MESSAGE, 
							new ImageIcon(getClass().getResource("DECK/1S.GIF")));
				}
				fromPanel = null;			
			}
			
			if (game.hasWinner()){
				// Plays music when you win
				AudioClip sound = Applet.newAudioClip(getClass().getResource("WINshort.wav"));
				sound.play();
				JOptionPane.showMessageDialog(AppView.this, 
						"You have won the game!", 
						"Game Over", 
						JOptionPane.INFORMATION_MESSAGE, 
						new ImageIcon(getClass().getResource("DECK/1S.GIF")));
			}
			else if (game.hasLoser()){
				JOptionPane.showMessageDialog(AppView.this, 
						"You have lost the game.", 
						"Game Over", 
						JOptionPane.INFORMATION_MESSAGE, 
						new ImageIcon(getClass().getResource("DECK/1S.GIF")));
			}
				
		}
	}
}
