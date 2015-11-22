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
    final double aHelp = r.d.dot(r.d);
    final double bHelp = r.d.dot((r.o.sub(c)).mul(2));
    final double cHelp = (r.o.sub(c).dot(r.o.sub(c))) - this.r * this.r;
    final double dHelp = bHelp * bHelp - 4 * aHelp * cHelp;

    if(aHelp == 0){
      return null;
    }
    if(dHelp < 0){
      return null;
    }
    final double t1 = ( -1 * bHelp + Math.sqrt(dHelp) ) / 2 * aHelp;
    final double t2 = ( -1 * bHelp - Math.sqrt(dHelp) ) / 2 * aHelp;

    if(t1 < 0){
      return new Hit(t2, r, this);
    }
    if(t2 < 0){
      return new Hit(t1, r, this);
    }
    return new Hit(Math.min(t1 ,t2), r, this);
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
