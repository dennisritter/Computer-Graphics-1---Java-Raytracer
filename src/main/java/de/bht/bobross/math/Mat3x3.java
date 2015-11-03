package de.bht.bobross.math;

import de.bht.bobross.exception.ParameterNullException;

/**
 * Represents a 3x3 Matrix and offers multiplication operations and methods to exchange column separately.
 * This class is immutable so all methods return new Mat3x3 instances and you won't be able to change any attributes after construction.
 *
 * @author      Jannik Portz
 */
public class Mat3x3 {

  /** The value for the matrix' (1,1) entry */
  public final double m11;

  /** The value for the matrix' (1,2) entry */
  public final double m12;

  /** The value for the matrix' (1,3) entry */
  public final double m13;

  /** The value for the matrix' (2,1) entry */
  public final double m21;

  /** The value for the matrix' (2,2) entry */
  public final double m22;

  /** The value for the matrix' (2,3) entry */
  public final double m23;

  /** The value for the matrix' (3,1) entry */
  public final double m31;

  /** The value for the matrix' (3,2) entry */
  public final double m32;

  /** The value for the matrix' (3,3) entry */
  public final double m33;

  /** The matrix' determinant value */
  public final double determinant;

  /**
   * Constructs a new 3x3 matrix with all entries from (1,1) to (3,3)
   *
   * @param     m11       The value for the matrix' (1,1) entry
   * @param     m12       The value for the matrix' (1,2) entry
   * @param     m13       The value for the matrix' (1,3) entry
   * @param     m21       The value for the matrix' (2,1) entry
   * @param     m22       The value for the matrix' (2,2) entry
   * @param     m23       The value for the matrix' (2,3) entry
   * @param     m31       The value for the matrix' (3,1) entry
   * @param     m32       The value for the matrix' (3,2) entry
   * @param     m33       The value for the matrix' (3,3) entry
   */
  public Mat3x3 ( final double m11, final double m12, final double m13,
                  final double m21, final double m22, final double m23,
                  final double m31, final double m32, final double m33 ) {
    this.m11 = m11;
    this.m12 = m12;
    this.m13 = m13;
    this.m21 = m21;
    this.m22 = m22;
    this.m23 = m23;
    this.m31 = m31;
    this.m32 = m32;
    this.m33 = m33;
    this.determinant = calculateDeterminant();
  }

  /**
   * Returns the calculated value of the matrix' determinant
   *
   * @return              The determinant value of the matrix
   */
  protected double calculateDeterminant () {
    return m11 * m22 * m33
        + m12 * m23 * m31
        + m13 * m21 * m32
        - m31 * m22 * m13
        - m32 * m23 * m11
        - m33 * m21 * m12;
  }

  /**
   * Returns the product of this matrix and the provided matrix m
   *
   * @param     m         The matrix to multiply this matrix with
   *
   * @return    Mat3x3    The product of this matrix and the provided matrix m
   */
  public Mat3x3 mul ( final Mat3x3 m ) {
    if ( m == null ) {
      throw new ParameterNullException("m");
    }
    return new Mat3x3 (
        m11 * m.m11 + m12 * m.m21 + m13 * m.m31,
        m11 * m.m12 + m12 * m.m22 + m13 * m.m32,
        m11 * m.m13 + m12 * m.m23 + m13 * m.m33,
        m21 * m.m11 + m22 * m.m21 + m23 * m.m31,
        m21 * m.m12 + m22 * m.m22 + m23 * m.m32,
        m21 * m.m13 + m22 * m.m23 + m23 * m.m33,
        m31 * m.m11 + m32 * m.m21 + m33 * m.m31,
        m31 * m.m12 + m32 * m.m22 + m33 * m.m32,
        m31 * m.m13 + m32 * m.m23 + m33 * m.m33
        );
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
        m11 * v.x + m12 * v.y + m13 * v.z,
        m21 * v.x + m22 * v.y + m23 * v.z,
        m31 * v.x + m32 * v.y + m33 * v.z
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
        m11 * p.x + m12 * p.y + m13 * p.z,
        m21 * p.x + m22 * p.y + m23 * p.z,
        m31 * p.x + m32 * p.y + m33 * p.z
    );
  }

  /**
   * Constructs a new matrix with the values of this matrix except for the first column,
   * which is replaced with the values of the provided vector
   *
   * @param     v   The vector to insert into the first column
   * @return        The new matrix
   */
  public Mat3x3 changeCol1 ( final Vector3 v ) {
    if ( v == null ) {
      throw new ParameterNullException("v");
    }
    return new Mat3x3(
        v.x, m12, m13,
        v.y, m22, m23,
        v.z, m32, m33
    );
  }

  /**
   * Constructs a new matrix with the values of this matrix except for the second column,
   * which is replaced with the values of the provided vector
   *
   * @param     v   The vector to insert into the first column
   * @return        The new matrix
   */
  public Mat3x3 changeCol2 ( final Vector3 v ) {
    if ( v == null ) {
      throw new ParameterNullException("v");
    }
    return new Mat3x3(
        m11, v.x, m13,
        m21, v.y, m23,
        m31, v.z, m33
    );
  }

  /**
   * Constructs a new matrix with the values of this matrix except for the second column,
   * which is replaced with the values of the provided vector
   *
   * @param     v   The vector to insert into the first column
   * @return        The new matrix
   */
  public Mat3x3 changeCol3 ( final Vector3 v ) {
    if ( v == null ) {
      throw new ParameterNullException("v");
    }
    return new Mat3x3(
        m11, m12, v.x,
        m21, m22, v.y,
        m31, m32, v.z
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Mat3x3 mat3x3 = (Mat3x3) o;

    if (Double.compare(mat3x3.m11, m11) != 0) return false;
    if (Double.compare(mat3x3.m12, m12) != 0) return false;
    if (Double.compare(mat3x3.m13, m13) != 0) return false;
    if (Double.compare(mat3x3.m21, m21) != 0) return false;
    if (Double.compare(mat3x3.m22, m22) != 0) return false;
    if (Double.compare(mat3x3.m23, m23) != 0) return false;
    if (Double.compare(mat3x3.m31, m31) != 0) return false;
    if (Double.compare(mat3x3.m32, m32) != 0) return false;
    return Double.compare(mat3x3.m33, m33) == 0;

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
    temp = Double.doubleToLongBits(m21);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m22);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m23);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m31);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m32);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(m33);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Mat3x3{" +
        "m11=" + m11 +
        ", m12=" + m12 +
        ", m13=" + m13 +
        ", m21=" + m21 +
        ", m22=" + m22 +
        ", m23=" + m23 +
        ", m31=" + m31 +
        ", m32=" + m32 +
        ", m33=" + m33 +
        ", determinant=" + determinant +
        '}';
  }
}