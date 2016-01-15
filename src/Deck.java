/**
 * @author Michael Holt and Ryan Grant
 * Name of File: Deck.java
 * Project 6
 * This class implements a deck of 52 playing cards, each
 * with a suit, rank, image, and face up status.
 */

import java.util.*;

public class Deck{

	private List<Card> deck;
	
 	/**
     * Constructor. Creates a new deck with 52 cards in sorted order.
     */
    public Deck(){
    	deck = new ArrayList<Card>();
    	initSuit(Suit.spade);
    	initSuit(Suit.heart);
    	initSuit(Suit.diamond);
    	initSuit(Suit.club);
    }
    
    /**
     * Adds all cards of the input suit to the deck.
     * @param suit - the card's suit
     */
    private void initSuit(Suit suit){
    	for (int i = 1; i <= 13; i++){
    			deck.add(new Card(suit, i));
    	}
    }
    
    /**
     * Tests the deck for emptiness.
     * @return true if the deck is empty
     */
    public boolean isEmpty(){
    	return (deck.size() <= 0);
    }
    
    /**
     * Removes and returns the card at the top of the deck.
     * @return the top card from the deck
     * @throws IllegalStateException if the deck is empty
     */
    public Card deal(){
    	if (!(this.isEmpty())){
    		Card c = deck.remove(deck.size()-1);
    		return c;
    	}
    	throw new IllegalStateException("the deck is empty");
    }
    
    /**
     * Shuffles the deck by arranging the deck's cards in random order.
     */
    public void shuffle(){
    	Collections.shuffle(deck);
    }
    
    /**
     * Returns the string representation of the deck's cards.
     * @overrides toString in class java.lang.Object
     * @return the string representation of the deck's cards
     */
    public String toString(){
    	String str = "";
    	for (Card c : deck) 
    		{
    			str = str + c + "\n";
    		} 
    	return str;	
    }
}
