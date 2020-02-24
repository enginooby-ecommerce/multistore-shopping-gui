package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.component.Setup;
import main.Customer;
import main.Database;
import main.Item;

import java.awt.Color;
import java.awt.Font;

public class AdminViewCustomerDetail extends JFrame {
	AdminViewCustomerDetail() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel content;
		JButton delete, back;
		Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);

		// sidebar
		add(Setup.adminSidebar());

		// tittle
		JLabel lblInfomation = new JLabel("INFORMATION");
		Setup.title3(lblInfomation);
		getContentPane().add(lblInfomation);

		// content
		content = new JLabel(Database.getCustomerContentById(Database.currentUserId));
		content.setFont(new Font("Tahoma", Font.PLAIN, 15));
		content.setBounds(337, 120, 500, 40);
		getContentPane().add(content);

		// "Delete" button
		delete = new JButton("Delete");
		Setup.rightButton(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Database.customer_list.remove(currentCustomer);
				new AdminViewCustomers().setVisible(true);
			}
		});
		getContentPane().add(delete);

		// "Back" button
		back = new JButton("Back");
		Setup.leftButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminViewCustomers().setVisible(true);

			}
		});
		getContentPane().add(back);

		//Setup.mainFrame(this);
	}
}
