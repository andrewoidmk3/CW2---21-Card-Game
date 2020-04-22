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
	private boolean hasAce = false;
	private boolean aceUsed = false;
	private int aceI = 0;
	
	public Hand(){
		this.hasAce = false;
		this.aceUsed = false;
		this.aceI = 0;
		for(int i = 0; i < playerHand.length; i++) {
			playerHand[i] = new Card();
		}
		
		cardsRevealed = 1;
		
		checkAce();
		
		for(int i = 0; i < cardsRevealed; i++) {
			playerTotal += playerHand[i].getCardNum();
		}
	}
	
	public Hand(String t) {
		if(t == "at") {
			playerHand[0] = new Card("n", 2, "h");
			playerHand[1] = new Card("n", 2, "h");
			playerHand[2] = new Card("a", 1, "s");
			playerHand[3] = new Card("n", 7, "h");
			for(int i = 4; i < playerHand.length; i++) {
				playerHand[i] = new Card();
			}
			cardsRevealed = 1;
			
			for(int i = 0; i < cardsRevealed; i++) {
				playerTotal += playerHand[i].getCardNum();
			}
		}
		else {
			for(int i = 0; i < playerHand.length; i++) {
				playerHand[i] = new Card();
			}
			
			cardsRevealed = 1;
			
			for(int i = 0; i < cardsRevealed; i++) {
				playerTotal += playerHand[i].getCardNum();
			}
		}
	}
	
	public void checkAce() {
		if(hasAce == false) {
			for(int i = 0; i <= cardsRevealed; i++) {
				if(playerHand[i].getCardType() == "a") {
					this.playerHand[i].aceValue();
					this.hasAce = true;
					this.aceI = i;
					break;
				}
			}
		}
	}
	
	public int getPlayerTotal() {
		this.playerTotal = 0;
		for(int i = 0; i <= cardsRevealed; i++) {
			this.playerTotal += this.playerHand[i].getCardNum();
		}
		return this.playerTotal;
	}
	
	public int getCardsRevealed() {return this.cardsRevealed;}
	
	public Card[] getPlayerHand() {return this.playerHand;}
	
	public Card getIndividualCard(int i) {return this.playerHand[i];}
	
	public boolean getHasAce() {return hasAce;}
	
	public int getAceI() {return aceI;}
	
	public boolean getAceUsed() {return aceUsed;}
	
	public void setHasAce() {
		if(hasAce == true) {
			this.hasAce = false;
		}
		else {
			this.hasAce = true;
		}
	}
	
	public void setAceUsed() {this.aceUsed = true;}
	
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
