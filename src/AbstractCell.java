/**
 * This class serves as the parent class, or superclass, 
 * of the HomeCell, FreeCell and Tableau classes.
 * @author Ryan Grant and Michael Holt
 */
 
import java.awt.Color;
import java.util.*;

abstract public class AbstractCell extends AbstractCollection<Card>
				   implements Cell{
								   
	protected List<Card> cell;
	
	/**
	 * Constructor for a cell.
	 */
	public AbstractCell(){
		cell = new ArrayList<Card>();
	}

	/**
	 * Adds the input cell to the current cell if possible.
	 * The source is otherCell and the destination is the cell calling the method.
	 * @param otherCell - a cell to be added to the cell calling the method
	 * @return true if the transfer was successful and false otherwise
	 */
	public boolean transfer(Cell otherCell){
		if (this.canTransfer(otherCell))
			return this.add(otherCell.remove());
		return false;
	}
	
	/**
	 * Returns true if the input cell can be transferred to the current cell.
	 * @return true if the input cell can be transferred to the current cell 
	 * and false otherwise
	 */
	abstract public boolean canTransfer(Cell otherCell);


	/**
	 * Looks at the last card of the cell without removing it.
	 * @return the last card of the cell
	 */
	public Card peekLast(){
		return cell.get(cell.size()-1);
	}

	/**
	 * Looks at the first card of the cell without removing it.
	 * @return the first card of the cell
	 */
	public Card peekFirst(){
		return cell.get(0);
	}

	/**
	 * Removes and returns the last card of the cell.
	 * @return the last card of the cell
	 */
	public Card remove(){
		return cell.remove(cell.size()-1);
	}

	/**
	 * Removes and returns the card at the specified index of the cell.
	 * @return the the card at the specified index of the cell
	 */
	public Card remove(int index){
		return cell.remove(index);
	}

	/**
	 * Adds a given card to the end of the cell.
	 * @param card - the card to be added to the cell
	 * @return true if the add was successful and false otherwise
	 */
	public boolean add(Card card){
		return cell.add(card);
	}


	/**
	 * Adds a given card to the specified index of the cell.
	 * @param card - the card to be added to the cell
	 * @param index - the index of the cell at which the card should be added
	 */
	public void add(Card card, int index){
		cell.add(index, card);
	}

	/**
	 * Tests if the cell is empty.
	 * @return true if the cell is empty and false otherwise
	 */
	public boolean isEmpty(){
		return cell.isEmpty();
	}

	/**
	 * Returns the size of the cell.
	 * @return the size of the cell
	 */
	public int size(){
		return cell.size();
	}

	/**
	 * Removes all cards from the cell.
	 */
	public void clear(){
		cell.clear();
	}

	/**
	 * Returns the string representation of the cell.
	 * @return the string representation of the cell
	 */
	public String toString(){
		String str = "";
		if (this.size() > 0){
			for (Card element : this){
				str = str + element.toString() + "\n";
			}  
		}
		return str;	
	}

	/**
	 * This class implements a cell iterator.
	 */
	private class CellIterator<Card> implements Iterator<Card>{
	
		private Iterator<Card> iter;
	
	   	/**
		 * Constructor. Creates a cell iterator from the input iterator.
		 * @param iter - the iterator of a list of cards
		 */
		private CellIterator(Iterator<Card> iter){
			this.iter = iter;
		}
	
		/**
		 * Returns true if the cell has a next card and false otherwise.
		 * @return true if the cell has a next card and false otherwise
		 */
		public boolean hasNext(){
			return iter.hasNext();
		}
	
		/**
		 * Returns the next card of the cell.
		 * @return the next card of the cell
		 */
		public Card next(){
			return iter.next();
		}
	
		/**
		 * Removes the list iterator's functionality for removing an item.
		 * @throws UnsupportedOperationException
		 */
		public void remove(){
			throw new UnsupportedOperationException(
					"remove not supported by Cell");
		}
	}

	/**
	 * Returns the iterator of the cell.
	 * @return the iterator of the cell
	 */
	public Iterator<Card> iterator(){
		return new CellIterator<Card>(cell.iterator());
	}
					   
}
