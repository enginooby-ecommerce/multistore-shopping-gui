package gui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import asystem.Database;
import asystem.Setup;
import main.Customer;
import main.Inbox;
import main.Item;
import main.Message;
import main.Retailer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DialogMessage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;



	/**
	 * Create the dialog.
	 */
	public DialogMessage() {

		textField = new JTextField();
		textField.setBounds(38, 40, 354, 134);
		contentPanel.add(textField);
		textField.setColumns(10);

		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(Setup.getWindow());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("Send");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Item currentItem = Database.getItemSuperStockById(Database.currentItemId);
					Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
					Retailer currentRetailer = Database.getRetailerByID(currentItem.getRetailerId());
					String inboxLabel = Database.currentUserId + "&" + currentItem.getRetailerId();
					Message newMessage = new Message(Database.currentUserId, textField.getText());
					
					if (currentCustomer.inboxExist(inboxLabel)) {	
						currentCustomer.getInboxByLabel(inboxLabel).getMessages().add(newMessage
								);
						currentRetailer.getInboxByLabel(inboxLabel).getMessages().add(newMessage
								);
						
					} else {
						Inbox newInbox = new Inbox(Database.currentUserId,currentItem.getRetailerId());
						newInbox.getMessages().add(newMessage);
						currentCustomer.getMy_inbox().add(newInbox);
						currentRetailer.getMy_inbox().add(newInbox);
						Database.addInbox(newInbox);			
					}
					Database.addMessage(newMessage, inboxLabel);			
					((JDialog) btnNewButton.getTopLevelAncestor()).dispose();
				}
			});
			btnNewButton.setBounds(166, 203, 97, 25);
			contentPanel.add(btnNewButton);
		}

	}
}
