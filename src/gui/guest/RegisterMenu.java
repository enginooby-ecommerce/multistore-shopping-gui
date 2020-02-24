package gui.guest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import asystem.AccountType;
import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.Z;

public class RegisterMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public RegisterMenu() {
		Setup.page(this);
		
		//guest sidebar
				//add(SetupComponent.guestSidebar());

				// title
				JLabel title = new JLabel("REGISTER");
				Setup.title2(title);
				add(title);

				// "Login as admin" button
				JButton b1 = new JButton("Retailer");
				Setup.buttonEffect(b1);
				b1.setFont(new Font(Setup.font, Font.BOLD, 16));
				b1.setBounds(210, 218, 200, 45);
				b1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//Database.loginAsAdmin = true;
						//Database.registerAsCustomer = false;
						Database.register_as = AccountType.RETAILER;
						Z.page= new RegisterForm();
						Z.pageSlide( 1);
					}
				});
			add(b1);

				// "Login as customer" button
				JButton b2 = new JButton("Customer");
				Setup.buttonEffect(b2);
				b2.setFont(new Font(Setup.font, Font.BOLD, 16));
				b2.setBounds(210, 295, 200, 45);
				b2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//Database.loginAsAdmin = false;
						//Database.registerAsCustomer = true;
						Database.register_as = AccountType.CUSTOMER;
						Z.page= new RegisterForm();
						Z.pageSlide(1);	}
				});
				add(b2);

				// "Back" button
				/*JButton back = new JButton("Back");
				Setup.buttonEffect(back);
				Setup.middleButton2(back);
				back.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Z.page = new Home();
						Z.pageSlide(2);
					}
				});
				add(back);*/
	}

}
