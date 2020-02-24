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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.ViewItemDetail;
import gui.common.Z;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Item;


public class Shopping extends JPanel {

	/**
	 * Create the panel.
	 */
	public Shopping() {
		Setup.page(this);
		List<JButton> detail = new ArrayList<JButton>(Database.super_stock.size());
		List<JLabel> content = new ArrayList<JLabel>(Database.super_stock.size());
		JButton myCart;

		
		// table
		JTable table = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Category", "Price", "In stock", "" }, 0)) {
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

		Setup.table(table);

		// change text to button
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 360);
		sp.setLocation(37, 130);

		add(sp);

		// display content of each item in stock
		if (!Database.super_stock.isEmpty()) {
			for (int i = 0; i < Database.super_stock.size(); i++) {
				final int index = i;
				Item currentItem = Database.super_stock.get(i);

				model.addRow(new Object[] { currentItem.getItemName(), currentItem.getCategory(),"$"+
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
					
					Database.currentItemId = Database.super_stock.get(row).getItemID();
					Z.page = new ViewItemDetail();
					Z.pageSlide(1);
				}
			}
		});

		// "My Cart" button
		myCart = new JButton("My cart");
		Setup.buttonEffect(myCart);
		Setup.middleButton2(myCart);
		myCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new MyCart();
				Z.pageSlide(1);

			}
		});
		add(myCart);


		// title
		JLabel lblStore = new JLabel("STORE");
		Setup.title2(lblStore);
		add(lblStore);
	}

}
