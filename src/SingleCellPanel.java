import javax.swing.*;
import java.awt.*;
/**
 * Represents the GUI component for painting an image of a playing card.
 * @author lambertk
 *
 */
public class SingleCellPanel extends AbstractPanel{

	/**
	 * Constructor to display a given cell's top card's image.
	 * @param inputCell - the cell to display
	 * @param inputInformer - the panel informer
	 */
	public SingleCellPanel(Cell inputCell, ViewInformer inputInformer){
		super(inputCell, inputInformer);
		
	}

	/**
	 * Paints the cell's top card's face image if a card is present, otherwise, paints an empty frame.
	 * @param g - the graphic to be painted
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Icon image;
		if (cell.isEmpty()){
			image = Card.getBack();
			g.setColor(Color.white);
			int x = (getWidth() - image.getIconWidth()) / 2;
			int y = (getHeight() - image.getIconHeight()) / 2;
			g.drawRect(x, y, image.getIconWidth(), image.getIconHeight());
		}
		else{
			image = cell.peekLast().getImage();
			int x = (getWidth() - image.getIconWidth()) / 2;
			int y = (getHeight() - image.getIconHeight()) / 2;
			image.paintIcon(this, g, x, y);
		}
	}

}
