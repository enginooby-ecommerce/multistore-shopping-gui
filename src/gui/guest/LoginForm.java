package gui.guest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.admin.AdminHome;
import gui.admin.AdminSidebar;
import gui.common.HomeOri;
import gui.common.Z;
import gui.component.InfoDialog;
import gui.customer.CustomerSidebar;
import gui.retailer.RetailerHome;
import gui.retailer.RetailerSidebar;
import main.Account;
import main.User;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import asystem.AccountType;
import asystem.Database;
import asystem.Setup;


public class LoginForm extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoginForm() {		
		JTextField t1;
		JButton b1;
		
		Setup.page(this);
		//this.setBounds(0, 0, 1000, 1000);
		//this.setLayout(null);
		
		//username field
		t1 = new JTextField();
		t1.setFont(new Font(Setup.font, Font.PLAIN, 12));
		t1.setBounds(151, 182, 312, 41);

		JPasswordField t2 = new JPasswordField("", 20);
		t2.setFont(new Font(Setup.font, Font.PLAIN, 12));
		t2.setBounds(151, 285, 312, 41);
		
		if(Database.login_as==AccountType.ADMIN) {
			t1.setText("admin");
			t2.setText("admin");
		} else if(Database.login_as==AccountType.RETAILER) {
			t1.setText("retailerDemo");
			t2.setText("retailerDemo");
		} else if(Database.login_as==AccountType.CUSTOMER) {
			t1.setText("customerDemo");
			t2.setText("customerDemo");
		}
		
		JLabel eye = new JLabel("");
		Setup.iconEffectWithoutOpaque(eye);
		eye.setIcon(new ImageIcon(LoginForm.class.getResource("/images/show25.png")));
		eye.setToolTipText("Show");
		eye.setBounds(425, 288, 35, 36);
		eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.playSound("click2.wav");
				if(eye.getToolTipText().equals("Show")) {
					t2.setEchoChar((char) 0);
					eye.setToolTipText("Hide");
					eye.setIcon(new ImageIcon(LoginForm.class.getResource("/images/hide25.png")));	
				} else {
					t2.setEchoChar('*');
					eye.setToolTipText("Show");
					eye.setIcon(new ImageIcon(LoginForm.class.getResource("/images/show25.png")));	
				}
			}
		});

		
		add(eye);
			
		
		

		//"Login" button
		b1 = new JButton("Login");
		Setup.buttonEffect(b1);
		b1.setFont(new Font(Setup.font, Font.BOLD, 16));
		b1.setBounds(361, 516, 200, 41);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameInput = t1.getText();
				char[] passwordInput = t2.getPassword();
				//String passwordInput = t2.getText();

				if (Database.loginIsSuccessful(usernameInput, passwordInput)) {
					if (Database.login_as==AccountType.ADMIN) {
						Z.page = new AdminHome();
						Z.pageSlide(1);
						Z.sidebar = new AdminSidebar();
						Z.sidebarSlide(2);					
					} else if (Database.login_as==AccountType.CUSTOMER){
						Database.isGuest=false;
						Z.page = new HomeOri();
						Z.pageSlide(1);			
						Z.sidebar = new CustomerSidebar();
						Z.sidebarSlide(2);
						Database.currentUserId = Database.getIdUserByUsername(usernameInput);
					} else {
						Z.page = new RetailerHome();
						Z.pageSlide(1);			
						Z.sidebar = new RetailerSidebar();
						Z.sidebarSlide(2);
						Database.currentUserId = Database.getIdUserByUsername(usernameInput);
					}
					System.out.println(Database.currentUserId);
					Database.isGuest=false;
				} else {
					Setup.playSound("error.wav");
					new InfoDialog("Wrong usename or password");
				}
			}
		});

		// back button
		JButton back = new JButton("Back");
		Setup.buttonEffect(back);
		back.setFont(new Font(Setup.font, Font.BOLD, 16));
		back.setBounds(41, 516, 200, 41);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page=new LoginMenu();
				Z.pageSlide(2);

			}
		});

		add(t1);
		add(t2);
		add(b1);
		add(back);
		
		JLabel lblEnterUsername = new JLabel("USERNAME");
		lblEnterUsername.setForeground(Setup.colorPageText);
		lblEnterUsername.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblEnterUsername.setBounds(151, 153, 177, 16);
		add(lblEnterUsername);
		
		JLabel lblEnterPassword = new JLabel("PASSWORD");
		lblEnterPassword.setForeground(Setup.colorPageText);
		lblEnterPassword.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblEnterPassword.setBounds(151, 256, 109, 16);
		add(lblEnterPassword);
		
		//title
		JLabel lblLogin = new JLabel();
		if(Database.login_as==AccountType.ADMIN) {
			lblLogin.setText("LOGIN AS ADMIN");
		} else if (Database.login_as==AccountType.CUSTOMER){
			lblLogin.setText("LOGIN AS CUSTOMER");
		} else {
			lblLogin.setText("LOGIN AS RETAILER");
		}
		Setup.title2(lblLogin);
		add(lblLogin);
		

		JLabel lblForget = new JLabel("Forgot password? ");
		lblForget.setHorizontalAlignment(SwingConstants.RIGHT);
		Setup.linkEffect(lblForget);
		lblForget.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new InfoDialog("Too bad for you!");
			}
		});
		
		
		lblForget.setForeground(new Color(0, 0, 255));
		lblForget.setFont(new Font(Setup.font, Font.ITALIC, 15));
		lblForget.setBounds(321, 328, 142, 30);
		add(lblForget);
		
		JLabel lblForgotUsername = new JLabel("Forgot username? ");
		lblForgotUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		Setup.linkEffect(lblForgotUsername);
		lblForgotUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new InfoDialog("Really?!");
			}
		});
		lblForgotUsername.setForeground(new Color(0, 0, 255));
		lblForgotUsername.setFont(new Font(Setup.font, Font.ITALIC, 15));
		lblForgotUsername.setBounds(321, 225, 142, 30);
		add(lblForgotUsername);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account yet? ");
		lblDontHaveAn.setForeground(Setup.colorPageText);
		lblDontHaveAn.setFont(new Font(Setup.font, Font.PLAIN, 15));
		lblDontHaveAn.setBounds(151, 414, 193, 30);
		add(lblDontHaveAn);
		
		JButton btnRegisterNow = new JButton("Register now");
		Setup.buttonEffect(btnRegisterNow);
		btnRegisterNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Z.page = new RegisterMenu();
				Z.pageSlide(1);
			}
		});
		btnRegisterNow.setFont(new Font(Setup.font, Font.PLAIN, 13));
		btnRegisterNow.setBounds(340, 418, 123, 25);
		add(btnRegisterNow);
	}

}
