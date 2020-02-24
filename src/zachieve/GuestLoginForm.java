package zachieve;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Setup;
import gui.admin.AdminHome;
import main.Account;
import main.Admin;
import main.Customer;
import main.Database;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuestLoginForm extends JFrame {
	public GuestLoginForm() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JTextField t1, t2;
		JButton b1;
		
		//guest sidebar
				add(Setup.guestSidebar());
		
		//username field
		t1 = new JTextField("admin");
		t1.setBounds(451, 182, 312, 41);

		//password field
		t2 = new JTextField("admin");
		t2.setBounds(451, 285, 312, 41);

		//"Login" button
		b1 = new JButton("Login");
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("Tahoma", Font.BOLD, 16));
		b1.setBackground(Color.GRAY);
		b1.setBounds(661, 516, 200, 41);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameInput = t1.getText();
				String passwordInput = t2.getText();

				if (new Account().loginIsSuccessful(usernameInput, passwordInput)) {
					//((JFrame) (b1.getTopLevelAncestor())).revalidate();
					((JFrame) (b1.getTopLevelAncestor())).dispose();
					if (Database.loginAsAdmin) {
						new AdminHome().setVisible(true);
					} else {
						Database.isGuest=false;
						new CustomerHome().setVisible(true);
						// indicate current customer
						Database.currentUserId = Database.getIdCustomerByUsername(usernameInput);
						System.out.println(Database.currentUserId);
					}

				} else
					JOptionPane.showMessageDialog(b1, "Wrong usename or password", "", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		// back button
		JButton back = new JButton("Back");
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Tahoma", Font.BOLD, 16));
		back.setBackground(Color.GRAY);
		back.setBounds(341, 516, 200, 41);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//((JFrame) (back.getTopLevelAncestor())).revalidate();
				((JFrame) (back.getTopLevelAncestor())).dispose();
				new GuestLoginMenu().setVisible(true);

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
		
		//tittle
		JLabel lblLogin = new JLabel();
		if(Database.loginAsAdmin) {
			lblLogin.setText("LOGIN AS ADMIN");
		} else {
			lblLogin.setText("LOGIN AS CUSTOMER");
		}
		Setup.title(lblLogin);
		getContentPane().add(lblLogin);
		

		JLabel lblForget = new JLabel("Forgot password?");
		lblForget.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Too bad for you!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lblForget.setForeground(SystemColor.textHighlight);
		lblForget.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblForget.setBounds(636, 328, 134, 30);
		getContentPane().add(lblForget);
		
		JLabel lblForgotUsername = new JLabel("Forgot username?");
		lblForgotUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Really???", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lblForgotUsername.setForeground(SystemColor.textHighlight);
		lblForgotUsername.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblForgotUsername.setBounds(636, 225, 134, 30);
		getContentPane().add(lblForgotUsername);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account yet? ");
		lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDontHaveAn.setBounds(451, 414, 193, 30);
		getContentPane().add(lblDontHaveAn);
		
		JButton btnRegisterNow = new JButton("Register now");
		btnRegisterNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//((JFrame) (b1.getTopLevelAncestor())).revalidate();
				((JFrame) (b1.getTopLevelAncestor())).dispose();
				new GuestRegisterForm().setVisible(true);
			}
		});
		btnRegisterNow.setForeground(Color.WHITE);
		btnRegisterNow.setBackground(Color.GRAY);
		btnRegisterNow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterNow.setBounds(640, 418, 123, 25);
		getContentPane().add(btnRegisterNow);
		
		//Setup.mainFrame(this);
		
		
		

	}
}
