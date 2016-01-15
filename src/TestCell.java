/**
 * This java file tests our HomeCell, FreeCell, and Tableaux classes.
 * @author Ryan Grant and Michael Holt
 */


public class TestCell{

	public static void main(String[] args){
		Cell free1 = new FreeCell();
		Cell free2 = new FreeCell();
		Cell home1 = new HomeCell();
		Cell home2 = new HomeCell();
		Cell home3 = new HomeCell();
		Cell tableau1 = new Tableau();
		Cell tableau2 = new Tableau();
		Cell tableau3 = new Tableau();

		Deck d = new Deck();
	
		// Gets rid of some cards to test on an Ace
		System.out.println("\n\n");
		for (int i = 1; i <= 12; i++){
			d.deal();
		}
		
		// Creating a standard home cell
		home3.add(d.deal());
		System.out.println("Expect 'Ace of clubs': " + home3);

		// Creating a standard free cell
		free1.add(d.deal());
		System.out.println("Expect 'King of diamonds': " + free1);

		// Testing remove() with a free cell
		free1.remove();
		System.out.println("Expect ' ': " + free1);
		free1.add(d.deal());
		System.out.println("Expect 'Queen of diamonds': " + free1);
		
		// Filling tableau1
		for (int i = 1; i <= 10; i++)
			tableau1.add(d.deal());
		
		// Standard tableau
		System.out.println("Expect 'Jack of diamonds\n10 of diamonds\n9 of diamonds\n8 of diamonds\n" +
		"7 of diamonds\n6 of diamonds\n5 of diamonds\n4 of diamonds\n3 of diamonds\n2 of diamonds':\n\n" + tableau1);
		
		// Transferring from home cell to tableau1
		tableau1.transfer(home3);
		System.out.println("Expect 'Jack of diamonds\n10 of diamonds\n9 of diamonds\n8 of diamonds\n" +
		"7 of diamonds\n6 of diamonds\n5 of diamonds\n4 of diamonds\n3 of diamonds\n2 of diamonds':\n\n" + tableau1);

		// Transferring from home cell to free cell
		free2.transfer(home3);
		System.out.println("Expect '': " + free2);
		
		// Transferring from home cell to home cell
		home2.transfer(home3);
		System.out.println("Expect '': " + home2);
		
		// Tableau with an Ace of diamonds at the end
		tableau1.add(d.deal());

		// Transferring from tableau1 to home cell
		home1.transfer(tableau1);
		System.out.println("Expect 'Ace of diamonds': " + home1);

		// Transferring from tableau1 to free cell
		free1.remove();
		free1.transfer(tableau1);
		System.out.println("Expect '2 of diamonds': " + free1);

		// Transferring from free cell to home cell
		home1.transfer(free1);
		System.out.println("Expect 'Ace of diamonds\n2 of diamonds':\n\n" + home1);
		System.out.println("Expect '': " + free1);

		// Creating a second tableau with all spades except the Ace
		for (int i = 1; i <= 13; i++)
			d.deal();
		for (int i = 1; i <= 10; i++)
			tableau2.add(d.deal());

		// Transferring from tableau1 to tableau2
		tableau2.transfer(tableau1);
		System.out.println("Expect 'King of spades\nQueen of spades\nJack of spades\n10 of spades\n9 of spades\n8 of spades\n" +
		"7 of spades\n6 of spades\n5 of spades\n4 of spades\n3 of diamonds':\n\n" + tableau2);
		tableau2.transfer(tableau1); 

		// No change to tableau2 expected
		System.out.println("Expect 'King of spades\nQueen of spades\nJack of spades\n10 of spades\n9 of spades\n8 of spades\n" +
		"7 of spades\n6 of spades\n5 of spades\n4 of spades\n3 of diamonds':\n\n" + tableau2);

		// Putting the Ace of hearts in a free cell
		free1.add(new Card(Suit.heart, 1));

		// Transferring from free cell to free cell
		free2.transfer(free1);
		System.out.println("Expect 'Ace of hearts': " + free2);
		System.out.println("Expect '': " + free1);
		
		// Putting the two of spades into a free cell
		d.deal();
		free1.add(d.deal());

		// Transferring from free cell to tableau1
		tableau2.transfer(free1);
		System.out.println("Expect 'King of spades\nQueen of spades\nJack of spades\n10 of spades\n9 of spades\n8 of spades\n" +
		"7 of spades\n6 of spades\n5 of spades\n4 of spades\n3 of diamonds\n2 of spades':\n\n" + tableau2);

		// Transferring from free cell to empty tableau
		tableau3.transfer(free2);
		System.out.println("Expect 'Ace of hearts': " + tableau3);
		home2.transfer(tableau3);

		// Transferring from home cell to empty tableau
		tableau3.transfer(home2);
		System.out.println("Expect '': " + tableau3);

		// Transferring from tableau2 to empty tableau
		tableau3.transfer(tableau2);
		System.out.println("Expect '4 of spades\n3 of diamonds\n2 of spades':\n\n" + tableau3);
	}	
}
