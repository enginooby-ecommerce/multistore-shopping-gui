package gui.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import asystem.Database;
import asystem.Setup;
import gui.common.Z;
import gui.component.ConfirmDialog;
import main.Customer;
import main.Item;

public class MyCustomerDetail extends JPanel {

	/**
	 * Create the panel.
	 */
	public MyCustomerDetail() {
		Setup.page(this);
		
		JLabel content;
		JButton delete, back;
		Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);

	
		// title
		JLabel lblInfomation = new JLabel("CUSTOMER INFORMATION");
		Setup.title2(lblInfomation);
		add(lblInfomation);

		// content
		content = new JLabel("ID: "+
				currentCustomer.getId() + ". Username: " + currentCustomer.getUsername() + ". Password: " + currentCustomer.getPassword());
		content.setFont(new Font(Setup.font, Font.PLAIN, 15));
		content.setBounds(37, 120, 500, 40);
		add(content);

		// "Delete" button
		delete = new JButton("Delete");
		Setup.buttonEffect(delete);
		Setup.rightButton2(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfirmDialog("Delete this customer?") {
					@Override
					public void confirmAction() {
						Database.removeCustomer(Database.currentUserId);
						Database.customer_list.remove(currentCustomer);
						Z.page= new MyCustomers();
						Z.pageSlide(2);
					}
				};
				
			}
		});
		add(delete);

		// "Back" button
		back = new JButton("Back");
		Setup.buttonEffect(back);
		Setup.leftButton2(back);
		add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Z.page = new MyCustomers();
				Z.pageSlide(2);

			}
		});
	}

}
