/**
 * 
 */

/**
 * @author Code Gorillaz
 *
 */
public class Hand {
	private Card[] playerHand = new Card[11];
	private int playerTotal = 0;
	private int cardsRevealed = 0;
	
	public Hand(){
		for(int i = 0; i < playerHand.length; i++) {
			playerHand[i] = new Card();
		}
		
		cardsRevealed = 1;
		
		for(int i = 0; i < cardsRevealed; i++) {
			playerTotal += playerHand[i].getCardNum();
		}
	}
	
	public int getPlayerTotal() {
		this.playerTotal = 0;
		for(int i = 0; i < cardsRevealed; i++) {
			this.playerTotal += this.playerHand[i].getCardNum();
		}
		return this.playerTotal;
	}
	
	public int getCardsRevealted() {return this.cardsRevealed;}
	
	public Card[] getPlayerHand() {return this.playerHand;}
	
	public Card getIndividualCard(int i) {return this.playerHand[i];}
	
	public void setCardsRevealed(int i) {this.cardsRevealed = i;}
	
	public void setPlayerHand(Card[] ca) {
		this.playerHand = new Card[ca.length];
		for(int i = 0; i < playerHand.length; i++) {
			this.playerHand[i] = ca[i];
		}
	}
	
	public void setIndividualCard(Card c, int i) {
		this.playerHand[i] = c;
	}
	
	public void hit() {this.cardsRevealed++;}
	
}
