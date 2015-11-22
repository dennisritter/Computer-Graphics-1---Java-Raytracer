package de.bht.bobross.raytracer;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RaytracerPanel extends JPanel {

  protected final Raytracer raytracer;
  protected final BufferedImage image;

  public RaytracerPanel ( final Raytracer raytracer, final int width, final int height ) {
    this.raytracer = raytracer;
    this.image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

    setSize( width, height );
  }

  @Override
  protected void paintComponent( Graphics g ) {
    super.paintComponent(g);

    final DataBufferInt dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
    final int[] pixels = dataBuffer.getData();
    final int w = image.getWidth();
    final int h = image.getHeight();

    for ( int y = 0; y < h; y++ ) {
      for ( int x = 0; x < w; x++ ) {
        pixels[ y * w + x ] = raytracer.traceRay( x, y, image.getWidth(), image.getHeight() ).asInt();
      }
    }

    g.drawImage( image, 0, 0, this );
  }
}