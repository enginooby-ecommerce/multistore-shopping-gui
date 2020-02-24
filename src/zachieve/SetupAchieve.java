import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import gui.common.SettingPage;
import gui.component.AdminHomeTemp;
import gui.component.AdminViewCustomers;
import gui.component.AdminViewPurchases;
import gui.component.AdminViewStock;
import gui.component.Setup;
import main.Database;

public class SetupAchieve {
	
	public static void rightPanel(JPanel panel) {
		panel.setBounds(0, 0, 900, 600);
		panel.setBackground(Database.background);
		panel.setLayout(null);
	}

	/*
	 * public static void mainFrame(JFrame frame) { // add clock frame.add(new
	 * Clock());
	 * 
	 * // create border //
	 * frame.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, //
	 * Color.BLACK));
	 * 
	 * // title line JSeparator separator = new JSeparator();
	 * separator.setBounds(300, 85, 601, 10); frame.add(separator);
	 * 
	 * // add close button JLabel x = new JLabel(); frame.add(x);
	 * x.setForeground(Color.BLACK); x.setBounds(858, 13, 32, 32); x.setFont(new
	 * Font("Tahoma", Font.PLAIN, 24));
	 * x.setHorizontalAlignment(SwingConstants.CENTER);
	 * x.setVerticalAlignment(SwingConstants.CENTER); //x.setIcon(new
	 * ImageIcon(Setup.class.getResource(("/images/close.png"))));
	 * x.addMouseListener(new MouseAdapter() {
	 * 
	 * @Override public void mouseClicked(MouseEvent arg0) { System.exit(0);
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent arg0) { x.setOpaque(true);
	 * x.setBackground(Color.GRAY); }
	 * 
	 * @Override public void mouseExited(MouseEvent e) {
	 * x.setBackground(Color.white); x.setOpaque(false); } });
	 * 
	 * // add minimize button JLabel minimize = new JLabel(); frame.add(minimize);
	 * minimize.setForeground(Color.BLACK); minimize.setBounds(820, 13, 32, 32);
	 * minimize.setFont(new Font("Tahoma", Font.PLAIN, 24));
	 * minimize.setHorizontalAlignment(SwingConstants.CENTER);
	 * minimize.setVerticalAlignment(SwingConstants.CENTER); minimize.setIcon(new
	 * ImageIcon(Setup.class.getResource(("/images/minimize.png"))));
	 * minimize.addMouseListener(new MouseAdapter() {
	 * 
	 * @Override public void mouseClicked(MouseEvent arg0) {
	 * frame.setState(Frame.ICONIFIED);
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent arg0) {
	 * minimize.setOpaque(true); minimize.setBackground(Color.GRAY); }
	 * 
	 * @Override public void mouseExited(MouseEvent e) {
	 * minimize.setBackground(Color.white); minimize.setOpaque(false); } });
	 * 
	 * // "Setting" button JButton setting = new JButton("Setting");
	 * setting.setFont(new Font("Tahoma", Font.PLAIN, 16)); setting.setBounds(100,
	 * 25, 100, 29); frame.add(setting); setting.addActionListener(new
	 * ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { new
	 * SettingPage().setVisible(true); } });
	 * 
	 * // add left panel JPanel panel = new JPanel();
	 * panel.setBackground(Database.sidebar); panel.setBounds(0, 0, 300, 600);
	 * frame.add(panel);
	 * 
	 * // setup frame // frame.setBackground(Color.LIGHT_GRAY); frame.setSize(901,
	 * 599); // width and height frame.setLayout(null); // absolute layout
	 * frame.setUndecorated(true); // hide title bar
	 * 
	 * frame.setResizable(true); frame.setLocationRelativeTo(null); // make the
	 * frame at the center // frame.setOpacity(50 * 0.01f);
	 * frame.setVisible(true);// making the frame visible
	 * 
	 * }
	 */

