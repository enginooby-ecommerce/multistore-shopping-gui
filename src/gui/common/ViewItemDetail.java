package gui.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileSystemView;

import asystem.Database;
import asystem.Setup;
import gui.component.DialogMessage;
import gui.component.InfoDialog;
import gui.customer.Shopping;
import main.Customer;
import main.Dislike;
import main.Item;
import main.ItemInCart;
import main.Like;
import main.Retailer;
import main.Review;

public class ViewItemDetail extends JPanel {

	int count = 0; // to change quantity
	Item currentItem = Database.getItemSuperStockById(Database.currentItemId);
	Customer currentCustomer = Database.getCustomerByID(Database.currentUserId);
	
	List<Retailer> subscription_list = currentCustomer.getMySubscriptionList();	
	Retailer currentRetailer = Database.getRetailerByID(currentItem.getRetailerId());
	int subscriber_count = currentRetailer.getMy_subscriber_id_list().size();


	public ViewItemDetail() {

		Setup.page(this);
		//this.setLayout(null);
		//this.setBounds(0, 0, 1000, 1000);

		Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel, empty;
		blackline = BorderFactory.createLineBorder(Color.black, 3);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();

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

		// TITLE
		JLabel lblItemInformation = new JLabel("ITEM INFORMATION");
		Setup.title2(lblItemInformation);
		add(lblItemInformation);

		// ITEM NAME
		content = new JLabel(currentItem.getItemName().toUpperCase());
		content.setForeground(Setup.colorPageText);
		content.setFont(new Font(Setup.font, Font.BOLD, 20));
		// content.setBounds(38, 116, 350, 40);
		content.setBounds(120, 95, 350, 40);
		add(content);

		// ITEM ICON
		JLabel icon = new JLabel("", SwingConstants.CENTER);
		icon.setBounds(38, 100, 60, 60);
		icon.setBorder(blackline);
		add(icon);
		if (currentItem.getIcon() != null) {
			icon.setIcon(Setup.getScaledIcon(currentItem.getIcon(), 40, 40));
		}

		// IN STOCK
		JLabel lblInStock = new JLabel("In stock: " + currentItem.getInStock());//,SwingConstants.CENTER);
		lblInStock.setForeground(Setup.colorPageText);
		lblInStock.setFont(new Font(Setup.font, Font.PLAIN, 14));
		lblInStock.setBounds(223, 134, 100, 30);
		add(lblInStock);
		//lblInStock.setBorder(raisedetched);

		// PRICE
		JLabel lblPrice = new JLabel("Price: $" + currentItem.getItemPrice());//,SwingConstants.CENTER);
		lblPrice.setForeground(Setup.colorPageText);
		lblPrice.setFont(new Font(Setup.font, Font.PLAIN, 14));
		lblPrice.setBounds(120, 134, 100, 30);
		add(lblPrice);
		//lblPrice.setBorder(raisedetched);

		// FAVORITE
		JLabel lblAddToFavorite = new JLabel("Add to favorite");
		lblAddToFavorite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddToFavorite.setForeground(Setup.colorPageText);
		lblAddToFavorite.setFont(new Font(Setup.font, Font.PLAIN, 14));
		lblAddToFavorite.setBounds(390, 102, 115, 29);
		add(lblAddToFavorite);

		JLabel favorite = new JLabel("");
		Setup.iconEffectWithoutOpaque(favorite);
		favorite.setBounds(512, 98, 38, 38);
		favorite.setBackground(Color.BLACK);
		add(favorite);
		ImageIcon favor = new ImageIcon(ViewItemDetail.class.getResource(("/images/icons8_heart_34px_1.png")));
		ImageIcon unfavor = new ImageIcon(ViewItemDetail.class.getResource(("/images/icons8_heart_34px_2.png")));
		favorite.setIcon(favor);
		if (currentCustomer.itemInFavoriteList(Database.currentItemId)) {
			favorite.setIcon(unfavor);
		}
		favorite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Database.isGuest) {
					Setup.notLoggedIn();
				} else if (currentCustomer.itemInFavoriteList(Database.currentItemId)) {
					Setup.playSound("click2.wav");
					currentCustomer.getMy_favorite_id().remove((Integer)Database.currentItemId);
					Database.removeFavorite(Database.currentUserId, Database.currentItemId);
					favorite.setIcon(favor);

				} else {
					Setup.playSound("click2.wav");
					currentCustomer.getMy_favorite_id().add((Integer)Database.currentItemId);
					Database.addFavorite(Database.currentUserId, Database.currentItemId);
					favorite.setIcon(unfavor);
				}
			}

		});

		// ASK QUESTION
		JLabel lblAskAQuestion = new JLabel("Ask a question");
		lblAskAQuestion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAskAQuestion.setForeground(Setup.colorPageText);
		lblAskAQuestion.setFont(new Font(Setup.font, Font.PLAIN, 14));
		lblAskAQuestion.setBounds(388, 137, 115, 29);
		add(lblAskAQuestion);

		JLabel ask = new JLabel("");
		Setup.iconEffectWithoutOpaque(ask);
		ask.setBounds(512, 135, 38, 38);
		ask.setBackground(Color.BLACK);
		add(ask);
		ImageIcon ask_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource(("/images/icons8_ask_question_34px.png"))),34,34);	
		ask.setIcon(ask_icon);
	
		ask.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Database.isGuest) {
					Setup.notLoggedIn();
				}  else {
					Setup.underDev();
				}
			}
		});
		
		//NUMBER OF BUYERS
		JLabel bought_num = new JLabel(currentItem.getBuyer_list().size()+" people bought this");
		bought_num.setForeground(Setup.colorPageText);
		bought_num.setFont(new Font(Setup.font, Font.ITALIC, 12));
		bought_num.setBounds(411, 175, 150, 29);
		add(bought_num);
		
		
		
		int mod=80;
		// RETAILER NAME
		Retailer current_retailer = Database.getRetailerByID(currentItem.getRetailerId());
		JLabel retailer = new JLabel(current_retailer.getUsername());
		Setup.linkEffect(retailer);
		// retailer.setForeground(Setup.text_color);
		retailer.setFont(new Font(Setup.font, Font.PLAIN, 18));
		retailer.setBounds(38+mod, 450, 100, 40);
		add(retailer);
		
		// RETAILER AVATAR
				JLabel avt = new JLabel("", SwingConstants.CENTER);
				avt.setBounds(38, 458, 60, 60);
				avt.setBorder(blackline);
				add(avt);
				ImageIcon ava_image = current_retailer.getAvt();
				if (ava_image == null) {
					ava_image = new ImageIcon(ViewItemDetail.class.getResource("/picture/avt/icons8_shop_100px.png"));
					
				}
				avt.setIcon(Setup.getScaledIcon(ava_image, 40, 40));

		// MESSAGE
		JLabel message = new JLabel();
		Setup.iconEffectWithoutOpaque(message);
		message.setBounds(26+mod, 480, 50, 50);
		ImageIcon message_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/icons8_chat_40px_1.png")),30,30);
		message.setIcon(message_icon);
		add(message);
		
		message.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Database.isGuest) {
					Setup.notLoggedIn();

				} else {

					DialogMessage dialog = new DialogMessage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		
		// SUBSCRIBE
		
		
		JPanel heart_panel = new JPanel();
		heart_panel.setOpaque(false);
		
		heart_panel.setBounds(134+mod, 455, 40, 50);
		heart_panel.setLayout(null);
		ImageIcon heart_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/icons8_heart_balloon_35px.png")),35,35);
		JLabel heart = new JLabel("",SwingConstants.CENTER);
		heart.setIcon(heart_icon);
		heart.setBounds(0, 0, 40, 50);
		//heart.setBorder(raisedetched);
		JLabel sub_num = new JLabel(subscriber_count+"");
		sub_num.setBounds(16, 8, 20, 20);
		sub_num.setForeground(Color.WHITE);
		sub_num.setFont(new Font(Setup.font, Font.BOLD, 10));
		heart_panel.add(heart);
		heart.add(sub_num);
		add(heart_panel);
		Setup.shakePanel(heart_panel,10);
		
		
		
		
		JPanel sub = new JPanel();
		
	sub.setBounds(80+mod, 493, 75, 25);
	
	sub.setBackground(Color.DARK_GRAY);//new Color(170, 0, 0));
			
				JLabel subscribe = new JLabel();
				subscribe.setFont(new Font(Setup.font, Font.BOLD, 12));
				subscribe.setForeground(Color.WHITE);
				//subscribe.setBorder(loweredetched);
				subscribe.setBounds(0, 0, 75, 25);
				
				//if (subscriber_list.contains(currentCustomer) && !Database.isGuest) {
				if (subscription_list.contains(currentRetailer) && !Database.isGuest) {
					subscribe.setText("Subscribed");
					
					//subscribe.setBackground(Color.DARK_GRAY);
					//subscribe.setForeground(Color.WHITE);
					
				} else {
					subscribe.setText("Subscribe");
					
					
					
					//subscribe.setBackground(Color.DARK_GRAY);
				}
				
				Setup.iconEffectWithoutOpaque(subscribe);
				
				sub.add(subscribe);
				add(sub);
				subscribe.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (!Database.isGuest) {
							if (subscribe.getText().equals("Subscribe")) {
								currentRetailer.getMy_subscriber_id_list().add(Database.currentUserId);
								//subscriber_list.add(currentCustomer);
								//subscription_list.add(currentRetailer);
								currentCustomer.getMy_subscription_id().add(currentRetailer.getId());
								subscribe.setText("Subscribed");
								sub_num.setText(++subscriber_count+"");
								Database.addSubscribe(Database.currentUserId, currentRetailer.getId());
								Setup.shakePanel(heart_panel,2);
							} else {
								//subscriber_list.remove(currentCustomer);
								//subscription_list.remove(currentRetailer);
								currentRetailer.getMy_subscriber_id_list().remove((Integer)Database.currentUserId);
								currentCustomer.getMy_subscription_id().remove((Integer)currentRetailer.getId());
								subscribe.setText("Subscribe");
								sub_num.setText(--subscriber_count+"");
								Database.removeSubscribe(Database.currentUserId, currentRetailer.getId());
								Setup.shakePanel(heart_panel,2);
							}
						} else {
							Setup.notLoggedIn();
						}
					}
				});
				
		
		

		/********* TAB SECTION *************/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 180, 524, 255);

		// DESCRIPTION TAB
		JPanel descriptionTab = new JPanel();
		// descriptionTab.setForeground(Setup.text_color);
		ImageIcon des_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/icons8_information_20px_1.png")),15,15);
		tabbedPane.addTab("Description", null, descriptionTab, null);
		descriptionTab.setLayout(null);
		// use html tag to warp and align justify text in jlabel
		JLabel description = new JLabel("<html><p align=\"justify\">" + currentItem.getDescription() + "</p></html>");
		description.setForeground(Setup.colorPageText);
		description.setFont(new Font(Setup.font, Font.PLAIN, 12));
		description.setBounds(13, 10, 500, 192);
		// description.setHorizontalAlignment(JLabel.LEFT);
		description.setVerticalAlignment(JLabel.TOP);
		// description.setBorder(blackline);
		descriptionTab.add(description);

		
		JPanel pictures = new JPanel();
		ImageIcon pic_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/icons8_picture_20px.png")),20,20);
		tabbedPane.addTab("Pictures", null,pictures, null);
		
		
		// REVIEW TAB
		// JScrollPane reviewTab = new JScrollPane();
		GridLayout layout = new GridLayout(0, 3);
		layout.setHgap(7);
		layout.setVgap(2);
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

				like.get(i).setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/like32.png")));

				like.get(i).setBounds(200, 265 + 50 * i, 95, 35);
				like.get(i).setFont(new Font(Setup.font, Font.BOLD, 12));

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
										new ImageIcon(ViewItemDetail.class.getResource("/images/liked32.png")));
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
								like.get(index)
										.setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/like32.png")));
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
						&& review_list.get(i).getLike().getInteractorList().contains(currentCustomer)
						&& !Database.isGuest) {
					like.get(i).setText(" ");
					like.get(i).setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/liked32.png")));
					// create and disable "Dislike" button
					dislike.add(new JButton(""));
					dislike.get(index)
							.setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/dislike32.png")));
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
				contentReview.get(i).setFont(new Font(Setup.font, Font.ITALIC, 12));
				reviewTab.add(contentReview.get(i));

				// "Dislike" button
				dislike.add(new JButton(""));

				dislike.get(i).setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/dislike32.png")));
				// dislike.get(i).setBounds(770, 265 + 50 * i, 95, 35);
				dislike.get(i).setBounds(300, 265 + 50 * i, 95, 35);
				dislike.get(i).setFont(new Font(Setup.font, Font.BOLD, 12));
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
									dislike.get(index).setIcon(
											new ImageIcon(ViewItemDetail.class.getResource("/images/disliked32.png")));
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
									dislike.get(index).setIcon(
											new ImageIcon(ViewItemDetail.class.getResource("/images/dislike32.png")));
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
							&& review_list.get(i).getDislike().getInteractorList().contains(currentCustomer)
							&& !Database.isGuest) {
						dislike.get(i).setText(" ");
						dislike.get(index)
								.setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/disliked32.png")));
						// create and disable "Like" button
						like.add(new JButton(""));
						like.get(index).setIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/like32.png")));
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
		JScrollPane scroll = new JScrollPane(reviewTab);//, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(300, 300));
		ImageIcon review_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/icons8_star_20px.png")),15,15);
		//tabbedPane.add(scroll, "Review");
		tabbedPane.addTab("Review ", null, scroll, null);
	

		
		//Q&A TAB
		JScrollPane qaTab = new JScrollPane();
		ImageIcon qa_icon = Setup.getScaledIcon(new ImageIcon(ViewItemDetail.class.getResource("/images/icons8_question_mark_20px_1.png")),15,15);

		tabbedPane.addTab("Q&A", null, qaTab, null);
		qaTab.setLayout(null);
		add(tabbedPane);
		
		//QUANTITY
		quality = new JLabel("Quantity: ");
		quality.setForeground(Setup.colorPageText);
		quality.setFont(new Font(Setup.font, Font.PLAIN, 14));
		quality.setBounds(367, 448, 100, 40);

		qualityField = new JTextField(count + "");
		qualityField.setHorizontalAlignment(JTextField.CENTER);
		qualityField.setBounds(430, 449, 90, 40);

		//SUBTOTAL
		JLabel subtotalLbl = new JLabel("Subtotal:");
		subtotalLbl.setForeground(Setup.colorPageText);
		subtotalLbl.setFont(new Font(Setup.font, Font.PLAIN, 14));
		subtotalLbl.setBounds(367, 495, 100, 40);
		add(subtotalLbl);
		JLabel subtotal = new JLabel("$" + count * currentItem.getItemPrice());
		subtotal.setForeground(Setup.colorPageText);
		subtotal.setFont(new Font(Setup.font, Font.PLAIN, 25));
		subtotal.setBounds(435, 490, 100, 40); //455
		add(subtotal);

		//ADD TO CART
		addToCart = new JButton("Add to cart");
		Setup.buttonEffect(addToCart);
		Setup.rightButton2(addToCart);
		addToCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(qualityField.getText());
				if (Database.isGuest) {
					Setup.notLoggedIn();

				} else if (number < 1) {
				} else {
					if (currentItem.getInStock() < number) {
						new InfoDialog("Out of stock");
						count = currentItem.getInStock();
					} else {
						Z.page = new Shopping();
						if (!currentCustomer.getCart().isEmpty()) {
							for (int i = 0; i < currentCustomer.getCart().size(); i++) {
								if (Database.currentItemId == currentCustomer.getCart().get(i).getItemID()) {
									Database.updateQuantityItemInCart(currentCart.get(i), number);
									currentCart.get(i).setQuantity(number);
									Z.pageSlide(2);
									return;
								}
							}
						}
						ItemInCart item =new ItemInCart(Database.countItemInCartId++,Database.currentItemId, number,Database.currentUserId);
						currentCustomer.getCart().add(item);
						Database.addItemInCart(item);
						Z.pageSlide(2);
					}
				}
			}
		});

		// + button
		JButton plus = new JButton("+");
		Setup.buttonEffect(plus);
		plus.setBounds(522, 448, 41, 20);
		add(plus);

		// - button
		JButton minus = new JButton("-");
		Setup.buttonEffect(minus);
		minus.setBounds(522, 468, 41, 20);
		add(minus);
		if (count == 0) {
			minus.setEnabled(false);
		}

		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(qualityField.getText());
				if (currentItem.getInStock() < number + 1) {
					new InfoDialog("Out of stock");
				} else {
					count++;
					qualityField.setText(count + "");
					subtotal.setText("$" + currentItem.getItemPrice() * count);
					minus.setEnabled(true);
				}
			}
		});

		minus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				qualityField.setText(count + "");
				subtotal.setText("$" + currentItem.getItemPrice() * count);
				if (count == 0) {
					minus.setEnabled(false);
				}
			}
		});

		// BACK
		back = new JButton("Back");
		Setup.buttonEffect(back);
		Setup.leftButton2(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Z.page = new HomeOri();
				Z.pageSlide(2);

			}
		});
		add(quality);
		add(qualityField);
		add(addToCart);
		add(back);

	}

	public Item getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Item currentItem) {
		this.currentItem = currentItem;
	}

}
