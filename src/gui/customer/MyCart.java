package gui.customer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.Z;
import gui.component.ConfirmDialog;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import gui.utility.GroupableColumnModel;
import gui.utility.GroupableTableHeader;
import main.Customer;
import main.Item;
import main.ItemInCart;
import main.Purchase;
import main.User;
import test.Test;
import test.TestGroups;


public class MyCart extends JPanel {

	/**
	 * Create the panel.
	 */
	Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
	List<ItemInCart> currentCart = currentCustomer.getCart();
	
	int sumUp = 0; // price in total to pay
	JLabel inTotal = new JLabel();

	public MyCart() {
		Setup.page(this);

		List<JButton> increase = new ArrayList<JButton>();
		List<JButton> decrease = new ArrayList<JButton>();
		List<JLabel> content = new ArrayList<JLabel>();
		//Customer currentCustomer = Database.customer_list.get(Database.currentUserId);
		

		JButton pay;
		JButton back;

		// table
		JTable table = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Price", "a", "Quantity", "b", "Total", "" }, 0)) {
			/*
			 * @Override public Class<?> getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		DefaultTableModel tm = new DefaultTableModel(0, 7);
		table.setModel(tm);

		table.setAutoCreateColumnsFromModel(false);
		table.setShowGrid(false);
		// table.setGridColor(Color.GRAY);
		GroupableColumnModel model = new GroupableColumnModel();
		model.addColumn(TestGroups.createColumn("Item", 0));
		model.addColumn(TestGroups.createColumn("Price", 1));
		model.addColumn(TestGroups.createColumn("+", 2));
		model.addColumn(TestGroups.createColumn("", 3));
		model.addColumn(TestGroups.createColumn("-", 4));
		model.addColumn(TestGroups.createColumn("Total", 5));
		model.addColumn(TestGroups.createColumn(" ", 6));

		GroupableColumnModel.IColumnGroup groupA = model.addGroup("Quantity");
		groupA.addColumn(model.getColumn(model.getColumnIndex("+")));
		groupA.addColumn(model.getColumn(model.getColumnIndex("")));
		groupA.addColumn(model.getColumn(model.getColumnIndex("-")));

		table.setColumnModel(model);
		table.setTableHeader(new GroupableTableHeader(table.getColumnModel()));
		table.setAutoCreateRowSorter(true);

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(240);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);

		DefaultTableModel model2 = (DefaultTableModel) table.getModel();

		Setup.table(table);

		// change text to button
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 300);
		sp.setLocation(37, 140);

		// TODO: update cart when admin removes item or decreases number item in stock
		if (!currentCart.isEmpty()) {

			for (int i = 0; i < currentCart.size(); i++) {
				final int index = i;
				Item currentItem = Database.getItemSuperStockById(currentCart.get(i).getItemID());
				int quantity = currentCart.get(i).getQuantity();
				int totalPrice = quantity * currentItem.getItemPrice();
				sumUp += totalPrice;

				model2.addRow(new Object[] { currentItem.getItemName(), "$" + currentItem.getItemPrice(), "+", quantity,
						"-", "$" + totalPrice, "Remove" });

			}
		}

		

		if (sumUp > 0) {
			// price in total
			JLabel inTotalLbl = new JLabel("Subtotal:");
			inTotalLbl.setFont(new Font(Setup.font, Font.PLAIN, 15));
			// inTotal.setForeground(new Color(179, 161, 27));
			inTotalLbl.setBounds(395, 490, 200, 40);
			add(inTotalLbl);

			// price in total
			inTotal = new JLabel("$" + sumUp);
			inTotal.setFont(new Font(Setup.font, Font.PLAIN, 30));
			// inTotal.setForeground(new Color(179, 161, 27));
			inTotal.setBounds(465, 486, 200, 40);
			add(inTotal);
			add(sp);
			
			//PAY
			JButton shopping = new JButton("Keep shopping");
			Setup.buttonEffect(shopping);
			Setup.leftButton2(shopping);
			add(shopping);
			shopping.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Z.page = new Shopping();
					Z.pageSlide(2);

				}
			});
			
			//PAY
			pay = new JButton("Pay");
			Setup.buttonEffect(pay);
			Setup.rightButton2(pay);
			add(pay);

			
			// final int amount = sumUp;
			pay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (sumUp > 0) {
						List<ItemInCart> copyCart = new ArrayList<ItemInCart>(currentCart); // shadow copy
						// create new purchase
						Purchase newPurchase = new Purchase(Database.countPurchaseId++, currentCustomer.getId(),
								sumUp);
						newPurchase.setItemList(copyCart);
						currentCustomer.getPurchaseList().add(newPurchase);
						Database.purchase_list.add(newPurchase);
						
						Database.updateStockAfterPurchase(copyCart);
						Database.addPurchase(newPurchase);

						// TODO: create update stock method
						for (int i = 0; i < currentCart.size(); i++) {
							Item currentItem = Database.getItemSuperStockById(currentCart.get(i).getItemID());
							//add current customer to buyer list of each item in cart
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							   LocalDateTime now = LocalDateTime.now();  
							   
							//this.time = dtf.format(now);
							currentItem.getBuyer_list().put(currentCustomer, dtf.format(now));
							//update stock after purchase				
							int quantity = currentCart.get(i).getQuantity();
							currentItem.setInStock(currentItem.getInStock() - quantity);
							
							
						}


						Z.page = new HomeOri();
						Z.pageSlide(2);

						currentCart.clear();
						Database.clearCart(currentCustomer);
					}

				}
			});
			
			

			// setup "Back" button
			// Setup.buttonEffect(b1);
		
		} else {
			

			JLabel lblCartIsEmpty = new JLabel("Your cart is empty");
			lblCartIsEmpty.setForeground(new Color(178, 34, 34));
			lblCartIsEmpty.setFont(new Font(Setup.font, Font.PLAIN, 22));
			lblCartIsEmpty.setBounds(213, 382, 196, 29);
			add(lblCartIsEmpty);

			JLabel lblAddSomethingTo = new JLabel("Add something to make her happy");
			lblAddSomethingTo.setForeground(Setup.colorPageText);
			lblAddSomethingTo.setFont(new Font(Setup.font, Font.PLAIN, 15));
			lblAddSomethingTo.setBounds(139, 424, 250, 29);
			add(lblAddSomethingTo);

			JButton btnGoShopping = new JButton("Go shopping");
			Setup.buttonEffect(btnGoShopping);
			btnGoShopping.setBounds(380, 427, 116, 24);
			add(btnGoShopping);
			btnGoShopping.setFont(new Font(Setup.font, Font.BOLD, 11));
			btnGoShopping.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					

					Z.page = new Shopping();
					Z.pageSlide(1);

				}
			});

			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(MyCart.class.getResource("/images/emptyCart2.png")));
			label.setBounds(190, 146, 244, 231);
			add(label);

		}

		// title
		JLabel lblMyCart = new JLabel("MY CART");
		Setup.title2(lblMyCart);
		add(lblMyCart);

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {

				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());

				Item currentItem = Database.getItemSuperStockById(currentCart.get(row).getItemID());
				int quantity = currentCart.get(row).getQuantity();
				int totalPrice = quantity * currentItem.getItemPrice();

				// + button
				if (column == 2) {
					if (currentItem.getInStock() < quantity + 1) {
						JOptionPane.showMessageDialog(null, "Out of stock", "", JOptionPane.INFORMATION_MESSAGE);
					} else {

						currentCart.get(row).setQuantity(quantity + 1);
						Database.updateQuantityItemInCart(currentCart.get(row), quantity+1);
						model2.setValueAt(quantity + 1, row, 3);
						model2.setValueAt("$" + (totalPrice + currentItem.getItemPrice()), row, 5);
						sumUp += currentItem.getItemPrice();
						;
					}
					// - button
				} else if (column == 4) {
					sumUp -= currentItem.getItemPrice();
					if (quantity == 1) {
						new ConfirmDialog("Remove item?") {
							@Override
							public void confirmAction() {
								Database.removeItemInCart(currentCart.get(row));
								currentCart.remove(row);			
								model2.removeRow(row);
							}
						};
						
					} else {
						currentCart.get(row).setQuantity(quantity - 1);
						Database.updateQuantityItemInCart(currentCart.get(row), quantity-1);
						model2.setValueAt(quantity - 1, row, 3);
						model2.setValueAt("$" + (totalPrice - currentItem.getItemPrice()), row, 5);
					}
					//remove item in cart
				} else if (column == 6) {
					new ConfirmDialog("Remove item?") {
						@Override
						public void confirmAction() {
							Database.removeItemInCart(currentCart.get(row));
							currentCart.remove(row);			
							model2.removeRow(row);
							sumUp -= quantity * currentItem.getItemPrice();
						}
					};
					
				}
				inTotal.setText("$" + sumUp);

			}
		});
		
		
	}
	
	

}
