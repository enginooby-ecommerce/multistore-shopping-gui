package gui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.Border;

import asystem.AccountType;
import asystem.Database;
import asystem.Setup;
import main.Customer;
import main.Inbox;
import main.Message;
import main.Retailer;
import main.User;

public class InboxSection extends JScrollPane {
	public InboxSection(int activeContact) {
		User currentUser = new User();
		if (Database.login_as == AccountType.CUSTOMER) {
			currentUser = Database.getCustomerByID(Database.currentUserId);
		} else {
			currentUser = Database.getRetailerByID(Database.currentUserId);
		}

		List<Inbox> inboxes = currentUser.getMy_inbox();

		// scrollPane_2.setSize(245, 20);
		// panelSlider.add(scrollPane_2);

		JPanel outside2 = new JPanel();
		setViewportView(outside2);

		// scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		BoxLayout boxlayout2 = new BoxLayout(outside2, BoxLayout.Y_AXIS);
		outside2.setLayout(boxlayout2);

		
		List<Message> message_list = inboxes.get(activeContact).getMessages();

		for (int i = 0; i < message_list.size(); i++) {

			JPanel panel = new JPanel();
			BoxLayout boxlayout3 = new BoxLayout(panel, BoxLayout.Y_AXIS);
			panel.setLayout(boxlayout3);
			// panel.setPreferredSize(new Dimension(400,30));

			JLabel name = new JLabel();

			// if (for int i=0;i<))

			name.setFont(new Font(Setup.font, Font.BOLD, 14));

			JLabel label = new JLabel();
			label.setText("  "+message_list.get(i).getContent() + "  ");
			label.setFont(new Font(Setup.font, Font.PLAIN, 14));
			// label.setLocation();

			JLabel label2 = new JLabel();
			label2.setText("  [" + message_list.get(i).getTime() + "]  ");
			label2.setFont(new Font(Setup.font, Font.PLAIN, 12));
			// label2.setLocation(50, 10);

			Border blackline = BorderFactory.createLineBorder(Color.black, 2);
		

			// panel.setSize(100, 20);
			int id = message_list.get(i).getSenderId();
			if (id == Database.currentUserId) {
				panel.setBackground(new Color(100, 100, 100));
				panel.setLocation(100, 100);
				label.setForeground(new Color(230, 230, 230));
				label2.setForeground(new Color(230, 230, 230));
				name.setForeground(new Color(230, 230, 230));
				if (Database.login_as == AccountType.CUSTOMER) {
					name.setText("  "+Database.getCustomerByID(id).getUsername());
				} else {
					name.setText("  "+Database.getRetailerByID(id).getUsername());
				}
				//panel.setBorder(whiteline);

			} else {
				panel.setBackground(new Color(230, 230, 230));
				//panel.setBorder(blackline);
				if (Database.login_as != AccountType.CUSTOMER) {
					name.setText("  "+Database.getCustomerByID(id).getUsername());
				} else {
					name.setText("  "+Database.getRetailerByID(id).getUsername());
					
				}

			}

			panel.add(name);
			panel.add(label);
			panel.add(label2);

			// panel.setBorder(empty);
			 panel.setBorder(blackline);
			// panel.setLayout(null);

			outside2.add(panel);
		outside2.add(Box.createRigidArea(new Dimension(0, 7)));

		}
	}
}
