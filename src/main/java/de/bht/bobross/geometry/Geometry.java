package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;

/**
 * Abstraction of a geometric object
 *
 * @author Dennis Ritter
 */
public abstract class Geometry {

  /** The Geometrie's material   */
  public final Material material;

  /**
   * The constructor
   * @param   material  The Material of this Geometry
   */
  public Geometry ( final Material material ) {
    this.material = material;
  }

  /**
   * Tests whether a ray hits a Geometry and returns an Hit Object representing this hit.
   * Returns null if the Geometry has not been hit by the Ray.
   *
   * @param   r   The Ray that hits a Geometry
   * @return      A Hit Object representing the intersection between the Ray and the Geometry or null if the Geometry has not been hit by the Ray
   */
  public abstract Hit hit ( final Ray r );

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Geometry geometry = (Geometry) o;

    return material.equals(geometry.material);

  }

  @Override
  public int hashCode() {
    return material.hashCode();
  }
}
