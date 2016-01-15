import javax.swing.*;
import java.awt.*;

/**
 * Represents the GUI component for painting an image of a cell of 
 * playing cards.
 * @author Ryan Grant and Michael Holt
 *
 */

public class TableauPanel extends AbstractPanel{

	/**
	 * Constructor to display given cards' images in an overlapped fashion.
	 * @param inputCell - the cell to display
	 * @param inputInformer - the panel informer
	 */
	public TableauPanel(Cell inputCell, ViewInformer inputInformer){
		super(inputCell, inputInformer);
	}

	/**
	 * Paints all of the card's face images in a cell if any are present, otherwise, paints an empty frame.
	 * @param g - the graphic to be painted
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Icon image;
		if (cell.isEmpty()){
			image = Card.getBack();
			g.setColor(Color.white);
			int x = (getWidth() - image.getIconWidth()) / 2;
			int y = (getHeight() - image.getIconHeight()) / 20;
			g.drawRect(x, y, image.getIconWidth(), image.getIconHeight());
		}
		else{
			int increment = 0;
			for (Card card : cell){
				image = card.getImage();
				int x = (getWidth() - image.getIconWidth()) / 2;
				int y = (getHeight() - image.getIconHeight()) / 20;
				image.paintIcon(this, g, x, y + increment);
				//We want cards to overlap by an increment
				increment += 30;
			}
		}
	}
}



