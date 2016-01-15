/**
 * This class is a main method template for our GUI-based game of FreeCell.
 * @author Ryan Grant and Michael Holt
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FreeCellApp{
	

    public static void main(String[] args){
        final JFrame view = new AppView();
        view.setTitle("FreeCell");
        
        // CUSTOM CURSOR
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = new ImageIcon(Card.class.getResource("joker.gif")).getImage();
        
        // Sets the click point to the upper left of the image
        Point cursorHotSpot = new Point(0,0);
        Cursor customCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, "Cursor");
        view.setCursor(customCursor);      
        
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(1000, 800);
        view.setMinimumSize(new Dimension(1000, 800));
        view.setVisible(true);
        
    }
}
