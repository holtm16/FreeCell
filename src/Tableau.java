/**
 * This class implements a tableau for the card game FreeCell.
 * @author Ryan Grant and Michael Holt
 */
 
 import java.util.*;
 
 public class Tableau extends AbstractCell{
 	
	private Tableau transferCellOfCards;

	/**
	 * Constructor. Creates a new tableau.
	 */
	public Tableau(){
		super();
	}

	/**
	 * Adds the input cell to the current cell if possible.
	 * The source is otherCell and the destination is the tableau calling the method.
	 * @param otherCell - a cell to be added to the tableau calling the method
	 * @return true if the transfer was successful and false otherwise
	 * @overrides transfer in class AbstractCell
	 */
	public boolean transfer(Cell otherCell){
		if (this.canTransfer(otherCell)){
			this.addAll(transferCellOfCards);
	
			for (int i = 0; i < transferCellOfCards.size(); i++)
				otherCell.remove();
		
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the input cell can be transferred to the current tableau, by 
	 * checking if the color, rank, and size of the input cell correspond to the 
	 * bottom card of the tableau calling the method.
	 * @param otherCell - a cell to be added to the tableau calling the method
	 * @return true if the input cell can be transferred to the current tableau 
	 * and false otherwise
	 */
	public boolean canTransfer(Cell otherCell){
		if (otherCell instanceof HomeCell)
			return false;		
			
		transferCellOfCards = new Tableau();
		for (Card card : otherCell){
			transferCellOfCards.add(card);
		}
	
		// Gets rid of cards that aren't allowed to be transferred
		while (!(transferCellOfCards.isInOrder())){
			transferCellOfCards.remove(0);
		}

		// Gets rid of cards that don't correspond to the last card of the destination tableau
		while (!(transferCellOfCards.isEmpty()) && !(this.canAdd(transferCellOfCards.peekFirst()))){
			transferCellOfCards.remove(0);
		}
		return (!(transferCellOfCards.isEmpty()));
	}

	/**
	 * Returns true if the input card can be transferred to the current tableau, by 
	 * checking if the color and rank of the input card correspond to the 
	 * last card of the tableau calling the method.
	 * @param otherCard - a card to be added to the tableau calling the method
	 * @return true if the input card can be transferred to the current tableau 
	 * and false otherwise
	 */
	private boolean canAdd(Card otherCard){
		if (this.isEmpty())
			return true;
		return (this.peekLast().isOppositeColor(otherCard) && this.peekLast().isCorrectRank(otherCard));
	}

	/**
	 * Tests if the tableau is in order.
	 * @return true if the tableau is in order and false otherwise
	 */
	public boolean isInOrder(){
		for (int i = 0; i < this.size() - 1; i++){
			Card card1 = this.cell.get(i);
			Card card2 = this.cell.get(i+1);
			if (!(card1.isCorrectRank(card2) && card1.isOppositeColor(card2))){
				return false;
			}		 
		}
		return true;
	}
}
