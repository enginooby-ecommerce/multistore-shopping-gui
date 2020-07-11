

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import asystem.Database;
import asystem.Setup;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import gui.common.Z;
import main.Admin;

public class ShoppingApp {
	public static void main(String[] args) {
		Database.initDatabase();
		// create an admin
		Database.admin_list.add(new Admin("admin", "admin"));

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NativeSwing.initialize();
				NativeInterface.open();
				UIUtils.setPreferredLookAndFeel();
				try {
					UIManager.setLookAndFeel(Setup.laf);

				} catch (Exception e) {
					System.out.println("Substance Graphite failed to initialize");
				}
				new Z();
			}
		});
	}

}
