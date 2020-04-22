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
	private Hand plh = new Hand();
	private Hand dlh = new Hand();
	private JFrame gw = new JFrame();
	private JLabel total = new JLabel();
	private JButton hit = new JButton("Hit");
	private JButton stay = new JButton("Stay");
	private JButton fold = new JButton("Fold");
	private JButton reroll = new JButton("Try again.");
	private JLabel[] plc = new JLabel[11];
	private boolean isNew = true;
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
		genCards();
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
		showCards();
		updateTotal();
	}
	/**
	 * wrapper for updating total listed on game window
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
			//found the bug, this if statement was missing a =
			if(plh.getAceUsed() == false) {
				plh.checkAce();
			}
			showCards();
			updateTotal();
			if(plh.getPlayerTotal() > 21) {
				if(plh.getHasAce() == true) {
					plh.getIndividualCard(plh.getAceI()).aceValue();
					plh.setHasAce();
					plh.setAceUsed();
					updateTotal();
				}
				else if(plh.getHasAce() == false) {
					updateTotal();
					JOptionPane.showMessageDialog(this.gw, "Bust! you lost by " + (plh.getPlayerTotal() - 21) + ".");
					resetGameState();
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
			resetGameState();
		}
		
		//fold and reroll are near identical, reroll is basically exclusively for speedy testing, and redundant once everything is functional
		else if(e.getSource() == fold) {
			JOptionPane.showMessageDialog(this.gw, "You folded, you lose.");
			resetGameState();
		}
		else if(e.getSource() == reroll) {
			resetGameState();
		}
	}
	/**
	 * wrapper for adding cards to be displayed
	 */
	public void genCards() {
		int wdth = 103;
		for(int i = 0; i < plc.length; i++) {
			//for if the the game is freshly opened, considering there won't be anything displayed yet.
			if(isNew == false) {
				gw.remove(plc[i]);
			}
			BufferedImage imgi;
			//uses the generated card id to pull from a folder of labeled card images
			try {
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
	
	/**
	 * wrapper for actually displaying the currently revealed cards on the screen
	 */
	public void showCards() {
		for(int i = 0; i <= plh.getCardsRevealed(); i++) {
			plc[i].setVisible(true);
		}
	}

	
	/**
	 * Wrapper for resetting the game state, saves a lot of redundant code.
	 */
	public void resetGameState() {
		plh = new Hand();
		genCards();
		showCards();
		updateTotal();
	}
}
