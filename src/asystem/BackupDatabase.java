package asystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.ImageIcon;

import main.Account;
import main.Admin;
import main.Customer;
import main.Inbox;
import main.Item;
import main.ItemInCart;
import main.Message;
import main.Purchase;
import main.Retailer;
import main.Review;
import main.User;

import java.awt.Color;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
	// TODO: save setting data
	// TODO: change to private and add setters, getters
	// TODO: get objects by id

	// account, item, purchase lists
	public static List<Customer> customer_list = new ArrayList<Customer>();
	public static List<Retailer> retailer_list = new ArrayList<Retailer>();
	public static List<Admin> admin_list = new ArrayList<Admin>();
	public static List<Item> super_stock = new ArrayList<Item>();
	public static List<Item> promotion = new ArrayList<Item>();
	public static List<Item> best_selling = new ArrayList<Item>();
	public static List<Purchase> purchase_list = new ArrayList<Purchase>();

	// id counter
	public static int countItemId;
	public static int countUserId;
	public static int countPurchaseId;
	public static int countReviewId;
	public static int countItemInCartId;

	// control system
	public static AccountType login_as;
	public static AccountType register_as;
	public static boolean isGuest = true;
	public static int currentItemId = 1;
	public static int currentUserId;
	public static int currentPurchaseId;
	public static int currentItemInCartId;
	public static String state = "Home";

	/******** SETUP DATABASE ********/
	public static Connection myConn;

	public static void connectDatabase() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("mysql/database.properties"));
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");

		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("Database connection successful to: " + dburl);
	}

	public static void initDatabase() {
		System.out.println("LOADING DATABASE...");
		loadSound();
		loadRetailer();
		loadCustomer();
		loadSuperStock();
		initIdCounter();
	}

	public static void saveDatabase() {
		saveIdCounter();
		saveSetting();
	}

	/******** ID COUNTER ********/
	public static void initIdCounter() {
		System.out.println("Initializing ID counters...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("select * from count_id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Database.countUserId = rs.getInt(2);
				Database.countItemId = rs.getInt(3);
				Database.countPurchaseId = rs.getInt(4);
				Database.countReviewId = rs.getInt(5);
				Database.countItemInCartId = rs.getInt(6);
			}
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// TODO
	public static void saveIdCounter() {
		System.out.println("Saving ID counters...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement(
					"update count_id set user=?, item=?, purchase=?, review=?, itemInCart=? where id=1");
			stmt.setInt(1, Database.countUserId);
			stmt.setInt(2, Database.countItemId);
			stmt.setInt(3, Database.countPurchaseId);
			stmt.setInt(4, Database.countReviewId);
			stmt.setInt(5, Database.countItemInCartId);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void saveSetting() {
		System.out.println("Saving setting...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement(
					"update count_id set user=?, item=?, purchase=?, review=?, itemInCart=? where id=1");
			stmt.setInt(1, Database.countUserId);
			stmt.setInt(2, Database.countItemId);
			stmt.setInt(3, Database.countPurchaseId);
			stmt.setInt(4, Database.countReviewId);
			stmt.setInt(5, Database.countItemInCartId);
			// stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** CUSTOMER ********/
	public static Customer getCustomerByID(int id) {
		if (!Database.customer_list.isEmpty()) {
			for (int i = 0; i < Database.customer_list.size(); i++) {
				if (id == Database.customer_list.get(i).getId()) {
					return Database.customer_list.get(i);
				}
			}
		}
		return new Customer();
	}

	// get id user by its username
	@SuppressWarnings("unchecked")
	public static int getIdUserByUsername(String username) {
		List<?> list = new ArrayList<>();
		if (Database.login_as == AccountType.CUSTOMER) {
			list = Database.customer_list;
		} else if (Database.login_as == AccountType.RETAILER) {
			list = Database.retailer_list;
		}
		if (!list.isEmpty()) {

			for (int i = 0; i < list.size(); i++) {
				if (username.equals(((List<User>) (List<?>) list).get(i).getUsername())) {
					return ((List<User>) (List<?>) list).get(i).getId();
				}
			}
		}
		return 0;

	}

	// register new customer
	public static User register(String usernameInput, String passwordInput) {
		User newUser = new User();
		if (Database.register_as == AccountType.CUSTOMER) {
			newUser = new Customer(usernameInput, passwordInput, Database.countUserId++);
			Database.customer_list.add((Customer) newUser);

		} else if (Database.register_as == AccountType.RETAILER) {
			newUser = new Retailer(usernameInput, passwordInput, Database.countUserId++);
			Database.retailer_list.add((Retailer) newUser);

		}
		return newUser;

	}

	// check if username exists in database, used by register()
	public static boolean usernameAlreadyExists(String usernameInput) {
		if (!Database.customer_list.isEmpty()) {
			for (int i = 0; i < Database.customer_list.size(); i++) {
				if (usernameInput.equals(Database.customer_list.get(i).getUsername())) {
					return true;
				}
			}

		}

		if (!Database.retailer_list.isEmpty()) {
			for (int i = 0; i < Database.retailer_list.size(); i++) {
				if (usernameInput.equals(Database.retailer_list.get(i).getUsername())) {
					return true;
				}
			}

		}
		return false;
	}

	public static boolean loginIsSuccessful(String usernameInput, char[] passwordInput) {
		List<?> list = new ArrayList<>();
		if (Database.login_as == AccountType.ADMIN) {
			list = Database.admin_list;
		} else if (Database.login_as == AccountType.CUSTOMER) {
			list = Database.customer_list;
		} else {
			list = Database.retailer_list;
		}

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				char[] correctPassword = ((Account) list.get(i)).getPassword().toCharArray();
				if (usernameInput.equals(((Account) list.get(i)).getUsername())
						&& Arrays.equals(passwordInput, correctPassword)) {
					Arrays.fill(correctPassword, (char) 1); // hide the password for security
					return true;
				}
			}
		}
		return false;
	}

	public static void loadCustomer() {
		System.out.println("Loading customers...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("select * from customer");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				// TODO: avatar
				// customer.setAvt(new ImageIcon(Database.class.getResource(rs.getString(4))));
				loadCart(customer);
				loadSubscriptions(customer);
				loadFavorites(customer);
				loadPurchases(customer);
				loadCustomerInboxes(customer);
				// TODO: load review
				Database.customer_list.add(customer);
			}
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void registerCustomer(Customer customer) {
		System.out.println("Creating new customer...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("insert into customer values(?,?,?,?)");
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.setInt(3, customer.getId());
			stmt.setString(4, "/picture/avt/icons8_male_user_100px.png");
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void removeCustomer(int Id) {
		System.out.println("Removing customer...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("delete from customer where customerId=" + Id);
			// TODO: remove cart, item in cart, purchase, review, inbox, favorite
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** CART ********/
	public static void loadCart(Customer customer) {
		System.out.println("Loading carts...");
		try {
			connectDatabase();
			// load cart
			PreparedStatement stmt2 = myConn
					.prepareStatement("select * from item_in_cart where customerId=" + customer.getId());
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				customer.getCart().add(new ItemInCart(rs2.getInt(1), rs2.getInt(2), rs2.getInt(3), rs2.getInt(4)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addItemInCart(ItemInCart item) {
		System.out.println("Adding item to cart...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("insert into item_in_cart (itemId, quantity, customerId) values(?,?,?)");
			stmt.setInt(1, item.getItemID());
			stmt.setInt(2, item.getQuantity());
			stmt.setInt(3, item.getCustomerId());
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void removeItemInCart(ItemInCart item) {
		System.out.println("Removing item from cart...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("delete from item_in_cart where itemId=" + item.getItemID()
					+ " and customerId=" + item.getCustomerId());
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateQuantityItemInCart(ItemInCart item, int quantity) {
		System.out.println("Updating item quantity in cart...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("update item_in_cart set quantity=? where itemId="
					+ item.getItemID() + " and customerId=" + item.getCustomerId());
			stmt.setInt(1, quantity);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void clearCart(Customer customer) {
		System.out.println("Clearing cart...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("delete from item_in_cart where customerId=" + customer.getId());
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** PURCHASE ********/
	public static Purchase getPurchaseById(int id) {
		if (!Database.purchase_list.isEmpty()) {
			for (int i = 0; i < Database.purchase_list.size(); i++) {
				if (id == Database.purchase_list.get(i).getPurchaseId()) {
					return Database.purchase_list.get(i);
				}
			}
		}
		return new Purchase();
	}

	public static void loadPurchases(Customer customer) {
		System.out.println("Loading purchases...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("select * from purchase where customerId=" + customer.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				purchase.setTime(rs.getString(4));
				loadPurchaseDetail(purchase);
				Database.purchase_list.add(purchase);
				customer.getPurchaseList().add(purchase);
			}
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void loadPurchaseDetail(Purchase purchase) {
		System.out.println("Loading purchase detail...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("select * from item_in_purchase where purchaseId=" + purchase.getPurchaseId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ItemInCart item = new ItemInCart(rs.getInt(2), rs.getInt(3));
				purchase.getItemList().add(item);
			}
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addPurchase(Purchase purchase) {
		System.out.println("Creating new purchase...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("insert into purchase values(?,?,?,?)");
			stmt.setInt(1, purchase.getPurchaseId());
			stmt.setInt(2, purchase.getCustomerId());
			stmt.setInt(3, purchase.getAmount());
			stmt.setString(4, purchase.getTime());
			addPurchaseDetail(purchase);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// TODO
	public static void addPurchaseDetail(Purchase purchase) {
		System.out.println("Adding purchase detail...");
		for (ItemInCart item : purchase.getItemList()) {
			try {
				connectDatabase();
				PreparedStatement stmt = myConn.prepareStatement(
						"insert into item_in_purchase (itemId, amount, purchaseId, isReviewed) values(?,?,?,?)");
				stmt.setInt(1, item.getItemID());
				stmt.setInt(2, item.getQuantity());
				stmt.setInt(3, purchase.getPurchaseId());
				stmt.setInt(4, 0);
				stmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/******** RETAILER ********/
	public static Retailer getRetailerByID(int id) {
		List<Retailer> list = Database.retailer_list;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (id == list.get(i).getId()) {
					return list.get(i);
				}
			}
		}
		return new Retailer();
	}

	public static void loadRetailer() {
		System.out.println("Loading retailers...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("select * from retailer");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Retailer ID: " + rs.getInt(3));
				Retailer retailer = new Retailer(rs.getString(1), rs.getString(2), rs.getInt(3));
				retailer.setAvt(new ImageIcon(Database.class.getResource(rs.getString(4))));
				Database.retailer_list.add(retailer);
				loadStock(retailer);
				loadSubscribers(retailer);
				loadRetailerInboxes(retailer);
			}
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void registerRetailer(Retailer retailer) {
		System.out.println("Registering new retailer...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("insert into retailer values(?,?,?,?)");
			stmt.setString(1, retailer.getUsername());
			stmt.setString(2, retailer.getPassword());
			stmt.setInt(3, retailer.getId());
			stmt.setString(4, "/picture/avt/icons8_shop_100px.png");
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void removeRetailer(int Id) {
		System.out.println("Removing retailer...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("delete from retailer where retailerId=" + Id);
			// TODO: remove item, review, inbox, subscribe, favorite
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** STOCK ********/

	public static Item getItemSuperStockById(int id) {
		List<Item> list = Database.super_stock;
		for (int i = 0; i < list.size(); i++) {
			if (id == list.get(i).getItemID()) {
				return list.get(i);
			}
		}
		return new Item();
	}

	public static Item getItemStockByID(Retailer retailer, int id) {
		List<Item> list = retailer.getMy_stock();
		for (int i = 0; i < list.size(); i++) {
			if (id == list.get(i).getItemID()) {
				return list.get(i);
			}
		}
		return new Item();
	}

	public static void loadSuperStock() {
		System.out.println("Loading super stock...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("select * from item");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7));
				item.setIcon(new ImageIcon(Database.class.getResource(rs.getString(8))));

				Database.super_stock.add(item);
				if (rs.getString(9) != null) {
					item.setPromo(Sale.getSaleByContent(rs.getString(9)));
					Database.promotion.add(item);
				}

				// load review list of each item
				PreparedStatement review = myConn.prepareStatement("select * from review where itemId=" + rs.getInt(1));
				ResultSet reviewRs = review.executeQuery();
				while (reviewRs.next()) {
					item.getReviewList().add(new Review(reviewRs.getInt(1), reviewRs.getString(2),
							reviewRs.getString(3), reviewRs.getInt(4), reviewRs.getInt(5), reviewRs.getInt(6)));
				}
			}
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void loadStock(Retailer retailer) {
		System.out.println("Loading stock...");
		try {
			connectDatabase();
			PreparedStatement stmt2 = myConn
					.prepareStatement("select * from item where retailerId=" + retailer.getId());
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				Item item = new Item(rs2.getInt(1), rs2.getInt(2), rs2.getString(3), rs2.getString(4), rs2.getString(5),
						rs2.getInt(6), rs2.getInt(7));
				item.setPromo(null);
				retailer.getMy_stock().add(item);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateStockAfterPurchase(List<ItemInCart> cart) {
		System.out.println("Updating stock after purchase...");
		for (ItemInCart item : cart) {
			try {
				connectDatabase();
				PreparedStatement stmt = myConn
						.prepareStatement("update item set stock =? where itemId=" + item.getItemID());
				int oldStock = Database.getItemSuperStockById(item.getItemID()).getInStock();
				int newStock = oldStock - item.getQuantity();
				System.out.println("Before: " + oldStock);
				System.out.println("After: " + newStock);
				stmt.setInt(1, newStock);
				stmt.executeUpdate();
				myConn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void addItem(Item item) {
		System.out.println("Adding new item...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("insert into item values(?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, item.getItemID());
			stmt.setInt(2, item.getRetailerId());
			stmt.setString(3, item.getDescription());
			stmt.setString(4, item.getItemName());
			stmt.setString(5, item.getCategory());
			stmt.setInt(6, item.getItemPrice());
			stmt.setInt(7, item.getInStock());
			stmt.setString(8, "/picture/item/unavailable.png");
			if (item.getPromo() != null) {
				stmt.setString(9, item.getPromo().getContent());
			} else {
				stmt.setString(9, null);
			}
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void removeItem() {
		System.out.println("Removing item...");
		// TODO: remove reviews, update carts and favorite of all customers

	}

	public static void updateItem(Item item) {
		System.out.println("Updating item...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement(
					"update item set description=?, name=?, category=?, price=?, stock =?, image=? where itemId="
							+ item.getItemID());
			stmt.setString(1, item.getDescription());
			stmt.setString(2, item.getItemName());
			stmt.setString(3, item.getCategory());
			stmt.setInt(4, item.getItemPrice());
			stmt.setInt(5, item.getInStock());
			stmt.setString(6, "/picture/item/unavailable.png");
			// TODO: update carts of all customer when number in stock decreases
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** SUBSCRIBE ********/
	public static void loadSubscriptions(Customer customer) {
		System.out.println("Loading subscriptions...");
		try {
			connectDatabase();
			PreparedStatement stmt3 = myConn
					.prepareStatement("select * from subscribe where customerId=" + customer.getId());
			ResultSet rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				customer.getMy_subscription_id().add(rs3.getInt(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void loadSubscribers(Retailer retailer) {
		System.out.println("Loading subscribers...");
		try {
			connectDatabase();
			PreparedStatement stmt3 = myConn
					.prepareStatement("select * from subscribe where retailerId=" + retailer.getId());
			ResultSet rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				retailer.getMy_subscriber_id_list().add(rs3.getInt(2));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addSubscribe(int customerId, int retailerId) {
		System.out.println("Adding new subscription...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("insert into subscribe (customerId, retailerId) values(?,?)");
			stmt.setInt(1, customerId);
			stmt.setInt(2, retailerId);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void removeSubscribe(int customerId, int retailerId) {
		System.out.println("Removing subscription...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement(
					"delete from subscribe where customerId=" + customerId + " and retailerId=" + retailerId);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** FAVORITE ********/
	public static void loadFavorites(Customer customer) {
		System.out.println("Loading favorites...");
		try {
			connectDatabase();
			PreparedStatement stmt3 = myConn
					.prepareStatement("select * from favorite where customerId=" + customer.getId());
			ResultSet rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				customer.getMy_favorite_id().add(rs3.getInt(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void addFavorite(int customerId, int itemId) {
		System.out.println("Adding new favorite...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("insert into favorite (customerId, itemId) values(?,?)");
			stmt.setInt(1, customerId);
			stmt.setInt(2, itemId);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void removeFavorite(int customerId, int itemId) {
		System.out.println("Removing favorite...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("delete from favorite where customerId=" + customerId + " and itemId=" + itemId);
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/******** INBOX ********/
	public static void loadCustomerInboxes(Customer customer) {
		System.out.println("Loading customer inboxes...");
		try {
			connectDatabase();
			PreparedStatement stmt3 = myConn
					.prepareStatement("select * from inbox where customerId=" + customer.getId());
			ResultSet rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				Inbox inbox = new Inbox(rs3.getInt(2), rs3.getInt(3));
				loadMessages(inbox);
				customer.getMy_inbox().add(inbox);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void loadRetailerInboxes(Retailer retailer) {
		System.out.println("Loading retailer inboxes...");
		try {
			connectDatabase();
			PreparedStatement stmt3 = myConn
					.prepareStatement("select * from inbox where retailerId=" + retailer.getId());
			ResultSet rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				System.out.println("Inbox label: " + rs3.getString(2) + "&" + rs3.getInt(3));
				Inbox inbox = new Inbox(rs3.getInt(2), rs3.getInt(3));
				loadMessages(inbox);
				retailer.getMy_inbox().add(inbox);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void loadMessages(Inbox inbox) {
		System.out.println("Loading messages...");
		try {
			connectDatabase();
			PreparedStatement stmt3 = myConn
					.prepareStatement("select * from message where label like '%" + inbox.getLabel() + "%'");
			ResultSet rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				Message message = new Message(rs3.getInt(3), rs3.getString(4));
				message.setTime(rs3.getString(5));
				inbox.getMessages().add(message);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void addInbox(Inbox inbox) {
		System.out.println("Adding new inbox...");
		try {
			connectDatabase();
			PreparedStatement stmt = myConn.prepareStatement("insert into inbox values(?,?,?)");
			stmt.setString(1, inbox.getLabel());
			stmt.setInt(2, inbox.getCustomerId());
			stmt.setInt(3, inbox.getRetailerId());
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addMessage(Message message, String label) {
		System.out.println("Adding new message...");

		try {
			connectDatabase();
			PreparedStatement stmt = myConn
					.prepareStatement("insert into message (label, senderId, content, time) values(?,?,?,?)");
			stmt.setString(1, label);
			stmt.setInt(2, message.getSenderId());
			stmt.setString(3, message.getContent());
			stmt.setString(4, message.getTime());
			stmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void loadSound() {
		System.out.println("Loading sounds...");
		Setup.playSound("click.wav");
		Setup.playSound("click2.wav");
		Setup.playSound("error.wav");
		Setup.mute = false;
	}

	/********* UPDATE AFTER REMOVING-CHANGING **********/
	// TODO: after removing retailer/customer, purchasing

	// update cart when the stock changes, remove item, purchase
	public static void updateCart() {
		List<Customer> list = Database.customer_list;
		if (!list.isEmpty()) {
			for (int k = 0; k < list.size(); k++) {
				List<ItemInCart> cart = list.get(k).getCart();
				if (!cart.isEmpty()) {
					for (int i = 0; i < cart.size(); i++) {
						boolean itemInStock = false;
						for (int j = 0; j < Database.super_stock.size(); j++) {
							if (cart.get(i).getItemID() == Database.super_stock.get(j).getItemID()) {
								itemInStock = true;
							}
						}
						if (!itemInStock) {
							cart.remove(i);
						}
					}
				}
			}
		}

	}

}