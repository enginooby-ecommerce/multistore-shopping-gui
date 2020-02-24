package gui.utility;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JFlashPlayer;
import test.SimpleFlashExample;

public class SWF {

	public static JPanel createContent(String content) {
		JPanel panel = new JPanel(new BorderLayout());
		JFlashPlayer flashPlayer = new JFlashPlayer();
		flashPlayer.load(SimpleFlashExample.class, "/swf/"+content+".swf");
		panel.add(flashPlayer);
		return panel;
	}
}
