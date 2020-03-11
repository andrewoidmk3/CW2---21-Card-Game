import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Code Gorillaz
 *
 */

public class GameWindow implements ActionListener{
	//class variable declaration
	protected Hand plh = new Hand();
	protected Hand dlh = new Hand();
	private JFrame gw = new JFrame();
	private JLabel total = new JLabel();
	private JButton hit = new JButton("Hit");
	private JButton stay = new JButton("Stay");
	private JButton fold = new JButton("Fold");
	private JButton reroll = new JButton("Try again.");
	private JLabel[] plc = new JLabel[11];
	private boolean isNew = true;
	private boolean hasAce = false;
	private boolean aceUsed = false;
	private int aceI = 0;
	/**
	 * Constructor for game window
	 */
	public GameWindow() {

		JLabel tl = new JLabel("Total:");
		//assigning bounds for fixed position items
		hit.setBounds(325, 500, 100, 40);
		stay.setBounds(420, 500, 100, 40);
		fold.setBounds(515, 500, 100, 40);
		reroll.setBounds(420, 10, 100, 40);
		tl.setBounds(450, 250, 50 , 50);
		total.setBounds(490, 250, 50, 50);
		//adding action listeners to the buttons
		hit.addActionListener(this);
		stay.addActionListener(this);
		fold.addActionListener(this);
		reroll.addActionListener(this);
		//calls the generate cards function, see below
		this.genCards();
		//adding buttons and information readout
		gw.add(hit);
		gw.add(stay);
		gw.add(fold);
		gw.add(reroll);
		gw.add(tl);
		gw.add(total);
		//defining the actual size of the box
		gw.setSize(1000,600);
		gw.setLayout(null);
		gw.setVisible(true);
		//initialises the game state, ready to play.
		this.showCards();
		this.updateTotal();
		this.checkAce();
	}
	/**
	 * wrapper for updating total listed on game window
	 * @param t total to be updated
	 */
	private void updateTotal() {
		this.total.setText(Integer.toString(plh.getPlayerTotal()));
	}
	/**
	 * implements actionPerformed for the event listener, outcome determined on what button is clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hit) {
			plh.hit();
			//this logic isn't quite working as it's supposed to.
			if(this.aceUsed == false) {
				this.checkAce();
			}
			this.showCards();
			this.updateTotal();
			if(plh.getPlayerTotal() > 21) {
				if(this.hasAce == true) {
					//it's SUPPOSED to check if there's an ace that's being used as an 11
					//for some reason aces revealed after first 2 cards default to 1 always
					plh.getIndividualCard(aceI).aceValue();
					this.hasAce = false;
					this.aceUsed = true;
					this.updateTotal();
				}
				else if(this.hasAce == false) {
					JOptionPane.showMessageDialog(this.gw, "you lose :-(");
				}
			}

		}
		
		else if(e.getSource() == stay) {
			if(plh.getPlayerTotal() == 21) {
				JOptionPane.showMessageDialog(this.gw, "You did it! Bang-on");
			}
			else {
				//lets the player know how far off 21 they were, in the future will let player know if they won or lost to dealer
				JOptionPane.showMessageDialog(this.gw, "You did it! though you were " + (21 - plh.getPlayerTotal()) + " off.");
			}
			
		}
		
		//fold and reroll are near identical, reroll is basically exclusively for speedy testing, and redundant once everything is functional
		else if(e.getSource() == fold) {
			JOptionPane.showMessageDialog(this.gw, "You folded, you lose.");
			this.plh = new Hand();
			this.hasAce = false;
			this.aceUsed = false;
			this.checkAce();
			this.genCards();
			this.showCards();
			this.updateTotal();
		}
		else if(e.getSource() == reroll) {
			this.plh = new Hand();
			this.hasAce = false;
			this.aceUsed = false;
			this.checkAce();
			this.genCards();
			this.showCards();
			this.updateTotal();
		}
	}
	/**
	 * wrapper for displaying the cards on the screen
	 */
	public void genCards() {
		int wdth = 103;
		for(int i = 0; i < plc.length; i++) {
			//for if the the game is freshly opened, considering there won't be anything displayed yet.
			if(isNew == false) {
				gw.remove(plc[i]);
			}
			BufferedImage imgi;
			try {
				//this was a complete stroke of genuis if i do say so myself, the whole reason i wanted card ids to begin with
				imgi = ImageIO.read(new File("src\\cards\\" + plh.getIndividualCard(i).getCardID() + ".png"));
			} catch(IOException ex) {
				System.out.println("src\\fnf.png");
				imgi = null;
			}
			
			plc[i] = new JLabel(new ImageIcon(imgi));
			plc[i].setBounds((wdth + 157), 320, 103, 157);
			plc[i].setVisible(false);
			gw.add(plc[i]);
			wdth += 103;
		}
		this.isNew = false;
	}
	//the way additional cards are "added" (more revealed lol)
	public void showCards() {
		for(int i = 0; i <= plh.getCardsRevealed(); i++) {
			plc[i].setVisible(true);
		}
	}
	//something is off with this logic. Fine for if the ace is part of the first 2 cards shown, it shits the bed if it's the 3rd card onwards
	//not a clue why,  need to get this working fully before i can really call this completed
	/**
	 * checks the currently revealed cards for aces, marks the location and fact an ace exists if there is one. For use in 11 or 1 logic
	 */
	public void checkAce() {
		if(hasAce == false) {
			for(int i = 0; i <= this.plh.getCardsRevealed(); i++) {
				if(plh.getIndividualCard(i).getCardType() == "a") {
					this.plh.getIndividualCard(i).aceValue();
					this.hasAce = true;
					this.aceI = i;
					break;
				}
			}
		}
	}
}
