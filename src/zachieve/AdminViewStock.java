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
import main.Database;
import main.Item;

import java.awt.Color;
import java.awt.Font;

public class AdminViewStock extends JFrame {
	public AdminViewStock() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		List<JButton> modify = new ArrayList<JButton>(Database.super_stock.size());
		List<JButton> delete = new ArrayList<JButton>(Database.super_stock.size());
		List<JLabel> content = new ArrayList<JLabel>(Database.super_stock.size());

		// sidebar
		add(Setup.adminSidebar());

		// title
		JLabel lblStock = new JLabel("STOCK");
		Setup.title3(lblStock);
		getContentPane().add(lblStock);
		
		// table
				JTable table = new JTable(
						new DefaultTableModel(new Object[] { "Item", "Category", "Price", "In stock", "","" }, 0)) {
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
				table.getColumnModel().getColumn(0).setPreferredWidth(210);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(80);
				table.getColumnModel().getColumn(3).setPreferredWidth(80);
				table.getColumnModel().getColumn(4).setPreferredWidth(90);
				table.getColumnModel().getColumn(5).setPreferredWidth(90);

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
				table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
				table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

				// add table to scrollpane
				JScrollPane sp = new JScrollPane(table);
				sp.setSize(530, 400);
				sp.setLocation(337, 100);

				add(sp);

		// display content of each item in stock
		if (!Database.super_stock.isEmpty()) {
			for (int i = 0; i < Database.super_stock.size(); i++) {
				final int index = i;
				
				Item currentItem = Database.super_stock.get(i);

				model.addRow(new Object[] { currentItem.getItemName(), currentItem.getCategory(),
						currentItem.getItemPrice(), currentItem.getInStock(), "Modify","Delete" });

				

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
							new AdminModifyItem().setVisible(true);
						} else if (column ==5) {
							Database.super_stock.remove(Database.super_stock.get(row));
							// update content
							dispose();
							new AdminViewStock().setVisible(true);
						}
					}
				});

		// "Add new" button
		JButton addNew = new JButton("Add new");
		Setup.rightButton(addNew);
		getContentPane().add(addNew);
		addNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminAddNewItem().setVisible(true);
			}
		});

		// "Back" button
		JButton back = new JButton("Back");
		Setup.leftButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				

			}
		});
		getContentPane().add(back);

		//Setup.mainFrame(this);
	}
}
