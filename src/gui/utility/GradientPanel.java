package gui.utility;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GradientPanel extends JPanel {

	/** Starting Gradient Color. */
    private Color startColor;
    
    /** Ending Gradient Color. */
    private Color endColor;

    public static final int DIRECTION_TOPDOWN = 0;
    public static final int DIRECTION_LEFTRIGHT = 0;

    public static final Color HEADER_COLOR_START = new Color(0x830401);
    public static final Color HEADER_COLOR_END = new Color(0xDD5731);
    public static final Color WIZARD_COLOR_START = new Color(0x000080);
    public static final Color WIZARD_COLOR_END = new Color(0x2179DA);
    public static final Color WARNING_COLOR_START = new Color(0xE80000);
    public static final Color WARNING_COLOR_END = new Color(0x000000);
    
    public static final Color SELECTED_GRID_CELL_BG_COLOR = new Color(0xE2F5FE);

    /**
     * Constructor supplying a color.
     *
     * @param startColor
     * @param endColor
     */
    public GradientPanel( Color startColor , Color endColor ) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        GradientPaint gradientPaint = new GradientPaint( panelWidth / 5 , 0 , startColor , panelWidth / 5 , panelHeight , endColor );
        if( g instanceof Graphics2D ) {
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.setPaint( gradientPaint );
            graphics2D.fillRect( 0 , 0 , panelWidth , panelHeight );
        }
    }

    
}