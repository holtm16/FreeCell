/**
 * This java file serves as the interface used by AbstractCell.java.
 * @author Ryan Grant and Michael Holt
 */
 
import java.util.Collection;

public interface Cell extends Collection<Card>{

	/**
	 * Looks at the last card of the cell without removing it.
	 * @return the last card of the cell
	 */
	public Card peekLast();

	/**
	 * Looks at the first card of the cell without removing it.
	 * @return the first card of the cell
	 */
	public Card peekFirst();

	/**
	 * Removes and returns the last card of the cell.
	 * @return the last card of the cell
	 */
	public Card remove();

	/**
	 * Removes and returns the card at the specified index of the cell.
	 * @return the the card at the specified index of the cell
	 */
	public Card remove(int index);

	/**
	 * Adds a given card to the end of the cell.
	 * @param card - the card to be added to the cell
	 * @return true if the add was successful and false otherwise
	 */
	public boolean add(Card card);

	/**
	 * Adds a given card to the specified index of the cell.
	 * @param card - the card to be added to the cell
	 * @param index - the index of the cell at which the card should be added
	 */
	public void add(Card card, int index);
	
	/**
	 * Tests if the cell is empty.
	 * @return true if the cell is empty and false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the size of the cell.
	 * @return the size of the cell
	 */
	public int size();
	
	/**
	 * Removes all cards from the cell.
	 */
	public void clear();
	
	/**
	 * Adds the input cell to the current cell if possible.
	 * The source is otherCell and the destination is the cell calling the method.
	 * @param otherCell - a cell to be added to the cell calling the method
	 * @return true if the transfer was successful and false otherwise
	 */
	public boolean transfer(Cell otherCell);

	/**
	 * Returns true if the input cell can be transferred to the current cell.
	 * @return true if the input cell can be transferred to the current cell 
	 * and false otherwise
	 */
	public boolean canTransfer(Cell otherCell);
   
}
