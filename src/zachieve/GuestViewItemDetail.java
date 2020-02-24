package zachieve;

import javax.swing.JFrame;

import gui.Setup;
import main.Database;

public class GuestViewItemDetail extends JFrame {
	public GuestViewItemDetail() {

		add(Setup.guestSidebar());
		add(new ViewItemDetail2());
		//Setup.mainFrame(this);

	}
}
