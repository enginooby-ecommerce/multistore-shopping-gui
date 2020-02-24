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

import asystem.Database;
import asystem.Setup;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Item;
import main.ItemInCart;
import main.Purchase;

import java.awt.Color;
import java.awt.Font;

public class AdminViewPurchases extends JPanel {
	public AdminViewPurchases() {
		Setup.page(this);
		// Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
		List<Purchase> list = Database.purchase_list;
		List<JButton> detail = new ArrayList<JButton>(list.size());
		List<JLabel> content = new ArrayList<JLabel>(list.size());

		// TITLE
		JLabel lblMyPurchases = new JLabel("PURCHASES");
		Setup.title2(lblMyPurchases);
		add(lblMyPurchases);

		// SORT BOX
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSortBy.setBounds(728, 91, 75, 29);
		add(lblSortBy);

		String sort[] = { "Time", "Amount" };
		JComboBox comboBox = new JComboBox(sort);
		comboBox.setBounds(792, 92, 75, 29);
		add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				if (option.equals("Time")) {
					Setup.underDev();
				} else if (option.equals("Amount")) {
					Setup.underDev();
				}
			}
		});
		comboBox.setEditable(true);
		comboBox.setSelectedItem("Select");
		comboBox.setEditable(false);

		// PURCHASES TABLE
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Time", "Amount", "Customer", "" }, 0)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// setup table
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		Setup.table(table);
		Setup.buttonTable(table, new int[] { 4 });
		Setup.columnWidth(table, new int[] { 50, 200, 200, 200, 100 });
		add(Setup.tableScrollPane(table));

		// add listener to cells
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 4) {
					Database.currentPurchaseId = list.get(row).getPurchaseId();
					Z.page = new AdminViewPurchaseDetail();
					Z.pageSlide(1);
				}
			}
		});

		// display content
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				final int index = i;
				model.addRow(new Object[] { i + 1, list.get(i).getTime(), "$"+list.get(i).getAmount(),
						Database.getCustomerByID(list.get(i).getCustomerId()).getUsername(), "Detail" });
			}
		}
	}
}
