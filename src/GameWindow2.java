import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



/**
 * @author Code Gorillaz
 *
 */

public class GameWindow2 implements ActionListener{
	//class variable declaration
	protected Hand plh = new Hand();
	protected Hand dlh = new DealerHand();
	private JFrame gw = new JFrame();
	
	JPanel container = new JPanel();
	
	JPanel dealpane = new JPanel(new GridBagLayout());
	JPanel cardpane = new JPanel();
	JPanel buttpane = new JPanel(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();
	
	private JLabel total = new JLabel();
	private JLabel[] plc = new JLabel[11];
	private JLabel[] dlc = new JLabel[11];

	
	private JButton hit = new JButton("Hit");
	private JButton stay = new JButton("Stay");
	private JButton fold = new JButton("Fold");
	private JButton reroll = new JButton("Try again");

	private boolean isNew = true;
	private boolean hasAce = false;
	private boolean aceUsed = false;
	private int aceI = 0;
	/**
	 * Constructor for game window
	 */
	
	public GameWindow2() {

		JLabel tl = new JLabel("Total:");
		
		
		//Defining the Panels
		//Sets up the container Panel
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); 
		container.setSize(1000, 600);
		//Setting up the Content Panels
		dealpane.setSize(1000, 250);
		cardpane.setSize(1000, 250);
		buttpane.setSize(1000, 100);
		
		//Assigning Colours (For development only)
		dealpane.setBackground(Color.BLUE);
		cardpane.setBackground(Color.RED);
        buttpane.setBackground(Color.WHITE);
        
        
		//assigning bounds for fixed position items
		//Adjustments needed
		//tl.setBounds(450, 250, 50 , 50);
		//total.setBounds(490, 250, 50, 50);

		//adding action listeners to the buttons
		hit.addActionListener(this);
		stay.addActionListener(this);
		fold.addActionListener(this);
		reroll.addActionListener(this);
		//calls the generate cards function, see below
		this.genCards();
		this.genDealCards();
		//adding buttons and information readout
		//gw.add(tl);
		//gw.add(total);
		
		//Assigning content to the dealpane
					// MO/ANDREW this^ is where the dealer stuff should go
		
		//Assigning content to the cardpane
		
		
		//Assign the buttons to the buttpane using a GridBagConstraint
		
		gbc.ipady = 10;
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttpane.add(tl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		buttpane.add(total, gbc);
		
		gbc.ipadx = 20;
		gbc.gridx = 1;
		gbc.gridy = 1;
		buttpane.add(hit, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		buttpane.add(stay, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		buttpane.add(fold, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(10,0,0,0);
		gbc.anchor = GridBagConstraints.PAGE_END;
		buttpane.add(reroll, gbc);
		

		//defining the actual size of the box
		container.add(dealpane);
		container.add(cardpane);
		container.add(buttpane);
		gw.add(container, BorderLayout.CENTER);
		gw.setSize(1000,600);
		gw.setLayout(null);
		gw.setVisible(true);
		//initialises the game state, ready to play.
		this.showDealCards();
		this.showCards();
		this.updateTotal();
		this.checkAce();
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
			if(this.aceUsed == false) {
				this.checkAce();
			}
			this.showCards();
			this.updateTotal();
			if(plh.getPlayerTotal() > 21) {
				if(this.hasAce == true) {
					plh.getIndividualCard(aceI).aceValue();
					this.hasAce = false;
					this.aceUsed = true;
					this.updateTotal();
				}
				else if(this.hasAce == false) {
					this.updateTotal();
					JOptionPane.showMessageDialog(this.gw, "Bust! You lost by " + (plh.getPlayerTotal() - 21) + ".");
					cardpane.removeAll();
					cardpane.revalidate();
					cardpane.repaint();
					dealpane.removeAll();
					dealpane.repaint();
					dealpane.revalidate();
					this.resetGameState();
				}
			}

		}
		
		else if(e.getSource() == stay) {
			if(plh.getPlayerTotal() == 21) {
				JOptionPane.showMessageDialog(this.gw, "You did it! Bang-on");
				
			}
			else {
				//lets the player know how far off 21 they were, in the future will let player know if they won or lost to dealer
				JOptionPane.showMessageDialog(this.gw, "You did it! Though you were " + (21 - plh.getPlayerTotal()) + " off.");
			}
			cardpane.removeAll();
			cardpane.revalidate();
			cardpane.repaint();
			dealpane.removeAll();
			dealpane.repaint();
			dealpane.revalidate();
			this.resetGameState();
		}
		
		//fold and reroll are near identical, reroll is basically exclusively for speedy testing, and redundant once everything is functional
		else if(e.getSource() == fold) {
			JOptionPane.showMessageDialog(this.gw, "You folded, you lose.");
			cardpane.removeAll();
			cardpane.revalidate();
			cardpane.repaint();
			dealpane.removeAll();
			dealpane.repaint();
			dealpane.revalidate();
			this.resetGameState();
		}
		else if(e.getSource() == reroll) {
			cardpane.removeAll();
			cardpane.revalidate();
			cardpane.repaint();
			dealpane.removeAll();
			dealpane.repaint();
			dealpane.revalidate();
			this.resetGameState();
		}
	}
	/**
	 * wrapper for adding cards to be displayed
	 */
	public void genDealCards() {
		int wdth = 103;
		for(int i = 0; i < dlc.length; i++) {
			//for if the the game is freshly opened, considering there won't be anything displayed yet.
			BufferedImage imgi;
			try {
				//this was a complete stroke of genius if i do say so myself, the whole reason i wanted card ids to begin with
				imgi = ImageIO.read(new File("src\\cards\\" + dlh.getIndividualCard(i).getCardID() + ".png"));
			} catch(IOException ex) {
				System.out.println("Card not found");
				imgi = null;
			}
			dlc[i] = new JLabel(new ImageIcon(imgi));
			dlc[i].setBounds((wdth + 157), 320, 103, 157);
			dlc[i].setVisible(true);
			dealpane.add(dlc[i]);
			wdth += 103;
		}
		this.isNew = false;
	}
	
	public void genCards() {
		int wdth = 103;
		for(int i = 0; i < plc.length; i++) {
			//for if the the game is freshly opened, considering there won't be anything displayed yet.
			if(isNew == false) {
				gw.remove(plc[i]);
			}
			BufferedImage imgi;
			try {
				//this was a complete stroke of genius if i do say so myself, the whole reason i wanted card ids to begin with
				imgi = ImageIO.read(new File("src\\cards\\" + plh.getIndividualCard(i).getCardID() + ".png"));
			} catch(IOException ex) {
				System.out.println("Card not found");
				imgi = null;
			}
			plc[i] = new JLabel(new ImageIcon(imgi));
			plc[i].setBounds((wdth + 157), 320, 103, 157);
			plc[i].setVisible(false);
			cardpane.add(plc[i]);
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
	public void showDealCards() {
		for(int i = 0; i <= dlh.getCardsRevealed(); i++) {
			dlc[i].setVisible(false);
			System.out.println(dlh.getCardsRevealed());
		}
	}
	//found the bug, should function correctly!
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
	/**
	 * Wrapper for resetting the game state, saves a lot of redundant code.
	 */
	public void resetGameState() {
		this.plh = new Hand();
		this.dlh = new DealerHand();
		this.hasAce = false;
		this.aceUsed = false;
		this.checkAce();
		this.genCards();
		this.genDealCards();
		this.showCards();
		this.showDealCards();
		this.updateTotal();
	}
}
