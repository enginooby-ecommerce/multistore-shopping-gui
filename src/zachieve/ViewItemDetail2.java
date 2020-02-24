package zachieve;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Setup;
import main.Customer;
import main.Database;
import main.Dislike;
import main.Item;
import main.ItemInCart;
import main.Like;
import main.Review;

public class ViewItemDetail2 extends JPanel {

	int count = 0; // to change quantity

	/**
	 * Create the panel.
	 */
	public ViewItemDetail2() {

		setBounds(0, 0, 1200, 600);
		setBackground(Database.background);
		setLayout(null);

		Item currentItem = Database.getItemByID(Database.currentItemId);
		Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
		List<ItemInCart> currentCart = currentCustomer.getCart();
		List<JLabel> review = new ArrayList<JLabel>();
		List<JLabel> contentReview = new ArrayList<JLabel>();
		List<Review> review_list = currentItem.getReviewList();
		List<JButton> like = new ArrayList<>();
		List<JButton> dislike = new ArrayList<>();
		List<JButton> likeView = new ArrayList<JButton>();
		List<JButton> dislikeView = new ArrayList<JButton>();
		JLabel content, quality;
		JTextField qualityField;
		JButton addToCart, back;

		// title line
		JSeparator separator = new JSeparator();
		separator.setBounds(300, 85, 601, 10);
		add(separator);

		// add close button
		JLabel x = new JLabel();
		add(x);
		x.setForeground(Color.BLACK);
		x.setBounds(858, 13, 32, 32);
		x.setFont(new Font("Tahoma", Font.PLAIN, 24));
		x.setHorizontalAlignment(SwingConstants.CENTER);
		x.setVerticalAlignment(SwingConstants.CENTER);
		x.setIcon(new ImageIcon(Setup.class.getResource(("/images/close.png"))));
		x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				x.setOpaque(true);
				x.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				x.setBackground(Color.white);
				x.setOpaque(false);
			}
		});

		// add minimize button
		JLabel minimize = new JLabel();
		add(minimize);
		minimize.setForeground(Color.BLACK);
		minimize.setBounds(820, 13, 32, 32);
		minimize.setFont(new Font("Tahoma", Font.PLAIN, 24));
		minimize.setHorizontalAlignment(SwingConstants.CENTER);
		minimize.setVerticalAlignment(SwingConstants.CENTER);
		minimize.setIcon(new ImageIcon(Setup.class.getResource(("/images/minimize.png"))));
		minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				((JFrame) (getTopLevelAncestor())).setState(Frame.ICONIFIED);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				minimize.setOpaque(true);
				minimize.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				minimize.setBackground(Color.white);
				minimize.setOpaque(false);
			}
		});

		if (!currentCustomer.getCart().isEmpty() && !Database.isGuest) {
			for (int i = 0; i < currentCustomer.getCart().size(); i++) {
				if (Database.currentItemId == currentCustomer.getCart().get(i).getItemID()) {
					count = currentCart.get(i).getQuality();
				}
			}
		}

		// display item name
		content = new JLabel(currentItem.getItemName().toUpperCase());
		content.setFont(new Font("Tahoma", Font.BOLD, 20));
		content.setBounds(338, 116, 350, 40);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(338, 165, 524, 260);

		JPanel descriptionTab = new JPanel();
		tabbedPane.addTab("Description", null, descriptionTab, null);
		descriptionTab.setLayout(null);

		JLabel description = new JLabel(currentItem.getDiscription());
		description.setBounds(23, 13, 473, 192);
		descriptionTab.add(description);

		GridLayout layout = new GridLayout(0, 3);
		layout.setHgap(7);
		layout.setVgap(2);

		// review tab
		// JScrollPane reviewTab = new JScrollPane();
		JPanel reviewTab = new JPanel();
		// tabbedPane.addTab("Review", null, reviewTab, null);
		reviewTab.setLayout(layout);
		if (!review_list.isEmpty()) {
			for (int i = 0; i < review_list.size(); i++) {
				final int index = i;

				// review content
				// review.add(new JLabel(list.get(i).reviewToString()));
				review.add(new JLabel("  " + Database.getCustomerByID(review_list.get(i).getCustomerId()).getUsername()
						+ " (" + review_list.get(i).getRate() + (char) 9733 + ")", SwingConstants.CENTER));
				// review.get(i).setBounds(337, 250 + 50 * i, 300, 40);
				// review.get(i).setBounds(50, 10 + 50 * i, 100, 40);
				reviewTab.add(review.get(i));

				// "Like" button
				like.add(new JButton(""));
				// like.get(i).setBounds(670, 265 + 50 * i, 95, 35);
				like.get(i).setIcon(new ImageIcon(CustomerViewItemDetail.class.getResource("/images/like32.png")));
				like.get(i).setBounds(200, 265 + 50 * i, 95, 35);
				like.get(i).setFont(new Font("Tahoma", Font.BOLD, 12));

				reviewTab.add(like.get(i));

				// view like button
				if (review_list.get(i).getLike() == null) {
					review_list.get(i).setLike(new Like());
				}
				likeView.add(new JButton(review_list.get(i).getLike().getNumber() + ""));
				likeView.get(i).setBounds(337, 277 + 50 * i, 80, 20);

				like.get(i).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (Database.isGuest) {
							Setup.notLoggedIn();

						} else {
							if (like.get(index).getText().equals("")) {
								// toggle text
								like.get(index).setText(" ");
								like.get(index).setIcon(
										new ImageIcon(CustomerViewItemDetail.class.getResource("/images/liked32.png")));
								// System.out.println(list.get(index).getLike().getNumber());

								if (review_list.get(index).getLike() == null) {
									review_list.get(index).setLike(new Like());
								}
								// add current customer to like list
								review_list.get(index).getLike().getInteractorList().add(currentCustomer);
								// increase number of like
								review_list.get(index).getLike().increase();
								// update like view
								likeView.get(index).setText("" + review_list.get(index).getLike().getNumber());
								// disable "Dislike" button
								dislike.get(index).setEnabled(false);

							} else {
								like.get(index).setText("");
								like.get(index).setIcon(
										new ImageIcon(CustomerViewItemDetail.class.getResource("/images/like32.png")));
								dislike.get(index).setEnabled(true);
								review_list.get(index).getLike().getInteractorList().remove(currentCustomer);
								review_list.get(index).getLike().decrease();
								// update like view
								likeView.get(index).setText("" + review_list.get(index).getLike().getNumber());

							}
						}

					}
				});

				// if current customer already liked
				if (review_list.get(i).getLike().getNumber() != 0
						&& review_list.get(i).getLike().getInteractorList().contains(currentCustomer)) {
					like.get(i).setText(" ");
					like.get(i).setIcon(new ImageIcon(CustomerViewItemDetail.class.getResource("/images/liked32.png")));
					// create and disable "Dislike" button
					dislike.add(new JButton(""));
					dislike.get(index)
							.setIcon(new ImageIcon(CustomerViewItemDetail.class.getResource("/images/dislike32.png")));
					dislike.get(index).setEnabled(false);

				}

				reviewTab.add(likeView.get(i));
				likeView.get(i).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (Database.isGuest) {
							Setup.notLoggedIn();

						} else {
							// TODO: StringBuilder
							String content = "";
							for (Customer customer : review_list.get(index).getLike().getInteractorList()) {
								content += customer.getUsername() + " ";
							}
							JOptionPane.showMessageDialog(null, content, "List of likes",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});

				contentReview.add(new JLabel("\"" + review_list.get(i).getContent() + "\"", SwingConstants.CENTER));
				contentReview.get(i).setFont(new Font("Tahoma", Font.ITALIC, 12));
				reviewTab.add(contentReview.get(i));

				// "Dislike" button
				dislike.add(new JButton(""));

				dislike.get(i)
						.setIcon(new ImageIcon(CustomerViewItemDetail.class.getResource("/images/dislike32.png")));
				// dislike.get(i).setBounds(770, 265 + 50 * i, 95, 35);
				dislike.get(i).setBounds(300, 265 + 50 * i, 95, 35);
				dislike.get(i).setFont(new Font("Tahoma", Font.BOLD, 12));
				reviewTab.add(dislike.get(i));

				// view dislike button
				if (review_list.get(i).getDislike() == null) {
					review_list.get(i).setDislike(new Dislike());
				}
				if (review_list.get(i).getDislike() != null) {
					dislikeView.add(new JButton("" + review_list.get(i).getDislike().getNumber()));
					dislikeView.get(i).setBounds(420, 277 + 50 * i, 100, 20);
					reviewTab.add(dislikeView.get(i));

					dislike.get(i).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (Database.isGuest) {
								Setup.notLoggedIn();

							} else {
								if (dislike.get(index).getText().equals("")) {
									dislike.get(index).setText(" ");
									dislike.get(index).setIcon(new ImageIcon(
											CustomerViewItemDetail.class.getResource("/images/disliked32.png")));
									if (review_list.get(index).getDislike() == null) {
										review_list.get(index).setDislike(new Dislike());
									}
									review_list.get(index).getDislike().getInteractorList().add(currentCustomer);
									review_list.get(index).getDislike().increase();
									dislikeView.get(index)
											.setText("" + review_list.get(index).getDislike().getNumber());
									like.get(index).setEnabled(false);

								} else {
									dislike.get(index).setText("");
									dislike.get(index).setIcon(new ImageIcon(
											CustomerViewItemDetail.class.getResource("/images/dislike32.png")));
									review_list.get(index).getDislike().getInteractorList().remove(currentCustomer);
									review_list.get(index).getDislike().decrease();
									dislikeView.get(index)
											.setText("" + review_list.get(index).getDislike().getNumber());
									like.get(index).setEnabled(true);

								}
							}
						}
					});

					// if current customer already disliked
					if (review_list.get(i).getDislike() != null
							&& review_list.get(i).getDislike().getInteractorList().contains(currentCustomer)) {
						dislike.get(i).setText(" ");
						dislike.get(index).setIcon(
								new ImageIcon(CustomerViewItemDetail.class.getResource("/images/disliked32.png")));
						// create and disable "Like" button
						like.add(new JButton(""));
						like.get(index)
								.setIcon(new ImageIcon(CustomerViewItemDetail.class.getResource("/images/like32.png")));
						like.get(index).setEnabled(false);
					}

					dislikeView.get(i).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (Database.isGuest) {
								Setup.notLoggedIn();

							} else {
								// TODO: StringBuilder
								String content = "";
								for (Customer customer : review_list.get(index).getDislike().getInteractorList()) {
									content += customer.getUsername() + " ";
								}
								JOptionPane.showMessageDialog(null, content, "List of dislikes",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
				}
				// JSeparator separator = new JSeparator();
				// separator.setBounds(0, 50 + 50 * i, 500, 20);
				reviewTab.add(new JSeparator());
				reviewTab.add(new JLabel());
				reviewTab.add(new JLabel());
			}
		}
		// JScrollPane scroll = new JScrollPane(reviewTab);
		// scroll.setPreferredSize(new Dimension(300, 300));
		JScrollPane scroll = new JScrollPane(reviewTab, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(300, 300));
		tabbedPane.add(scroll, "Review");
		// tabbedPane.addTab("Review", null, reviewTab, null);

		JScrollPane qaTab = new JScrollPane();
		tabbedPane.addTab("Q&A", null, qaTab, null);
		qaTab.setLayout(null);

		// add tabs
		add(tabbedPane);
		// tabbedPane.setSelectedIndex(Database.currentTab);

		quality = new JLabel("Quantity: ");
		quality.setForeground(new Color(0, 0, 0));
		quality.setFont(new Font("Tahoma", Font.BOLD, 14));
		quality.setBounds(667, 438, 100, 40);

		qualityField = new JTextField(count + "");
		qualityField.setBounds(740, 439, 80, 40);

		// in total
		JLabel total = new JLabel("In total: $" + count * currentItem.getItemPrice());
		total.setForeground(new Color(0, 0, 0));
		total.setFont(new Font("Tahoma", Font.BOLD, 14));
		total.setBounds(667, 473, 100, 40);
		add(total);

		// "Add to cart" button
		addToCart = new JButton("Add to cart");
		Setup.rightButton(addToCart);
		addToCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Database.isGuest) {
					Setup.notLoggedIn();

				} else {
					// TODO: set limit for quality
					int number = Integer.parseInt(qualityField.getText());
					if (currentItem.getInStock() < number) {
						JOptionPane.showMessageDialog(null, "Out of stock", "", JOptionPane.INFORMATION_MESSAGE);
						count = currentItem.getInStock();
					} else {

						if (!currentCustomer.getCart().isEmpty()) {
							for (int i = 0; i < currentCustomer.getCart().size(); i++) {
								if (Database.currentItemId == currentCustomer.getCart().get(i).getItemID()) {
									currentCart.get(i).setQuality(number);
									((JFrame) (addToCart.getTopLevelAncestor())).dispose();
									new CustomerShopping().setVisible(true);
									return;
								}
							}

						}
						
						
						currentCustomer.getCart().add(new ItemInCart(Database.currentItemId, number));

						((JFrame) (addToCart.getTopLevelAncestor())).dispose();
						new CustomerShopping().setVisible(true);
					}
				}
			}
		});

		// + button
		JButton plus = new JButton("+");
		plus.setBounds(822, 438, 41, 20);
		add(plus);

		// - button
		JButton minus = new JButton("-");
		minus.setBounds(822, 458, 41, 20);
		add(minus);
		if (count == 0) {
			minus.setEnabled(false);
		}

		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(qualityField.getText());
				if (currentItem.getInStock() < number + 1) {
					JOptionPane.showMessageDialog(null, "Out of stock", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					count++;
					qualityField.setText(count + "");
					total.setText("In total: $" + currentItem.getItemPrice() * count);
					minus.setEnabled(true);
				}
			}
		});

		minus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				qualityField.setText(count + "");
				total.setText("In total: $" + currentItem.getItemPrice() * count);
				if (count == 0) {
					minus.setEnabled(false);
				}
			}
		});

		// "Back" button
		back = new JButton("Back");
		Setup.leftButton(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				((JFrame) (back.getTopLevelAncestor())).dispose();
				if (Database.isGuest) {
					// ((JFrame) (back.getTopLevelAncestor())).revalidate();
					// ((JFrame) (back.getTopLevelAncestor())).repaint();
					new GuestHome();

					// ((JFrame) (back.getTopLevelAncestor())).setVisible(false);

				} else {
					// Database.currentTab=0;
					new CustomerShopping().setVisible(true);
					// ((JFrame) (back.getTopLevelAncestor())).dispose();
					// ((JFrame) (back.getTopLevelAncestor())).setVisible(false);
				}
			}
		});

		add(content);
		add(quality);
		add(qualityField);
		add(addToCart);
		add(back);

		// title
		JLabel lblItemInformation = new JLabel("ITEM INFORMATION");
		Setup.title(lblItemInformation);
		// lblItemInformation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		// lblItemInformation.setBounds(337, 32, 331, 37);
		add(lblItemInformation);

		JLabel lblInStock = new JLabel("In stock: " + currentItem.getInStock());
		lblInStock.setForeground(new Color(0, 0, 0));
		lblInStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInStock.setBounds(338, 440, 100, 40);
		add(lblInStock);

		JLabel lblPrice = new JLabel("Price: $" + currentItem.getItemPrice());
		lblPrice.setForeground(new Color(0, 0, 0));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(338, 473, 100, 40);
		add(lblPrice);

		JLabel lblAskAQuestion = new JLabel("Ask a question");
		lblAskAQuestion.setForeground(new Color(0, 0, 0));
		lblAskAQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAskAQuestion.setBounds(711, 137, 131, 29);
		add(lblAskAQuestion);

		JLabel lblAddToFavorite = new JLabel("Add to favorite");
		lblAddToFavorite.setForeground(new Color(0, 0, 0));
		lblAddToFavorite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddToFavorite.setBounds(711, 102, 131, 29);
		add(lblAddToFavorite);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(812, 103, 50, 25);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Database.isGuest) {
					Setup.notLoggedIn();

				} else {

					Setup.underDev();
				}
			}
		});

		JButton button = new JButton("");
		button.setBounds(812, 141, 50, 25);
		add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Database.isGuest) {
					Setup.notLoggedIn();

				} else {
					Setup.underDev();
				}
			}
		});

	}

}
