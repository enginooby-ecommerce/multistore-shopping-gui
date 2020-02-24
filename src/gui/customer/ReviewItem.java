package gui.customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import asystem.Database;
import asystem.Setup;
import gui.common.ViewItemDetail;
import gui.common.Z;
import gui.utility.StarRater;
import main.Customer;
import main.Item;
import main.Review;


public class ReviewItem extends JPanel {
	private int rate;

	/**
	 * Create the panel.
	 */
	public ReviewItem() {
		Setup.page(this);

		JTextField titleField;
		JButton submit, back;
		Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
		Item currentItem = Database.getItemSuperStockById(Database.currentItemId);

		// star rater
		JPanel panel2 = new JPanel();

		panel2.setSize(123, 32);
		panel2.setLocation(248, 378);
		StarRater starRater = new StarRater(5, 0, 0);
		starRater.setSize(119, 40);
		starRater.setLocation(new Point(233, 182));
		starRater.setFont(new Font(Setup.font, Font.PLAIN, 18));
		starRater.addStarListener(new StarRater.StarListener() {

			public void handleSelection(int selection) {
				rate = selection;
			}
		});
		// panel2.add(starRater);
		panel2.setBackground(Setup.colorPage);
		// add(panel2);
		add(starRater);
		;

//content field
		JTextArea textArea = new JTextArea(5, 5);
		textArea.setBounds(130, 364, 341, 90);
		add(textArea);

		JLabel lblEnterContent = new JLabel("CONTENT");
//lblEnterContent.setForeground(new Color(0, 0, 255));
		lblEnterContent.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblEnterContent.setBounds(130, 335, 119, 16);
		add(lblEnterContent);

//title field
		titleField = new JTextField("");
		titleField.setBounds(130, 264, 341, 40);
		add(titleField);

		JLabel lblTitle = new JLabel("TITLE");
		lblTitle.setFont(new Font(Setup.font, Font.PLAIN, 16));
		lblTitle.setBounds(130, 235, 119, 16);
		add(lblTitle);

		// "Submit" button
		submit = new JButton("Submit");
		Setup.buttonEffect(submit);
		Setup.rightButton2(submit);
		add(submit);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Review newReview = new Review(Database.countReviewId++,titleField.getText(), textArea.getText(), Database.currentUserId,
						Database.currentItemId, rate);
				currentCustomer.getMyReview().add(newReview);
				currentItem.getReviewList().add(newReview);

				Z.page = new MyPurchaseDetail();
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

				Z.page = new MyPurchaseDetail();
				Z.pageSlide(2);
			}
		});
		add(back);

		// title
		JLabel lblReview = new JLabel("REVIEW");
		Setup.title2(lblReview);
		// lblReview.setFont(new Font(Setup.font, Font.BOLD, 18));
		// lblReview.setBounds(101, 59, 80, 28);
		add(lblReview);

		// JLabel label = new JLabel("<html><span bgcolor=\"yellow\">This is the label
		// text </span></html>");//
		int size = Database.getItemSuperStockById(Database.currentItemId).getItemName().length();
		JLabel label = new JLabel(Database.getItemSuperStockById(Database.currentItemId).getItemName());
		label.setForeground(SystemColor.textHighlight);
		label.setForeground(new Color(0, 0, 255));
		label.setFont(new Font(Setup.font, Font.PLAIN, 20));
		label.setBounds(38, 107, 13 * size, 30);
		Setup.linkEffect(label);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
Z.page = new ViewItemDetail();
Z.pageSlide(1);
				//new CustomerViewItemDetail().setVisible(true);
			}

		});
		add(label);
	}

}
