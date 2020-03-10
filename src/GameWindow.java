import javax.swing.*;
import java.awt.event.*;

/**
 * 
 */

/**
 * @author Andrew
 *
 */

public class GameWindow implements ActionListener{
	Hand plh = new Hand();
	JFrame gw = new JFrame();
	JLabel total = new JLabel();
	JButton hit = new JButton("Hit");
	JButton stay = new JButton("Stay");
	JButton fold = new JButton("Fold");
	JButton reroll = new JButton("Try again.");
	
	public GameWindow() {

		JLabel tl = new JLabel("Total:");
	
		hit.setBounds(325, 500, 100, 40);
		stay.setBounds(420, 500, 100, 40);
		fold.setBounds(515, 500, 100, 40);
		reroll.setBounds(420, 10, 100, 40);
		tl.setBounds(450, 250, 50 , 50);
		total.setBounds(490, 250, 50, 50);
		
		hit.addActionListener(this);
		stay.addActionListener(this);
		fold.addActionListener(this);
		reroll.addActionListener(this);
		
		gw.add(hit);
		gw.add(stay);
		gw.add(fold);
		gw.add(reroll);
		gw.add(tl);
		gw.add(total);
	
		gw.setSize(1000,600);
		gw.setLayout(null);
		gw.setVisible(true);
		
		this.updateTotal(Integer.toString(plh.getPlayerTotal()));
	}
	
	private void updateTotal(String t) {
		this.total.setText(t);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hit) {
			plh.hit();
			if(plh.getPlayerTotal() <= 21) {
				this.updateTotal(Integer.toString(plh.getPlayerTotal()));
			}
			else {
				this.updateTotal(Integer.toString(plh.getPlayerTotal()));
				JOptionPane.showMessageDialog(this.gw, "you lose :-(");
			}
		}
		else if(e.getSource() == stay) {
			if(plh.getPlayerTotal() == 21) {
				JOptionPane.showMessageDialog(this.gw, "You did it! Bang-on");
			}
			else {
				JOptionPane.showMessageDialog(this.gw, "You did it! though you were " + (21 - plh.getPlayerTotal()) + " off.");
			}
			
		}
		else if(e.getSource() == fold) {
			JOptionPane.showMessageDialog(this.gw, "You folded, you lose.");
		}
		else if(e.getSource() == reroll) {
			this.plh = new Hand();
			this.updateTotal(Integer.toString(plh.getPlayerTotal()));
		}
	}
}
