package de.bht.bobross;

import de.bht.bobross.geometry.Geometry;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.light.Light;
import de.bht.bobross.math.Helpers;

import java.util.Arrays;

/**
 * Represents a world containing a collection of geometries
 *
 * @author      Jannik Portz
 */
public class World {

  /** Collection of all light sources in the world */
  public final Light[] lights;

  /** Collection of geometries in the world */
  public final Geometry[] objects;

  /** The world's background color */
  public final Color backgroundColor;

  /** The world's ambient light color */
  public final Color ambientLightColor;

  /**
   * Constructs a new world
   *
   * @param     objects         Array of geometries that this world contains
   * @param     lights          Array of lights that this world contains
   * @param     backgroundColor The world's background color
   * @param     ambientLightColor The color of the ambient light
   */
  public World ( final Geometry[] objects, final Light[] lights, final Color backgroundColor, final Color ambientLightColor ) {
    this.objects = objects;
    this.lights = lights;
    this.backgroundColor = backgroundColor;
    this.ambientLightColor = ambientLightColor;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    World world = (World) o;

    // Probably incorrect - comparing Object[] arrays with Arrays.equals
    if (!Arrays.equals(objects, world.objects)) return false;
    return !(backgroundColor != null ? !backgroundColor.equals(world.backgroundColor) : world.backgroundColor != null);
  }

  @Override
  public int hashCode() {
    int result = objects != null ? Arrays.hashCode(objects) : 0;
    result = 31 * result + (backgroundColor != null ? backgroundColor.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "World{" +
        "objects=" + Arrays.toString(objects) +
        ", backgroundColor=" + backgroundColor +
        '}';
  }
}
