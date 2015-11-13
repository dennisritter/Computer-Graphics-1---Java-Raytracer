package de.bht.bobross;

import de.bht.bobross.geometry.Geometry;
import de.bht.bobross.geometry.Hit;

/**
 * Represents a world containing a collection of geometries
 *
 * @author      Jannik Portz
 */
public class World {

  /** Collection of geometries in the world */
  public final Geometry[] objects;

  /** The world's background color */
  public final Color backgroundColor;

  /**
   * Constructs a new world
   *
   * @param     objects     Array of geometries that this world contains
   * @param     backgroundColor The world's background color
   */
  public World ( Geometry[] objects, Color backgroundColor ) {
    this.objects = objects;
    this.backgroundColor = backgroundColor;
  }

  /**
   * Determines the hit of the specified ray with each of the geometries in the collection and returns the hit closest to the camera
   *
   * @param     r     The ray to hit the geometries
   * @return          The hit closest to the camera or null if the ray does not hit any of the geometries
   */
  public Hit hit ( Ray r ) {
    Hit minHit = null;

    for ( Geometry g : objects ) {
      final Hit h = g.hit( r );
      if ( h != null && ( minHit == null || h.t < minHit.t ) )
        minHit = h;
    }

    return minHit;
  }
}
