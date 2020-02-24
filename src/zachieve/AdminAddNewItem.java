package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.component.Setup;
import main.Database;
import main.Item;

import java.awt.Font;
import java.awt.Color;

public class AdminAddNewItem extends JFrame {
	AdminAddNewItem() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel name, category, price, instock;
		JTextField nameField, categoryField, priceField, instockField;
		JButton add, back;

		// sidebar
		add(Setup.adminSidebar());

		// tittle
		JLabel lblAddNewItem = new JLabel("ADD NEW ITEM");
		Setup.title3(lblAddNewItem);
		getContentPane().add(lblAddNewItem);

		// item name
		name = new JLabel("NAME");
		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBounds(417, 128, 100, 40);
		getContentPane().add(name);
		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(341, 165, 198, 40);
		getContentPane().add(nameField);

		// category
		category = new JLabel("CATEGORY");
		category.setFont(new Font("Tahoma", Font.PLAIN, 16));
		category.setBounds(399, 214, 91, 40);
		getContentPane().add(category);
		categoryField = new JTextField();
		categoryField.setHorizontalAlignment(SwingConstants.CENTER);
		categoryField.setBounds(341, 250, 198, 40);
		getContentPane().add(categoryField);

		// price
		price = new JLabel("PRICE");
		price.setFont(new Font("Tahoma", Font.PLAIN, 16));
		price.setBounds(417, 297, 100, 40);
		getContentPane().add(price);
		priceField = new JTextField();
		priceField.setHorizontalAlignment(SwingConstants.CENTER);
		priceField.setBounds(342, 333, 197, 40);
		getContentPane().add(priceField);

		// instock
		instock = new JLabel("IN STOCK");
		instock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instock.setBounds(400, 379, 100, 40);
		getContentPane().add(instock);
		instockField = new JTextField();
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
		JTextArea textArea = new JTextArea();
		textArea.setRows(4);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		// "Add" button
		add = new JButton("Add");
		Setup.rightButton(add);
		getContentPane().add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: check if itemName exist
				String itemName = nameField.getText();
				String category = categoryField.getText();
				String discription = textArea.getText();
				int itemPrice = Integer.parseInt(priceField.getText());
				int inStock = Integer.parseInt(instockField.getText());
				Database.super_stock.add(new Item(++Database.countItemId, Database.currentUserId,discription,itemName, category, itemPrice, inStock));
				dispose();
				new AdminViewStock().setVisible(true);
			}
		});

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

		//Setup.mainFrame(this);
	}
}
