package zachieve;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.sun.glass.events.MouseEvent;

import gui.Setup;
import javafx.geometry.Insets;
import main.Customer;
import main.Database;
import main.Dislike;
import main.Item;
import main.ItemInCart;
import main.Like;
import main.Review;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

public class CustomerViewItemDetail extends JFrame {

	

	public CustomerViewItemDetail() {
		add(Setup.customerSidebar());
		add(new ViewItemDetail2());
		//Setup.mainFrame(this);

	}
}
