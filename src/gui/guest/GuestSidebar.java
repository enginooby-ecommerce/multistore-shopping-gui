package gui.guest;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import gui.common.Headbar;
import gui.common.HomeOri;
import gui.common.Z;
import gui.component.ItemSidebar;
import gui.component.MenuSidebar;

import javax.swing.SwingConstants;

import asystem.Database;
import asystem.Setup;

public class GuestSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	List<JPanel> items = new ArrayList<>();
	
	public GuestSidebar() {
		setLayout(null);
		setBackground(Setup.colorSidebar);
		setBounds(0, 0, 292, 600);
		
		//add(new SettingSidebar());
		add(new MenuSidebar());
		
		ItemSidebar home = new ItemSidebar("Home",3);
		home.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_home_30px.png")));	
		home.setStartItem();
		add(home);
		items.add(home);
		home.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				home.active(items);
				
				Z.page = new HomeOri();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}
			}

		});

		ItemSidebar login = new ItemSidebar("Login",2);
		login.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_enter_30px.png")));
		add(login);
		items.add(login);
		login.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login.active(items);
				Z.page = new LoginMenu();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}
			}

		});
		
		
		ItemSidebar register = new ItemSidebar("Register",1);
		register.getIcon().setIcon(new ImageIcon(ItemSidebar.class.getResource("/images/icons8_add_user_male_30px_1.png")));
		add(register);
		items.add(register);
		register.getLayer().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				register.active(items);
				Z.page = new RegisterMenu();
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}

			}

		});
		
		

		// "Setting" button
		/*JButton setting = new JButton("Setting");
		Setup.buttonEffect(setting);
		setting.setFont(new Font(Setup.font, Font.PLAIN, 16));
		setting.setBounds(100, 635, 100, 29);
		add(setting);
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame window =((JFrame) (setting.getTopLevelAncestor()));
				


Z.bigger();

			}
		});*/

	

		/******* Items on left panel ********/
		if (!Setup.smallSidebar) {
		JLabel lblOop = new JLabel("OOP");
		lblOop.setHorizontalAlignment(SwingConstants.CENTER);
		lblOop.setForeground(Setup.colorSidebarText);
		lblOop.setFont(new Font(Setup.font, Font.BOLD, 44));
		lblOop.setBounds(0, 99, 280, 42);
		add(lblOop);

		JLabel lblTask = new JLabel("TASK 5");
		lblTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblTask.setBounds(0, 160, 280, 46);
		add(lblTask);
		lblTask.setForeground(Setup.colorSidebarText);
		lblTask.setFont(new Font(Setup.font, Font.PLAIN, 29));

		// title line
		JSeparator separator = new JSeparator();
		separator.setBounds(-7, 155, 320, 10);
		add(separator);

		JLabel lblTask2 = new JLabel("Shopping application");
		lblTask2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTask2.setBounds(0, 200, 280, 46);
		add(lblTask2);
		lblTask2.setForeground(Setup.colorSidebarText);
		lblTask2.setFont(new Font(Setup.font, Font.PLAIN, 20));}

		

		
	}
	
	

}
