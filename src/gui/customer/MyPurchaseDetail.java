package gui.customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import asystem.Database;
import asystem.Setup;
import gui.common.ViewItemDetail;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Item;
import main.ItemInCart;
import main.Review;

public class MyPurchaseDetail extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MyPurchaseDetail() {
		Setup.page(this);

		List<ItemInCart> itemList = Database.getPurchaseById(Database.currentPurchaseId).getItemList();
		List<JLabel> contentItem = new ArrayList<JLabel>(itemList.size());
		List<JButton> review = new ArrayList<JButton>(itemList.size());

		// table
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Item name", "Quantity", "Price", "" }, 0)) {
			/*@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}*/

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			// change item name color
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				if (column == 1) {
					comp.setForeground(new Color(0, 0, 255));
				} else
					comp.setForeground(Setup.colorPageText);
				return comp;

			}
		};

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Setup.table(table);

		// change text to button
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 400);
		sp.setLocation(37, 100);
		add(sp);

		// display item content
		for (int i = 0; i < itemList.size(); i++) {
			final int index = i;
			Item currentItem = Database.getItemSuperStockById(itemList.get(i).getItemID());
			model.addRow(new Object[] { i + 1, currentItem.getItemName(), itemList.get(i).getQuantity(),"$"+
					currentItem.getItemPrice() * itemList.get(i).getQuantity(), "Review" });
		}

		// find item that customer already reviewed
		for (int i = 0; i < itemList.size(); i++) {
			for (Review aReview : Database.getCustomerByID(Database.currentUserId).getMyReview()) {
				if (aReview.getItemId() == itemList.get(i).getItemID()) {
					table.setValueAt("Reviewed", i, 4);
					// reviewedList
				}
			}
		}

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 4 && table.getModel().getValueAt(row, 4).equals("Review")) {

					Database.currentItemId = itemList.get(row).getItemID();
					Z.page = new ReviewItem();
					Z.pageSlide(1);
				}
				if (column == 1) {
					Database.currentItemId = itemList.get(row).getItemID();
					Z.page = new ViewItemDetail();
					Z.pageSlide(1);
				}
			}
		});

		// "Reorder" button
		JButton reoder = new JButton("Reoder");
		Setup.buttonEffect(reoder);
		Setup.rightButton2(reoder);
		reoder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<ItemInCart> copyCart = new ArrayList<ItemInCart>(itemList); // shadow copy
				Database.getCustomerByID(Database.currentUserId).setCart(copyCart);
				Z.page = new MyCart();
				Z.pageSlide(2);

			}
		});
		add(reoder);

		// "Back" button
		JButton back = new JButton("Back");
		Setup.buttonEffect(back);
		Setup.leftButton2(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new MyPurchases();
				Z.pageSlide(2);

			}
		});
		add(back);

		// title
		JLabel lblMyPurchases = new JLabel("PURCHASE DETAIL");
		Setup.title2(lblMyPurchases);
		add(lblMyPurchases);
	}

}
