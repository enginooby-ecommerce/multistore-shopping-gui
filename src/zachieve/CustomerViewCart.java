package zachieve;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gui.ButtonEditor;
import gui.ButtonRenderer;
import gui.Setup;
import main.Customer;
import main.Database;
import main.Item;
import main.ItemInCart;
import main.Purchase;

import java.awt.Font;
import java.awt.Color;

public class CustomerViewCart extends JFrame {
	public CustomerViewCart() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		List<JButton> increase = new ArrayList<JButton>();
		List<JButton> decrease = new ArrayList<JButton>();
		List<JLabel> content = new ArrayList<JLabel>();
		Customer currentCustomer = Database.customer_list.get(Database.currentUserId);
		List<ItemInCart> currentCart = currentCustomer.getCart();
		int sumUp = 0; // price in total to pay
		JLabel inTotal;
		JButton pay;
		JButton back;

		// sidebar
		getContentPane().add(Setup.customerSidebar());

		// table
		JTable table = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Price", "", "Quatity", "", "Total", "" }, 0)) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// disable dragging columns
		table.getTableHeader().setReorderingAllowed(false);

		// center header
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));

		// center cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// change text to button
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 360);
		sp.setLocation(337, 100);

		// TODO: update cart when admin removes item or decreases number item in stock
		//System.out.println(currentCart.size());
		System.out.println(currentCustomer.getUsername()+ " "+currentCart.size());
		if (!currentCart.isEmpty()) {
			
			for (int i = 0; i < currentCart.size(); i++) {
				final int index = i;
				Item currentItem = Database.getItemByID(currentCart.get(i).getItemID());
				int quality = currentCart.get(i).getQuality();
				int totalPrice = quality * currentItem.getItemPrice();
				sumUp += totalPrice;

				model.addRow(new Object[] { currentItem.getItemName(), currentItem.getItemPrice(), "+", quality, "-",
						totalPrice, "Remove" });

			}
		}

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {

				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());

				Item currentItem = Database.getItemByID(currentCart.get(row).getItemID());
				int quatity = currentCart.get(row).getQuality();
				int totalPrice = quatity * currentItem.getItemPrice();

				//+ button
				if (column == 2) {
					if (currentItem.getInStock() < quatity + 1) {
						JOptionPane.showMessageDialog(null, "Out of stock", "", JOptionPane.INFORMATION_MESSAGE);
					} else {
						// TODO: not reset frame. reason: button + works only one time
						currentCart.get(row).setQuality(quatity + 1);
						//((JFrame) (table.getTopLevelAncestor())).revalidate();
						((JFrame) (table.getTopLevelAncestor())).dispose();
						//dispose();
						new CustomerViewCart().setVisible(true);
					}
					//- button
				} else if (column == 4) {
					if (quatity == 1) {
						currentCart.remove(row);

					} else {
						currentCart.get(row).setQuality(quatity - 1);
					}
					dispose();
					new CustomerViewCart().setVisible(true);

				} else if (column == 6) {
					currentCart.remove(row);
					dispose();
					new CustomerViewCart().setVisible(true);
				}

			}
		});

		// "Back" button
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerHome().setVisible(true);

			}
		});
		getContentPane().add(back);

		// "Pay" Button
		if (sumUp > 0) {
			getContentPane().add(sp);
			pay = new JButton("Pay");
			Setup.rightButton(pay);
			getContentPane().add(pay);
			final int amount = sumUp;
			pay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					List<ItemInCart> copyCart = new ArrayList<ItemInCart>(currentCart); // shadow copy
					// create new purchase
					Purchase newPurchase = new Purchase(Database.countPurchaseId++, currentCustomer.getCustomerId(),
							 amount, copyCart);
					currentCustomer.getPurchaseList().add(newPurchase);
					Database.purchaseList.add(newPurchase);

					// TODO: create update stock method
					for (int i = 0; i < currentCart.size(); i++) {
						// final int index = i;
						Item currentItem = Database.getItemByID(currentCart.get(i).getItemID());
						int quality = currentCart.get(i).getQuality();
						currentItem.setInStock(currentItem.getInStock() - quality);
					}

					dispose();
					new CustomerHome().setVisible(true);
					currentCart.clear();

				}
			});

			// price in total
			inTotal = new JLabel("$" + sumUp);
			inTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
			// inTotal.setForeground(new Color(179, 161, 27));
			inTotal.setBounds(735, 470, 200, 40);
			getContentPane().add(inTotal);

			// setup "Back" button
			Setup.leftButton(back);
		} else {

			JLabel lblCartIsEmpty = new JLabel("Your cart is empty");
			lblCartIsEmpty.setForeground(new Color(178, 34, 34));
			lblCartIsEmpty.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblCartIsEmpty.setBounds(513, 382, 196, 29);
			getContentPane().add(lblCartIsEmpty);

			JLabel lblAddSomethingTo = new JLabel("Add something to make her happy");
			lblAddSomethingTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAddSomethingTo.setBounds(439, 424, 250, 29);
			getContentPane().add(lblAddSomethingTo);

			JButton btnGoShopping = new JButton("Go shopping");
			btnGoShopping.setBounds(680, 427, 116, 24);
			getContentPane().add(btnGoShopping);
			btnGoShopping.setFont(new Font("Tahoma", Font.BOLD, 11));
			// btnGoShopping.setForeground(Color.WHITE);
			// btnGoShopping.setBackground(Color.GRAY);
			btnGoShopping.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//((JFrame) (btnGoShopping.getTopLevelAncestor())).revalidate();
					((JFrame) (btnGoShopping.getTopLevelAncestor())).dispose();
					//dispose();
					new CustomerShopping().setVisible(true);

				}
			});

			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(CustomerViewCart.class.getResource("/images/emptyCart2.png")));
			label.setBounds(490, 146, 244, 231);
			getContentPane().add(label);

			Setup.middleButton(back);
		}

		// title
		JLabel lblMyCart = new JLabel("MY CART");
		Setup.title(lblMyCart);
		getContentPane().add(lblMyCart);


	//	Setup.mainFrame(this);

	}
}
