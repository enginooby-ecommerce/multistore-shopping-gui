package gui.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import asystem.Database;
import asystem.Setup;
import gui.component.ClickAwayDialog;
import gui.component.ConfirmDialog;


public class Headbar extends JPanel {

	/**
	 * Create the 
	 */
	public Headbar() {
		setBackground(Setup.colorPage);
		// setOpaque(false);
		setLayout(null);
		

		
		
		
		
		// add close button
				JLabel x = new JLabel();
				add(x);
				x.setForeground(Color.BLACK);
				x.setBounds(568+Setup.size_hor, 10, 28, 28);
				x.setFont(new Font(Setup.font, Font.PLAIN, 24));
				x.setHorizontalAlignment(SwingConstants.CENTER);
				x.setVerticalAlignment(SwingConstants.CENTER);
				x.setIcon(new ImageIcon(Setup.class.getResource(("/images/close25.png"))));
				x.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Setup.playSound("click2.wav");
						
						new ConfirmDialog("Exit program?") {
							@Override
							public void confirmAction() {
								Setup.playSound("shutdown.wav");
								  new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
										public int i = 50;
										@Override
										public void run() {
											i--;
											Setup.getWindow().setOpacity(i * 0.02f); 
											if (i == 0) {
												cancel();
												Database.saveDatabase();
												System.exit(0);
											}
										}
									}, 0, 10);
							}
						};
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
				minimize.setBounds(540+Setup.size_hor, 10, 28, 28);
				minimize.setFont(new Font(Setup.font, Font.PLAIN, 24));
				minimize.setHorizontalAlignment(SwingConstants.CENTER);
				minimize.setVerticalAlignment(SwingConstants.CENTER);
				minimize.setIcon(new ImageIcon(Setup.class.getResource(("/images/minimize25.png"))));
				minimize.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						Setup.playSound("minimize.wav");
						new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
							public int i = 0;
							@Override
							public void run() {
								i++;
								Setup.getWindow().setLocation(Setup.getWindow().getX(), Setup.getWindow().getY()+20);
								if (i == 50) {
									cancel();
									 Setup.getWindow().setState(Frame.ICONIFIED);
									 Setup.getWindow().setLocationRelativeTo(null);
								}
							}
						}, 0, 5);
					        
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

				// add pin button
				JLabel resize = new JLabel();
				if (Setup.window_is_big) {
					resize.setIcon(new ImageIcon(Setup.class.getResource(("/images/resize-minimize20.png"))));
				} else {
					resize.setIcon(new ImageIcon(Setup.class.getResource(("/images/resize-maximize20.png"))));
				}

				add(resize);
				resize.setForeground(Color.BLACK);
				resize.setBounds(512+Setup.size_hor, 10, 28, 28);
				resize.setFont(new Font(Setup.font, Font.PLAIN, 24));
				resize.setHorizontalAlignment(SwingConstants.CENTER);
				resize.setVerticalAlignment(SwingConstants.CENTER);
				
				

				resize.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Setup.playSound("click2.wav");
						if (Setup.window_is_big) {
							
							//resize.setIcon(new ImageIcon(Setup.class.getResource(("/images/resize-maximize20.png"))));
							Setup.window_is_big=false;
							Z.smaller();
							

						} else {
							//resize.setIcon(new ImageIcon(Setup.class.getResource(("/images/resize-minimize20.png"))));
						
							
							Setup.window_is_big=true;
							Z.bigger();
						}

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						resize.setOpaque(true);
						resize.setBackground(Color.GRAY);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						resize.setBackground(Color.white);
						resize.setOpaque(false);
					}
				});
				
				
			
				
				//pin button
				JLabel pin = new JLabel();
				pin.setToolTipText(Setup.pinState);
				pin.setHorizontalAlignment(SwingConstants.CENTER);
				pin.setVerticalAlignment(SwingConstants.CENTER);
				if (Setup.pinState.equals("Pin window")) {
					pin.setIcon(Setup.getScaledIcon(new ImageIcon(Setup.class.getResource(("/images/pin.png"))),20,20));
				} else {
					pin.setIcon(Setup.getScaledIcon(new ImageIcon(Setup.class.getResource(("/images/unpin.png"))),20,20));
				}		
				add(pin);
				pin.setForeground(Color.BLACK);
				pin.setBounds(456+Setup.size_hor, 10, 28, 28); //483
				pin.setFont(new Font(Setup.font, Font.PLAIN, 24));
				
				
				

				pin.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Setup.playSound("click2.wav");
						if (Setup.pinState.equals("Pin window")) {
							((JFrame) (pin.getTopLevelAncestor())).setAlwaysOnTop(true);
							pin.setIcon(Setup.getScaledIcon(new ImageIcon(Setup.class.getResource(("/images/unpin.png"))),20,20));
							Setup.pinState = "Unpin window";
							pin.setToolTipText(Setup.pinState);

						} else {
							((JFrame) (pin.getTopLevelAncestor())).setAlwaysOnTop(false);
							pin.setIcon(Setup.getScaledIcon(new ImageIcon(Setup.class.getResource(("/images/pin.png"))),20,20));
							Setup.pinState = "Pin window";
							pin.setToolTipText(Setup.pinState);
						}

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						pin.setOpaque(true);
						pin.setBackground(Color.GRAY);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						pin.setBackground(Color.white);
						pin.setOpaque(false);
					}
				});
				
				//SETTING
				JLabel setting = new JLabel();
				setting.setToolTipText(Setup.settingState);
				setting.setIcon(Setup.getScaledIcon(new ImageIcon(Setup.class.getResource(("/images/icons8_settings_25px1.png"))),20,20));
				
				/*if (Setup.pinState.equals("Pin window")) {
					setting.setIcon(new ImageIcon(Setup.class.getResource(("/images/icons8_settings_25px.png"))));
				} else {
					setting.setIcon(new ImageIcon(Setup.class.getResource(("/images/icons8_settings_25px.png"))));
				}*/		
				add(setting);
				setting.setForeground(Color.BLACK);
				setting.setBounds(484+Setup.size_hor, 10, 28, 28);
				setting.setFont(new Font(Setup.font, Font.PLAIN, 24));
				setting.setHorizontalAlignment(SwingConstants.CENTER);
				setting.setVerticalAlignment(SwingConstants.CENTER);
				
				

				setting.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Setup.playSound("click2.wav");
						if(Setup.settingState.equals("Hide setting")){
					Setup.getWindow().setSize(850+Setup.modiSidebarWidth+Setup.size_hor, 687); 
					Setup.settingState = "Show setting";			
					} else {
						Setup.getWindow().setSize(850+Setup.modiSidebarWidth+Setup.size_hor+237, 687); 
						Setup.settingState = "Hide setting";			
					}
						setting.setToolTipText(Setup.settingState);

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						setting.setOpaque(true);
						setting.setBackground(Color.GRAY);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setting.setBackground(Color.white);
						setting.setOpaque(false);
					}
				});
	
				
			
	}
}

 
