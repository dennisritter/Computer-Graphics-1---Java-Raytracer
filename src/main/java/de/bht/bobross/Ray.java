package de.bht.bobross;

import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a Ray described by its origin and direction
 *
 * @author      Jannik Portz
 */
public class Ray {

  /** The Ray's origin */
  public final Point3 o;

  /** The Ray's direction */
  public final Vector3 d;

  /**
   * Constructs the Ray wih its origin and direction
   *
   * @param     o   The Ray's origin
   * @param     d   The Ray's direction
   */
  public Ray ( final Point3 o, final Vector3 d ) {
    this.o = o;
    this.d = d;
  }

  /**
   * Returns the point at which the ray is when the direction vector is multiplied with t
   *
   * @param     t   The factor to multiply the direction vector with
   * @return        The point at which the ray will be
   */
  public Point3 at ( final double t ) {
    return o.add( d.mul( t ) );
  }

  /**
   * Returns the factor to multiply the direction vector with to reach the specified point p
   *
   * @param     p     The point to reach
   * @return          The factor to multiply the direction vector with to reach the point p
   */
  public double tOf ( final Point3 p ) {
    return ( p.sub( o ).magnitude) / d.magnitude;
  }

  @Override
  public boolean equals(Object o1) {
    if (this == o1) return true;
    if (o1 == null || getClass() != o1.getClass()) return false;

    Ray ray = (Ray) o1;

    if (!o.equals(ray.o)) return false;
    return d.equals(ray.d);
  }

  @Override
  public int hashCode() {
    int result = o.hashCode();
    result = 31 * result + d.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Ray{" +
        "o=" + o +
        ", d=" + d +
        '}';
  }
}
