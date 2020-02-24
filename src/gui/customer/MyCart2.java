package gui.customer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import gui.common.ViewItemDetail;
import gui.component.Setup;
import main.Customer;
import main.Database;
import main.Item;
import main.ItemInCart;
import javax.swing.JScrollPane;

public class MyCart2 extends JPanel {

	/**
	 * Create the 
	 */
	
	public MyCart2() {
		Setup.page(this);
				
		Customer currentCustomer = new Customer().getCustomerByID(Database.currentUserId);
		List<ItemInCart> currentCart = currentCustomer.getCart();
		List<JLabel> trashes = new ArrayList<>();
		
		JPanel outside = new JPanel();
		outside.setBackground(Setup.colorPage);
		BoxLayout boxlayout = new BoxLayout(outside, BoxLayout.Y_AXIS);
		//outside.setLayout(boxlayout);
		outside.setLayout(null);


		JScrollPane scrollPane = new JScrollPane(outside);
		scrollPane.setBounds(61, 151, 485, 336);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		
		
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		HashMap<JLabel,ItemInCart>  map = new HashMap<>();
	
		for (int i=0;i<currentCart.size();i++) {
			final int index = i;
			Item currentItem = Database.getItemSuperStockById(currentCart.get(i).getItemID());
			
			
			JPanel panel = new JPanel();
			//panel.setPreferredSize(new Dimension(450,100));
			//panel.setMaximumSize(getPreferredSize());
			//panel.setMinimumSize(new Dimension(475,100));
			panel.setSize(485, 100);
			panel.setLocation(0, 100*i);
			panel.setBackground(Setup.colorPage);
			panel.setLayout(null);
			panel.setBorder(raisedbevel);
			//panels.add(panel);
			
			JLabel name = new JLabel(currentItem.getItemName());
			name.setFont(new Font(Setup.font, Font.BOLD, 14));
			name.setBounds(10, 10, 100, 20);
			panel.add(name);
			
			
			JLabel trash = new JLabel();
			trash.setBounds(400, 20, 40, 40);
			ImageIcon trash_icon = Setup.getScaledIcon(new ImageIcon(MyCart2.class.getResource("/images/trash.png")), 40, 40);
			trash.setIcon(trash_icon);
			panel.add(trash);
			trashes.add(trash);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 100, 625, 10);
			
			panel.add(separator);
			
			map.put(trashes.get(i), currentCart.get(i));
		
			trashes.get(index).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//set current item to null instead of removing it to keep original index of current cart
					//currentCart.set(index, null);
					
					currentCart.remove(map.get(trashes.get(index)));
					System.out.println(currentCart.size());
					
				
					
					//outside.remove(panels.get(index));	
					outside.remove(trashes.get(index).getParent());
					
					outside.revalidate();
					outside.repaint();
		
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					trash.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					trash.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});

	outside.add(panel);
			//outside.add(panels.get(i));
			//outside.add(Box.createVerticalGlue());
			
		}
		
		

		
		
		
		
		
		
		
		

		
	}
}
