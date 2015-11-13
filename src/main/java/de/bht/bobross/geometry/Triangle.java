package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.math.Mat3x3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

public class Triangle extends Geometry {

  /** The triangle's first vertex */
  public final Point3 a;

  /** The triangle's second vertex */
  public final Point3 b;

  /** The triangle's third vertex */
  public final Point3 c;

  /**
   * Constructs a new triangle with its vertices and color
   *
   * @param     a     The triangle's first vertex
   * @param     b     The triangle's second vertex
   * @param     c     The triangle's third vertex
   * @param     color The triangle's color
   */
  public Triangle (final Point3 a, final Point3 b, final Point3 c, final Color color) {
    super(color);
    this.a = a;
    this.b = b;
    this.c = c;
  }

  @Override
  public Hit hit (Ray r) {
    final Mat3x3 m = new Mat3x3(
        a.x-b.x, a.x-c.x, r.d.x,
        a.y-b.y, a.y-c.y, r.d.y,
        a.z-b.z, a.z-c.z, r.d.z
    );

    final Vector3 v = new Vector3( a.x-r.o.x, a.y-r.o.y, a.z-r.o.z );

    final Mat3x3 m1 = m.changeCol1( v );
    final Mat3x3 m2 = m.changeCol2(v);
    final Mat3x3 m3 = m.changeCol3(v);

    final double beta = m1.determinant / m.determinant;
    final double gamma = m2.determinant / m.determinant;
    final double t = m3.determinant / m.determinant;

    if ( beta < 0 || beta > 1 )
      return null;

    if ( gamma < 0 || gamma > 1 )
      return null;

    if ( gamma + beta > 1 )
      return null;

    return new Hit( t, r, this );
  }
}
