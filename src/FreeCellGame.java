/**
 * This class represents the model for a game of FreeCell.
 * @author Ryan Grant and Michael Holt
 */

 public class FreeCellGame{
 	
 	private Deck deck;
	private FreeCell[] freeCells;
	private HomeCell[] homeCells;
	private Tableau[] tableauCells;
  	
	/**
	 * Constructor. Sets up the game of FreeCell by dealing cards to each tableau.
	 */
  	public FreeCellGame(){
		deck = new Deck();
		freeCells = new FreeCell[4];
		homeCells = new HomeCell[4];
		tableauCells = new Tableau[8];
		
		for (int i = 0; i < 4; i++)
			freeCells[i] = new FreeCell();
		for (int i = 0; i < 4; i++)
			homeCells[i] = new HomeCell();
		for (int i = 0; i < 8; i++)
			tableauCells[i] = new Tableau();

		this.resetGame();
  	}
        
	/**
	 * Resets the game by clearing all cells and re-dealing the cards of a new
	 * deck to the tableaux.
	 */
	public void resetGame(){
		deck = new Deck();
		
		for (Cell c : freeCells)
			c.clear();
		for (Cell c : homeCells)
			c.clear();
		for (Cell c : tableauCells)
			c.clear();

  		deck.shuffle();
		
		this.dealCardsToTableaux();
	}

	/**
	 * Deals the cards out to the tableaux.
	 */
	public void dealCardsToTableaux(){
		int i = 0;
		while (!(deck.isEmpty())){
			Card card = deck.deal();
			card.turn();
			tableauCells[i].add(card);
			i++;

			// This is to start adding to the first tableau again
			if (i == 8)
				i = 0;
		}
	}
  	
  	/**
  	 * Determines if the FreeCell game has been lost.
  	 * @return true if the game has been lost and false otherwise
  	 */
  	public boolean hasLoser(){
		for (FreeCell freeCell : freeCells){
			if (freeCell.isEmpty()){
				return false;
			}
			for (HomeCell homeCell : homeCells){
				if (homeCell.canTransfer(freeCell)){
					return false;
				}
			}
			for (Tableau tableau : tableauCells){
				if (tableau.canTransfer(freeCell)){
					return false;
				}
			}
		}
		
		for (int i = 0; i < tableauCells.length; i++){
			for (HomeCell homeCell : homeCells){
				if (homeCell.canTransfer(tableauCells[i])){
					return false;
				}
			}

			for (int j = i; j < tableauCells.length; j++){
				if (((tableauCells[i].canTransfer(tableauCells[j])) ||
					(tableauCells[j].canTransfer(tableauCells[i])))){
					return false;
				}
  			}
		}
  		return true;
	}
  	
  	
	/**
  	 * Determines if the FreeCell game has been won.
  	 * @return true if the game has been won and false otherwise
  	 */
  	public boolean hasWinner(){
		for (Tableau tableau : tableauCells){
			if (!(tableau.isInOrder()))
				return false;
		}
  		return true;
	}
	
	/**
	 * Returns the Tableau at the given position.
	 * @param index - the index of the Tableau being returned
	 * @return the Tableau at the given position
	 */
	public Tableau getTableau(int index){
		return tableauCells[index];
	}

	/**
	 * Returns the tableau at the given position.
	 * @param index - the index of the Free Cell being returned
	 * @return the Free Cell at the given position
	 */
  	public FreeCell getFreeCell(int index){
		return freeCells[index];
	}
	
	/**
	 * Returns the Home Cell at the given position.
	 * @param index - the index of the Home Cell being returned
	 * @return the Home Cell at the given position
	 */
	public HomeCell getHomeCell(int index){
		return homeCells[index];
	}
 }
