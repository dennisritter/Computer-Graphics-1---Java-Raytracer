package de.bht.bobross.raytracer;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Iterates through all pixels with the provided raytracer and draws the scene as an image
 *
 * @author    Jannik Portz
 */
public class RaytracerPanel extends JPanel {

  /** The raytracer to use for the pixels */
  protected final Raytracer raytracer;

  /** The image to draw on */
  protected final BufferedImage image;

  /**
   * Constructs a new RaytracerPanel with a raytracer and the image dimensions
   *
   * @param     raytracer The raytracer containing the scene and camera
   * @param     width     The image's width
   * @param     height    The image's height
   */
  public RaytracerPanel ( final Raytracer raytracer, final int width, final int height ) {
    this.raytracer = raytracer;
    this.image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

    setSize( width, height );
  }


  /**
   * Iterates through all pixels and draws the colors onto the image
   *
   * @param     g       The graphics instance to use for drawing the image
   */
  @Override
  protected void paintComponent( final Graphics g ) {
    super.paintComponent(g);

    final DataBufferInt dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
    final int[] pixels = dataBuffer.getData();
    final int w = image.getWidth();
    final int h = image.getHeight();

    for ( int y = 0; y < h; y++ ) {
      for ( int x = 0; x < w; x++ ) {
        pixels[ (h-y-1) * w + x ] = raytracer.traceRay( x, y, image.getWidth(), image.getHeight() ).asInt();
      }
    }

    g.drawImage( image, 0, 0, this );
  }
}