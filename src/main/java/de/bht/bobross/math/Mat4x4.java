package de.bht.bobross.math;

import de.bht.bobross.exception.ParameterNullException;

/**
 * Represents a 4x4 Matrix and offers multiplication operations and methods to exchange column separately.
 * This class is immutable so all methods return new Mat4x4 instances and you won't be able to change any attributes after construction.
 *
 * @author      Nathalie Junker
 */

public class Mat4x4 {

  /** The value for the matrix' (1,1) entry */
  public final double m11;

  /** The value for the matrix' (1,2) entry */
  public final double m12;

  /** The value for the matrix' (1,3) entry */
  public final double m13;

  /** The value for the matrix' (1,4) entry */
  public final double m14;

  /** The value for the matrix' (2,1) entry */
  public final double m21;

  /** The value for the matrix' (2,2) entry */
  public final double m22;

  /** The value for the matrix' (2,3) entry */
  public final double m23;

  /** The value for the matrix' (2,4) entry */
  public final double m24;

  /** The value for the matrix' (3,1) entry */
  public final double m31;

  /** The value for the matrix' (3,2) entry */
  public final double m32;

  /** The value for the matrix' (3,3) entry */
  public final double m33;

  /** The value for the matrix' (3,4) entry */
  public final double m34;

  /** The value for the matrix' (4,1) entry */
  public final double m41;

  /** The value for the matrix' (4,2) entry */
  public final double m42;

  /** The value for the matrix' (4,3) entry */
  public final double m43;

  /** The value for the matrix' (4,4) entry */
  public final double m44;

  public Mat4x4 ( final double m11, final double m12, final double m13, final double m14,
                  final double m21, final double m22, final double m23, final double m24,
                  final double m31, final double m32, final double m33, final double m34,
                  final double m41, final double m42, final double m43, final double m44
                  ) {
    this.m11 = m11;
    this.m12 = m12;
    this.m13 = m13;
    this.m14 = m14;
    this.m21 = m21;
    this.m22 = m22;
    this.m23 = m23;
    this.m24 = m24;
    this.m31 = m31;
    this.m32 = m32;
    this.m33 = m33;
    this.m34 = m34;
    this.m41 = m41;
    this.m42 = m42;
    this.m43 = m43;
    this.m44 = m44;
  }

  /**
   * Returns the product of this matrix and the provided vector v
   *
   * @param     v         The vector to multiply this matrix with
   * @return              The product of this matrix and the provided vector v
   */
  public Vector3 mul ( final Vector3 v ) {
    if ( v == null ) {
      throw new ParameterNullException("v");
    }
    return new Vector3(
        m11 * v.x + m12 * v.y + m13 * v.z + m14 * 0,
        m21 * v.x + m22 * v.y + m23 * v.z + m24 * 0,
        m31 * v.x + m32 * v.y + m33 * v.z + m34 * 0
    );
  }

  /**
   * Multiplies this matrix with a Point3 and returns the resulting Point3 instance
   *
   * @param     p         The Point3 to multiply this matrix with
   * @return              The resulting Point3
   */
  public Point3 mul ( final Point3 p ) {
    if ( p == null ) {
      throw new ParameterNullException("p");
    }
    return new Point3 (
        m11 * p.x + m12 * p.y + m13 * p.z + m14 * 1,
        m21 * p.x + m22 * p.y + m23 * p.z + m24 * 1,
        m31 * p.x + m32 * p.y + m33 * p.z + m34 * 1
    );
  }

  /**
   * Returns the product of this matrix and the provided matrix m
   *
   * @param     m         The matrix to multiply this matrix with
   *
   * @return    Mat4x4    The product of this matrix and the provided matrix m
   */
  public Mat4x4 mul ( final Mat4x4 m ) {
    if ( m == null ) {
      throw new ParameterNullException("m");
    }
    return new Mat4x4 (
        m11 * m.m11 + m12 * m.m21 + m13 * m.m31 + m14 * m.m41,
        m11 * m.m12 + m12 * m.m22 + m13 * m.m32 + m14 * m.m42,
        m11 * m.m13 + m12 * m.m23 + m13 * m.m33 + m14 * m.m43,
        m11 * m.m14 + m12 * m.m24 + m13 * m.m34 + m14 * m.m44,

        m21 * m.m11 + m22 * m.m21 + m23 * m.m31 + m24 * m.m41,
        m21 * m.m12 + m22 * m.m22 + m23 * m.m32 + m24 * m.m42,
        m21 * m.m13 + m22 * m.m23 + m23 * m.m33 + m24 + m.m43,
        m21 * m.m14 + m22 * m.m24 + m23 * m.m34 + m24 * m.m44,

        m31 * m.m11 + m32 * m.m21 + m33 * m.m31 + m34 * m.m41,
        m31 * m.m12 + m32 * m.m22 + m33 * m.m32 + m34 * m.m42,
        m31 * m.m13 + m32 * m.m23 + m33 * m.m33 + m34 * m.m43,
        m31 * m.m14 + m32 * m.m24 + m33 * m.m34 + m34 * m.m44,

        m41 * m.m11 + m42 * m.m21 + m43 * m.m31 + m44 * m.m41,
        m41 * m.m12 + m42 * m.m22 + m43 * m.m32 + m44 * m.m42,
        m41 * m.m13 + m42 * m.m23 + m43 * m.m33 + m44 * m.m43,
        m41 * m.m14 + m42 * m.m24 + m43 * m.m34 + m44 * m.m44
    );
  }

