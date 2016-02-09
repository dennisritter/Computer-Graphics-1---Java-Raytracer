package de.bht.bobross.geometry;

import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;

/**
 * Abstraction of a geometric object
 *
 * @author Dennis Ritter
 */
public abstract class Geometry {

  /** The Geometry's material   */
  public final Material material;

  /**
   * The constructor
   * @param   material  The Material of this geometry
   */
  public Geometry ( final Material material ) {
    this.material = material;
  }

  /**
   * Tests whether a ray hits a geometry and returns an Hit Object representing this hit.
   * Returns null if the geometry has not been hit by the Ray.
   *
   * @param   r   The Ray that hits a geometry
   * @return      A Hit Object representing the intersection between the Ray and the geometry or null if the geometry has not been hit by the Ray
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
