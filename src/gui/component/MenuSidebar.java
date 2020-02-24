package gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import asystem.Setup;
import gui.common.Z;
import gui.guest.GuestSidebar;

public class MenuSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuSidebar() {
		this.setBackground(Setup.colorSidebar);
		setBounds(250, 3, 50, 50);
		if (Setup.smallSidebar) {
			this.setLocation(24, 10);
		} else {
			this.setLocation(120, 10);
		}
		this.setLayout(null);

		JLabel menu = new JLabel("");
		menu.setBounds(5, 5, 40, 40);
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setIcon(Setup.getScaledIcon(new ImageIcon(MenuSidebar.class.getResource("/images/icons8_menu_50px.png")),
				30, 30));
		add(menu);
		Setup.iconEffectWithoutOpaque(menu);
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.playSound("click2.wav");
				if (!Setup.smallSidebar) {
					Setup.modiSidebarWidth = -157;
					Setup.smallSidebar = true;
					Setup.getWindow().setLocation(Setup.getWindow().getX() + 187, Setup.getWindow().getY());
				} else {
					Setup.modiSidebarWidth = 30;
					Setup.smallSidebar = false;
					Setup.getWindow().setLocation(Setup.getWindow().getX() - 187, Setup.getWindow().getY());
				}

				Z.resetSidebar();
				Z.initSize(Setup.getWindow());
			}
		});
	}

}
