package gui.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;

import gui.component.Setup;
import main.Database;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class SettingPage extends JFrame {
	private JTextField textField;
	public SettingPage() {
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY));
		
		
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 13));

		setBackground(Color.GRAY);
		String color[] = { "","Gray", "Black", "White", "Blue" };
		
		Border blackline, raisedetched, loweredetched,
	       raisedbevel, loweredbevel, empty;

	blackline = BorderFactory.createLineBorder(Color.black,3);
	raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	raisedbevel = BorderFactory.createRaisedBevelBorder();
	loweredbevel = BorderFactory.createLoweredBevelBorder();
	empty = BorderFactory.createEmptyBorder();
	
	TitledBorder test = BorderFactory.createTitledBorder("hihi");
		
		
		//slide speed label
		JLabel lblSlideSpeed = new JLabel("Slide speed");
		lblSlideSpeed.setForeground(Color.BLACK);
		lblSlideSpeed.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSlideSpeed.setBounds(50, 139, 109, 19);
		getContentPane().add(lblSlideSpeed);
		
		//slide speed field
		textField = new JTextField();
		textField.setBounds(134, 137, 57, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JCheckBox chckbxInstantly = new JCheckBox("Instantly");
		chckbxInstantly.setFont(new Font(Setup.font, Font.BOLD, 13));
		chckbxInstantly.setBounds(247, 136, 113, 25);
		getContentPane().add(chckbxInstantly);
		
		JLabel lblOr = new JLabel("(%) or");
		lblOr.setForeground(Color.BLACK);
		lblOr.setFont(new Font("Dialog", Font.BOLD, 13));
		lblOr.setBounds(203, 139, 48, 19);
		getContentPane().add(lblOr);

		// "Confirm" button
		JButton confirm = new JButton("Confirm");
		confirm.setFont(new Font(Setup.font, Font.BOLD, 16));
		confirm.setBounds(190, 226, 102, 29);
		getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length()!=0) {
				int speed = Integer.parseInt(textField.getText());
				Database.slicer_speed = (100*7)/speed;}
				if (chckbxInstantly.isSelected()) {
					Database.slicer_speed=1;
				}
				System.out.println(Database.slicer_speed);
				dispose();
				// new MainMenu().setVisible(true);
			}
		});

		// theme section
		
		String theme[] = { "Nimbus", "Metal", "Motif", "Window", "Classic" };
		JComboBox comboBox = new JComboBox(theme);
		comboBox.setEditable(true);
		comboBox.setSelectedItem(" ");
		comboBox.setEditable(false);
		
		comboBox.setBorder(test);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				if (option.equals("Nimbus")) {
					Database.theme = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
			
				} else if (option.equals("Metal")) {
					Database.theme = "javax.swing.plaf.metal.MetalLookAndFeel";
				} else if (option.equals("Motif")) {
					
					Database.theme = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				} else if (option.equals("Window")) {
					Database.theme = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				} else if (option.equals("Classic")) {
					Database.theme = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
				}

				try {
					UIManager.setLookAndFeel(Database.theme);
					for (Frame f : Frame.getFrames()) {
						SwingUtilities.updateComponentTreeUI(f);
					}
				} catch (Exception e) {
					System.out.println("Substance Graphite failed to initialize");
				}
			}
		});
		comboBox.setBounds(111, 40, 80, 30);
		getContentPane().add(comboBox);
		JLabel lblChooseTheme = new JLabel("L&F");
		lblChooseTheme.setBounds(50, 45, 109, 19);
		getContentPane().add(lblChooseTheme);
		lblChooseTheme.setForeground(Color.BLACK);
		lblChooseTheme.setFont(new Font("Dialog", Font.BOLD, 13));

		// background section

		JComboBox comboBox2 = new JComboBox(color);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox2.getItemAt(comboBox2.getSelectedIndex()) + "";
				if (option.equals("Gray")) {
					Database.background = Color.LIGHT_GRAY;
				} else if (option.equals("Black")) {
					Database.background = Color.BLACK;
				} else if (option.equals("White")) {
					Database.background = Color.WHITE;
				} else if (option.equals("Blue")) {
					Database.background = Color.BLUE;
				}

				for (Frame f : Frame.getFrames()) {
					SwingUtilities.updateComponentTreeUI(f); // ???
				}
			}
		});
		comboBox2.setBounds(350, 40, 80, 30);
		getContentPane().add(comboBox2);
		JLabel lblChooseBackground = new JLabel("Background");
		lblChooseBackground.setBounds(247, 45, 109, 19);
		getContentPane().add(lblChooseBackground);
		lblChooseBackground.setForeground(Color.BLACK);
		lblChooseBackground.setFont(new Font("Dialog", Font.BOLD, 13));

		// sidebar section

		JComboBox comboBox3 = new JComboBox(color);
		comboBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox3.getItemAt(comboBox3.getSelectedIndex()) + "";
				if (option.equals("Gray")) {
					Database.sidebar = Color.LIGHT_GRAY;
				} else if (option.equals("Black")) {
					Database.sidebar = Color.BLACK;
				} else if (option.equals("White")) {
					Database.sidebar = Color.WHITE;
				} else if (option.equals("Blue")) {
					Database.sidebar = Color.BLUE;
				}
				for (Frame f : Frame.getFrames()) {
					SwingUtilities.updateComponentTreeUI(f); // ???
				}
			}
		});
		comboBox3.setBounds(350, 86, 80, 30);
		getContentPane().add(comboBox3);
		JLabel chooseSidebar = new JLabel("Sidebar");
		chooseSidebar.setBounds(247, 91, 109, 19);
		getContentPane().add(chooseSidebar);
		chooseSidebar.setForeground(Color.BLACK);
		chooseSidebar.setFont(new Font("Dialog", Font.BOLD, 13));

		// text section

				JComboBox comboBox4 = new JComboBox(color);
				comboBox4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String option = comboBox4.getItemAt(comboBox4.getSelectedIndex()) + "";
						if (option.equals("Gray")) {
							Database.text = Color.LIGHT_GRAY;
						} else if (option.equals("Black")) {
							Database.text = Color.BLACK;
						} else if (option.equals("White")) {
							Database.text = Color.WHITE;
						} else if (option.equals("Blue")) {
							Database.text = Color.BLUE;
						}
						for (Frame f : Frame.getFrames()) {
							SwingUtilities.updateComponentTreeUI(f); // ???
						}
					}
				});
				comboBox4.setBounds(111, 86, 80, 30);
				getContentPane().add(comboBox4);
				JLabel chooseText = new JLabel("Text");
				chooseText.setBounds(50, 91, 109, 19);
				getContentPane().add(chooseText);
				chooseText.setForeground(Color.BLACK);
				chooseText.setFont(new Font("Dialog", Font.BOLD, 13));
		
		// "Default" button
		JButton btnDefault = new JButton("Default");
		btnDefault.setFont(new Font(Setup.font, Font.BOLD, 16));
		btnDefault.setBounds(328, 226, 102, 29);
		getContentPane().add(btnDefault);
		btnDefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.theme = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
				Database.sidebar =  Color.DARK_GRAY;
				Database.text = Color.BLACK;
				Database.background = Color.LIGHT_GRAY;
				Database.slicer_speed = 7;
				// update theme
				try {
					UIManager.setLookAndFeel(Database.theme);
					for (Frame f : Frame.getFrames()) {
						SwingUtilities.updateComponentTreeUI(f);
					}
				} catch (Exception evt) {
					System.out.println("Substance Graphite failed to initialize");
				}
				dispose();

			}
		});

		setSize(500, 315); // width and height
		getContentPane().setLayout(null); // absolute layout

		// "Back" button
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font(Setup.font, Font.BOLD, 16));
		btnBack.setBounds(50, 226, 102, 29);
		getContentPane().add(btnBack);
		
		JLabel lblmax = new JLabel("(max: 500%)");
		lblmax.setForeground(Color.BLACK);
		lblmax.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblmax.setBounds(50, 159, 109, 19);
		getContentPane().add(lblmax);
		
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setAlwaysOnTop (true);
		setUndecorated(true); // hide title bar
		setOpacity(90 * 0.01f);
		setResizable(true);
		setLocationRelativeTo(null); // make the frame at the center
		setVisible(true);// making the frame visible

	}
}
