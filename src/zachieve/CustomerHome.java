package zachieve;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gui.Setup;
import main.Database;
import main.Item;

import java.awt.Color;
import java.awt.Font;

public class CustomerHome extends JFrame {
	public CustomerHome() {
		add(Setup.customerSidebar());
		add(new Home2());
			
		//Setup.mainFrame(this);

	}
}
