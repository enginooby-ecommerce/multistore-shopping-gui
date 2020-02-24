package test;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CircleSplashScreen {

    public CircleSplashScreen() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new ImagePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CircleSplashScreen();
            }
        });
    }

    @SuppressWarnings("serial")
    public class ImagePanel extends JPanel {

        BufferedImage img;

        public ImagePanel() {
            setOpaque(false);
            setLayout(new GridBagLayout());
            try {
                img =  ImageIO.read(new File("C:\\Users\\Administrator\\Downloads\\5a352d24950917.8154114015134344046105.png"));
            } catch (IOException ex) {
                Logger.getLogger(CircleSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }
    }
}