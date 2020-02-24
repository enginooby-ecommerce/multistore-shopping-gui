package gui.admin;

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
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Retailer;

public class MyRetailers extends JPanel {

	/**
	 * Create the panel.
	 */
	public MyRetailers() {
		Setup.page(this);
		
		List<Retailer> list = Database.retailer_list;
		
		List<JButton> detail = new ArrayList<JButton>(Database.super_stock.size());
		List<JLabel> content = new ArrayList<JLabel>(Database.super_stock.size());

		
		// title
		JLabel lblListOfCustomers = new JLabel("RETAILER LIST");
		Setup.title2(lblListOfCustomers);

		add(lblListOfCustomers);

		// table
		JTable table = new JTable(new DefaultTableModel(new Object[] { "ID", "Username", "Password", "", "" }, 0)) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

	Setup.table(table);

		// change text to button
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());

				if (column == 3) {
					Database.currentUserId = list.get(row).getId();
					Z.page = new MyRetailerDetail();
					Z.pageSlide(2);
				} else if (column == 4) {
					//Database.currentUserId = list.get(row).getId();
					//Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
					//Database.customer_list.remove(currentCustomer);
					
					//Database.user_list.remove(list.get(row));
					list.remove(list.get(row));
					
					
					model.removeRow(row);
				}
			}
		});

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 400);
		sp.setLocation(37, 100);
		add(sp);

		// display content of each customer in database
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				final int index = i;
				Retailer retailer = list.get(i);

				model.addRow(new Object[] { retailer.getId(), retailer.getUsername(), retailer.getPassword(),
						"Detail", "Delete" });

			}
		}

		
	}

}
