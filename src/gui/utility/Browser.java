/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
package gui.utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import asystem.Database;
import asystem.Setup;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.DefaultWebBrowserDecorator;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserDecorator;
import chrriis.dj.nativeswing.swtimpl.components.DefaultWebBrowserDecorator.WebBrowserButtonBar;
import chrriis.dj.nativeswing.swtimpl.components.DefaultWebBrowserDecorator.WebBrowserMenuBar;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser.WebBrowserDecoratorFactory;


/**
 * @author Christopher Deckers
 */
public class Browser {
	

	public static JPanel createContent(String url) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		
		Border blackline, raisedetched, loweredetched,
	       raisedbevel, loweredbevel, empty;

	
		//webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Browser demo"));
		//final JWebBrowser webBrowser = new JWebBrowser();
		//webBrowser.navigate(url);	
		
		// We create a web browser that replaces its decorator.
				final JWebBrowser webBrowser = new JWebBrowser() {
					@Override
					protected WebBrowserDecorator createWebBrowserDecorator(Component renderingComponent) {
						return createCustomWebBrowserDecorator(this, renderingComponent);
					}
				};
				webBrowser.navigate(url);
				webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
				contentPane.add(webBrowserPanel, BorderLayout.CENTER);
				JPanel southPanel = new JPanel();
				
				
				
		
	//	webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		//contentPane.add(webBrowserPanel, BorderLayout.CENTER);
		
		// Create an additional bar allowing to show/hide the menu bar of the web
		// browser.
		/*JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
		JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
		menuBarCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		buttonPanel.add(menuBarCheckBox);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);*/
				
				try {
					UIManager.setLookAndFeel(Setup.laf);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}
		return contentPane;
		
	}
	
	//customize menubar and buttonbar
	private static WebBrowserDecorator createCustomWebBrowserDecorator(JWebBrowser webBrowser, Component renderingComponent) {
	    return new DefaultWebBrowserDecorator(webBrowser, renderingComponent) {
	      @Override
	      protected void addMenuBarComponents(WebBrowserMenuBar menuBar) {
	        // We let the default menus to be added and then we add ours.
	        super.addMenuBarComponents(menuBar);
	        JMenu myMenu = new JMenu("Menu demo");
	        myMenu.add(new JMenuItem("Item 1"));
	        myMenu.add(new JMenuItem("Item 2"));
	        menuBar.add(myMenu);
	      }
	      @Override
	      protected void addButtonBarComponents(WebBrowserButtonBar buttonBar) {
	        buttonBar.add(buttonBar.getBackButton());
	        final JButton button = new JButton("Button demo");
	        button.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(button, "My button demo was pressed!");
	          }
	        });
	        buttonBar.add(button);
	        buttonBar.add(buttonBar.getForwardButton());
	        buttonBar.add(buttonBar.getReloadButton());
	        buttonBar.add(buttonBar.getStopButton());
	      }
	    };
	  }

	/* Standard main method to try that test as a standalone application. */
	public static void main(String[] args) {
		NativeInterface.open();
		UIUtils.setPreferredLookAndFeel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("DJ Native Swing Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(createContent("youtube.com"), BorderLayout.CENTER);
				frame.setSize(800, 600);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
	}

}
