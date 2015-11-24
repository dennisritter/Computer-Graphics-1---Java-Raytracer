package de.bht.bobross.geometry;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Normal3;

/**
 * Represents a intersection between a Ray and Geometry
 *
 * @author Dennis Ritter
 */
public class Hit {

  /** The scalar when the direction Vector hit the Geometry */
  public final double t;

  /** The Ray that hits the Geometry */
  public final Ray ray;

  /** The Geometry that has been hit */
  public final Geometry geo;

  /** The Normal of the intersection */
  public final Normal3 normal;

  /**
   * Constructs a new Hit instance with the factor t, the Ray and the Geometry
   *
   * @param   t     The scalar when the direction Vector hits the Geometry
   * @param   ray   The Ray that hits the Geometry
   * @param   geo   The Geometry that has been hit
   */
  public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 normal){
    this.t = t;
    this.ray = ray;
    this.geo = geo;
    this.normal = normal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Hit hit = (Hit) o;

    if (Double.compare(hit.t, t) != 0) return false;
    if (!ray.equals(hit.ray)) return false;
    return geo.equals(hit.geo);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(t);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + ray.hashCode();
    result = 31 * result + geo.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Hit{" +
        "t=" + t +
        ", ray=" + ray +
        ", geo=" + geo +
        '}';
  }
}
