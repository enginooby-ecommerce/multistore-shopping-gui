package asystem;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.ui.Window;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import gui.common.Headbar;

import gui.common.Z;
import gui.component.InfoDialog;
import gui.guest.LoginMenu;
import gui.utility.Browser;
import gui.utility.ButtonEditor;
import gui.utility.ButtonRenderer;
import gui.utility.SWF;

public class Setup {
	public static String pinState = "Pin window";
	public static String settingState = "Hide setting";
	public static Color bgButton = Color.GRAY;
	public static Color fgButton = Color.WHITE;
	public static Color colorSidebar = Color.DARK_GRAY;
	public static Color colorPage = Color.LIGHT_GRAY;
	public static Color colorPageText = Color.BLACK;
	public static Color colorSidebarText = Color.WHITE;
	public static Color colorTaskbar = new Color(170, 170, 170);
	

	public static boolean spotlightMode = true;
	public static String font = "Tahoma";
	public static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	public static int windowCorner =0;
	public static int slicerSpeed = 7;
	public static int opacity = 100;
	public static int buttonShakingTimes =1;
	public static int delayShakeButton = 50;
	public static int amplitudeShakeButton = 2;
	public static int brightness = 100;
	public static int blurriness = 1;
	public static boolean mute = true;
	public static int size_hor = 0; // to change position of components horizontally when window size is changed
	public static boolean window_is_big = false;
	public static int sidebarWidth = 250;
	public static int modiSidebarWidth = 30;
	public static boolean smallSidebar = false;

	public static JFrame getWindow() {
		return ((JFrame) (Z.main_panel.getTopLevelAncestor()));
	}

	

	/************* TABLE *****************/
	// change text in cells to button
	public static void buttonTable(JTable table, int[] array) {
		for (int i = 0; i < array.length; i++) {
			table.getColumnModel().getColumn(array[i]).setCellRenderer(new ButtonRenderer());
			table.getColumnModel().getColumn(array[i]).setCellEditor(new ButtonEditor(new JCheckBox()));
		}
	}

	public static void columnWidth(JTable table, int[] array) {
		for (int i = 0; i < array.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(array[i]);

		}
	}

	public static void table(JTable table) {
		// disable select row
		table.setRowSelectionAllowed(false);

		// disable dragging columns
		table.getTableHeader().setReorderingAllowed(false);
	
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Setup.colorPage);
		headerRenderer.setForeground(Setup.colorPageText);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setFont(new Font(Setup.font, Font.BOLD, 13));
		 //table.getTableHeader().setOpaque(false);
		// center cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			//table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setFont(new Font(Setup.font, Font.PLAIN, 13));
		table.setForeground(Setup.colorPageText);
		 //table.setBackground(Setup.colorPage);
		table.getTableHeader().setFont(new Font(Setup.font, Font.BOLD, 13));
		table.getTableHeader().setForeground(Setup.colorPageText);
		
