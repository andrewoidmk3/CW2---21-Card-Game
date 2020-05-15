/**
 * @author Code Gorillaz
 *
 */

public class DealerHand extends Hand{
	/**
	 *Constructor for DealerHand class, uses attributes and more from Hand class but changes the way how Aces work
	 */
	  public DealerHand(){
		  for(int i = 0; i < playerHand.length; i++) {
				playerHand[i] = new Card();
			}
			cardsRevealed = 1;
	      getPlayerTotal();
	            for(int i = 0; i <= cardsRevealed; i++) {
	                if(playerHand[i].getCardType() == "a") {
	                      if((playerTotal + 10) > 16){
	                        this.playerHand[i].aceValue();
	                        break;
	                      }
	                }
	      getPlayerTotal();
	            }
	            this.dealerAi();
	  }
	  //method for the dealer AI
		public void dealerAi() {
			do {
				this.hit();
				this.getPlayerTotal();
			}
			while (this.getPlayerTotal() <=16);
		}
	    
}

