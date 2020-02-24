package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Item;
import main.ItemInCart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import asystem.Database;
import asystem.Setup;
import gui.common.Z;

public class AdminViewPurchaseDetail extends JPanel {
	AdminViewPurchaseDetail() {
		Setup.page(this);
		List<ItemInCart> itemList = Database.getPurchaseById(Database.currentPurchaseId).getItemList();

		// TITLE
		JLabel lblMyPurchases = new JLabel("PURCHASE DETAIL");
		Setup.title2(lblMyPurchases);
		add(lblMyPurchases);

		// TABLE
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Item name", "Quantity", "Total" }, 0)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// setup table
		table.setBounds(30, 40, 200, 300);
		table.setRowHeight(30);
		Setup.table(table);
		Setup.columnWidth(table, new int[] { 50, 300, 70, 70 });
		add(Setup.tableScrollPane(table));

		// display item content
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < itemList.size(); i++) {
			Item currentItem = Database.getItemSuperStockById(itemList.get(i).getItemID());
			model.addRow(new Object[] { i + 1, currentItem.getItemName(), itemList.get(i).getQuantity(),"$"+
					currentItem.getItemPrice() * itemList.get(i).getQuantity(), "Review" });
		}

		// BACK
		JButton back = new JButton("Back");
		Setup.middleButton2(back);
		Setup.buttonEffect(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new AdminViewPurchases();
				Z.pageSlide(2);
			}
		});
		add(back);

	}
}
