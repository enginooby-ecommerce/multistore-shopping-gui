package gui.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import javax.swing.text.Highlighter;

import asystem.Database;
import asystem.Setup;
import gui.common.ViewItemDetail;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import main.Item;

public class HomeOri extends JPanel {

	/**
	 * Create the
	 */
	public HomeOri() {

		Setup.page(this);

		// title
		JLabel lblMenu = new JLabel("HOME");
		Setup.title2(lblMenu);
		add(lblMenu);

		// sort box
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSortBy.setForeground(Setup.colorPageText);
		lblSortBy.setFont(new Font(Setup.font, Font.PLAIN, 15));
		lblSortBy.setBounds(386, 128, 83, 29);
		add(lblSortBy);

		String sort[] = { "Price", "Rating" };
		JComboBox comboBox = new JComboBox(sort);
		comboBox.setFont(new Font(Setup.font, Font.PLAIN, 15));
		comboBox.setBounds(476, 129, 90, 29);
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
		lblBestSeller.setForeground(Setup.colorPageText);
		lblBestSeller.setFont(new Font(Setup.font, Font.BOLD, 20));
		lblBestSeller.setBounds(39, 118, 197, 48);
		add(lblBestSeller);

		JLabel lblPromotion = new JLabel("PROMOTION");
		lblPromotion.setForeground(Setup.colorPageText);
		lblPromotion.setFont(new Font(Setup.font, Font.BOLD, 20));
		lblPromotion.setBounds(39, 347, 197, 48);
		add(lblPromotion);

		// best selling table
		JTable table1 = new JTable(new DefaultTableModel(new Object[] { "Item", "Rating", "Price", "Stock", "" }, 0)) {

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
		Setup.table(table1);

		// change text to button
		table1.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table1.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// change font to display star symbols correctly
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
			Font font = new Font("Serif", Font.PLAIN, 13);

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setFont(font);
				setHorizontalAlignment(JLabel.CENTER);
				return this;
			}

		};

		table1.getColumnModel().getColumn(1).setCellRenderer(r);

		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table1);
		sp.setSize(530, 150);
		sp.setLocation(36, 171);
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

				model.addRow(new Object[] { currentItem.getItemName(),
						star + " (" + currentItem.getReviewList().size() + ")", "$" + currentItem.getItemPrice(),
						currentItem.getInStock(), "Detail" });

			}
		}

		// add listener to cells for "Detail" button
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JTable table1 = (JTable) e.getSource();
				int column = table1.columnAtPoint(e.getPoint());
				int row = table1.rowAtPoint(e.getPoint());
				if (column == 4) {
					Setup.playSound("click.wav");
					// table1.setCursor(new Cursor(Cursor.HAND_CURSOR));
					Database.currentItemId = Database.super_stock.get(row).getItemID();
					Z.page = new ViewItemDetail();
					Z.pageSlide(1);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JTable table1 = (JTable) e.getSource();
				int column = table1.columnAtPoint(e.getPoint());
				int row = table1.rowAtPoint(e.getPoint());
				if (column == 4) {
					table1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					table1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JTable table1 = (JTable) e.getSource();
				int column = table1.columnAtPoint(e.getPoint());
				int row = table1.rowAtPoint(e.getPoint());
				if (column == 4) {
					table1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

		});

		// view promotion type

		JLabel viewType = new JLabel("View type:");
		viewType.setHorizontalAlignment(SwingConstants.RIGHT);
		viewType.setForeground(Setup.colorPageText);
		viewType.setFont(new Font(Setup.font, Font.PLAIN, 15));
		viewType.setBounds(372, 353, 97, 29);
		add(viewType);

		String sale[] = {"Sale 25%","Sale 50%", "Sale 80%", "Buy 2 get 1", "Buy 3 get 1","Buy 3 get 2"};

		JComboBox comboBox2 = new JComboBox(sale);
		comboBox2.setBounds(476, 354, 90, 29);
		comboBox2.setEditable(true);
		comboBox2.setSelectedItem("Select");
		comboBox2.setEditable(false);
		comboBox2.setFont(new Font(Setup.font, Font.PLAIN, 15));
		comboBox2.setForeground(Setup.colorPageText);
		add(comboBox2);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox2.getItemAt(comboBox2.getSelectedIndex()) + "";
				Setup.underDev();
			}
		});

		// promotion table
		JTable table2 = new JTable(
				new DefaultTableModel(new Object[] { "Item", "Promotion", "Price", "Stock", "" }, 0)) {
			/*
			 * @Override public Class<?> getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */

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

		Setup.table(table2);

		// change text to button
		table2.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table2.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

		// add table to scrollpane
		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setSize(530, 150);
		sp2.setLocation(36, 396);
		add(sp2);

		// display content of each item in stock
		if (!Database.promotion.isEmpty()) {
			for (int i = 0; i < Database.promotion.size(); i++) {
				final int index = i;
				Item currentItem = Database.promotion.get(i);
				/*if (currentItem.getPromo() == null) {
					model2.addRow(new Object[] { currentItem.getItemName(), "None", "$" + currentItem.getItemPrice(),
							currentItem.getInStock(), "Detail" });
				} else {*/
					model2.addRow(new Object[] { currentItem.getItemName(), currentItem.getPromo().getContent(),
							"$" + currentItem.getItemPrice(), currentItem.getInStock(), "Detail" });
				//}

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
					Setup.playSound("click.wav");
					Database.currentItemId = Database.promotion.get(row).getItemID();
					Z.page = new ViewItemDetail();
					Z.pageSlide(1);
				}
			}
		});
	}

}
