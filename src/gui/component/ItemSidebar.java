package gui.component;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import asystem.Database;
import asystem.Setup;
import gui.common.HomeOri;
import gui.common.Z;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ItemSidebar extends JPanel {
	private JLabel layer = new JLabel("");
	private JLabel label = new JLabel();
	private JLabel icon = new JLabel("");
	private JPanel small_panel = new JPanel();
	private Color inactive_color =Setup.colorSidebar; // setup.bgsidebar
	private Color active_color = new Color(100, 100, 100);
	private int itemHeight = 64;

	public ItemSidebar(String name, int position) {
		setBackground(inactive_color);
		setBounds(0, 108, Setup.sidebarWidth+Setup.modiSidebarWidth, itemHeight);
		setLayout(null);
		
		if (position == 1) {
			setLocation(0, 583); 
		} else {
			setLocation(0, 583 - itemHeight * (position - 1)); 
		}
	
		layer.setBounds(0, 0, 300, 64);
		add(layer);
		layer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.playSound("click2.wav");

			}

		});
		enableLayer();

		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setBackground(Color.BLUE);
		icon.setBounds(20, 0, 64, 64);
		add(icon);

		small_panel.setEnabled(false);
		small_panel.setOpaque(false);
		small_panel.setBounds(0, 0, 12, 64);
		add(small_panel);

		label.setText(name);
		label.setForeground(Setup.colorSidebarText);
		label.setFont(new Font(Setup.font, Font.PLAIN, 18));
		label.setBounds(90, 0, 210, 64);
		add(label);

	}

	public JLabel getIcon() {
		return icon;
	}

	public void setIcon(JLabel icon) {
		this.icon = icon;
	}

	public JLabel getLayer() {
		return layer;
	}

	public void setLayer(JLabel layer) {
		this.layer = layer;
	}

	public void enableLayer() {
		layer.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				((JPanel) label.getParent()).setBackground(active_color);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JPanel) label.getParent()).setBackground(inactive_color);
			}
		});

	}

	public void disableLayer() {
		// layer.removeMouseListener();
		layer.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				((JPanel) label.getParent()).setBackground(active_color);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JPanel) label.getParent()).setBackground(active_color);
			}
		});

	}

	public void active(List<JPanel> items) {
		for (JPanel item : items) {
			if (item.equals(this)) {
				((ItemSidebar) item).small_panel.setOpaque(true);
				item.setBackground(Color.GRAY);
				((ItemSidebar) item).disableLayer();

			} else {
				((ItemSidebar) item).small_panel.setOpaque(false);
				item.setBackground(Setup.colorSidebar);
				((ItemSidebar) item).enableLayer();

			}
		}
	}

	public void setStartItem() {
		small_panel.setOpaque(true);
		setBackground(new Color(100, 100, 100));
		disableLayer();
	}

}
