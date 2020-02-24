package gui.guest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import asystem.AccountType;
import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.Z;
import gui.component.InfoDialog;
import gui.customer.CustomerSidebar;
import gui.retailer.RetailerHome;
import gui.retailer.RetailerSidebar;
import main.Customer;
import main.Retailer;
import main.User;

public class RegisterForm extends JPanel {

	/**
	 * Create the panel.
	 */
	public RegisterForm() {
		Setup.page(this);
		JTextField t1;
		JButton b1;

		// title
		JLabel lblSignUp = new JLabel();
		if (Database.register_as == AccountType.CUSTOMER) {
			lblSignUp.setText("SIGN UP FOR CUSTOMER");
		} else if (Database.register_as == AccountType.RETAILER) {
			lblSignUp.setText("SIGN UP FOR RETAILER");
		}

		Setup.title2(lblSignUp);
		add(lblSignUp);

		t1 = new JTextField();
		t1.setFont(new Font(Setup.font, Font.PLAIN, 12));
		t1.setBounds(151, 182, 312, 41);
		add(t1);
		
		JPasswordField t2 = new JPasswordField(20);
		t2.setFont(new Font(Setup.font, Font.PLAIN, 12));
		t2.setBounds(151, 285, 312, 41);
		
		
		JPasswordField t3 = new JPasswordField(20);
		t3.setFont(new Font(Setup.font, Font.PLAIN, 12));
		t3.setBounds(151, 389, 312, 41);
		add(t3);
		
		JLabel eye = new JLabel("");
		Setup.iconEffectWithoutOpaque(eye);
		eye.setIcon(new ImageIcon(LoginForm.class.getResource("/images/show25.png")));
		eye.setToolTipText("Show");
		//eye.setBounds(450, 288, 35, 36);
		eye.setBounds(425, 288, 35, 36);
		eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.playSound("click2.wav");
				if(eye.getToolTipText().equals("Show")) {
					t2.setEchoChar((char) 0);
					eye.setToolTipText("Hide");
					eye.setIcon(new ImageIcon(LoginForm.class.getResource("/images/hide25.png")));	
					t3.setText(t2.getText());
					t3.setEchoChar((char) 0);
				} else {
					t2.setEchoChar('*');
					t3.setEchoChar('*');
					eye.setToolTipText("Show");
					eye.setIcon(new ImageIcon(LoginForm.class.getResource("/images/show25.png")));	
				}
			}
		});
		add(eye);
		add(t2);
		

		// "Register" button
		b1 = new JButton("Register");
		Setup.buttonEffect(b1);
		b1.setFont(new Font(Setup.font, Font.BOLD, 16));
		b1.setBounds(361, 516, 200, 41);
		b1.addActionListener(new ActionListener() {
			@Override
			// TODO: register method
			public void actionPerformed(ActionEvent e) {
				String usernameInput = t1.getText();
				//char[] passwordInput = t2.getPassword();
				String passwordInput = t2.getText();
				String passwordRepeat = t3.getText();
				if (usernameInput.length() == 0 || passwordInput.length() == 0 || passwordRepeat.length() == 0) {
					Setup.playSound("error.wav");
					new InfoDialog("Complete the form");
				} else if (Database.usernameAlreadyExists(usernameInput)) {
					Setup.playSound("error.wav");
					new InfoDialog("Username already exists");
				} else if (!passwordInput.equals(passwordRepeat)) {
					Setup.playSound("error.wav");
					new InfoDialog("Password does not match");
				} else {
					User newUser = Database.register(usernameInput, passwordInput);				
					Database.currentUserId = newUser.getId();
					System.out.println(Database.currentUserId);
					if (Database.register_as == AccountType.CUSTOMER) {
						Database.registerCustomer((Customer)newUser);
						Database.isGuest = false;
						Z.page = new HomeOri();
						Z.pageSlide(1);
						Z.sidebar = new CustomerSidebar();
						Z.sidebarSlide(2);
					} else {
						Database.registerRetailer((Retailer)newUser);
						Z.page = new RetailerHome();
						Z.pageSlide(1);			
						Z.sidebar = new RetailerSidebar();
						Z.sidebarSlide(2);
					}
				}
			}
		});

		// "Back" button
		JButton back = new JButton("Back");
		Setup.buttonEffect(back);
		back.setFont(new Font(Setup.font, Font.BOLD, 16));
		back.setBounds(41, 516, 200, 41);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new RegisterMenu();
				Z.pageSlide(2);
			}
		});
		add(b1);
		add(back);

		JLabel lblEnterUsername = new JLabel("USERNAME");
		lblEnterUsername.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblEnterUsername.setBounds(151, 153, 177, 16);
		add(lblEnterUsername);

		JLabel lblEnterPassword = new JLabel("PASSWORD");
		lblEnterPassword.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblEnterPassword.setBounds(151, 256, 109, 16);
		add(lblEnterPassword);

		JLabel lblRepeatPassword = new JLabel("REPEAT PASSWORD");
		lblRepeatPassword.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblRepeatPassword.setBounds(151, 360, 205, 16);
		add(lblRepeatPassword);

	}

}
