package gui.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import asystem.AccountType;
import asystem.Database;
import asystem.Setup;
import main.Customer;
import main.Inbox;
import main.Message;
import main.Retailer;
import main.User;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import diu.swe.habib.JPanelSlider.JPanelSlider;
import gui.component.InboxSection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyInbox extends JPanel {
	private int activeContact = 0;

	private JTextField search_box;
	User currentSender = new User();
	User currentReceiver = new User();

	/**
	 * Create the
	 */
	public MyInbox() {
		Setup.page(this);

		// title
		JLabel my_inbox = new JLabel("MY INBOX");
		Setup.title2(my_inbox);
		add(my_inbox);
	
		
		if (Database.login_as == AccountType.CUSTOMER) {
			currentSender = Database.getCustomerByID(Database.currentUserId);
			currentReceiver = Database.getRetailerByID(currentSender.getMy_inbox().get(activeContact).getRetailerId());
		} else {
			
			currentSender = Database.getRetailerByID(Database.currentUserId);
			currentReceiver = Database.getCustomerByID(currentSender.getMy_inbox().get(activeContact).getCustomerId());
		}
		System.out.println("Sender: "+currentSender.getUsername());
		System.out.println("Receiver: "+currentReceiver.getUsername());
		List<Inbox> inboxes = currentSender.getMy_inbox();
	
		
		List<JPanel> contact = new ArrayList<>(inboxes.size());
		List<JLabel> name = new ArrayList<>(inboxes.size());

		// contact side
		JPanel outside = new JPanel();
		// outside.setBounds(43, 150, 157, 340);
		JScrollPane scrollPane_1 = new JScrollPane(outside);
		scrollPane_1.setBounds(43, 150, 157, 340);
		BoxLayout boxlayout = new BoxLayout(outside, BoxLayout.Y_AXIS);
		outside.setLayout(boxlayout);

		// Set the BoxLayout to be X_AXIS: from left to right

		JPanelSlider panelSlider = new JPanelSlider();
		panelSlider.setBounds(212, 150, 345, 340);
		add(panelSlider);
		if (!inboxes.isEmpty()) {
			panelSlider.add(new InboxSection(activeContact));
		}

		// list of contacts
		List<JPanel> panels = new ArrayList<>();

		for (int i = 0; i < inboxes.size(); i++) {
			final int index = i;
			JPanel panel = new JPanel();
			panel.setSize(150, 300);
			if (i == 0) {
				panel.setBackground(Color.LIGHT_GRAY);
			} else {
				panel.setBackground(Color.GRAY);
			}
			Border blackline = BorderFactory.createLineBorder(Color.black, 2);
			panel.setBorder(blackline);

			JLabel label = new JLabel();
			if (Database.login_as == AccountType.CUSTOMER) {
				label.setText(Database.getRetailerByID(inboxes.get(i).getRetailerId()).getUsername());
			} else {
				label.setText(Database.getCustomerByID(inboxes.get(i).getCustomerId()).getUsername());
			}
			label.setFont(new Font(Setup.font, Font.BOLD, 16));
			Setup.linkEffect(label);
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					activeContact = index;
					if (Database.login_as == AccountType.CUSTOMER) {
						currentReceiver = Database.getRetailerByID(currentSender.getMy_inbox().get(activeContact).getRetailerId());
					} else {						
						currentReceiver =Database.getCustomerByID(currentSender.getMy_inbox().get(activeContact).getCustomerId());
					}
					System.out.println("Sender: "+currentSender.getUsername());
					System.out.println("Receiver: "+currentReceiver.getUsername());
					InboxSection another = new InboxSection(activeContact);
					panelSlider.add(another);
					panelSlider.nextPanel(1, another, true);

					if (panelSlider.getComponentCount() > 2) {
						panelSlider.remove(0);
					}

					for (int j = 0; j < panels.size(); j++) {
						if (j == index) {
							continue;
						}
						panels.get(j).setBackground(Color.GRAY);
					}

					panels.get(index).setBackground(Color.LIGHT_GRAY);
				}

			});
			panel.add(label);
			panels.add(panel);
			outside.add(panel);
		}
		add(scrollPane_1);

		search_box = new JTextField();
		search_box.setBounds(43, 503, 157, 41);
		//add(search_box);
		search_box.setColumns(10);

		JTextArea message = new JTextArea();
		message.setBounds(212, 503, 276, 41);
		add(message);

		// "Send button"
		JButton send = new JButton("Send");
		Setup.buttonEffect(send);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String content = message.getText();
				Inbox receiverInbox =   currentReceiver.getInboxByLabel(inboxes.get(activeContact).getLabel());
				Message newMessage = new Message(Database.currentUserId, content);
				String label;
				if (Database.login_as==AccountType.CUSTOMER) {
					label = currentSender.getId()+"&"+currentReceiver.getId();
				} else {
					label = currentReceiver.getId()+"&"+currentSender.getId();
				}
				
				inboxes.get(activeContact).getMessages().add(newMessage);
				receiverInbox.getMessages().add(newMessage);
				Database.addMessage(newMessage, label);
				message.setText("");
				
				InboxSection updateInbox = new InboxSection(activeContact);
				panelSlider.add(updateInbox);
				panelSlider.nextPanel(1, updateInbox, true);
			}
		});
		send.setBounds(493, 503, 64, 41);
		add(send);

	}
}
