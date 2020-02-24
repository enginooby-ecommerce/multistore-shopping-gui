package gui.customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.ViewItemDetail;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Item;

public class MyFavorite extends JPanel {

	/**
	 * Create the panel.
	 */
	Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
	List<Item> list = currentCustomer.getMyFavoriteList();

	public MyFavorite() {
		Setup.page(this);

		// table
		JTable table = new JTable(new DefaultTableModel(new Object[] { "Item", "Price", "Stock", "", "" }, 0)) {
			/*
			 * @Override public Class<?> getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			
		};

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(230);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Setup.table(table);

		// change text to button
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 360);
		sp.setLocation(37, 130);

		add(sp);

		// display content of each item in stock
		if (!list.isEmpty()) {
			for (int i = 0; i <list.size(); i++) {
				final int index = i;
				Item currentItem = list.get(i);

				model.addRow(new Object[] { currentItem.getItemName(),"$"+ currentItem.getItemPrice(),
						currentItem.getInStock(), "Detail", "Remove" });

			}
		}

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 4) {
					int currentItemId = list.get(row).getItemID();
					list.remove(row);
					currentCustomer.getMy_favorite_id().remove((Integer)currentItemId);
					model.removeRow(row);
					Database.removeFavorite(Database.currentUserId, currentItemId);
				}  else if (column == 3) {
					Database.currentItemId = list.get(row).getItemID();
					Z.page = new ViewItemDetail();
					Z.pageSlide(1);
				}
			}
		});

		// title
		JLabel lblStore = new JLabel("MY FAVORITE");
		Setup.title2(lblStore);
		add(lblStore);
	}

}
