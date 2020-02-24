package zachieve;

import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;


import gui.Setup;
import gui.guest.GuestSidebar;
import main.Database;

import java.awt.Font;
import java.awt.Color;

public class GuestHome extends JFrame{
	
	public GuestHome() {
		add(new GuestSidebar());	
		add(new Home2());
		//Setup.mainFrame(this);
		
		//AnimationClass test = new AnimationClass();
		JLabel label = new JLabel("This is a test");
		label.setBackground(Color.BLACK);
		label.setForeground(Color.BLACK);
		label.setBounds(100, 100, 500, 500);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		add(label);
		//test.jLabelXRight(label.getX(),500,3,1,label);
		
		
		
	}
	
	
	
}