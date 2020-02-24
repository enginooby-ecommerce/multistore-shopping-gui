package gui.utility;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.Kernel;
import java.awt.image.ConvolveOp;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;

import asystem.Setup;

public class BlurLayerUI extends LayerUI<JPanel> {
  private BufferedImage mOffscreenImage;
  private BufferedImageOp mOperation;

  public BlurLayerUI() {
    
  }

  @Override
  public void paint (Graphics g, JComponent c) {
	  
	  float ninth = Setup.brightness/100f; //brightness
	    float[] blurKernel = {
	      ninth, ninth, ninth,
	      ninth, ninth, ninth,
	      ninth, ninth, ninth
	    };
	    mOperation = new ConvolveOp(
	            new Kernel(Setup.blurriness, Setup.blurriness, blurKernel), //blurriness
	            ConvolveOp.EDGE_NO_OP, null);
	  
    int w = c.getWidth();
    int h = c.getHeight();

    if (w == 0 || h == 0) {
      return;
    }

    // Only create the offscreen image if the one we have
    // is the wrong size.
    if (mOffscreenImage == null ||
            mOffscreenImage.getWidth() != w ||
            mOffscreenImage.getHeight() != h) {
      mOffscreenImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }

    Graphics2D ig2 = mOffscreenImage.createGraphics();
    ig2.setClip(g.getClip());
    super.paint(ig2, c);
    ig2.dispose();

    Graphics2D g2 = (Graphics2D)g;
    g2.drawImage(mOffscreenImage, mOperation, 0, 0);
  }
}