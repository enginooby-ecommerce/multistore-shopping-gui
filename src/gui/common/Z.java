package gui.common;

import java.awt.BorderLayout;
import gui.utility.BlurLayerUI;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.LayerUI;

import com.sun.awt.AWTUtilities;
import com.sun.jna.examples.WindowUtils;

import asystem.AccountType;
import asystem.Database;
import asystem.Setup;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import diu.swe.habib.JPanelSlider.JPanelSlider;
import gui.admin.AdminHome;
import gui.admin.AdminSidebar;
import gui.customer.CustomerSidebar;
import gui.guest.GuestSidebar;
import gui.guest.LoginMenu;
import gui.retailer.RetailerHome;
import gui.retailer.RetailerSidebar;
import gui.utility.MoveMouseListener;
import gui.utility.SpotlightLayerUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class Z extends JFrame {
	public static JPanel main_panel=new JPanel();
	public static JPanel bg = new JPanel();
	public static JPanelSlider panelSlider = new JPanelSlider();
	public static JPanelSlider sidebarSlider = new JPanelSlider();
	public static JPanel page, sidebar, headbar, taskbar,setting;
	public static LayerUI<JPanel> spotlightLayer = new SpotlightLayerUI();
	public static LayerUI<JPanel> blurLayer = new BlurLayerUI();
	public static JLayer<JPanel> jlayer = new JLayer<JPanel>(Z.main_panel, blurLayer);
	

	/**
	 * Create the frame.
	 */
	public Z() {		
		getContentPane().setLayout(null); // absolute layout
		setUndecorated(true); // hide title bar
		
		Border gray_border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		Border darkgray_border = BorderFactory.createLineBorder(Color.DARK_GRAY);
		
		Z.panelSlider.setBorder(null);
		

		//MAIN PANEL
		main_panel.setLayout(null);
				
		//SIDEBAR
		sidebar = new GuestSidebar();		
		sidebarSlider.add(sidebar);
		sidebarSlider.setBorder(darkgray_border);
		main_panel.add(sidebarSlider, BorderLayout.CENTER);

		//MAINPAGE
		page = new HomeOri();
		panelSlider.add(page);		
		panelSlider.setBorder(gray_border);
		main_panel.add(panelSlider, BorderLayout.CENTER);
		
		//TASKBAR
		taskbar = new Taskbar();		
		main_panel.add(taskbar, BorderLayout.CENTER);
		
		//HEADBAR
		headbar = new Headbar();		
		main_panel.add(headbar);
			
		//SETTING
		setting = new Setting();
		main_panel.add(setting);
		

		// enable dragging window
		MoveMouseListener mml = new MoveMouseListener(main_panel);
		addMouseListener(mml);
		addMouseMotionListener(mml);

		
		JMenuItem admin = new JMenuItem("Quick login: admin demo");
		JMenuItem customer = new JMenuItem("Quick login: customer demo");
		JMenuItem retailer = new JMenuItem("Quick login: retailer demo");


		final JPopupMenu pagePopup = new JPopupMenu();
		pagePopup.add(admin);
		pagePopup.add(retailer);
		pagePopup.add(customer); 
		
		getContentPane().add(pagePopup);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isMetaDown()) {
					if (e.getX() > 0) {
						pagePopup.show(sidebarSlider.getParent(), e.getX(), e.getY());
					} else {
						//sidebarPopup.show(sidebarSlider.getParent(), e.getX(), e.getY());
					}
				}
			}
		});

		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.login_as=AccountType.ADMIN;
				Database.isGuest = false;
				Z.page = new AdminHome();
				Z.pageSlide(1);
				Z.sidebar = new AdminSidebar();
				Z.sidebarSlide(2);
			}
		});

		customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.login_as = AccountType.CUSTOMER;
				Database.isGuest = false;
				Database.currentUserId = 1;
				Z.page = new HomeOri();
				Z.sidebar = new CustomerSidebar();
				Z.sidebarSlide(2);
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}
			} 
		});
		
		retailer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.login_as = AccountType.RETAILER;
				Database.isGuest = false;
				Database.currentUserId = 11;
				Z.page = new RetailerHome();
				Z.sidebar = new RetailerSidebar();
				Z.sidebarSlide(2);
				if (!Database.state.equals(Z.page.getClass().getSimpleName())) {
					Z.pageSlide(1);
				}
			} 
		});
		
		  add(jlayer);
		initSize(this);	    
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void pageSlide(int direction) {
		panelSlider.add(page);
		panelSlider.nextPanel(Setup.slicerSpeed, page, (direction == 1) ? true : false);
			
		//timer to delete previous panel to keep smooth sliding
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	//if (panelSlider.getComponentCount() > 1) {
		        			panelSlider.remove(0);
		        		//}
		            }
		        }, 
		       900 //ms
		);
		
		
		Database.state = page.getClass().getSimpleName();
	}

	public static void sidebarSlide(int direction) {
		sidebarSlider.add(sidebar);
		sidebarSlider.nextPanel(Setup.slicerSpeed, sidebar, (direction == 1) ? true : false);
		
		//timer to delete previous panel to keep smooth slicing
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	//if (sidebarSlider.getComponentCount() > 2) {
				        			sidebarSlider.remove(0);
				        		//}
				            }
				        }, 
				        900 //ms
				);
				
		
	}
	
	public static void initSize(JFrame frame) {		
		
		//reset headbar and taskbar to change position of buttons
		Z.main_panel.remove(headbar);
		headbar = new Headbar();
		Z.main_panel.add(headbar);
		
		Z.main_panel.remove(taskbar);
		taskbar= new Taskbar();
		Z.main_panel.add(taskbar);
		
		frame.setOpacity(Setup.opacity*0.01f); 
		//frame.setLocationRelativeTo(null);
		 
		
		
		frame.setSize(850+Setup.modiSidebarWidth+Setup.size_hor+237, 687); 
		main_panel.setSize(900+Setup.size_hor+340, 687);
		jlayer.setSize(900+Setup.size_hor+340, 687);
		setting.setBounds(Setup.sidebarWidth+Setup.modiSidebarWidth+605+Setup.size_hor, 0, 232, 607+43+50-13);
		sidebarSlider.setBounds(-5, -5, Setup.sidebarWidth+Setup.modiSidebarWidth, 695);
		panelSlider.setBounds(Setup.sidebarWidth+Setup.modiSidebarWidth-5, 37, 610+Setup.size_hor, 607);
		taskbar.setBounds(Setup.sidebarWidth+Setup.modiSidebarWidth-5, 644, 610+Setup.size_hor, 43);
		headbar.setBounds(Setup.sidebarWidth+Setup.modiSidebarWidth-5, -5, 610+Setup.size_hor, 50);
		frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), Setup.windowCorner, Setup.windowCorner));
	
		
	}
	
	public static void roundCorner(int num) {
		Setup.getWindow().setShape(new RoundRectangle2D.Double(0, 0, Setup.getWindow().getWidth(), Setup.getWindow().getHeight(), num, num));
	}
	
	public static void bigger() {
		Setup.size_hor=450;	
		initSize(Setup.getWindow());	
	}
	
	public static void smaller() {
		Setup.size_hor=0;			
		initSize(Setup.getWindow());	
		
	}
	
	public static void resetSidebar() {
		main_panel.remove(sidebarSlider);
		sidebarSlider.remove(sidebar);
		Border darkgray_border = BorderFactory.createLineBorder(Color.DARK_GRAY);
		if (Database.isGuest) {
		sidebar = new GuestSidebar();} else if (Database.login_as==AccountType.CUSTOMER) {
			sidebar = new CustomerSidebar();
		} else if (Database.login_as == AccountType.RETAILER) {
			sidebar = new RetailerSidebar();
		} else if(Database.login_as==AccountType.ADMIN) {
			sidebar = new AdminSidebar();
		
		}
		sidebarSlider.add(sidebar);
		sidebarSlider.setBorder(darkgray_border);
		main_panel.add(sidebarSlider, BorderLayout.CENTER);
	}
	
	
	public static void resetPage() {
		//main_panel.remove(Z.panelSlider);
		
		int temp = Setup.slicerSpeed;
		Setup.slicerSpeed=1;	
		if (Database.isGuest ||Database.login_as==AccountType.CUSTOMER ) {
		Z.page= new HomeOri();
		
		} else if (Database.login_as == AccountType.RETAILER) {
			Z.page = new RetailerHome();
		} else if(Database.login_as==AccountType.ADMIN) {
			Z.page= new AdminHome();
		
		}
		Z.pageSlide(1);
		Setup.slicerSpeed=temp;
		
	}
	

	
	
}
