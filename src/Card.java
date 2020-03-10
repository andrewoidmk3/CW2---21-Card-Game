/**
 * 
 */

/**
 * @author Card Gorillaz
 *
 */
import java.util.Random;

public class Card {
	
	private int cardNum;
	private String cardSuit;
	private String cardType;
	private String cardID;
	
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
	
	public String getCardID() {return this.cardID;}
	
	public String getCardType() {return this.cardType;}
	
	public String getCardSuit() {return this.cardSuit;}
	
	public int getCardNum() {return this.cardNum;}
	
	public void setCardType(String c) {
		this.cardType = c;
		if(c == "a") {
			this.cardNum = 1;
		}
		else if(c == "k" ^ c == "q" ^ c == "j") {
			this.cardNum = 10;
		}
	}
	
	public void setCardSuit(String c) {
		this.cardSuit = c;
	}
	
	public void setCardNum(int i) {
		this.cardNum = i;
	}
	
	public void aceValue() {
		if((this.cardType == "a") && (this.cardNum == 1)) {
			this.cardNum = 11;
		}
		else if ((this.cardType == "a") && (this.cardNum == 11)) {
			this.cardNum = 1;
		}
		else {
			System.out.println("Not an ace ya dingus!");
		}
	}
	
	public void genCardID() {
		if(cardNum != 10) {
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
