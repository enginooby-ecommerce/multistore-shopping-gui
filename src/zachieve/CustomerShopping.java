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
import main.Database;
import main.Item;

import java.awt.Color;
import java.awt.Font;

public class CustomerShopping extends JFrame {
	public CustomerShopping() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		List<JButton> detail = new ArrayList<JButton>(Database.super_stock.size());
		List<JLabel> content = new ArrayList<JLabel>(Database.super_stock.size());
		JButton myCart;

		add(Setup.customerSidebar());

		// table
		JTable table = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Category", "Price", "In stock", "" }, 0)) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(230);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);

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
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 360);
		sp.setLocation(337, 130);

		add(sp);

		// display content of each item in stock
		if (!Database.super_stock.isEmpty()) {
			for (int i = 0; i < Database.super_stock.size(); i++) {
				final int index = i;
				Item currentItem = Database.super_stock.get(i);

				model.addRow(new Object[] { currentItem.getItemName(), currentItem.getCategory(),
						currentItem.getItemPrice(), currentItem.getInStock(), "Detail" });

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
					dispose();
					Database.currentItemId = Database.super_stock.get(row).getItemID();
					new CustomerViewItemDetail().setVisible(true);
				}
			}
		});

		// "My Cart" button
		myCart = new JButton("My cart");
		Setup.rightButton(myCart);
		myCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerViewCart().setVisible(true);

			}
		});
		getContentPane().add(myCart);

		// "Back" button
		JButton back = new JButton("Back");
		Setup.leftButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerHome().setVisible(true);

			}
		});
		getContentPane().add(back);

		// title
		JLabel lblStore = new JLabel("STORE");
		Setup.title(lblStore);
		getContentPane().add(lblStore);

		//Setup.mainFrame(this);
	}
}
