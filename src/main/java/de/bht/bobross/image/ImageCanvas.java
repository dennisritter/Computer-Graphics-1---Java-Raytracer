package de.bht.bobross.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Canvas which draws the provided image inside.
 *
 * @author      Nathalie Junker
 */
public class ImageCanvas extends Canvas {

  /**
   * Initialises a new BufferedImage with black as background-color and draws a red diagonal line.
   *
   * @param     g         The Graphics object to draw with
   */
  public void paint ( Graphics g ) {
    super.paint(g);
    final BufferedImage image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
    final WritableRaster raster = image.getRaster();
    final ColorModel m = image.getColorModel();

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        raster.setDataElements(i, j, m.getDataElements(Color.BLACK.getRGB(), null));
        if (i == j) {
          raster.setDataElements(i, j, m.getDataElements(Color.RED.getRGB(), null));
        }
      }
    }
    g.drawImage( image, 0, 0, this );
  }

}