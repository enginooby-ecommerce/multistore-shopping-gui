package gui.retailer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import asystem.Database;
import asystem.Sale;
import asystem.Setup;
import gui.common.Z;
import main.Item;
import main.Retailer;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import java.awt.Component;

public class AddNewItem extends JPanel {

	/**
	 * Create the panel.
	 */
	ImageIcon icon = new ImageIcon();

	public AddNewItem() {
		Setup.page(this);
		
		

		JLabel name, category, price, instock;
		JTextField nameField, categoryField, priceField, instockField;
		JButton add, back;

		// tittle
		JLabel lblAddNewItem = new JLabel("ADD NEW ITEM");
		Setup.title2(lblAddNewItem);
		add(lblAddNewItem);

		// item name
		name = new JLabel("NAME");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font(Setup.font, Font.PLAIN, 16));
		name.setBounds(44, 128, 194, 40);
		add(name);
		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(41, 165, 198, 40);
		add(nameField);

		// category
		category = new JLabel("CATEGORY");
		category.setHorizontalAlignment(SwingConstants.CENTER);
		category.setFont(new Font(Setup.font, Font.PLAIN, 16));
		category.setBounds(42, 214, 196, 40);
		add(category);
		categoryField = new JTextField();
		categoryField.setHorizontalAlignment(SwingConstants.CENTER);
		categoryField.setBounds(41, 250, 198, 40);
		add(categoryField);

		// price
		price = new JLabel("PRICE");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font(Setup.font, Font.PLAIN, 16));
		price.setBounds(43, 297, 86, 40);
		add(price);
		priceField = new JTextField();
		priceField.setHorizontalAlignment(SwingConstants.CENTER);
		priceField.setBounds(42, 333, 88, 40);
		add(priceField);

		// instock
		instock = new JLabel("STOCK");
		instock.setHorizontalAlignment(SwingConstants.CENTER);
		instock.setFont(new Font(Setup.font, Font.PLAIN, 16));
		instock.setBounds(155, 299, 83, 40);
		add(instock);
		instockField = new JTextField();
		instockField.setHorizontalAlignment(SwingConstants.CENTER);
		instockField.setBounds(154, 334, 84, 40);
		add(instockField);
		
		// promotion
		JLabel promo = new JLabel("PROMOTION");
		
		promo.setHorizontalAlignment(SwingConstants.CENTER);
		promo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		promo.setBounds(44, 386, 193, 40);
		add(promo);
		
		String sale[] = {"None","Sale 25%","Sale 50%", "Sale 80%", "Buy 2 get 1", "Buy 3 get 1","Buy 3 get 2"};
		JComboBox comboBox = new JComboBox(sale);
		comboBox.setFont(new Font(Setup.font, Font.PLAIN, 12));
		((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBox.setBounds(44, 424, 194, 40);
		add(comboBox);

		// image
		JLabel lblImage = new JLabel("IMAGE");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblImage.setBounds(376, 128, 98, 40);
		add(lblImage);
		JLabel image = new JLabel("");
		image.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(AddNewItem.class.getResource("/images/choose_image1.png")));
		image.setBounds(376, 168, 98, 97);
		add(image);
		image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				icon = Setup.chooseIcon(image, 80, 80);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				image.setOpaque(true);
				image.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				image.setBackground(Color.white);
				image.setOpaque(false);
			}
		});

		// description
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblDescription.setBounds(289, 297, 272, 40);
		add(lblDescription);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 335, 272, 129);
		add(scrollPane);
		JTextArea textArea = new JTextArea();
		textArea.setRows(4);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		// "Add" button
		add = new JButton("Add");
		Setup.buttonEffect(add);
		Setup.rightButton2(add);
		add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: check if itemName exist
				String itemName = nameField.getText();
				String category = categoryField.getText();
				String discription = textArea.getText();
				int itemPrice = Integer.parseInt(priceField.getText());
				int inStock = Integer.parseInt(instockField.getText());
				
				String promotion = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				Sale promo = Sale.getSaleByContent(promotion);

				Item new_item = new Item(++Database.countItemId, Database.currentUserId, discription, itemName, category,
						itemPrice, inStock);
				new_item.setPromo(promo);
				new_item.setIcon(icon);
				Database.addItem(new_item);
				Database.super_stock.add(new_item);
				Database.getRetailerByID(Database.currentUserId).getMy_stock().add(new_item);
				
				System.out.println(Database.currentUserId);
				System.out.println(Database.getRetailerByID(Database.currentUserId).getUsername());

				Z.page = new MyStock();
				Z.pageSlide(2);
			}
		});

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
