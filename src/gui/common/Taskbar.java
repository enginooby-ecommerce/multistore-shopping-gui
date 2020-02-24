package gui.common;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import asystem.Database;
import asystem.Setup;
import gui.component.ClickAwayDialog;
import gui.customer.CustomerSidebar;
import gui.utility.Browser;
import gui.utility.Clock;
import gui.utility.Kernel32;

public class Taskbar extends JPanel {

	/**
	 * Create the panel.
	 */
	public Taskbar() {
		setBackground(Setup.colorTaskbar);
		// setOpaque(false);
		setLayout(null);
		setBounds(0, 0, 615, 45);

		JLabel google = new JLabel("");
		google.setHorizontalAlignment(SwingConstants.CENTER);
		google.setIcon(new ImageIcon(Taskbar.class.getResource("/images/chrome.png")));
		google.setBounds(1, 0, 40, 44);
		add(google);
		// Setup.linkEffect(google);
		Setup.iconWebEffect(google, "google.com");

		JLabel facebook = new JLabel("");
		facebook.setIcon(new ImageIcon(Taskbar.class.getResource("/images/facebook-logo.png")));
		facebook.setHorizontalAlignment(SwingConstants.CENTER);
		facebook.setBounds(38, 0, 38, 44);
		add(facebook);
		Setup.iconWebEffect(facebook, "facebook.com");

		JLabel youtube = new JLabel("");
		youtube.setIcon(new ImageIcon(Taskbar.class.getResource("/images/youtube.png")));
		youtube.setHorizontalAlignment(SwingConstants.CENTER);
		youtube.setBounds(75, 0, 38, 44);
		add(youtube);
		Setup.iconWebEffect(youtube, "youtube.com");

		JLabel game = new JLabel("");
		game.setIcon(new ImageIcon(Taskbar.class.getResource("/images/gamepad5.png")));
		game.setHorizontalAlignment(SwingConstants.CENTER);
		game.setBounds(112, 0, 38, 44);
		add(game);

		Setup.iconEffect(game);

		// popup menu to select game
		JMenuItem game1 = new JMenuItem("Age of war [Credit: Max Games]");
		JMenuItem game3 = new JMenuItem("Tower defence [Credit: Ninjawiki]");
		JMenuItem game2 = new JMenuItem("Tower crush [Credit: Armor Games]");
		final JPopupMenu gamePopup = new JPopupMenu();
		gamePopup.add(game1);
		gamePopup.add(game2);
		gamePopup.add(game3);
		game.add(gamePopup);
		game.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				gamePopup.show(game, e.getX(), e.getY());
			}
		});
		Setup.itemGame(game1, "AgeofWar");
		Setup.itemGame(game3, "Balloontowerdefence3");
		Setup.itemGame(game2, "crushthecastle");

		JLabel volumn = new JLabel("");
		Setup.iconEffect(volumn);
		if (!Setup.mute) {
			volumn.setIcon(Setup.getScaledIcon(
					new ImageIcon(Taskbar.class.getResource("/images/icons8_speaker_25px.png")), 22, 22));
		} else {
			volumn.setIcon(Setup.getScaledIcon(
					new ImageIcon(Taskbar.class.getResource("/images/icons8_no_audio_30px.png")), 22, 22));

		}
		volumn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!Setup.mute) {
					
					volumn.setIcon(Setup.getScaledIcon(
							new ImageIcon(Taskbar.class.getResource("/images/icons8_no_audio_30px.png")), 22, 22));
					Setup.mute=true;
				} else {
					volumn.setIcon(Setup.getScaledIcon(
							new ImageIcon(Taskbar.class.getResource("/images/icons8_speaker_25px.png")), 22, 22));
					Setup.mute=false;
				}

			}

		});
		volumn.setHorizontalAlignment(SwingConstants.CENTER);
		volumn.setBounds(480 + Setup.size_hor, 0, 28, 44);
		add(volumn);

		JLabel wifi = new JLabel("");
		Setup.iconEffect(wifi);
		wifi.setIcon(new ImageIcon(Taskbar.class.getResource("/images/icons8_wi-fi_22px.png")));
		wifi.setHorizontalAlignment(SwingConstants.CENTER);
		wifi.setBounds(450 + Setup.size_hor, 0, 28, 44);
		add(wifi);

		JLabel charge = new JLabel("");
		Setup.iconEffect(charge);
		charge.setIcon(new ImageIcon(Taskbar.class.getResource("/images/icons8_battery_unknown_22px.png")));
		charge.setHorizontalAlignment(SwingConstants.CENTER);
		charge.setBounds(421 + Setup.size_hor, 0, 28, 44);
		add(charge);
		charge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
				Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
				ClickAwayDialog dialog = new ClickAwayDialog(batteryStatus.toString());
				JFrame window = ((JFrame) (Z.main_panel.getTopLevelAncestor()));
				dialog.setSize(150, 48);
				dialog.setLocation(window.getX() + charge.getX() + 180 + Setup.modiSidebarWidth,
						window.getY() + charge.getY() + 595);
				dialog.setVisible(true);

			}

		});

		/*
		 * JLabel more = new JLabel(""); Setup.iconEffect(more); more.setIcon(new
		 * ImageIcon(Taskbar.class.getResource("/images/icons8_chevron_up_32px.png")));
		 * more.setHorizontalAlignment(SwingConstants.CENTER); more.setBounds(392 +
		 * Setup.size_hor, 0, 28, 44); add(more);
		 */
		JLabel clock_pre = new JLabel("00:00:00");
		clock_pre.setSize(80, 25);
		clock_pre.setForeground(Color.BLACK);
		clock_pre.setFont(new Font(Setup.font, Font.BOLD, 15));
		clock_pre.setLocation(522 + Setup.size_hor, 9);
		// add(clock_pre);
		Clock clock = new Clock();
		clock.setLocation(507 + Setup.size_hor, 7);
		add(clock);

	}
}
