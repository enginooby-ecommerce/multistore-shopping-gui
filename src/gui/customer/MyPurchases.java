package gui.customer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Purchase;

public class MyPurchases extends JPanel {

	/**
	 * Create the panel.
	 */
	
	Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
	List<Purchase> list = currentCustomer.getPurchaseList();
	List<JButton> detail = new ArrayList<JButton>(list.size());
	List<JLabel> content = new ArrayList<JLabel>(list.size());
	
	public MyPurchases() {
		Setup.page(this);
		add(Setup.title("MY PURCHASES"));
		

		// table
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Time", "Amount", "" }, 0)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}		
		};
		add(Setup.tableScrollPane(table));

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Setup.table(table);
		Setup.buttonTable(table, new int[] {3});

		// add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 3) {
					
					Database.currentPurchaseId = list.get(row).getPurchaseId();
					Z.page = new MyPurchaseDetail();
					Z.pageSlide(1);
				}
				
				
			}
		});

		// display content of each item in stock
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				final int index = i;
				model.addRow(new Object[] { i+1, list.get(i).getTime(),"$"+ list.get(i).getAmount(), "Detail" });
			}
		}

		
		
		
		
		//sort box
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font(Setup.font, Font.PLAIN, 15));
		lblSortBy.setBounds(428, 91, 75, 29);
		add(lblSortBy);
		
		String sort[] = { "Time", "Amount"};
		JComboBox comboBox = new JComboBox(sort);
		comboBox.setBounds(492, 92, 75, 29);
		add(comboBox);
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
	}

}
