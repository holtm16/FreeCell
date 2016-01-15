/**
 * This class represents the abstract class for the panels used in
 * the game FreeCell.
 * @author Ryan Grant and Michael Holt
 */
import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

abstract public class AbstractPanel extends JPanel{
	
	protected Cell cell;
	
	/**
	 * Constructor to display a given cell's top card's image.
	 * @param inputCell - the cell to display
	 * @param inputInformer - the panel informer to be passed to the panel
	 * listener
	 */
	public AbstractPanel(Cell inputCell, ViewInformer inputInformer){
		setBackground(AppView.GREEN_COLOR);
		this.cell = inputCell;
		MouseListener mouseListener = new PanelListener(inputInformer, this);
		this.addMouseListener(mouseListener);
		
	}

	/**
	 * Resets the panel's card and refreshes the panel.
	 * @param c the cell to be displayed.
	 */
	public void setCell(Cell c){
		cell = c;
		repaint();
	}
	
	/**
	 * Returns the cell from this panel.
	 * @return the cell from this panel
	 */
	public Cell getCell(){
		return cell;
	}

	/**
	 * This class represents the panel listener used to listen for mouse events.
	 * @author Ryan Grant and Michael Holt
	 */
	private class PanelListener extends MouseAdapter{
        private AudioClip sound2 = Applet.newAudioClip(getClass().getResource("Chime.wav"));

		
		private ViewInformer informer;
		
		private AbstractPanel panel;
		
		/**
		 * Constructor. 
		 * @param inputInformer the input panel informer
		 * @param inputPanel the input panel 
		 */
		public PanelListener(ViewInformer inputInformer, AbstractPanel inputPanel){
			super();			
			this.informer = inputInformer;
			this.panel = inputPanel;
		}
		
		/**
		 * Responds to a mouse press by calling panelPressed
		 * on the given panelListener's informer.
		 * @param e the mouse event 
		 */
		public void mousePressed(MouseEvent e){
			sound2.play();
			informer.panelPressed(panel);
		}
	}
	
}
