package gui.component;
import javax.swing.*;

import com.sun.awt.AWTUtilities;

import asystem.Setup;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class ClickAwayDialog extends JDialog {

    public ClickAwayDialog(String content) {
    	this.setLayout(null);
    	this.setUndecorated(true);
        JPanel pnl = new JPanel(new BorderLayout());
        JLabel label = new JLabel(content,SwingConstants.CENTER);
        label.setFont(new Font(Setup.font, Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        
       // pnl.setPreferredSize(new Dimension(100,30));
        pnl.add(label, BorderLayout.CENTER);
      
        this.setContentPane(pnl);
        pnl.setOpaque (false);
        
        
       
 
      setBackground (new Color (0, 0, 0, 150));
        
        
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        //AWTUtilities.setWindowShape(this, new Ellipse2D.Float(20f, 20f, 20f, 20f));  
        
        new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
			public int i = 0;
			@Override
			public void run() {
				i++;
				ClickAwayDialog.this.setBackground (new Color (0, 0, 0, 150-i));
				if (i == 150) {
					cancel();
					ClickAwayDialog.this.dispose();
				}
			}
		}, 0, 15);

        
        
        
        this.addWindowFocusListener(new WindowFocusListener() {

            public void windowGainedFocus(WindowEvent e) {
                //do nothing ClickAwayDialog.this.dispose();
            	 //ClickAwayDialog.this.setVisible(false);
            }

            public void windowLostFocus(WindowEvent e) {
                if (SwingUtilities.isDescendingFrom(e.getOppositeWindow(), ClickAwayDialog.this)) {
                	// ClickAwayDialog.this.dispose();
                    return;
                }
                ClickAwayDialog.this.setVisible(false);
                ClickAwayDialog.this.dispose();
                
            }

        });
    }

 
}