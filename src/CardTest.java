/**
 * 
 */

/**
 * @author Code Gorillaz
 *
 */
public class CardTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Card c1 = new Card();
		Card c2 = new Card();
		Card c3 = new Card();
		Card[] ca = new Card[4];
		
		for(int i = 0; i < ca.length; i++) {
			ca[i] = new Card();
		}
		
		//System.out.println(c1.getCardID());
		//System.out.println(c2.getCardID());
		//System.out.println(c3.getCardID());
		
		for(int i = 0; i < ca.length; i++) {
			System.out.println(ca[i].getCardID());
		}
		
		c3.setCardType("k");
		c2.setCardType("j");
		c1.setCardType("a");
		
		c1.genCardID();
		c2.genCardID();
		c3.genCardID();

		//System.out.println(c1.getCardID());
		//System.out.println(c2.getCardID());
		//System.out.println(c3.getCardID());
	}

}