  /**
   * Returns the transposed matrix of this matrix
   *
   * @return     The transposed matrix
   */

  public Mat4x4 transpose(){
    return new Mat4x4(
        m11, m21, m31, m44, m12, m22, m32, m42, m13, m23, m33, m43, m14, m24, m34, m44
    );
  }

  /**
   * Nathalie's Arbeit sollte gewürdigt werden.
   *
   * @return  Schweiß und Tränen
   */
  public Mat4x4 getInverse() {
    if (
        (m11 * m22 * m33 * m44 + m11 * m23 * m34 * m42 + m11 * m24 * m32 * m43 +
            m12 * m21 * m34 * m43 + m12 * m23* m31 * m44 + m12 * m24 *m33 * m41 +
            m13 * m21 * m32 * m44 + m13 * m22 * m34 * m41 + m13 * m24 * m31 * m42 +
            m14 * m21 * m33 * m42 + m14 * m22 * m31 * m43 + m14 * m23 * m32 * m41 -
            m11 * m22 * m34 * m43 - m11 * m23 * m32 * m44 - m11 * m24 * m33 * m42 -
            m12 * m21 * m33 * m44 - m12 * m23 * m34 * m41 - m12 * m24 * m31 * m43 -
            m13 * m21 * m34 * m42 - m13 * m22 * m31 * m44 - m13 * m24 * m32 * m41 -
            m41 * m21 * m32 * m43 - m14 * m22 * m33 * m41 - m14 * m23 * m31 * m42
        ) == 0) {
      return null;
    }

    return
       null;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Mat4x4 mat4x4 = (Mat4x4) o;

    if (!Helpers.compareDouble(mat4x4.m11, m11)) return false;
    if (!Helpers.compareDouble(mat4x4.m12, m12)) return false;
    if (!Helpers.compareDouble(mat4x4.m13, m13)) return false;
    if (!Helpers.compareDouble(mat4x4.m14, m14)) return false;
    if (!Helpers.compareDouble(mat4x4.m21, m21)) return false;
    if (!Helpers.compareDouble(mat4x4.m22, m22)) return false;
    if (!Helpers.compareDouble(mat4x4.m23, m23)) return false;
    if (!Helpers.compareDouble(mat4x4.m24, m24)) return false;
    if (!Helpers.compareDouble(mat4x4.m31, m31)) return false;
    if (!Helpers.compareDouble(mat4x4.m32, m32)) return false;
    if (!Helpers.compareDouble(mat4x4.m33, m33)) return false;
    if (!Helpers.compareDouble(mat4x4.m34, m34)) return false;
    if (!Helpers.compareDouble(mat4x4.m41, m41)) return false;
    if (!Helpers.compareDouble(mat4x4.m42, m42)) return false;
    if (!Helpers.compareDouble(mat4x4.m43, m43)) return false;
    return Helpers.compareDouble(mat4x4.m44, m44);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(m11);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m12);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m13);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m14);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m21);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m22);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m23);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m24);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m31);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m32);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m33);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m34);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m41);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m42);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m43);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m44);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Mat3x3{" +
        "m11=" + m11 +
        ", m12=" + m12 +
        ", m13=" + m13 +
        ", m14=" + m14 +
        ", m21=" + m21 +
        ", m22=" + m22 +
        ", m23=" + m23 +
        ", m24=" + m24 +
        ", m31=" + m31 +
        ", m32=" + m32 +
        ", m33=" + m33 +
        ", m34=" + m34 +
        ", m41=" + m41 +
        ", m42=" + m42 +
        ", m43=" + m43 +
        ", m44=" + m44 +
        '}';
  }
}
