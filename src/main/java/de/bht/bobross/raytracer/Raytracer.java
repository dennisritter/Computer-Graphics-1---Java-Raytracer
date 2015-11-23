package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.geometry.Hit;

/**
 * A Raytracer traces rays coming from a camera and returns the closest hit with a Geometry.
 *
 * @author      Jannik Portz
 */
public class Raytracer {

  /** The camera whose rays to hit against the geometries */
  public final Camera camera;

  /** The world containing all geometries in the scene */
  public final World world;

  /**
   * Constructs a new Raytracer with a camera and a world
   *
   * @param     camera    The camera whose rays to hit against the geometries
   * @param     world     The world containing all geometries in the scene
   */
  public Raytracer ( Camera camera, World world ) {
    this.camera = camera;
    this.world = world;
  }

  /**
   * Traces a ray representing the specified pixel and determines the color of the pixel
   *
   * @param     x         The pixel's x coordinate
   * @param     y         The pixel's y coordinate
   * @param     width     The image's width
   * @param     height    The image's height
   * @return              The color in which the specified pixel shall be displayed
   */
  public Color traceRay ( final int x, final int y, final int width, final int height ) {
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
