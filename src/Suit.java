/**
 * Represents the four suits in a deck of cards.
 * They are ordered, in decreasing rank, from
 * spade to heart to diamond to club.
 * @author lambertk
 *
 */
public class Suit implements Comparable<Suit>{

	/**
	 * Suit for all spades.
	 */
	static public final Suit spade   = new Suit(4, "spades");
	/**
	 * Suit for all hearts.
	 */
    static public final Suit heart   = new Suit(3, "hearts");   
	/**
	 * Suit for all diamonds.
	 */
    static public final Suit diamond = new Suit(2, "diamonds");   
	/**
	 * Suit for all clubs.
	 */
    static public final Suit club    = new Suit(1, "clubs");

    private int order;
    private String name;

    private Suit(int ord, String nm){
        name = nm;
        order = ord;
    }

    /**
     * Compares two suits using their rank ordering.
     * @return 0 if equal, less than 0 if less, greater than 0 if greater
     */
    public int compareTo(Suit other){
        return order - other.order;
    }

    /**
     *  Returns the string representation of the suit.
     *  @return the string representation of the suit.
     */
    public String toString(){
        return name;
    }
}