package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;

/**
 * Represents a geometric object
 * @author Dennis Ritter
 */
public abstract class Geometry {

  public final Color color;

  /**
   * The constructor
   * @param   color   The color of this Geometry
   */
  public Geometry(Color color){
    this.color = color;
  }

  /**
   * Tests whether a ray hit a Geometry and returns a Hit Object representing this hit
   * @param   r   The Ray that hit a Geometry
   * @return      A Hit Object representing the intersection between the Ray and the Geometry
   */
  public abstract Hit hit(Ray r);

}