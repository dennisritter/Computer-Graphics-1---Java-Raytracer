package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.math.Helpers;

/**
 * Class used to trace a single ray
 * Contains a recursion counter
 *
 * @author      Jannik Portz
 */
public class Tracer {

  /** Number of allowed recursions */
  public static final int ALLOWED_RECURSIONS = 4;

  /** The world containing all Geometries ans Lights */
  public final World world;

  /** Counter for recursions */
  public int recursions;

  /**
   * Constructs a new Tracer
   *
   * @param     world     The world containing all Geometries ans Lights
   */
  public Tracer ( final World world ) {
    this.world = world;
    this.recursions = ALLOWED_RECURSIONS;
  }

  /**
   * Traces a ray representing the specified pixel and determines the color of the pixel
   *
   * @param     ray       The ray to trace
   * @return              The color in which the specified pixel shall be displayed
   */
  public Color traceRay ( final Ray ray ) {
    if ( recursions == 0 )
      return world.backgroundColor;

    final Hit h = world.hit( ray );

    if ( h == null || h.t <= 0 )
      return world.backgroundColor;

    --recursions;
    return h.geo.material.colorFor( h, world, this );
  }

  /**
   * Resets the recursion counter.
   * Call this method when tracing a ray coming from a new pixel.
   */
  public void resetRecursionCounter () {
    recursions = ALLOWED_RECURSIONS;
  }

  /**
   * Decrements the Recursion counter
   */
  public void decrementRecursionCounter () {
    recursions++;
  }
}