package gui.retailer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import asystem.Database;
import asystem.Setup;
import gui.common.Z;
import main.Item;
import main.Retailer;

public class ModifyItem extends JPanel {

	/**
	 * Create the panel.
	 */
	public ModifyItem() {
		Setup.page(this);
		
		JLabel name, category, price, instock;
		JTextField nameField, categoryField, priceField, instockField;
		JButton update, back;
		Item currentItem = Database.getItemSuperStockById(Database.currentItemId);
		Retailer currentRetailer = Database.getRetailerByID(Database.currentUserId);

		
		// title
		JLabel lblModify = new JLabel("UPDATE ITEM");
		Setup.title2(lblModify);
		add(lblModify);

		// item name
		name = new JLabel("NAME");
		name.setFont(new Font(Setup.font, Font.PLAIN, 16));
		name.setBounds(117, 128, 100, 40);
		add(name);
		nameField = new JTextField(currentItem.getItemName());
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(41, 165, 198, 40);
		add(nameField);

		// category
		category = new JLabel("CATEGORY");
		category.setFont(new Font(Setup.font, Font.PLAIN, 16));
		category.setBounds(99, 214, 91, 40);
		add(category);
		categoryField = new JTextField(currentItem.getCategory());
		categoryField.setHorizontalAlignment(SwingConstants.CENTER);
		categoryField.setBounds(41, 250, 198, 40);
		add(categoryField);

		// price
		price = new JLabel("PRICE");
		price.setFont(new Font(Setup.font, Font.PLAIN, 16));
		price.setBounds(117, 297, 100, 40);
		add(price);
		priceField = new JTextField(currentItem.getItemPrice() + "");
		priceField.setHorizontalAlignment(SwingConstants.CENTER);
		priceField.setBounds(42, 333, 197, 40);
		add(priceField);

		// instock
		instock = new JLabel("IN STOCK");
		instock.setFont(new Font(Setup.font, Font.PLAIN, 16));
		instock.setBounds(100, 379, 100, 40);
		add(instock);
		instockField = new JTextField(currentItem.getInStock() + "");
		instockField.setHorizontalAlignment(SwingConstants.CENTER);
		instockField.setBounds(41, 414, 198, 40);
		add(instockField);

		// image
		JLabel lblImage = new JLabel("IMAGE");
		lblImage.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblImage.setBounds(391, 126, 91, 40);
		add(lblImage);
		JLabel image = new JLabel("");
		//image.setIcon(new ImageIcon(AdminModifyItem.class.getResource("/images/choose_image1.png")));
		if (currentItem.getIcon()!=null) {
		image.setIcon(Setup.getScaledIcon(currentItem.getIcon(), 80, 80) );}
		image.setBounds(386, 165, 75, 64);
		add(image);

		// description
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblDescription.setBounds(367, 262, 134, 40);
		add(lblDescription);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 303, 272, 151);
		add(scrollPane);
		JTextArea textArea = new JTextArea(currentItem.getDescription());
		textArea.setRows(4);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		// "Update" button
		update = new JButton("Update");
		Setup.buttonEffect(update);
		Setup.rightButton2(update);
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: style+check if itemName exist
				String itemName = nameField.getText();
				String category = categoryField.getText();
				Item currentItem = Database.getItemSuperStockById(Database.currentItemId);
				Item currentItem2 = Database.getItemStockByID(currentRetailer, Database.currentItemId);
				int itemPrice = Integer.parseInt(priceField.getText());
				int inStock = Integer.parseInt(instockField.getText());
				String discription = textArea.getText();

				currentItem.setDescription(discription);
				currentItem.setItemName(itemName);
				currentItem.setCategory(category);
				currentItem.setItemPrice(itemPrice);
				currentItem.setInStock(inStock);
				
				currentItem2.setDescription(discription);
				currentItem2.setItemName(itemName);
				currentItem2.setCategory(category);
				currentItem2.setItemPrice(itemPrice);
				currentItem2.setInStock(inStock);
				
				
				Database.updateItem(currentItem);
				Database.updateItem(currentItem2);

				Z.page = new MyStock();
				Z.pageSlide(2);
			}
		});
		add(update);

		// "Back" button
		back = new JButton("Back");
		Setup.buttonEffect(back);
		Setup.leftButton2(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new MyStock();
				Z.pageSlide(2);

			}
		});
		add(back);
	}

}
