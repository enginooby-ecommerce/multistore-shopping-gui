package asystem;

import java.awt.Color;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import gui.common.ViewItemDetail;
import gui.common.Z;
import main.Admin;

public class Main {
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