	public static JPanel customerSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setBackground(Database.sidebar);
		sidebar.setBounds(0, 0, 300, 600);

		// "Setting" button
		JButton setting = new JButton("Setting");
		setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setting.setBounds(100, 25, 100, 29);
		sidebar.add(setting);
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingPage().setVisible(true);
			}
		});

		// "Home" button
		JButton b6 = new JButton("Home");
		b6.setFont(new Font("Tahoma", Font.BOLD, 13));
		b6.setForeground(Color.WHITE);
		b6.setBackground(Color.GRAY);
		b6.setBounds(83, 150, 129, 40);
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				// ((JFrame) (sidebar.getTopLevelAncestor())).repaint();
				// new CustomerHome().setVisible(true);
			}
		});
		sidebar.add(b6);

		// "My favorite" button
		JButton b5 = new JButton("My favorite");
		b5.setFont(new Font("Tahoma", Font.BOLD, 13));
		b5.setForeground(Color.WHITE);
		b5.setBackground(Color.GRAY);
		b5.setBounds(83, 220, 129, 40);
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new CustomerShopping().setVisible(true);
				Setup.underDev();
			}
		});
		sidebar.add(b5);

		// "Shopping" button
		JButton b1 = new JButton("Shopping");
		b1.setFont(new Font("Tahoma", Font.BOLD, 13));
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.GRAY);
		b1.setBounds(83, 290, 129, 40);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new CustomerShopping().setVisible(true);
			}
		});

		// "My cart" button
		JButton b2 = new JButton("My cart");
		b2.setForeground(Color.WHITE);
		b2.setFont(new Font("Tahoma", Font.BOLD, 13));
		b2.setBackground(Color.GRAY);
		b2.setBounds(83, 360, 129, 40);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.customer_list.get(Database.currentUserId).updateCart(); // update cart
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new CustomerViewCart().setVisible(true);
			}
		});

		// "My purchases" button
		JButton b3 = new JButton("My purchases");
		b3.setForeground(Color.WHITE);
		b3.setBackground(Color.GRAY);
		b3.setFont(new Font("Tahoma", Font.BOLD, 13));
		b3.setBounds(83, 430, 129, 40);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// dispose();
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new CustomerViewPurchase().setVisible(true);
			}
		});

		// "Log out" button
		JButton b4 = new JButton("Log out");
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font("Tahoma", Font.BOLD, 13));
		b4.setBackground(Color.GRAY);
		b4.setBounds(83, 500, 129, 40);
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// dispose();
				Database.isGuest = true;
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new GuestHome().setVisible(true);
			}
		});
		sidebar.setLayout(null);
		sidebar.add(b1);
		sidebar.add(b2);
		sidebar.add(b3);
		sidebar.add(b4);
		return sidebar;
	}

	public static JPanel adminSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setBackground(Database.sidebar);
		sidebar.setBounds(0, 0, 300, 600);

		// "Setting" button
		JButton setting = new JButton("Setting");
		setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setting.setBounds(100, 25, 100, 29);
		sidebar.add(setting);
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingPage().setVisible(true);
			}
		});

		// "Home" button
		JButton b6 = new JButton("Home");
		b6.setFont(new Font("Tahoma", Font.BOLD, 13));
		b6.setForeground(Color.WHITE);
		b6.setBackground(Color.GRAY);
		b6.setBounds(83, 150, 129, 40);
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				new AdminHomeTemp().setVisible(true);
			}
		});
		sidebar.add(b6);

		// "Questions" button
		JButton b5 = new JButton("Question");
		b5.setFont(new Font("Tahoma", Font.BOLD, 13));
		b5.setForeground(Color.WHITE);
		b5.setBackground(Color.GRAY);
		b5.setBounds(83, 220, 129, 40);
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new CustomerShopping().setVisible(true);
				Setup.underDev();
			}
		});
		sidebar.add(b5);

		// "Shopping" button
		JButton b1 = new JButton("Stock");
		b1.setFont(new Font("Tahoma", Font.BOLD, 13));
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.GRAY);
		b1.setBounds(83, 290, 129, 40);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				new AdminViewStock().setVisible(true);
			}
		});

		// "My cart" button
		JButton b2 = new JButton("Customers");
		b2.setForeground(Color.WHITE);
		b2.setFont(new Font("Tahoma", Font.BOLD, 13));
		b2.setBackground(Color.GRAY);
		b2.setBounds(83, 360, 129, 40);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				new AdminViewCustomers().setVisible(true);
			}
		});

		// "Purchases" button
		JButton b3 = new JButton("Purchases");
		b3.setForeground(Color.WHITE);
		b3.setBackground(Color.GRAY);
		b3.setFont(new Font("Tahoma", Font.BOLD, 13));
		b3.setBounds(83, 430, 129, 40);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (sidebar.getTopLevelAncestor())).revalidate();
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				new AdminViewPurchases().setVisible(true);
			}
		});

		// "Log out" button
		JButton b4 = new JButton("Log out");
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font("Tahoma", Font.BOLD, 13));
		b4.setBackground(Color.GRAY);
		b4.setBounds(83, 500, 129, 40);
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				((JFrame) (sidebar.getTopLevelAncestor())).dispose();

				// new GuestHome().setVisible(true);
			}
		});
		sidebar.setLayout(null);
		sidebar.add(b1);
		sidebar.add(b2);
		sidebar.add(b3);
		sidebar.add(b4);
		return sidebar;
	}

	public static JPanel guestSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setBackground(Database.sidebar);
		sidebar.setBounds(0, 0, 300, 600);

		// "Setting" button
		JButton setting = new JButton("Setting");
		setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setting.setBounds(100, 25, 100, 29);
		sidebar.add(setting);
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingPage().setVisible(true);
			}
		});

		// "Login" button
		JButton b3 = new JButton("Login");
		b3.setForeground(Color.WHITE);
		b3.setBackground(Color.GRAY);
		b3.setFont(new Font("Tahoma", Font.BOLD, 13));
		b3.setBounds(83, 430, 129, 40);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((JFrame) (b3.getTopLevelAncestor())).dispose();
				// new GuestLoginMenu();
			}
		});

		// "Register" button
		JButton b4 = new JButton("Register");
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font("Tahoma", Font.BOLD, 13));
		b4.setBackground(Color.GRAY);
		b4.setBounds(83, 500, 129, 40);
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((JFrame) (sidebar.getTopLevelAncestor())).dispose();
				// new GuestRegisterForm();
			}
		});

		/******* Items on left panel ********/
		JLabel lblOop = new JLabel("OOP");
		lblOop.setForeground(Color.WHITE);
		lblOop.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblOop.setBounds(100, 100, 274, 42);
		sidebar.add(lblOop);

		JLabel lblTask = new JLabel("TASK 5");
		lblTask.setBounds(100, 160, 145, 46);
		sidebar.add(lblTask);
		lblTask.setForeground(Color.WHITE);
		lblTask.setFont(new Font("Tahoma", Font.PLAIN, 33));

		// title line
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 155, 300, 10);
		sidebar.add(separator);

		JLabel lblTask2 = new JLabel("Shopping application");
		lblTask2.setBounds(60, 200, 250, 46);
		sidebar.add(lblTask2);
		lblTask2.setForeground(Color.WHITE);
		lblTask2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		/*
		 * JLabel lblNewLabel = new JLabel(""); lblNewLabel.setBounds(20, 300, 250,
		 * 250); sidebar.add(lblNewLabel);
		 * lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		 * lblNewLabel.setIcon(new
		 * ImageIcon(GuestHome.class.getResource("/images/logo1.png")));
		 */

		sidebar.setLayout(null);
		sidebar.add(b3);
		sidebar.add(b4);
		return sidebar;
	}
}
