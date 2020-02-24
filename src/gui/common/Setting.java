package gui.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import asystem.Database;
import asystem.Setup;

import javax.swing.event.ChangeEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;

public class Setting extends JPanel {

	/**
	 * Create the panel.
	 */
	private Color textColor = Color.WHITE;
	private JComboBox comboBox,comboBox1,comboBox2,comboBox3,comboBox4,comboBox5,comboBox6;
	JSlider slider, slider2,slider3,slider4;
	SpinnerModel model = new SpinnerNumberModel(100, 30, 500, 10);
	JSpinner spinner = new JSpinner(model);
	
	SpinnerModel model2 = new SpinnerNumberModel(2, 0, 20, 1);
	JSpinner spinner2 = new JSpinner(model2);
	JCheckBox chckbxInstantly;
	//JSpinner spinner;

	public Setting() {
		Border blackline = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK);

		setLayout(null);
		this.setBorder(blackline);
		setBounds(0, 0, 130, 600);
		setSize(232, 706);
		int a = 30;
		setBackground(new Color(a, a, a));
		
		JLabel lblPx = new JLabel("px");
		lblPx.setForeground(Color.WHITE);
		lblPx.setBounds(192, 345, 30, 16);
		add(lblPx);
		
		JLabel label = new JLabel("%");
		label.setForeground(Color.WHITE);
		label.setBounds(84, 339, 19, 30);
		add(label);
		
		
		JLabel lblSetting = new JLabel("Setting");
		lblSetting.setForeground(Color.WHITE);
		lblSetting.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetting.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetting.setBounds(83, 7, 66, 25);
		add(lblSetting);

		String color[] = { "Gray", "Black", "White", "Blue" };

		//FONT
		
		JLabel font = new JLabel("Font");
		font.setHorizontalAlignment(SwingConstants.CENTER);
		font.setForeground(Color.WHITE);
		font.setFont(new Font("Dialog", Font.BOLD, 11));
		font.setBounds(142, 43, 60, 19);
		add(font);
		
