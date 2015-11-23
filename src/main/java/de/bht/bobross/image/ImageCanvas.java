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
   * The image to draw in
   */
  protected BufferedImage image;

  /**
   * Initialises a new BufferedImage with black as background-color and draws a red diagonal line.
   *
   * @param     g         The Graphics object to draw with
   */
  public void paint ( Graphics g ) {
    super.paint(g);
    image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
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

  /**
   * @return    The BufferedImage with black background and a red diagonal line
   */
  public BufferedImage getImage() {
    return image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ImageCanvas that = (ImageCanvas) o;

    return !(image != null ? !image.equals(that.image) : that.image != null);

  }

  @Override
  public int hashCode() {
    return image != null ? image.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "ImageCanvas{" +
        "image=" + image +
        '}';
  }
}