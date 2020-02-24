package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.component.Setup;
import main.Database;
import main.Item;

import java.awt.Font;
import java.awt.Color;

public class AdminModifyItem extends JFrame {
	private JTextField textField;

	AdminModifyItem() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel name, category, price, instock;
		JTextField nameField, categoryField, priceField, instockField;
		JButton update, back;
		Item currentItem = Database.getItemByID(Database.currentItemId);

		// sidebar
		getContentPane().add(Setup.adminSidebar());

		// title
		JLabel lblModify = new JLabel("MODIFY");
		Setup.title3(lblModify);
		getContentPane().add(lblModify);

		// item name
		name = new JLabel("NAME");
		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBounds(417, 128, 100, 40);
		getContentPane().add(name);
		nameField = new JTextField(currentItem.getItemName());
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(341, 165, 198, 40);
		getContentPane().add(nameField);

		// category
		category = new JLabel("CATEGORY");
		category.setFont(new Font("Tahoma", Font.PLAIN, 16));
		category.setBounds(399, 214, 91, 40);
		getContentPane().add(category);
		categoryField = new JTextField(currentItem.getCategory());
		categoryField.setHorizontalAlignment(SwingConstants.CENTER);
		categoryField.setBounds(341, 250, 198, 40);
		getContentPane().add(categoryField);

		// price
		price = new JLabel("PRICE");
		price.setFont(new Font("Tahoma", Font.PLAIN, 16));
		price.setBounds(417, 297, 100, 40);
		getContentPane().add(price);
		priceField = new JTextField(currentItem.getItemPrice() + "");
		priceField.setHorizontalAlignment(SwingConstants.CENTER);
		priceField.setBounds(342, 333, 197, 40);
		getContentPane().add(priceField);

		// instock
		instock = new JLabel("IN STOCK");
		instock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instock.setBounds(400, 379, 100, 40);
		getContentPane().add(instock);
		instockField = new JTextField(currentItem.getInStock() + "");
		instockField.setHorizontalAlignment(SwingConstants.CENTER);
		instockField.setBounds(341, 414, 198, 40);
		getContentPane().add(instockField);

		// image
		JLabel lblImage = new JLabel("IMAGE");
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImage.setBounds(691, 126, 91, 40);
		getContentPane().add(lblImage);
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(AdminModifyItem.class.getResource("/images/choose_image1.png")));
		image.setBounds(686, 165, 75, 64);
		getContentPane().add(image);

		// description
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(667, 262, 134, 40);
		getContentPane().add(lblDescription);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(589, 303, 272, 151);
		getContentPane().add(scrollPane);
		JTextArea textArea = new JTextArea(currentItem.getDiscription());
		textArea.setRows(4);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		// "Update" button
		update = new JButton("Update");
		Setup.rightButton(update);
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: style+check if itemName exist
				String itemName = nameField.getText();
				String category = categoryField.getText();
				Item currentItem = Database.getItemByID(Database.currentItemId);
				int itemPrice = Integer.parseInt(priceField.getText());
				int inStock = Integer.parseInt(instockField.getText());
				String discription = textArea.getText();

				currentItem.setDiscription(discription);
				currentItem.setItemName(itemName);
				currentItem.setCategory(category);
				currentItem.setItemPrice(itemPrice);
				currentItem.setInStock(inStock);

				dispose();
				new AdminViewStock().setVisible(true);
			}
		});
		getContentPane().add(update);

		// "Back" button
		back = new JButton("Back");
		Setup.leftButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminViewStock().setVisible(true);

			}
		});
		getContentPane().add(back);

		

//		Setup.mainFrame(this);

	

	}
}