		String fontList[] = { "Tahoma", "Arial", "Courier", "Serif","Comic Sans MS", "Georgia","Monospaced","MS Gothic","Times New Roman" };
		 comboBox5 = new JComboBox(fontList);
		comboBox5.setEditable(true);
		comboBox5.setSelectedItem("Tahoma");
		comboBox5.setBounds(131, 65, 80, 30);
		add(comboBox5);
		comboBox5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String value= comboBox5.getSelectedItem().toString();
				updateFont(value);
			}
		});
		
		JLabel fontInfo = new JLabel("?");
		Setup.iconEffectWithoutOpaque(fontInfo);
		fontInfo.setToolTipText("You can choose provided fonts or enter any font names");
		fontInfo.setBackground(Color.LIGHT_GRAY);
		fontInfo.setHorizontalAlignment(SwingConstants.CENTER);
		fontInfo.setForeground(Color.WHITE);
		fontInfo.setFont(new Font("Dialog", Font.BOLD, 11));
		fontInfo.setBounds(192, 43, 19, 19);
		add(fontInfo);

		//SLIDER SPEED
		JLabel lblSlideSpeed = new JLabel("Slider speed");
		lblSlideSpeed.setBounds(12, 319, 101, 19);
		lblSlideSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlideSpeed.setForeground(textColor);
		lblSlideSpeed.setFont(new Font("Dialog", Font.BOLD, 11));
		add(lblSlideSpeed);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));

		
		
		
		spinner.setForeground(Color.BLACK);
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateSliderSpeed ((int) spinner.getValue());
				
			}
		});
		spinner.setBounds(25, 340, 57, 27);
		JFormattedTextField ftf = getTextField(spinner);
		    ftf.setHorizontalAlignment(JTextField.LEFT);
		
		add(spinner);

		chckbxInstantly = new JCheckBox("Instantly");
		chckbxInstantly.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (chckbxInstantly.isSelected()) {
					Setup.slicerSpeed = 1;
				} else {
					int a = (int) spinner.getValue();
					Setup.slicerSpeed = (100 * 7) / a;
				}
			}
		});
		chckbxInstantly.setBounds(23, 370, 75, 25);
		chckbxInstantly.setFont(new Font(Setup.font, Font.PLAIN, 11));
		chckbxInstantly.setBackground(new Color(a, a, a));
		chckbxInstantly.setForeground(textColor);
		add(chckbxInstantly);

		//L&F
		String theme[] = { "Nimbus", "Metal", "Motif", "Window", "Classic" };
		comboBox = new JComboBox(theme);
		comboBox.setBounds(23, 65, 80, 30);
		comboBox.setEditable(true);
		comboBox.setSelectedItem("Select");
		comboBox.setEditable(false);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox.getItemAt(comboBox.getSelectedIndex()) + "";
				updateLaf(option);
			}
		});
		add(comboBox);
		JLabel lblChooseTheme = new JLabel("L&F");
		lblChooseTheme.setBounds(26, 43, 74, 19);
		lblChooseTheme.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblChooseTheme);
		lblChooseTheme.setForeground(textColor);
		lblChooseTheme.setFont(new Font("Dialog", Font.BOLD, 11));

		// PAGE COLOR
		 comboBox2 = new JComboBox(color);
		comboBox2.setBounds(23, 133, 80, 30);
		comboBox2.setEditable(true);
		comboBox2.setSelectedItem("Select");
		comboBox2.setEditable(false);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox2.getItemAt(comboBox2.getSelectedIndex()) + "";
				updatePageColor(option);
			}
		});
		add(comboBox2);
		JLabel lblChooseBackground = new JLabel("Page");
		lblChooseBackground.setBounds(24, 111, 77, 19);
		lblChooseBackground.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblChooseBackground);
		lblChooseBackground.setForeground(textColor);
		lblChooseBackground.setFont(new Font("Dialog", Font.BOLD, 11));

		// SIDEBAR COLOR
		 comboBox3 = new JComboBox(color);
		comboBox3.setBounds(23, 202, 80, 30);
		comboBox3.setEditable(true);
		comboBox3.setSelectedItem("Select");
		comboBox3.setEditable(false);
		comboBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox3.getItemAt(comboBox3.getSelectedIndex()) + "";
				updateSidebarColor(option);
			}
		});
		add(comboBox3);
		JLabel chooseSidebar = new JLabel("Sidebar");
		chooseSidebar.setBounds(30, 180, 65, 19);
		chooseSidebar.setHorizontalAlignment(SwingConstants.CENTER);

		add(chooseSidebar);
		chooseSidebar.setForeground(textColor);
		chooseSidebar.setFont(new Font("Dialog", Font.BOLD, 11));

		// PAGE TEXT COLOR
		 comboBox4 = new JComboBox(color);
		comboBox4.setBounds(131, 133, 80, 30);
		comboBox4.setEditable(true);
		comboBox4.setSelectedItem("Select");
		comboBox4.setEditable(false);
		comboBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox4.getItemAt(comboBox4.getSelectedIndex()) + "";
				updatePageTextColor(option);
			}
		});
		add(comboBox4);
		JLabel chooseText = new JLabel("Text");
		chooseText.setBounds(141, 113, 60, 19);
		chooseText.setHorizontalAlignment(SwingConstants.CENTER);
		add(chooseText);
		chooseText.setForeground(textColor);
		chooseText.setFont(new Font("Dialog", Font.BOLD, 11));

		// OPACITY
		JLabel opacity = new JLabel("Opacity");
		opacity.setHorizontalAlignment(SwingConstants.CENTER);
		opacity.setForeground(Color.WHITE);
		opacity.setFont(new Font("Dialog", Font.BOLD, 11));
		opacity.setBounds(9, 452, 101, 19);
		add(opacity);	

		slider = new JSlider(SwingConstants.HORIZONTAL, 50, 100, 100);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateOpacity(slider.getValue());
			}
		});
		slider.setBorder(null);
		slider.setBackground(new Color(a, a, a));
		slider.setForeground(Color.WHITE);
		slider.setSize(87, 52);
		slider.setLocation(16, 473);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(25);
		add(slider);

		
		//CORNER
		JLabel corner = new JLabel("Round corner");
		corner.setHorizontalAlignment(SwingConstants.CENTER);
		corner.setForeground(Color.WHITE);
		corner.setFont(new Font("Dialog", Font.BOLD, 11));
		corner.setBounds(9, 546, 101, 19);
		add(corner);

		 slider2 = new JSlider(SwingConstants.HORIZONTAL, 0, 150, 0);
		slider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateWindowCorner(slider2.getValue());
			}
		});
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setMinorTickSpacing(25);
		slider2.setMajorTickSpacing(75);
		slider2.setForeground(Color.WHITE);
		slider2.setBorder(null);
		slider2.setBackground(new Color(30, 30, 30));
		slider2.setBounds(16, 568, 87, 52);
		add(slider2);

		// DEFAULT
		JButton btnDefault = new JButton("Default");
		btnDefault.setBounds(131, 645, 87, 29);
		btnDefault.setFont(new Font(Setup.font, Font.BOLD, 13));
		add(btnDefault);

		

		//BRIGHTNESS
		JLabel bright = new JLabel("Brightness");
		bright.setHorizontalAlignment(SwingConstants.CENTER);
		bright.setForeground(Color.WHITE);
		bright.setFont(new Font("Dialog", Font.BOLD, 11));
		bright.setBounds(138, 453, 76, 19);
		add(bright);
		
		 slider3 = new JSlider(SwingConstants.HORIZONTAL, 20, 100, 100);
		slider3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateBrightness(slider3.getValue());
			}
		});
		slider3.setPaintTicks(true);
		slider3.setPaintLabels(true);
		slider3.setMinorTickSpacing(10);
		slider3.setMajorTickSpacing(40);
		slider3.setForeground(Color.WHITE);
		slider3.setBorder(null);
		slider3.setBackground(new Color(30, 30, 30));
		slider3.setBounds(131, 473, 87, 52);
		add(slider3);

		//SHARPNESS
		JLabel blur = new JLabel("Sharpness");
		blur.setHorizontalAlignment(SwingConstants.CENTER);
		blur.setForeground(Color.WHITE);
		blur.setFont(new Font("Dialog", Font.BOLD, 11));
		blur.setBounds(144, 547, 60, 19);
		add(blur);

		 slider4 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
		slider4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Setup.underDev();
			}
		});
		slider4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setup.underDev();
			}
		});
		slider4.setPaintTicks(true);
		slider4.setPaintLabels(true);
		slider4.setMinorTickSpacing(25);
		slider4.setMajorTickSpacing(50);
		slider4.setForeground(Color.WHITE);
		slider4.setBorder(null);
		slider4.setBackground(new Color(30, 30, 30));
		slider4.setBounds(133, 568, 87, 52);
		slider4.setValue(100);
		add(slider4);

		

		//SIDEBAR TEXT COLOR
		JLabel textsidebar = new JLabel("Text");
		textsidebar.setHorizontalAlignment(SwingConstants.CENTER);
		textsidebar.setForeground(Color.WHITE);
		textsidebar.setFont(new Font("Dialog", Font.BOLD, 11));
		textsidebar.setBounds(141, 182, 60, 19);
		add(textsidebar);

		 comboBox6 = new JComboBox(color);
		comboBox6.setEditable(true);
		comboBox6.setSelectedItem("Select");
		comboBox6.setEditable(false);
		comboBox6.setBounds(131, 202, 80, 30);
		comboBox6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox6.getItemAt(comboBox6.getSelectedIndex()) + "";
				updateSidebarTextColor(option);
			}
		});
		add(comboBox6);

		JButton random = new JButton("Random");
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Setup.underDev();
			}
		});
		random.setFont(new Font("Tahoma", Font.BOLD, 13));
		random.setBounds(14, 645, 87, 29);
		add(random);
		
		JLabel textButton = new JLabel("Text");
		textButton.setHorizontalAlignment(SwingConstants.CENTER);
		textButton.setForeground(Color.WHITE);
		textButton.setFont(new Font("Dialog", Font.BOLD, 11));
		textButton.setBounds(141, 250, 60, 19);
		add(textButton);
		
		JComboBox comboBox7 = new JComboBox(color);
		comboBox7.setEditable(true);
		comboBox7.setSelectedItem("Select");
		comboBox7.setEditable(false);
		comboBox7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox6.getItemAt(comboBox6.getSelectedIndex()) + "";
				Setup.underDev();
			}
		});
		comboBox7.setBounds(131, 270, 80, 30);
		add(comboBox7);
		
		JComboBox comboBox8 = new JComboBox(color);
		comboBox8.setEditable(true);
		comboBox8.setSelectedItem("Select");
		comboBox8.setEditable(false);
		comboBox8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String option = comboBox6.getItemAt(comboBox6.getSelectedIndex()) + "";
				Setup.underDev();
			}
		});
		comboBox8.setBounds(23, 270, 80, 30);
		add(comboBox8);
		
		JLabel colorButton = new JLabel("Button");
		colorButton.setHorizontalAlignment(SwingConstants.CENTER);
		colorButton.setForeground(Color.WHITE);
		colorButton.setFont(new Font("Dialog", Font.BOLD, 11));
		colorButton.setBounds(30, 250, 65, 19);
		add(colorButton);
		
		//BUTTON SHAKING EFFECT
		JLabel lblButtonShaking = new JLabel("Button shaking");
		lblButtonShaking.setHorizontalAlignment(SwingConstants.CENTER);
		lblButtonShaking.setForeground(Color.WHITE);
		lblButtonShaking.setFont(new Font("Dialog", Font.BOLD, 11));
		lblButtonShaking.setBounds(120, 319, 101, 19);
		add(lblButtonShaking);
		
		/*JComponent editor2 = spinner2.getEditor();
		JSpinner.DefaultEditor spinnerEditor2 = (JSpinner.DefaultEditor)editor2;
		spinnerEditor2.getTextField().setHorizontalAlignment(JTextField.RIGHT);	*/	
		spinner2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner2.setForeground(Color.BLACK);
		spinner2.setBounds(133, 340, 57, 27);
		spinner2.setEditor(new JSpinner.DefaultEditor(spinner2));
		spinner2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateButtonShaking ((int) spinner2.getValue());				
			}
		});
		add(spinner2);
		
		
		
		/*JCheckBox spotlight = new JCheckBox("Spotlight mode");
		spotlight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (spotlight.isSelected()) {
					Z.changeLayer(Setup.getWindow());
					Setup.spotlightMode=true;
				} else {
					Setup.spotlightMode=false;
				}
				Z.jlayer.repaint();
			}
		});
		spotlight.setForeground(Color.WHITE);
		spotlight.setBackground(new Color(a,a,a));
		spotlight.setBounds(61, 403, 123, 25);
		add(spotlight);*/
		
		
		btnDefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateBrightness(100);
				updateSliderSpeed(100);
				updateButtonShaking(2);
				updateOpacity(100);
				updateWindowCorner(0);
				updateLaf("Nimbus");
				updatePageColor("Gray");
				updatePageTextColor("Black");
				updateSidebarColor("Gray");
				updateSidebarTextColor("White");
				updateFont("Tahoma");
			}
		});
	}
	
	public void updateFont(String newFont) {
		Setup.font = newFont;
		Z.resetPage();
		Z.resetSidebar();
		comboBox5.setSelectedItem(newFont);
	}
	
	public void updateLaf(String newLaf) {
		if (newLaf.equals("Nimbus")) {
			Setup.laf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		} else if (newLaf.equals("Metal")) {
			Setup.laf = "javax.swing.plaf.metal.MetalLookAndFeel";
		} else if (newLaf.equals("Motif")) {

			Setup.laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		} else if (newLaf.equals("Window")) {
			Setup.laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		} else if (newLaf.equals("Classic")) {
			Setup.laf = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
		}
		try {
			UIManager.setLookAndFeel(Setup.laf);
			for (Frame f : Frame.getFrames()) {
				SwingUtilities.updateComponentTreeUI(f);
			}
		} catch (Exception e) {
			System.out.println("Substance Graphite failed to initialize");
		}
		
		comboBox.setSelectedItem(newLaf);
		
	}
	
	public void updatePageColor(String newColor) {
		if (newColor.equals("Gray")) {
			Setup.colorPage = Color.LIGHT_GRAY;
		} else if (newColor.equals("Black")) {
			Setup.colorPage = Color.BLACK;
		} else if (newColor.equals("White")) {
			Setup.colorPage = Color.WHITE;
		} else if (newColor.equals("Blue")) {
			Setup.colorPage = Color.BLUE;
		}
		Z.page.setBackground(Setup.colorPage);
		Z.headbar.setBackground(Setup.colorPage);
		comboBox2.setSelectedItem(newColor);
	}
	
	public void updatePageTextColor(String newColor) {
		if (newColor.equals("Gray")) {
			Setup.colorPageText = Color.LIGHT_GRAY;
		} else if (newColor.equals("Black")) {
			Setup.colorPageText = Color.BLACK;
		} else if (newColor.equals("White")) {
			Setup.colorPageText = Color.WHITE;
		} else if (newColor.equals("Blue")) {
			Setup.colorPageText = Color.BLUE;
		}
		Z.resetPage();
		comboBox4.setSelectedItem(newColor);
	}
	
	public void updateSidebarColor(String newColor) {
		if (newColor.equals("Gray")) {
			Setup.colorSidebar = Color.DARK_GRAY;
		} else if (newColor.equals("Black")) {
			Setup.colorSidebar = Color.BLACK;
		} else if (newColor.equals("White")) {
			Setup.colorSidebar = Color.WHITE;
		} else if (newColor.equals("Blue")) {
			Setup.colorSidebar = Color.BLUE;
		}
		Z.resetSidebar();
		comboBox3.setSelectedItem(newColor);
	}
	
	public void updateSidebarTextColor(String newColor) {
		if (newColor.equals("Gray")) {
			Setup.colorSidebarText = Color.LIGHT_GRAY;
		} else if (newColor.equals("Black")) {
			Setup.colorSidebarText = Color.BLACK;
		} else if (newColor.equals("White")) {
			Setup.colorSidebarText = Color.WHITE;
		} else if (newColor.equals("Blue")) {
			Setup.colorSidebarText = Color.BLUE;
		}
		Z.resetSidebar();
		comboBox6.setSelectedItem(newColor);
	}
	
	public void updateWindowCorner(int num) {
		slider2.setValue(num);
		Setup.windowCorner = num;
		Setup.getWindow().setShape(new RoundRectangle2D.Double(0, 0, Setup.getWindow().getWidth(), Setup.getWindow().getHeight(), num, num));
	}
	
	
	public void updateSliderSpeed(int num) {
		Setup.slicerSpeed = (100 * 7) / num;
		spinner.setValue(num);
		chckbxInstantly.setSelected(false);
	}
	
	public void updateOpacity(int num) {
		Setup.opacity = num;
		Setup.getWindow().setOpacity(Setup.opacity * 0.01f);
		slider.setValue(num);
	}
	
	public void updateBrightness(int num) {
		Setup.brightness = num;
		Z.jlayer.repaint();
		slider3.setValue(num);
	}
	
	public void updateButtonShaking(int num) {
		spinner2.setValue(num);
		Setup.amplitudeShakeButton=num;
	}
	
	public JFormattedTextField getTextField(JSpinner spinner) {
	    JComponent editor = spinner.getEditor();
	    if (editor instanceof JSpinner.DefaultEditor) {
	        return ((JSpinner.DefaultEditor)editor).getTextField();
	    } else {
	        System.err.println("Unexpected editor type: "
	                           + spinner.getEditor().getClass()
	                           + " isn't a descendant of DefaultEditor");
	        return null;
	    }
	}
}
