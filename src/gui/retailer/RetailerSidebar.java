package gui.retailer;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.MyInbox;
import gui.common.Z;
import gui.component.ItemSidebar;
import gui.component.MenuSidebar;
import gui.guest.GuestSidebar;


public class RetailerSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	
	List<JPanel> items = new ArrayList<>();
	
	public RetailerSidebar() {
		setBackground(Setup.colorSidebar);
		setBounds(0, 0, 300, 600);
		setLayout(null);
		
		add(new MenuSidebar());
		
		// "Setting" button
		/*JButton setting = new JButton("Setting");
		Setup.buttonEffect(setting);
		setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setting.setBounds(100, 25, 100, 29);
		add(setting);
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingPage().setVisible(true);
			}
		});*/
		
		
		ItemSidebar home = new ItemSidebar("Home",7);
		home.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_home_30px.png")));
		add(home);
		items.add(home);
		home.setStartItem();
		home.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				home.active(items);
				Z.page = new RetailerHome();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		ItemSidebar stock = new ItemSidebar("Stock",6);
		stock.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_product_30px.png")));
		add(stock);
		items.add(stock);
		stock.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				stock.active(items);
				Z.page = new MyStock();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		ItemSidebar purchase = new ItemSidebar("Purchases",5);
		purchase.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_purchase_order_30px.png")));
		add(purchase);
		items.add(purchase);
		purchase.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//purchase.active(items);
				Setup.underDev();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					//Z.pageSlide(2);
				}

			}

		});
		
		
		
		ItemSidebar subscriber = new ItemSidebar("Subscribers",4);
		subscriber.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_subscription_30px.png")));
		add(subscriber);
		items.add(subscriber);
		subscriber.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				subscriber.active(items);
				Z.page = new MySubscriber();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		ItemSidebar inbox = new ItemSidebar("Inbox",3);
		inbox.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_inbox_30px.png")));
		add(inbox);
		items.add(inbox);
		inbox.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				inbox.active(items);
				Z.page = new MyInbox();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}

			}

		});
		
		ItemSidebar question = new ItemSidebar("Questions",2);
		question.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_questions_30px.png")));
		add(question);
		items.add(question);
		question.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//question.active(items);
				Setup.underDev();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					//Z.pageSlide(1);
				}

			}

		});
		
		ItemSidebar logout = new ItemSidebar("Log out",1);
		logout.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_logout_rounded_left_30px.png")));
		add(logout);
		items.add(logout);
		logout.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logout.active(items);
				Database.isGuest = true;
				Z.page = new HomeOri();
				Z.pageSlide(2);
				Z.sidebar = new GuestSidebar();
				Z.sidebarSlide(1);

			}

		});

		// "Home" button
		/*JButton home = new JButton("Home");
		home.setFont(new Font("Tahoma", Font.BOLD, 13));
		home.setForeground(Color.WHITE);
		home.setBackground(Color.GRAY);
		home.setBounds(83, 90, 129, 40);
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Z.page = new RetailerHome();
			Z.pageSlide(2);
			}
		});
		add(home);
		
		JButton inbox = new JButton("Inbox");
		inbox.setFont(new Font("Tahoma", Font.BOLD, 13));
		inbox.setForeground(Color.WHITE);
		inbox.setBackground(Color.GRAY);
		inbox.setBounds(83, 150, 129, 40);
		inbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Z.page = new MyInbox();
			Z.pageSlide(2);
			}
		});
		add(inbox);

		// "Questions" button
		JButton b5 = new JButton("Questions");
		b5.setFont(new Font("Tahoma", Font.BOLD, 13));
		b5.setForeground(Color.WHITE);
		b5.setBackground(Color.GRAY);
		b5.setBounds(83, 220, 129, 40);
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (getTopLevelAncestor())).dispose();
				// new CustomerShopping().setVisible(true);
				Setup.underDev();
			}
		});
		add(b5);

		// "Shopping" button
		JButton b1 = new JButton("Stock");
		b1.setFont(new Font("Tahoma", Font.BOLD, 13));
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.GRAY);
		b1.setBounds(83, 290, 129, 40);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new MyStock();
				Z.pageSlide(1);
			}
		});
		add(b1);

		// "My cart" button
		JButton b2 = new JButton("Purchases");
		b2.setForeground(Color.WHITE);
		b2.setFont(new Font("Tahoma", Font.BOLD, 13));
		b2.setBackground(Color.GRAY);
		b2.setBounds(83, 360, 129, 40);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Z.page = new MyCustomers();
				//Z.pageSlide(1);
			}
		});

		// "Purchases" button
				JButton b3 = new JButton("Subscribers");
				b3.setForeground(Color.WHITE);
				b3.setBackground(Color.GRAY);
				b3.setFont(new Font("Tahoma", Font.BOLD, 13));
				b3.setBounds(83, 430, 129, 40);
				b3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
					}
				});
		
		// "Purchases" button
		/*JButton b3 = new JButton("Purchases");
		b3.setForeground(Color.WHITE);
		b3.setBackground(Color.GRAY);
		b3.setFont(new Font("Tahoma", Font.BOLD, 13));
		b3.setBounds(83, 430, 129, 40);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ((JFrame) (getTopLevelAncestor())).revalidate();
				((JFrame) (getTopLevelAncestor())).dispose();
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
Z.page = new HomeOri();
Z.pageSlide(2);
Z.sidebar = new GuestSidebar();
Z.sidebarSlide(1);
			}
		});
		
		//add(b1);
		add(b2);
		add(b3);
		add(b4);*/
	}

}
