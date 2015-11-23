package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.geometry.Hit;

import java.awt.image.DataBufferInt;

/**
 * @author      Jannik Portz
 * TODO: Add Comments
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

    if ( h == null || h.t <= 0 )
      return world.backgroundColor;

    return h.geo.color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Raytracer raytracer = (Raytracer) o;

    if (camera != null ? !camera.equals(raytracer.camera) : raytracer.camera != null) return false;
    return !(world != null ? !world.equals(raytracer.world) : raytracer.world != null);

  }

  @Override
  public int hashCode() {
    int result = camera != null ? camera.hashCode() : 0;
    result = 31 * result + (world != null ? world.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Raytracer{" +
        "camera=" + camera +
        ", world=" + world +
        '}';
  }
}
