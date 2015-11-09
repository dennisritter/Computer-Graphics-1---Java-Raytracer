package de.bht.bobross.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * @author      Nathalie Junker
 */
public class CreateImageCanvas extends Canvas {

  /**The generated image */
  private BufferedImage img;

  /**
   * This method initialises a new BufferedImage with black as background-color and a red line.
   */
  public void paint(Graphics g){
    super.paint(g);
    this.img = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
    final WritableRaster raster = this.img.getRaster();
    final ColorModel m = this.img.getColorModel();

    for (int i = 0; i < this.img.getWidth(); i++) {
      for (int j = 0; j < this.img.getHeight(); j++) {
        raster.setDataElements(i, j, m.getDataElements(Color.BLACK.getRGB(), null));
        if (i == j) {
          raster.setDataElements(i, j, m.getDataElements(Color.RED.getRGB(), null));
        }
      }
    }
    g.drawImage(this.img, 0,0, this);
  }



}
