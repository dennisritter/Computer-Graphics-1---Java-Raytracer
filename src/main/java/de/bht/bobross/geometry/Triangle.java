package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;
import de.bht.bobross.math.Mat3x3;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a triangle in three-dimensional room
 *
 * @author      Jannik Portz
 */

public class Triangle extends Geometry {

  /** The triangle's first vertex */
  public final Point3 a;

  /** The triangle's second vertex */
  public final Point3 b;

  /** The triangle's third vertex */
  public final Point3 c;

  /** The Normal of the triangle's first vertex */
  public final Normal3 aNormal;

  /** The Normal of the triangle's second vertex */
  public final Normal3 bNormal;

  /** The Normal of the triangle's third vertex */
  public final Normal3 cNormal;

  /**
   * Constructs a new triangle with its vertices and color
   *
   * @param     a         The triangle's first vertex
   * @param     b         The triangle's second vertex
   * @param     c         The triangle's third vertex
   * @param     material  The material of the triangle
   * @param     aNormal   The Normal of the triangle's first vertex
   * @param     bNormal   The Normal of the triangle's second vertex
   * @param     cNormal   The Normal of the triangle's third vertex
   */
  public Triangle ( final Point3 a, final Point3 b, final Point3 c,
                    final Normal3 aNormal, final Normal3 bNormal, final Normal3 cNormal,
                    final Material material ) {
    super(material);
    this.a = a;
    this.b = b;
    this.c = c;
    this.aNormal = aNormal;
    this.bNormal = bNormal;
    this.cNormal = aNormal;
  }

  public Triangle ( final Point3 a, final Point3 b, final Point3 c, final Material material) {
    super(material);
    this.a = a;
    this.b = b;
    this.c = c;
    aNormal = bNormal = cNormal = (b.sub(a).x(c.sub(a))).asNormal();
  }


  @Override
  public Hit hit ( final Ray r ) {
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

    final double alpha = 1 - gamma + beta;
    final Normal3 normal = aNormal.mul(alpha).add(bNormal.mul(beta)).add(cNormal.mul(gamma));

    return new Hit( t, r, this, normal );
  }

  @Override
  public String toString() {
    return "Triangle{" +
        "a=" + a +
        ", b=" + b +
        ", c=" + c +
        ", aNormal=" + aNormal +
        ", bNormal=" + bNormal +
        ", cNormal=" + cNormal +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Triangle triangle = (Triangle) o;

    if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
    if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
    if (c != null ? !c.equals(triangle.c) : triangle.c != null) return false;
    if (aNormal != null ? !aNormal.equals(triangle.aNormal) : triangle.aNormal != null) return false;
    if (bNormal != null ? !bNormal.equals(triangle.bNormal) : triangle.bNormal != null) return false;
    return !(cNormal != null ? !cNormal.equals(triangle.cNormal) : triangle.cNormal != null);

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (a != null ? a.hashCode() : 0);
    result = 31 * result + (b != null ? b.hashCode() : 0);
    result = 31 * result + (c != null ? c.hashCode() : 0);
    result = 31 * result + (aNormal != null ? aNormal.hashCode() : 0);
    result = 31 * result + (bNormal != null ? bNormal.hashCode() : 0);
    result = 31 * result + (cNormal != null ? cNormal.hashCode() : 0);
    return result;
  }
}
