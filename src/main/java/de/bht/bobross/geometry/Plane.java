package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;

/**
 * Represents a plane in three-dimensional room
 *
 * @author      Nathalie Junker
 */
public class Plane extends Geometry {

  /** An arbitrary point on the plane */
  public final Point3 a;

  /** The plane's normal */
  public final Normal3 n;

  /**
   * Constructs a new plane
   *
   * @param     a           An arbitrary point on the plane
   * @param     n           The plane's normal
   * @param     material    The plane's material
   */
  public Plane ( final Point3 a, final Normal3 n, final Material material ) {
    super(material);
    this.a = a;
    this.n = n;
  }

  @Override
  public Hit hit(Ray r) {
    if(n.dot(r.d) == 0){
      return null;
    }

    double t = n.dot(a.sub(r.o))/n.dot(r.d);

    return new Hit(t, r, this, n);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Plane plane = (Plane) o;

    if (a != null ? !a.equals(plane.a) : plane.a != null) return false;
    return !(n != null ? !n.equals(plane.n) : plane.n != null);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (a != null ? a.hashCode() : 0);
    result = 31 * result + (n != null ? n.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Plane{" +
        "a=" + a +
        ", n=" + n +
        ", material=" + material +
        "} " + super.toString();
  }
}