		// center header
			((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		 //table.getTableHeader().setOpaque(false);
		 //table.getTableHeader().setBackground(Setup.colorPage);
		 //table.getTableHeader().setForeground(Setup.colorText);
	}

	public static JScrollPane tableScrollPane(JTable table) {
		JScrollPane sp = new JScrollPane(table);
		// SetupComponent.tableScrollPane(sp);
		sp.setSize(530, 360);
		sp.setLocation(37, 130);
		return sp;
		// getContentPane().add(sp);
	}

	/******************* ICON ******************/
	public static ImageIcon getScaledIcon(ImageIcon srcIcon, int w, int h) {
		Image srcImg = srcIcon.getImage();
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return new ImageIcon(resizedImg);
	}

	public static ImageIcon chooseIcon(JLabel label, int width, int height) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);
		File selectedFile = new File("hi");
		ImageIcon icon = new ImageIcon();
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getPath());
			icon = new ImageIcon(selectedFile.getPath());
			label.setIcon(Setup.getScaledIcon(icon, width, height));
		}
		return icon;
	}

	public static void linkEffect(JLabel label) {
		label.setForeground(new Color(0, 0, 255));
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setForeground(SystemColor.textHighlight);
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				label.setForeground(new Color(0, 0, 255));
				label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
		});
	}

	public static void iconEffectWithoutOpaque(JLabel label) {
		// create a line border with the specified color and width
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		// label.setBorder(border);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public static void iconEffect(JLabel label) {
		// create a line border with the specified color and width
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		// label.setBorder(border);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setOpaque(true);
				label.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setBackground(Color.white);
				label.setOpaque(false);
			}
		});
	}

	public static void iconWebEffect(JLabel label, String url) {
		Setup.iconEffect(label);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.playSound("click2.wav");
				int temp = Setup.slicerSpeed;
				Setup.slicerSpeed = 1;
				Z.page = Browser.createContent(url);
				Z.pageSlide(2);
				Setup.slicerSpeed = temp;
				Z.panelSlider.remove(0);

			}
		});

	}

	public static void itemGame(JMenuItem item, String game) {
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Setup.playSound("click2.wav");
				int temp = Setup.slicerSpeed;
				Setup.slicerSpeed = 1;
				Z.page = SWF.createContent(game);
				Z.pageSlide(2);
				Setup.slicerSpeed = temp;
				Z.panelSlider.remove(0);
			}
		});
	}
	
	
	/*************** SOUND *******************/

	public static void playSound(String soundName) {
		if (!Setup.mute) {
			try {
				Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
				File file = new File(path + "\\src\\sound\\" + soundName);
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
		}
	}

	/*************** BUTTON *******************/
	public static void buttonEffect(JButton button) {
		button.setForeground(Setup.fgButton);
		button.setBackground(Setup.bgButton);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Setup.playSound("click.wav");

			}

		});
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				shakeButton(button);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public static void shakeButton(JButton button) {

		final Point point = button.getLocation();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					try {

						for (int j = 0; j < Setup.buttonShakingTimes; j++) {
							moveButton(button, new Point(point.x + Setup.amplitudeShakeButton, point.y));
							Thread.sleep(Setup.delayShakeButton);
							moveButton(button, point);
							Thread.sleep(Setup.delayShakeButton);
							moveButton(button, new Point(point.x - Setup.amplitudeShakeButton, point.y));
							Thread.sleep(Setup.delayShakeButton);
							moveButton(button, point);
							Thread.sleep(Setup.delayShakeButton);
						}

						Thread.sleep(200000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
						continue;
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();

	}

	public static void moveButton(JButton button, final Point p) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				button.setLocation(p);
			}
		});
	}

	public static void shakePanel(JPanel panel, int num) {

		final Point point = panel.getLocation();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					try {

						for (int j = 0; j < num; j++) {
							movePanel(panel, new Point(point.x, point.y + Setup.amplitudeShakeButton));
							Thread.sleep(Setup.delayShakeButton);
							movePanel(panel, point);
							Thread.sleep(Setup.delayShakeButton);
							movePanel(panel, new Point(point.x, point.y - Setup.amplitudeShakeButton));
							Thread.sleep(Setup.delayShakeButton);
							movePanel(panel, point);
							Thread.sleep(Setup.delayShakeButton);
						}

						Thread.sleep(200000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
						continue;
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();

	}

	public static void movePanel(JPanel panel, final Point p) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				panel.setLocation(p);
			}
		});
	}

	public static void leftButton(JButton button) {
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Setup.font, Font.BOLD, 16));
		button.setBackground(Color.GRAY);
		button.setBounds(337, 516, 200, 41);
	}

	public static void rightButton(JButton button) {
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Setup.font, Font.BOLD, 16));
		button.setBackground(Color.GRAY);
		button.setBounds(666, 516, 200, 41);
	}

	public static void middleButton(JButton button) {
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Setup.font, Font.BOLD, 16));
		button.setBackground(Color.GRAY);
		button.setBounds(510, 516, 200, 41);
	}

	public static void title2(JLabel title) {
		title.setFont(new Font(Setup.font, Font.PLAIN, 30));
		title.setForeground(Setup.colorPageText);
		title.setBounds(37, 10, 381, 37);
	}

	public static void leftButton2(JButton button) {
		// button.setForeground(Color.WHITE);
		button.setFont(new Font(Setup.font, Font.BOLD, 16));
		// button.setBackground(Color.GRAY);
		button.setBounds(37, 536, 200, 41);
	}

	public static void rightButton2(JButton button) {
		// button.setForeground(Color.WHITE);
		button.setFont(new Font(Setup.font, Font.BOLD, 16));
		// button.setBackground(Color.GRAY);
		button.setBounds(366, 536, 200, 41);
	}

	public static void middleButton2(JButton button) {
		// button.setForeground(Color.WHITE);
		button.setFont(new Font(Setup.font, Font.BOLD, 16));
		// button.setBackground(Color.GRAY);
		button.setBounds(210, 536, 200, 41);
	}

	public static void title3(JLabel title) {
		title.setFont(new Font(Setup.font, Font.PLAIN, 30));
		title.setForeground(Setup.colorPageText);
		title.setBounds(337, 32, 381, 37);
	}

	/**************** PAGE ******************/
	public static void page(JPanel panel) {
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(Setup.colorPage);

		// title line
		JSeparator separator = new JSeparator();
		separator.setBounds(-10, 70, 625 + 500, 10);
		panel.add(separator);

	}

	public static JLabel title(String content) {
		JLabel title = new JLabel(content);
		title.setFont(new Font(Setup.font, Font.PLAIN, 30));
		title.setForeground(Setup.colorPageText);
		title.setBounds(37, 10, 381, 37);
		return title;
	}

	/************ NOTIFICATIONS ****************/

	public static void underDev() {
		Setup.playSound("error.wav");
		new InfoDialog("This feature is under development");
	}

	public static void notLoggedIn() {
		Setup.playSound("error.wav");
		new InfoDialog("You are not logged in");
	}

}
