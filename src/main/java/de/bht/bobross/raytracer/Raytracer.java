package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.geometry.Hit;

import java.awt.image.DataBufferInt;

/**
 * @author      Jannik Portz
 */
public class Raytracer {

  public final Camera camera;
  public final World world;

  public Raytracer ( Camera camera, World world ) {
    this.camera = camera;
    this.world = world;
  }

  public Color traceRay (final int x, final int y, final int width, final int height ) {
    if ( x < 0 || x >= width )
      throw new IllegalArgumentException( "Parameter x must be in the range of the image's width." );

    if ( y < 0 || y >= height )
      throw new IllegalArgumentException( "Parameter y must be in the range of the image's height" );

    final Ray r = camera.rayFor( width, height, x, y );
    final Hit h = world.hit( r );

    if ( h == null )
      return world.backgroundColor;

    return h.geo.color;
  }

}
