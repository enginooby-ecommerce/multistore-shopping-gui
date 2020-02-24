package test;

import com.sun.java.swing.Painter;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GridLayout;
import java.util.Map;

/**
 * ThemeDemo
 *
 * @author Created by Jasper Potts (May 7, 2008)
 * @version 1.0
 */
public class SliderSkinDemo {

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(laf.getName())){
                        try {
                            UIManager.setLookAndFeel(laf.getClassName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                for (Map.Entry<Object, Object> entry : UIManager.getLookAndFeelDefaults().entrySet()) {
                    if ((entry.getKey().toString()).startsWith("Slider")){
                        System.out.println(entry.getKey() +" = "+ entry.getValue());
                    }
                }

                JFrame frame = new JFrame("Slider Skining Demo");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                JPanel panel = new JPanel(new GridLayout(0,1,20,20));
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel.setBackground(Color.darkGray);

                UIDefaults sliderDefaults = new UIDefaults();

                sliderDefaults.put("Slider.thumbWidth", 20);
                sliderDefaults.put("Slider.thumbHeight", 20);
                sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new Painter<JComponent>() {
                    public void paint(Graphics2D g, JComponent c, int w, int h) {
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setStroke(new BasicStroke(2f));
                        g.setColor(Color.RED);
                        g.fillOval(1, 1, w-3, h-3);
                        g.setColor(Color.WHITE);
                        g.drawOval(1, 1, w-3, h-3);
                    }
                });
                sliderDefaults.put("Slider:SliderTrack.backgroundPainter", new Painter<JComponent>() {
                    public void paint(Graphics2D g, JComponent c, int w, int h) {
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setStroke(new BasicStroke(2f));
                        g.setColor(Color.GRAY);
                        g.fillRoundRect(0, 6, w-1, 8, 8, 8);
                        g.setColor(Color.WHITE);
                        g.drawRoundRect(0, 6, w-1, 8, 8, 8);
                    }
                });

                JSlider slider = new JSlider(0, 100, 50);
                panel.add(slider);
                slider.putClientProperty("Nimbus.Overrides",sliderDefaults);
                slider.putClientProperty("Nimbus.Overrides.InheritDefaults",false);

                // Add a normal themed slider for comparison
                JSlider normalSlider = new JSlider(0, 100, 50);
                panel.add(normalSlider);

                frame.getContentPane().add(panel, BorderLayout.CENTER);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }*/
}