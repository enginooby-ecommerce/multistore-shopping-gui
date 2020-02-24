package zachieve;

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

import main.Database;
import main.Item;
import main.ItemInCart;
import main.Review;

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

import gui.ButtonEditor;
import gui.ButtonRenderer;
import gui.Setup;

public class CustomerViewPurchaseDetail extends JFrame {
	private JTable table;

	CustomerViewPurchaseDetail() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		List<ItemInCart> cart = Database.getPurchaseById(Database.currentPurchaseId).getCart();
		List<JLabel> contentItem = new ArrayList<JLabel>(cart.size());
		List<JButton> review = new ArrayList<JButton>(cart.size());

		// sidebar
		getContentPane().add(Setup.customerSidebar());

		// table
		JTable table = new JTable(new DefaultTableModel(new Object[] { "", "Item name", "Quatity", "Price", "" }, 0)) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);

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

		//change text to button
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));


		
		// add table to scrollpane
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(530, 400);
		sp.setLocation(337, 100);
		getContentPane().add(sp);

		// display item content
		for (int i = 0; i < cart.size(); i++) {
			final int index = i;
			Item currentItem = Database.getItemByID(cart.get(i).getItemID());
			model.addRow(new Object[] { i+1, currentItem.getItemName(), cart.get(i).getQuality(),
					currentItem.getItemPrice() * cart.get(i).getQuality(), "Review" });
		}

		

		//List<Integer> reviewedList = new ArrayList<Integer>();
		for(int i=0;i<cart.size();i++) { 
			for(Review aReview: Database.getCustomerByID(Database.currentUserId).getMyReview() ) {
				if (aReview.getItemId()==cart.get(i).getItemID()) {
					//System.out.println(Database.getItemByID(cart.get(i).getItemID()).getItemName());
					table.setValueAt("Reviewed", i, 4); 
					//reviewedList
				}
			}
		}
		
		//add listener to cells for review
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 4 && table.getModel().getValueAt(row, 4).equals("Review")) {
					//dispose();
					//((JFrame) (table.getTopLevelAncestor())).revalidate();
					((JFrame) (table.getTopLevelAncestor())).dispose();
					Database.currentItemId = cart.get(row).getItemID();
					new CustomerReviewItem().setVisible(true);
				}
			}
		});
		

		// "Back" button
		JButton back = new JButton("Back");
		Setup.middleButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//((JFrame) (back.getTopLevelAncestor())).revalidate();
				((JFrame) (back.getTopLevelAncestor())).dispose();
				//dispose();
				new CustomerViewPurchase().setVisible(true);

			}
		});
		getContentPane().add(back);

		// getContentPane().add(panelList);

		// tittle
		JLabel lblMyPurchases = new JLabel("PURCHASE DETAIL");
		Setup.title(lblMyPurchases);
		getContentPane().add(lblMyPurchases);

		//Setup.mainFrame(this);

	}
}

