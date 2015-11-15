package de.bht.bobross;

import de.bht.bobross.camera.Camera;
import de.bht.bobross.geometry.Hit;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * @author      Jannik Portz
 */
public class Raytracer {

  public final Camera camera;
  public final World world;
  public final BufferedImage image;

  public Raytracer ( Camera camera, World world, BufferedImage image ) {
    this.camera = camera;
    this.world = world;
    this.image = image;
  }

  public void fillImage () {
    final DataBufferInt dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
    final int[] pixels = dataBuffer.getData();
    final int w = image.getWidth();
    final int h = image.getHeight();

    for ( int x = 0; x < image.getWidth(); x++ ) {
      for ( int y = 0; y < image.getHeight(); y++ ) {
        pixels[ y * w + x ] = traceRay( x, y ).asInt();
      }
    }
  }

  public Color traceRay ( int x, int y ) {
    if ( x < 0 || x >= image.getWidth() )
      throw new IllegalArgumentException( "Parameter x must be in the range of the image's width." );

    if ( y < 0 || y >= image.getHeight() )
      throw new IllegalArgumentException( "Parameter y must be in the range of the image's height" );

    final Ray r = camera.rayFor( image.getWidth(), image.getHeight(), x, y );
    final Hit h = world.hit( r );

    if ( h == null )
      return world.backgroundColor;

    return h.geo.color;
  }

}
