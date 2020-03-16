/**
 * 
 */

/**
 * @author Code Gorillaz
 *
 */
import java.util.Random;


public class Card {
	
	private int cardNum;
	private String cardSuit;
	private String cardType;
	private String cardID;
	
	/**
 	 * Constructor for Card class, generates the type of card and appropriate number alongside the suit based on a random int from Random.
 	 */
	public Card() {
		Random r = new Random();
		int i  = r.nextInt(13);
		if(i == 0){
			cardType = "a";
			cardNum = 1;
		}
		else if((i >= 1) && (i <= 9)) {
			cardType = "n";
			cardNum = i + 1;
		}
		else if(i == 10) {
			cardType = "j";
			cardNum = 10;
		}
		else if(i == 11) {
			cardType = "q";
			cardNum = 10;
		}
		else if(i == 12) {
			cardType = "k";
			cardNum = 10;
		}
		
		i = r.nextInt(4);
		
		switch(i) {
			case 0:
				cardSuit = "h";
				break;
			case 1:
				cardSuit = "d";
				break;
			case 2: 
				cardSuit = "s";
				break;
			case 3:
				cardSuit = "c";
				break;
		}
		
		if(cardNum != 10) {
			cardID = cardSuit + cardType + "0" + cardNum;
		}
		else if(cardNum == 10) {
			this.cardID = cardSuit + cardType + cardNum;
		}
		else if(cardType == "a") {
			this.cardID = cardSuit + cardType + "01";
		}
		
	}
	

	public Card (String t, int n, String s) {
		this.cardType = t;
		this.cardNum = n;
		this.cardSuit = s;
		this.genCardID();
	}
	

	/**
	 * Getter method for cardID
	 * @return class variable cardID
	 */
	public String getCardID() {return this.cardID;}
	
	/**
	 * Getter method for cardType
	 * @return class variable cardType
	 */
	public String getCardType() {return this.cardType;}
	
	/**
	 * Getter method for cardSuit
	 * @return class variable cardSuit
	 */
	public String getCardSuit() {return this.cardSuit;}
	
	/**
	 * Getter method for cardNum
	 * @return class variable cardNum
	 */
	public int getCardNum() {return this.cardNum;}
	
	/**
	 * Setter method for cardType, also assigns values based on the type of card being set.
	 * @param c input card type. (expected inputs are "a", "n", "k", "q" or "j")
	 */
	public void setCardType(String c) {
		this.cardType = c;
		if(c == "a") {
			this.cardNum = 1;
		}
		else if(c == "k" ^ c == "q" ^ c == "j") {
			this.cardNum = 10;
		}
	}
	
	/**
	 * Setter method for cardSuit
	 * @param c input card suit. (expected inputs are "c", "s", "h" or "d")
	 */
	public void setCardSuit(String c) {
		this.cardSuit = c;
	}
	
	/**
	 * Setter method for cardNum
	 * @param i input value for card. (expected inputs are an int in the range 2-10)
	 */
	public void setCardNum(int i) {
		this.cardNum = i;
	}
	
	/**
	 * swaps the value for a card type "a" between 1 or 11.
	 */
	public void aceValue() {
		if(this.cardNum == 1) {
			this.cardNum = 11;
		}
		else if (this.cardNum == 11) {
			this.cardNum = 1;
		}
		else {
			System.out.println("Not an ace ya dingus!");
		}
	}
	
	/**
	 * regenerates the cardID for if the values have been changed.
	 */
	public void genCardID() {
		if(cardNum != 10) {
			//consider switching to StringBuilder
			this.cardID = cardSuit + cardType + "0" + cardNum;
		}
		else if(cardNum == 10) {
			this.cardID = cardSuit + cardType + cardNum;
		}
		else if(cardType == "a") {
			this.cardID = cardSuit + cardType + "01";
		}
	}
}
