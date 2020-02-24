package zachieve;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import gui.Setup;
import main.Customer;
import main.Database;

import java.awt.Color;
import java.awt.Font;

public class GuestRegisterForm extends JFrame {
	private JTextField t3;

	public GuestRegisterForm() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JTextField t1, t2;
		JButton b1;
		
		//guest sidebar
				add(Setup.guestSidebar());
		
		//title
		JLabel lblSignUp = new JLabel("SIGN UP");
		Setup.title(lblSignUp);
		getContentPane().add(lblSignUp);
		
		
		
		t1 = new JTextField();
		t1.setToolTipText("Enter username");
		t1.setBounds(451, 182, 312, 41);

		t2 = new JTextField();
		t2.setToolTipText("Enter password");
		t2.setBounds(451, 285, 312, 41);

		// "Register" button
		b1 = new JButton("Register");
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("Tahoma", Font.BOLD, 16));
		b1.setBackground(Color.GRAY);
		b1.setBounds(661, 516, 200, 41);
		b1.addActionListener(new ActionListener() {
			@Override
			//TODO: register method
			public void actionPerformed(ActionEvent e) {
				String usernameInput = t1.getText();
				String passwordInput = t2.getText();
				String passwordRepeat = t3.getText();
				if (usernameInput.length() == 0 || passwordInput.length() == 0 || passwordRepeat.length() == 0) {
					JOptionPane.showMessageDialog(null, "Complete input", "", JOptionPane.INFORMATION_MESSAGE);
				} else if (new Customer().usernameAlreadyExists(usernameInput)) {
					JOptionPane.showMessageDialog(null, "Username already exists", "", JOptionPane.INFORMATION_MESSAGE);
				} else if (!passwordInput.equals(passwordRepeat)) {
					JOptionPane.showMessageDialog(null, "Password does not match", "", JOptionPane.INFORMATION_MESSAGE);
				}

				else {
					//((JFrame) (b1.getTopLevelAncestor())).revalidate();
					((JFrame) (b1.getTopLevelAncestor())).dispose();
					new Customer().register(usernameInput, passwordInput);
					Database.isGuest=false;
					// indicate current customer
					Database.currentUserId = Database.getIdCustomerByUsername(usernameInput);
					new CustomerHome().setVisible(true);

				}

			}
		});

		// "Back" button
		JButton back = new JButton("Back");
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Tahoma", Font.BOLD, 16));
		back.setBackground(Color.GRAY);
		back.setBounds(341, 516, 200, 41);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//((JFrame) (b1.getTopLevelAncestor())).revalidate();
				((JFrame) (b1.getTopLevelAncestor())).dispose();
				new GuestHome().setVisible(true);

			}
		});


		getContentPane().add(t1);
		getContentPane().add(t2);
		getContentPane().add(b1);
		getContentPane().add(back);

		JLabel lblEnterUsername = new JLabel("USERNAME");
		lblEnterUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterUsername.setBounds(451, 153, 177, 16);
		getContentPane().add(lblEnterUsername);

		JLabel lblEnterPassword = new JLabel("PASSWORD");
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterPassword.setBounds(451, 256, 109, 16);
		getContentPane().add(lblEnterPassword);

		

		JLabel lblRepeatPassword = new JLabel("REPEAT PASSWORD");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRepeatPassword.setBounds(451, 360, 205, 16);
		getContentPane().add(lblRepeatPassword);

		t3 = new JTextField();
		t3.setToolTipText("Enter password");
		t3.setBounds(451, 389, 312, 41);
		getContentPane().add(t3);

		//Setup.mainFrame(this);

	}
}
