package zachieve;

import javax.swing.*;

import gui.Setup;
import main.Database;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;

public class GuestLoginMenu extends JFrame {
	public GuestLoginMenu() {
		getContentPane().setBackground(Database.background);

		//guest sidebar
		add(Setup.guestSidebar());

		// title
		JLabel title = new JLabel("LOGIN");
		Setup.title(title);
		getContentPane().add(title);

		// "Login as admin" button
		JButton b1 = new JButton("Login as admin");
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.GRAY);
		b1.setFont(new Font("Tahoma", Font.BOLD, 16));
		b1.setBounds(510, 218, 200, 45);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.loginAsAdmin = true;
				new GuestLoginForm().setVisible(true);
				//((JFrame) (b1.getTopLevelAncestor())).revalidate();
				((JFrame) (b1.getTopLevelAncestor())).dispose();
			}
		});
		getContentPane().add(b1);

		// "Login as customer" button
		JButton b2 = new JButton("Login as customer");
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.GRAY);
		b2.setFont(new Font("Tahoma", Font.BOLD, 16));
		b2.setBounds(510, 295, 200, 45);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.loginAsAdmin = false;
				new GuestLoginForm().setVisible(true);
				//((JFrame) (b1.getTopLevelAncestor())).revalidate();
				((JFrame) (b1.getTopLevelAncestor())).dispose();			}
		});
		getContentPane().add(b2);

		// "Back" button
		JButton back = new JButton("Back");
		Setup.middleButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//((JFrame) (b1.getTopLevelAncestor())).revalidate();
				((JFrame) (b1.getTopLevelAncestor())).dispose();
				new GuestHome().setVisible(true);
			}
		});
		getContentPane().add(back);

		//Setup.mainFrame(this);

	}

}
