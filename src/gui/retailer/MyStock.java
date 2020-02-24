package gui.retailer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import asystem.Database;
import asystem.Setup;
import gui.common.Z;
import gui.component.ConfirmDialog;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Item;
import main.Retailer;
import main.User;

public class MyStock extends JPanel {

	/**
	 * Create the panel.
	 */
	public MyStock() {

		Setup.page(this);
		List<JButton> modify = new ArrayList<JButton>(Database.super_stock.size());
		List<JButton> delete = new ArrayList<JButton>(Database.super_stock.size());
		List<JLabel> content = new ArrayList<JLabel>(Database.super_stock.size());
		List<Item> stock = Database.getRetailerByID(Database.currentUserId).getMy_stock();

		// title
		JLabel lblStock = new JLabel("STOCK");
		Setup.title2(lblStock);
		add(lblStock);

		// table
		JTable table = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Category", "Price", "In stock", "", "" }, 0)) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Setup.table(table);

		// change text to button
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 400);
		sp.setLocation(37, 100);

		add(sp);

		// display content of each item in stock
		if (!stock.isEmpty()) {
			for (int i = 0; i < stock.size(); i++) {
				final int index = i;

				Item currentItem =stock.get(i);

				model.addRow(new Object[] { currentItem.getItemName(), currentItem.getCategory(),
						"$"+currentItem.getItemPrice(), currentItem.getInStock(), "Update", "Remove" });

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

					//Database.currentItemId = Database.super_stock.get(row).getItemID();
					Database.currentItemId = stock.get(row).getItemID();
					Z.page = new ModifyItem();
					Z.pageSlide(1);
				} else if (column == 5) {
					new ConfirmDialog("Remove item?") {
						@Override
						public void confirmAction() {
							Database.currentItemId = stock.get(row).getItemID();
							stock.remove(stock.get(row));
							Database.super_stock.remove(Database.getItemSuperStockById(Database.currentItemId));
							model.removeRow(row);
						}
					};
					
				}
			}
		});

		// "Add new" button
		JButton addNew = new JButton("Add new");
		Setup.buttonEffect(addNew);
		Setup.middleButton2(addNew);
		add(addNew);
		addNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Z.page = new AddNewItem();
				Z.pageSlide(1);
			}
		});

		
	}

}
