package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.math.Point3;

/**
 * Represents a circle in a three-dimensional room.
 *
 * @author    Dennis Ritter
 */
public class Sphere extends Geometry
{
  /**
   * The center of this Sphere
   */
  public final Point3 c;

  /**
   * The radius of this Sphere
   */
  public final double r;

  /**
   * Constructs a new Sphere
   * @param   c       The center of the new Sphere
   * @param   r       The radius of the new Sphere
   * @param   color   The color of the new Sphere
   */
  public Sphere(final Point3 c, final double r, Color color){
    super(color);
    this.c = c;
    this.r = r;
  }

  @Override
  public Hit hit(Ray r) {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Sphere sphere = (Sphere) o;

    if (Double.compare(sphere.r, r) != 0) return false;
    return !(c != null ? !c.equals(sphere.c) : sphere.c != null);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    result = 31 * result + (c != null ? c.hashCode() : 0);
    temp = Double.doubleToLongBits(r);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  public String toString(){
    return "Sphere{" +
        "c=" + c +
        ", r=" + r +
        ", color=" + color +
        "} " + super.toString();
  }
}
