/**
 * This class implements a home cell for the card game FreeCell.
 * @author Ryan Grant and Michael Holt
 */
 
 import java.util.*;
 
 public class HomeCell extends AbstractCell{
 	
	private Suit suit;


	/**
	 * Constructor. Creates a new home cell.
	 */
	public HomeCell(){
		super();
	}

	/**
	 * Returns true if the input cell can be transferred to the current home cell.
	 * Sets the suit of the home cell if the home cell is empty and otherCell
	 * can be transferred.
	 * @return true if the input cell can be transferred to the current home cell 
	 * and false otherwise
	 */
	public boolean canTransfer(Cell otherCell){		
		if (!(otherCell instanceof HomeCell) && !(otherCell.isEmpty()) && !(this.isFull())){
			Card card = otherCell.peekLast();
			if (this.isEmpty() && card.getRank() == 1){
				this.suit = card.getSuit(); 
				return true;
			}
			return (card.getSuit() == this.suit && card.getRank() == this.size() + 1);
		}
		return false;
	}

	/**
	 * Tests if the home cell is full.
	 * @return true if the home cell is full and false otherwise
	 */
	public boolean isFull(){
		return (this.size() == 13);
	}
}
