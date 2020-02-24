package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gui.component.Setup;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Database;
import main.Item;

import java.awt.Color;
import java.awt.Font;

public class AdminViewCustomers extends JFrame {
	public AdminViewCustomers() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		List<JButton> detail = new ArrayList<JButton>(Database.super_stock.size());
		List<JLabel> content = new ArrayList<JLabel>(Database.super_stock.size());

		// sidebar
		add(Setup.adminSidebar());

		// title
		JLabel lblListOfCustomers = new JLabel("CUSTOMER LIST");
		Setup.title3(lblListOfCustomers);

		getContentPane().add(lblListOfCustomers);

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
					dispose();
					Database.currentUserId = Database.customer_list.get(row).getId();
					new AdminViewCustomerDetail().setVisible(true);
				} else if (column == 4) {
					dispose();
					Database.currentUserId = Database.customer_list.get(row).getId();
					Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
					Database.customer_list.remove(currentCustomer);
					new AdminViewCustomers().setVisible(true);
				}
			}
		});

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 400);
		sp.setLocation(337, 100);
		add(sp);

		// display content of each customer in database
		if (!Database.customer_list.isEmpty()) {
			for (int i = 0; i < Database.customer_list.size(); i++) {
				final int index = i;
				Customer customer = Database.customer_list.get(i);

				model.addRow(new Object[] { customer.getId(), customer.getUsername(), customer.getPassword(),
						"Detail", "Delete" });

			}
		}

		// "Back" button
		JButton back = new JButton("Back");
		Setup.middleButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminHome().setVisible(true);

			}
		});
		getContentPane().add(back);

		//Setup.mainFrame(this);
	}
}
