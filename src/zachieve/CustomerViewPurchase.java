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

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

public class CustomerViewPurchase extends JFrame {
	public CustomerViewPurchase() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
		List<Purchase> list = currentCustomer.getPurchaseList();
		List<JButton> detail = new ArrayList<JButton>(list.size());
		List<JLabel> content = new ArrayList<JLabel>(list.size());

		getContentPane().add(Setup.customerSidebar());

		// table
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Time", "Amount", "" }, 0)) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		getContentPane().add(Setup.tableScrollPane(table));

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);

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

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 3) {
					//((JFrame) (table.getTopLevelAncestor())).revalidate();
					((JFrame) (table.getTopLevelAncestor())).dispose();
					Database.currentPurchaseId = list.get(row).getPurchaseId();
					// Database.currentItemId = Database.stock.get(index).getItemID();
					new CustomerViewPurchaseDetail().setVisible(true);
				}
			}
		});

		// display content of each item in stock
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				final int index = i;
				model.addRow(new Object[] { i+1, list.get(i).getTime(), list.get(i).getAmount(), "Detail" });
			}
		}

		// "Back" button
		JButton back = new JButton("Back");
		Setup.middleButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//((JFrame) (table.getTopLevelAncestor())).revalidate();
				((JFrame) (table.getTopLevelAncestor())).dispose();
				new CustomerHome().setVisible(true);

			}
		});
		getContentPane().add(back);

		// tittle
		JLabel lblMyPurchases = new JLabel("MY PURCHASES");
		Setup.title(lblMyPurchases);
		// lblMyPurchases.setFont(new Font("Tahoma", Font.BOLD, 18));
		// lblMyPurchases.setBounds(129, 23, 124, 22);
		getContentPane().add(lblMyPurchases);
		
		
		//sort box
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSortBy.setBounds(728, 91, 75, 29);
		getContentPane().add(lblSortBy);
		
		String sort[] = { "Time", "Amount"};
		JComboBox comboBox = new JComboBox(sort);
		comboBox.setBounds(792, 92, 75, 29);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				if (option.equals("Time")) {
					Setup.underDev();
				} else if (option.equals("Amount")) {
					Setup.underDev();
				} }
		});
		
		
		comboBox.setEditable(true);
		comboBox.setSelectedItem("");
		//comboBox.setSelectedItem(Database.theme);
		comboBox.setEditable(false);

//		Setup.mainFrame(this);
		
		
		
		
		
		
		


	}
}
