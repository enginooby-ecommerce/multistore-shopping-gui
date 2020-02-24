package gui.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class SettingSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	public SettingSidebar() {
		this.setBackground(new Color(55,55,55));
		setBounds(250, 3, 300, 50);
		this.setLocation(0, 645);
		this.setLayout(null);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		//setBorder(raisedbevel);
		
		JLabel menu = new JLabel("");
		menu.setBounds(5, 5, 40, 40);
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		//menu.setBounds(0, 0, 64, 64);
		menu.setIcon(Setup.getScaledIcon(new ImageIcon(SettingSidebar.class.getResource("/images/icons8_menu_50px.png")),35,35));
		//add(menu);
	}

}
