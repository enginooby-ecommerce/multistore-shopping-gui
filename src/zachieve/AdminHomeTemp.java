package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class AdminHomeTemp extends JFrame {
	int num = 5; //display number of newest customers

	public AdminHomeTemp() {
		getContentPane().setBackground(Color.LIGHT_GRAY);

		// sidebar
		getContentPane().add(Setup.adminSidebar());

		// title
		JLabel lblMenu = new JLabel("HOME");
		Setup.title3(lblMenu);
		getContentPane().add(lblMenu);

		// view promotion type

		// sort box
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSortBy.setBounds(727, 354, 75, 29);
		getContentPane().add(lblSortBy);

		String sort[] = { "Amount", "Time" };
		JComboBox comboBox = new JComboBox(sort);
		comboBox.setBounds(791, 354, 75, 29);
		comboBox.setEditable(true);
		comboBox.setSelectedItem("Select");
		comboBox.setEditable(false);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				if (option.equals("Amount")) {
					Setup.underDev();
				} else if (option.equals("Time")) {
					Setup.underDev();
				}
			}
		});

		JLabel lblBestSeller = new JLabel("NEW CUSTOMERS");
		lblBestSeller.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBestSeller.setBounds(339, 118, 200, 48);
		getContentPane().add(lblBestSeller);

		JLabel lblPromotion = new JLabel("NEW PURCHASES");
		lblPromotion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPromotion.setBounds(339, 347, 200, 48);
		getContentPane().add(lblPromotion);

		// best selling table
		JTable table1 = new JTable(
				new DefaultTableModel(new Object[] { "Username", "Password", "Registration time", "" }, 0)) {
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
		table1.setBounds(30, 40, 200, 300);
		table1.setRowHeight(30);
		table1.getColumnModel().getColumn(0).setPreferredWidth(210);
		table1.getColumnModel().getColumn(1).setPreferredWidth(210);
		table1.getColumnModel().getColumn(2).setPreferredWidth(250);
		table1.getColumnModel().getColumn(3).setPreferredWidth(120);

		DefaultTableModel model = (DefaultTableModel) table1.getModel();

		// disable dragging columns
		table1.getTableHeader().setReorderingAllowed(false);

		// center header
		((DefaultTableCellRenderer) table1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table1.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));

		// center cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table1.getColumnCount(); i++) {
			table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// change text to button
		table1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table1);
		sp.setSize(530, 150);
		sp.setLocation(336, 171);

		getContentPane().add(sp);

		JLabel viewType = new JLabel("View");
		viewType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		viewType.setBounds(654, 128, 52, 29);
		getContentPane().add(viewType);

		JLabel lblNewestCustomers = new JLabel("newest customers");
		lblNewestCustomers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewestCustomers.setBounds(746, 128, 135, 29);
		getContentPane().add(lblNewestCustomers);
		
		List<Customer> list = Database.customer_list;
		String view[] = { "5", "10", "15" };
		JComboBox comboBox2 = new JComboBox(view);
		comboBox2.setBounds(692, 129, 48, 29);
		// comboBox2.setEditable(true);
		// comboBox2.setSelectedItem("10");
		// comboBox2.setEditable(false);
		getContentPane().add(comboBox2);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox2.getItemAt(comboBox2.getSelectedIndex()) + "";
				((DefaultTableModel) table1.getModel()).setNumRows(0);
				table1.revalidate();
				table1.repaint();
				if (option.equals("5")) {
					num = 5;

				} else if (option.equals("10")) {
					num = 10;

				} else if (option.equals("15")) {
					num = 15;

				}
				if (!Database.customer_list.isEmpty()) {
					for (int i = list.size() - 1; i > list.size() - num - 1 && i > -1; i--) {

						model.addRow(
								new Object[] { list.get(i).getUsername(), list.get(i).getPassword(), list.get(i).getTimeCreated(), "Detail" });

					}
				}

			}
		});

		if (!Database.customer_list.isEmpty()) {
			for (int i = list.size() - 1; i > list.size() - num - 1 && i > -1; i--) {

				model.addRow(new Object[] { list.get(i).getUsername(), list.get(i).getPassword(), list.get(i).getTimeCreated(), "Detail" });

			}
		}

		// add listener to cells for review
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table1 = (JTable) e.getSource();
				int column = table1.columnAtPoint(e.getPoint());
				int row = table1.rowAtPoint(e.getPoint());

				if (column == 3) {
					dispose();
					Database.currentUserId = list.get(list.size() - row - 1).getId();
				
				}
			}
		});

		// promotion table
		JTable table2 = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Promotion", "Price", "In stock", "" }, 0)) {
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
		table2.setBounds(30, 40, 200, 300);
		table2.setRowHeight(30);
		table2.getColumnModel().getColumn(0).setPreferredWidth(230);
		table2.getColumnModel().getColumn(1).setPreferredWidth(120);
		table2.getColumnModel().getColumn(2).setPreferredWidth(80);
		table2.getColumnModel().getColumn(3).setPreferredWidth(80);
		table2.getColumnModel().getColumn(4).setPreferredWidth(80);

		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

		// disable dragging columns
		table2.getTableHeader().setReorderingAllowed(false);

		// center header
		((DefaultTableCellRenderer) table2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table2.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));

		// center cells
		DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table2.getColumnCount(); i++) {
			table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
		}

		// change text to button
		table2.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table2.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setSize(530, 150);
		sp2.setLocation(336, 396);
		getContentPane().add(sp2);

		// display content of each item in stock
		if (!Database.super_stock.isEmpty()) {
			for (int i = 0; i < Database.super_stock.size(); i++) {
				final int index = i;
				Item currentItem = Database.super_stock.get(i);

				model2.addRow(new Object[] { currentItem.getItemName(), currentItem.getCategory(),
						currentItem.getItemPrice(), currentItem.getInStock(), "Detail" });

			}
		}

		// add listener to cells for review
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table2 = (JTable) e.getSource();
				int column = table2.columnAtPoint(e.getPoint());
				int row = table2.rowAtPoint(e.getPoint());
				if (column == 4) {
					dispose();
					Database.currentItemId = Database.super_stock.get(row).getItemID();
					//new CustomerViewItemDetail().setVisible(true);
				}
			}
		});

		// Setup.mainFrame(this);

	

	}
}
