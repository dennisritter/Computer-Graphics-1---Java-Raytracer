package de.bht.bobross.geometry;

import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;
import de.bht.bobross.math.Helpers;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a circle in a three-dimensional room.
 *
 * @author    Dennis Ritter
 */
public class Sphere extends Geometry
{
  /** The center of this Sphere */
  public final Point3 c;

  /** The radius of this Sphere */
  public final double r;

  /**
   * Constructs a new Sphere with a center at 0,0,0 and radius 1
   * @param   material   The color of the new Sphere
   */
  public Sphere( final Material material ){
    super(material);
    this.c = new Point3(0,0,0);
    this.r = 1;
  }

  /**
   * Constructs a new Sphere
   * @param   c          The center of the new Sphere
   * @param   r          The radius of the new Sphere
   * @param   material   The color of the new Sphere
   */
  public Sphere( final Point3 c, final double r, final Material material ){
    super(material);
    this.c = c;
    this.r = r;
  }

  @Override
  public Hit hit( final Ray ray ) {
    final double aHelp = ray.d.dot(ray.d);
    final double bHelp = ray.d.dot((ray.o.sub(c)).mul(2));
    final double cHelp = (ray.o.sub(c).dot(ray.o.sub(c))) - this.r * this.r;
    final double dHelp = bHelp * bHelp - 4 * aHelp * cHelp;

    if(dHelp < 0){
      return null;
    }

    if(aHelp == 0){
      return null;
    }

    final double t1 = ( -bHelp - Math.sqrt(dHelp) ) / ( 2 * aHelp ) ;
    final double t2 = ( -bHelp + Math.sqrt(dHelp) ) / ( 2 * aHelp ) ;
    final double tHi = Math.max( t1, t2 );
    final double tLo = Math.min( t1, t2 );

    if ( tHi < Helpers.EPSILON ) {
      return null;
    }
    double t = tLo;
    if ( tLo < 0 && tHi > Helpers.EPSILON) {
      t = tHi;
    }

    final Point3 pr = ray.at(t);
    final Vector3 prc = pr.sub(c);
    final Normal3 n = prc.asNormal();

    return new Hit(t, ray, this, n);
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

  @Override
  public String toString(){
    return "Sphere{" +
        "c=" + c +
        ", r=" + r +
        ", material=" + material +
        "} " + super.toString();
  }
}
