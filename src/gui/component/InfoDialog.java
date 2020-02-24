package gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import gui.common.Z;

import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import asystem.Database;
import asystem.Setup;

public class InfoDialog extends JDialog {
 
    private static final long serialVersionUID = 1L;
    private int brightnessTemp = Setup.brightness;
 
    public InfoDialog(String message) {
        super(Setup.getWindow(), "title");
        getContentPane().setBackground(Color.LIGHT_GRAY);
        
        //blur background
		Setup.brightness=30;
		Z.jlayer.repaint();
        
        setModal(true);
        this.setSize(270, 100);
        this.setLocationRelativeTo(Setup.getWindow());
        this.setUndecorated(true);
        getContentPane().setLayout(null)
        ;
     
        
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Setup.font, Font.BOLD, 15));
        label.setBounds(0, 13, 270, 35);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(label);
        
        
        Border blackline = BorderFactory.createLineBorder(Color.BLACK, 2);
        JLabel close = new JLabel("X");
        close.setFont(new Font(Setup.font, Font.BOLD, 15));
        close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.playSound("click2.wav");
				 //un-blur background
	    		Setup.brightness=brightnessTemp;
	    		Z.jlayer.repaint();
	    		setVisible(false);
	            dispose();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				close.setOpaque(true);
				close.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				close.setBackground(Color.white);
				close.setOpaque(false);
			}
		});
        close.setBorder(blackline);
        close.setHorizontalAlignment(SwingConstants.CENTER);
        close.setBounds(124, 61, 24, 25);
        getContentPane().add(close);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
 
    // override the createRootPane inherited by the JDialog, to create the rootPane.
    // create functionality to close the window when "Escape" or "Enter" button is pressed
    public JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        KeyStroke stroke2 = KeyStroke.getKeyStroke("ENTER");
        Action action = new AbstractAction() {
             
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
            	//un-blur background
        		Setup.brightness=brightnessTemp;
        		Z.jlayer.repaint();
        		
                setVisible(false);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        inputMap.put(stroke2, "ENTER");
        rootPane.getActionMap().put("ESCAPE", action);
        rootPane.getActionMap().put("ENTER", action);
        return rootPane;
    }
 
}