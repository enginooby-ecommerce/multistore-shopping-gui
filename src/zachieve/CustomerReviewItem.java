package zachieve;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.Setup;
import gui.StarRater;
import gui.StarRater.StarListener;
import main.Customer;
import main.Database;
import main.Item;
import main.Review;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.SystemColor;

public class CustomerReviewItem extends JFrame {
	
	int rate;
	CustomerReviewItem() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JTextField titleField;
		JButton submit, back;
		Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
		Item currentItem = Database.getItemByID(Database.currentItemId);
		
		//sidebar
		add(Setup.customerSidebar());
		
		//star rater
		JPanel panel2 = new JPanel();
		panel2.setSize(123, 32);
		panel2.setLocation(548, 378);
        StarRater starRater = new StarRater(5, 0, 0);
        starRater.setSize(119, 40);
        starRater.setLocation(new Point(533, 182));
        starRater.setFont(new Font("Tahoma", Font.PLAIN, 18));
        starRater.addStarListener(
            new StarRater.StarListener()   {

                public void handleSelection(int selection) {
                    rate = selection;
                }
            });
       // panel2.add(starRater);
        panel2.setBackground(Database.background);
       // getContentPane().add(panel2);
        getContentPane().add(starRater);       
;

//content field
JTextArea textArea = new JTextArea(5,5);
textArea.setBounds(430, 364, 341, 90);
getContentPane().add(textArea);

JLabel lblEnterContent = new JLabel("CONTENT");
//lblEnterContent.setForeground(new Color(0, 0, 255));
lblEnterContent.setFont(new Font("Tahoma", Font.PLAIN, 16));
lblEnterContent.setBounds(430, 335, 119, 16);
getContentPane().add(lblEnterContent);

//title field
titleField = new JTextField("");
titleField.setBounds(430, 264, 341, 40);
getContentPane().add(titleField);

JLabel lblTitle = new JLabel("TITLE");
lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
lblTitle.setBounds(430, 235, 119, 16);
getContentPane().add(lblTitle);

		//"Submit" button
		submit = new JButton("Submit");
		Setup.rightButton(submit);
		getContentPane().add(submit);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				Review newReview = new Review(titleField.getText(), textArea.getText(), Database.currentUserId, Database.currentItemId,rate);
				currentCustomer.getMyReview().add(newReview);
				currentItem.getReviewList().add(newReview);
				
				dispose();
				new CustomerViewPurchaseDetail().setVisible(true);
			}
		});
		
		//"Back" button
		back = new JButton("Back");
	Setup.leftButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerViewPurchaseDetail().setVisible(true);
			}
		});
		getContentPane().add(back);		
		
		//title
		JLabel lblReview = new JLabel("REVIEW");
		Setup.title(lblReview);
		//lblReview.setFont(new Font("Tahoma", Font.BOLD, 18));
		//lblReview.setBounds(101, 59, 80, 28);
		getContentPane().add(lblReview);
		
		
		
		
		
		//JLabel label = new JLabel("<html><span bgcolor=\"yellow\">This is the label text                             </span></html>");//
		int size = Database.getItemByID(Database.currentItemId).getItemName().length();
		JLabel label = new JLabel( Database.getItemByID(Database.currentItemId).getItemName());
		label.setForeground(SystemColor.textHighlight);
		label.setForeground(new Color(0, 0, 255));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(338, 107, 13*size, 30);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new CustomerViewItemDetail().setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setForeground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				label.setForeground(new Color(0, 0, 255));
				//label.setBackground(Color.white);
				//label.setOpaque(false);
			}
		});
		getContentPane().add(label);
		
		
		
		//Setup.mainFrame(this);
		
		
		

			


	}
}
