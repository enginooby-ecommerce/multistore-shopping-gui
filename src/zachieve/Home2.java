package zachieve;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gui.ButtonEditor;
import gui.ButtonRenderer;
import gui.SettingPage;
import gui.Setup;
import main.Database;
import main.Item;

public class Home2 extends JPanel {

	/**
	 * Create the
	 */
	public Home2() {

		setBounds(0, 0, 900, 600);
		//setBounds(0, 0, 600, 600);
		setBackground(Database.background);
		setLayout(null);
		

		// title line
		JSeparator separator = new JSeparator();
		separator.setBounds(300, 85, 601, 10);
		add(separator);

		// add close button
		JLabel x = new JLabel();
		add(x);
		x.setForeground(Color.BLACK);
		x.setBounds(858, 13, 32, 32);
		x.setFont(new Font("Tahoma", Font.PLAIN, 24));
		x.setHorizontalAlignment(SwingConstants.CENTER);
		x.setVerticalAlignment(SwingConstants.CENTER);
		x.setIcon(new ImageIcon(Setup.class.getResource(("/images/close.png"))));
		x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				x.setOpaque(true);
				x.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				x.setBackground(Color.white);
				x.setOpaque(false);
			}
		});

		// add minimize button
		JLabel minimize = new JLabel();
		add(minimize);
		minimize.setForeground(Color.BLACK);
		minimize.setBounds(820, 13, 32, 32);
		minimize.setFont(new Font("Tahoma", Font.PLAIN, 24));
		minimize.setHorizontalAlignment(SwingConstants.CENTER);
		minimize.setVerticalAlignment(SwingConstants.CENTER);
		minimize.setIcon(new ImageIcon(Setup.class.getResource(("/images/minimize.png"))));
		minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				((JFrame) (getTopLevelAncestor())).setState(Frame.ICONIFIED);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				minimize.setOpaque(true);
				minimize.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				minimize.setBackground(Color.white);
				minimize.setOpaque(false);
			}
		});

		// "Setting" button
		JButton setting = new JButton("Setting");
		setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setting.setBounds(100, 25, 100, 29);
		add(setting);
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingPage().setVisible(true);
			}
		});

		if (((JFrame) (getTopLevelAncestor())) != null) {
			//Setup.mainFrame(((JFrame) (getTopLevelAncestor())));
		}
		// title
		JLabel lblMenu = new JLabel("HOME");
		Setup.title(lblMenu);
		add(lblMenu);

		// sort box
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSortBy.setBounds(727, 128, 75, 29);
		add(lblSortBy);

		String sort[] = { "Price", "Rating" };
		JComboBox comboBox = new JComboBox(sort);
		comboBox.setBounds(791, 129, 75, 29);
		comboBox.setEditable(true);
		comboBox.setSelectedItem("Select");
		comboBox.setEditable(false);
		add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				if (option.equals("Price")) {
					Setup.underDev();
				} else if (option.equals("Rating")) {
					Setup.underDev();
				}
			}
		});

		JLabel lblBestSeller = new JLabel("BEST SELLING");
		lblBestSeller.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBestSeller.setBounds(339, 118, 157, 48);
		add(lblBestSeller);

		JLabel lblPromotion = new JLabel("PROMOTION");
		lblPromotion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPromotion.setBounds(339, 347, 157, 48);
		add(lblPromotion);

		// best selling table
		JTable table1 = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Rating", "Price", "In stock", "" }, 0)) {
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
		table1.getColumnModel().getColumn(0).setPreferredWidth(230);
		table1.getColumnModel().getColumn(1).setPreferredWidth(120);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(80);
		table1.getColumnModel().getColumn(4).setPreferredWidth(80);

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
		table1.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table1.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table1);
		sp.setSize(530, 150);
		sp.setLocation(336, 171);

		add(sp);

		// display content of each item in stock
		if (!Database.super_stock.isEmpty()) {
			for (int i = 0; i < Database.super_stock.size(); i++) {
				final int index = i;
				Item currentItem = Database.super_stock.get(i);

				// convert rating to star symbols
				String star = "";
				for (int j = 0; j < currentItem.getRating(); j++) {
					star += (char) (9733);
				}
				if (currentItem.getRating() < 5) {
					for (int k = 0; k < 5 - currentItem.getRating(); k++) {
						star += (char) (9734);
					}
				}

				model.addRow(new Object[] { currentItem.getItemName(), star, currentItem.getItemPrice(),
						currentItem.getInStock(), "Detail" });

			}
		}

		// add listener to cells for review
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table1 = (JTable) e.getSource();
				int column = table1.columnAtPoint(e.getPoint());
				int row = table1.rowAtPoint(e.getPoint());
				if (column == 4) {
					
					((JFrame) (table1.getTopLevelAncestor())).revalidate();
					((JFrame) (table1.getTopLevelAncestor())).repaint();
					
					Database.currentItemId = Database.super_stock.get(row).getItemID();

					if (Database.isGuest) {
						new GuestViewItemDetail().setVisible(true);
						((JFrame) (table1.getTopLevelAncestor())).dispose();
						//((JFrame) (table1.getTopLevelAncestor())).setVisible(false);
					} else {

						new CustomerViewItemDetail().setVisible(true);
						((JFrame) (table1.getTopLevelAncestor())).dispose();
						//((JFrame) (table1.getTopLevelAncestor())).setVisible(false);
					}
				}
			}
		});

		// view promotion type

		JLabel viewType = new JLabel("View type:");
		viewType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		viewType.setBounds(692, 353, 75, 29);
		add(viewType);

		String view[] = { "Sale 50%", "Buy 2 get 1" };
		JComboBox comboBox2 = new JComboBox(view);
		comboBox2.setBounds(768, 354, 98, 29);
		comboBox2.setEditable(true);
		comboBox2.setSelectedItem("Select");
		comboBox2.setEditable(false);
		add(comboBox2);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox2.getItemAt(comboBox2.getSelectedIndex()) + "";
				if (option.equals("Sale 50%")) {
					Setup.underDev();
				} else if (option.equals("Buy 2 get 1")) {
					Setup.underDev();
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
		add(sp2);

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
					((JFrame) (table1.getTopLevelAncestor())). dispose();
					((JFrame) (table1.getTopLevelAncestor())).repaint();
					Database.currentItemId = Database.super_stock.get(row).getItemID();

					if (Database.isGuest) {
						
						new GuestViewItemDetail().setVisible(true);
					} else {

						new CustomerViewItemDetail().setVisible(true);
					}
				}
			}
		});
	}

}
