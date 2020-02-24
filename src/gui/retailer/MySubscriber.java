package gui.retailer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import asystem.Database;
import asystem.Setup;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Retailer;

public class MySubscriber extends JPanel {

	/**
	 * Create the panel.
	 */
	Retailer currentRetailer = Database.getRetailerByID(Database.currentUserId);
	List<Customer> list = currentRetailer.getMySubsriberList();
	//List<Retailer> list = currentCustomer.getMy_subscription();

	public MySubscriber() {
		Setup.page(this);
		add(Setup.title("MY SUBSCRIPTIONS"));
	
		// table header
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Retailer", "", "", "" }, 0)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		add(Setup.tableScrollPane(table));

		// table rows
		DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (int i = 0; i < list.size(); i++) {
				model.addRow(new Object[] { i + 1, list.get(i).getUsername(), "View store", "Inbox", "Remove" });
			}

		// size stuffs
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);

		Setup.columnWidth(table, new int[] { 50, 150, 150, 150, 150 });
		Setup.table(table);
		Setup.buttonTable(table, new int[] { 2, 3, 4 });

		// add listener to cells
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 2) {

				} else if (column == 3) {

				} else if (column == 4) {
					//list.get(row).getMy_subscriber_id_list().remove((Integer)Database.currentUserId);
					//currentRetailer.getMy_subscription_id().remove((Integer)list.get(row).getId());
					//model.removeRow(row);
				}

			}
		});

	}

}
