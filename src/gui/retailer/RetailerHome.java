package gui.retailer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import asystem.Database;
import asystem.Setup;
import gui.admin.MyCustomerDetail;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Customer;
import main.Item;
import main.Retailer;

public class RetailerHome extends JPanel {
	int num = 5; // display number of newest customers

	/**
	 * Create the panel.
	 */
	public RetailerHome() {
		Retailer currentRetailer = Database.getRetailerByID(Database.currentUserId);
		Setup.page(this);
		// title
		JLabel lblMenu = new JLabel("HOME");
		Setup.title2(lblMenu);
		add(lblMenu);


		

		JLabel lblBestSeller = new JLabel("NEW SUBSCRIBERS");
		lblBestSeller.setFont(new Font(Setup.font, Font.BOLD, 20));
		lblBestSeller.setBounds(39, 118, 250, 48);
		add(lblBestSeller);

		

		/**********new customers table*************/
		JTable table1 = new JTable(
				new DefaultTableModel(new Object[] { "Customer", "", "", "" }, 0)) {
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
		
		Setup.table(table1);


		// change text to button
		table1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));
		table1.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		table1.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
		table1.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
		table1.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table1);
		sp.setSize(530, 150);
		sp.setLocation(36, 171);

		add(sp);

		JLabel viewType = new JLabel("View");
		viewType.setFont(new Font(Setup.font, Font.PLAIN, 15));
		viewType.setBounds(354, 128, 52, 29);
		add(viewType);

		JLabel lblNewestCustomers = new JLabel("newest customers");
		lblNewestCustomers.setFont(new Font(Setup.font, Font.PLAIN, 15));
		lblNewestCustomers.setBounds(446, 128, 135, 29);
		add(lblNewestCustomers);

		List<Customer> list1 = currentRetailer.getMySubsriberList();
		String view[] = { "5", "10", "15" };
		JComboBox comboBox2 = new JComboBox(view);
		comboBox2.setBounds(392, 129, 48, 29);
		add(comboBox2);
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
				if (!list1.isEmpty()) {
					for (int i = list1.size() - 1; i > list1.size() - num - 1 && i > -1; i--) {

						model.addRow(new Object[] { list1.get(i).getUsername(), "View detail",
								"Inbox", "Remove" });

					}
				}

			}
		});

		if (!list1.isEmpty()) {
			for (int i = list1.size() - 1; i > list1.size() - num - 1 && i > -1; i--) {

				model.addRow(new Object[] { list1.get(i).getUsername(), "View detail",
						"Inbox", "Remove" });

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
					Setup.underDev();

					//Database.currentUserId = list1.get(list1.size() - row - 1).getId();
					//Z.page = new MyCustomerDetail();
					//Z.pageSlide(1);
				}
				
				if (column == 2) {
					Setup.underDev();

					//Database.currentUserId = list1.get(list1.size() - row - 1).getId();
					//Z.page = new MyCustomerDetail();
					//Z.pageSlide(1);
				}
				if (column == 1) {
					Setup.underDev();

					//Database.currentUserId = list1.get(list1.size() - row - 1).getId();
					//Z.page = new MyCustomerDetail();
					//Z.pageSlide(1);
				}
			}
		});

		
		
		
		
		
		
		/**********new retailers table*************/
		
		JLabel lblPromotion = new JLabel("NEW PURCHASES");
		lblPromotion.setFont(new Font(Setup.font, Font.BOLD, 20));
		lblPromotion.setBounds(39, 347, 200, 48);
		add(lblPromotion);
		
		JTable table2 = new JTable(
				new DefaultTableModel(new Object[] { "Customer", "Item", "Quantity", "Rate" }, 0)) {
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
		table2.getColumnModel().getColumn(0).setPreferredWidth(210);
		table2.getColumnModel().getColumn(1).setPreferredWidth(210);
		table2.getColumnModel().getColumn(2).setPreferredWidth(200);
		table2.getColumnModel().getColumn(3).setPreferredWidth(200);

		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
		
		Setup.table(table2);


		// change text to button
		table2.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		table2.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setSize(530, 150);
		sp2.setLocation(36, 396);
		add(sp2);


		JLabel viewType2 = new JLabel("View");
		viewType2.setFont(new Font(Setup.font, Font.PLAIN, 15));
		viewType2.setBounds(354, 354, 52, 29);
		add(viewType2);

		JLabel lblNewestCustomers2 = new JLabel("newest purchases");
		lblNewestCustomers2.setFont(new Font(Setup.font, Font.PLAIN, 15));
		lblNewestCustomers2.setBounds(446, 354, 135, 29);
		add(lblNewestCustomers2);

		List<Retailer> list2=null;// = Database.retailer_list;
		//String view[] = { "5", "10", "15" };
		JComboBox comboBox22 = new JComboBox(view);
		comboBox22.setBounds(392, 354, 48, 29);
		add(comboBox22);
		/*comboBox22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox22.getItemAt(comboBox22.getSelectedIndex()) + "";
				((DefaultTableModel) table2.getModel()).setNumRows(0);
				table2.revalidate();
				table2.repaint();
				if (option.equals("5")) {
					num = 5;

				} else if (option.equals("10")) {
					num = 10;

				} else if (option.equals("15")) {
					num = 15;

				}
				if (!list2.isEmpty()) {
					for (int i = list2.size() - 1; i > list2.size() - num - 1 && i > -1; i--) {

						//model2.addRow(new Object[] { list2.get(i).getUsername(), list2.get(i).getPassword(),
								//list2.get(i).getTimeCreated(), "Detail" });

					}
				}

			}
		});

		if (!list2.isEmpty()) {
			for (int i = list2.size() - 1; i > list2.size() - num - 1 && i > -1; i--) {

				//model2.addRow(new Object[] { list2.get(i).getUsername(), list2.get(i).getPassword(),
						//list2.get(i).getTimeCreated(), "Detail" });

			}
		}*/

		// add listener to cells for review
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table2 = (JTable) e.getSource();
				int column = table2.columnAtPoint(e.getPoint());
				int row = table2.rowAtPoint(e.getPoint());

				if (column == 3) {

					Database.currentUserId = list2.get(list2.size() - row - 1).getId();
					//Z.page = new MyCustomerDetail();
					Z.pageSlide(1);
				}
			}
		});
		
		
		
		
	
		
	}

}
