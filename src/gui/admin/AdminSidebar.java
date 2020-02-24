package gui.admin;

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
import gui.common.ViewPurchases;
import gui.common.Z;
import gui.component.ItemSidebar;
import gui.component.MenuSidebar;
import gui.guest.GuestSidebar;
import gui.retailer.RetailerHome;


public class AdminSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	
	List<JPanel> items = new ArrayList<>();
	
	public AdminSidebar() {
		setBackground(Setup.colorSidebar);
		setBounds(0, 0, 300, 600);
		setLayout(null);
		
		add(new MenuSidebar());

		
		
		ItemSidebar home = new ItemSidebar("Home",5);
		home.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_home_30px.png")));
		add(home);
		items.add(home);
		home.setStartItem();
		home.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				home.active(items);
				Z.page = new AdminHome();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		ItemSidebar customers = new ItemSidebar("Customers",4);
		customers.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_customer_30px.png")));
		add(customers);
		items.add(customers);
		customers.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				customers.active(items);
				Z.page = new MyCustomers();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		
		
	
		
		ItemSidebar retailers = new ItemSidebar("Retailers",3);
		retailers.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_online_store_30px.png")));
		add(retailers);
		items.add(retailers);
		retailers.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				retailers.active(items);
				Z.page = new MyRetailers();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		ItemSidebar purchases = new ItemSidebar("Purchases",2);
		purchases.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_purchase_order_30px.png")));
		add(purchases);
		items.add(purchases);
		purchases.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				purchases.active(items);
				Z.page = new AdminViewPurchases();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
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
				Z.page = new HomeOri();
				Z.pageSlide(2);
				Z.sidebar = new GuestSidebar();
				Z.sidebarSlide(1);
				Database.isGuest=true;
			}

		});

		// "Home" button
		/*JButton b6 = new JButton("Home");
		b6.setFont(new Font(Setup.font, Font.BOLD, 13));
		b6.setForeground(Color.WHITE);
		b6.setBackground(Color.GRAY);
		b6.setBounds(83, 150, 129, 40);
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Z.page = new AdminHome();
			Z.pageSlide(2);
			}
		});
		add(b6);

		

		// "My cart" button
		JButton b2 = new JButton("Customers");
		b2.setForeground(Color.WHITE);
		b2.setFont(new Font(Setup.font, Font.BOLD, 13));
		b2.setBackground(Color.GRAY);
		b2.setBounds(83, 360, 129, 40);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new MyCustomers();
				Z.pageSlide(1);
			}
		});

		// "Purchases" button
				JButton b3 = new JButton("Retailers");
				b3.setForeground(Color.WHITE);
				b3.setBackground(Color.GRAY);
				b3.setFont(new Font(Setup.font, Font.BOLD, 13));
				b3.setBounds(83, 260, 129, 40);
				b3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Z.page=new MyRetailers();
						Z.pageSlide(1);
					}
				});
		
		// "Purchases" button
		JButton purchases = new JButton("Purchases");
		purchases.setForeground(Color.WHITE);
		purchases.setBackground(Color.GRAY);
		purchases.setFont(new Font(Setup.font, Font.BOLD, 13));
		purchases.setBounds(83, 430, 129, 40);
		purchases.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new ViewPurchases();
				Z.pageSlide(1);
			}
		});
		add(purchases);

		// "Log out" button
		JButton b4 = new JButton("Log out");
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font(Setup.font, Font.BOLD, 13));
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
