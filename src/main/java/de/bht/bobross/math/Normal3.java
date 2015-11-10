package de.bht.bobross.math;

import de.bht.bobross.exception.ParameterNullException;

/**
 * Represents a normal in a three-dimensional room with x, y and z coordinates.
 *
 * @author      Jannik Portz
 */
public class Normal3 {

  /** The first normal component */
  public final double x;

  /** The second normal component */
  public final double y;

  /** The third normal component */
  public final double z;

  /**
   * Constructs a new normal with its x, y and z coordinates
   *
   * @param     x   The x coordinate
   * @param     y   The y coordinate
   * @param     z   The z coordinate
   */
  public Normal3 ( final double x, final double y, final double z ) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Multiplies this normal with a scalar and returns the result as a new normal
   *
   * @param     n   The scalar value to multiply this normal with
   * @return        The normal representing the product of this vector and the scalar value n
   */
  public Normal3 mul ( final double n ) {
    return new Normal3( x * n, y * n, z * n );
  }

  /**
   * Adds the provided normal n to this normal and returns the result as a new normal
   *
   * @param     n   The normal to add to this normal
   * @return        The product of this normal and the provided normal n
   */
  public Normal3 add ( final Normal3 n ) {
    if ( n == null ) {
      throw new ParameterNullException("n");
    }
    return new Normal3( x + n.x, y + n.y, z + n.z );
  }

  /**
   * Calculates the dot product of this normal and the provided vector v
   *
   * @param     v   The vector to calculate the dot product with
   * @return        The dot product of this normal and the vector v
   */
  public double dot ( final Vector3 v ) {
    if ( v == null ) {
      throw new ParameterNullException("v");
    }
    return x * v.x + y * v.y + z * v.z;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Normal3 normal3 = (Normal3) o;

    if (!Helpers.compareDouble(normal3.x, x)) return false;
    if (!Helpers.compareDouble(normal3.y, y)) return false;
    return Helpers.compareDouble(normal3.z, z);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(z);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Normal3{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }
}