/**
 * This class implements a free cell for the card game FreeCell.
 * @author Ryan Grant and Michael Holt
 */
 
 import java.util.*;
 
 public class FreeCell extends AbstractCell{
	 	
	/**
	 * Constructor. Creates a new free cell.
	 */
	public FreeCell(){
		super();
	}	

	/**
	 * Returns true if the input cell can be transferred to the current free cell.
	 * @return true if the input cell can be transferred to the current free cell 
	 * and false otherwise
	 */
	public boolean canTransfer(Cell otherCell){
		return (this.isEmpty() && !(otherCell.isEmpty()) && !(otherCell instanceof HomeCell));
	}
}
