package gui.customer;

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
import gui.guest.RegisterMenu;
import main.Customer;

public class CustomerSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	
	List<JPanel> items = new ArrayList<>();

	public CustomerSidebar() {
		setBackground(Setup.colorSidebar);
		setBounds(0, 0, 300, 600);
		setLayout(null);

		add(new MenuSidebar());
		
		

	
		
		ItemSidebar home = new ItemSidebar("Home",8);
		home.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_home_30px.png")));
		add(home);
		items.add(home);
		home.setStartItem();
		home.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				home.active(items);
				Z.page = new HomeOri();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(2);
				}

			}

		});
		
		ItemSidebar sub = new ItemSidebar("Subsciptions",7);
		sub.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_subscription_30px.png")));
		add(sub);
		items.add(sub);
		sub.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sub.active(items);
				Z.page = new MySubscriptions();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}

			}

		});
		
		ItemSidebar shopping = new ItemSidebar("Shopping",6);
		shopping.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_online_store_30px.png")));
		add(shopping);
		items.add(shopping);
		shopping.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopping.active(items);
				Z.page = new Shopping();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}

			}

		});
		
		
		ItemSidebar inbox = new ItemSidebar("Inbox",4);
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
		
		ItemSidebar favorite = new ItemSidebar("Favorite",3);
		favorite.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_favorite_folder_30px.png")));
		add(favorite);
		items.add(favorite);
		favorite.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				favorite.active(items);
				Z.page = new MyFavorite();
				Z.pageSlide(1);

			}

		});
		
		ItemSidebar cart = new ItemSidebar("Cart",5);
		cart.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_shopping_cart_30px.png")));
		add(cart);
		items.add(cart);
		cart.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cart.active(items);
				Database.updateCart();
				Z.page = new MyCart();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
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
				Z.page = new MyPurchases();
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
				Database.isGuest = true;
				Z.page = new HomeOri();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {

					Z.pageSlide(2);
				}
				Z.sidebar = new GuestSidebar();
				Z.sidebarSlide(1);


			}

		});
		
		
	}

}
