package de.bht.bobross.geometry;

import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;
import de.bht.bobross.math.Helpers;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a Disc Geometry
 *
 * @author Jannik Portz
 */

public class Disc extends Geometry {

  /** The disc's center */
  public final Point3 c;

  /** The disc's normal */
  public final Normal3 n;

  /** The disc's radius */
  public final double radius;

  /**
   * The Constructor.
   * @param c         The disc's center
   * @param n         The disc's normal
   * @param radius    The disc's radius
   * @param material  The disc's material
   */
  public Disc ( final Point3 c, final Normal3 n, final double radius, final Material material ) {
    super( material );
    this.c = c;
    this.n = n;
    this.radius = radius;
  }

  public Disc ( final Material material ) {
    super( material );
    this.c = new Point3(0,0,0);
    this.n = new Normal3(0,1,0);
    this.radius = 1.0;
  }

  @Override
  public Hit hit(Ray r) {
    if ( n.dot(r.d) == 0 ) {
      return null;
    }

    final double t = n.dot(c.sub(r.o))/n.dot(r.d);
    final Point3 i = r.at( t );
    final Vector3 v = i.sub( c );

    if ( v.magnitude > radius )
      return null;

    if ( t < Helpers.EPSILON )
      return null;

    return new Hit( t, r, this, this.n );
  }
}
