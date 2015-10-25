package de.bht.schinken.math;

/**
 * Represents a 3 element vector
 *
 * @author      Jannik Portz
 */
public class Vector3 {

  /** The first vector component */
  public final double x;

  /** The second vector component */
  public final double y;

  /** The third vector component */
  public final double z;

  /** The vector's magnitude */
  public final double magnitude;

  /**
   * Constructs the vector with the first, second and third component as x, y, z
   *
   * @param     x   The first vector component
   * @param     y   The second vector component
   * @param     z   The third vector component
   */
  public Vector3 ( final double x, final double y, final double z ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.magnitude = Math.sqrt( x * x + y * y + z * z );
  }

  /**
   * Creates a new vector which represents the sum of this vector and the provided vector v
   *
   * @param     v   The vector to add to this vector
   * @return        The new vector which represents the sum of the two vectors
   */
  public Vector3 add ( final Vector3 v ) {
    return new Vector3( x + v.x, y + v.y, z + v.z );
  }

  /**
   * Creates a new vector which represents the product of this vector and the provided vector v
   *
   * @param     v   The vector to multiply with this vector
   * @return        The new vector which represents the product of the two vectors
   */
  public Vector3 mul ( final Vector3 v ) {
    return new Vector3( x * v.x, y * v.y, z * v.z );
  }

  /**
   * Calculates the dot product of this vector and the provided vector v
   *
   * @param     v   The other vector to use for the dot product
   * @return        The dot product of this vector and the provided vector v
   */
  public double dot ( final Vector3 v ) {
    return Math.sqrt( x * v.x + y * v.y + z * v.z );
  }

  /**
   * Creates a new vector that is the normalized version of this vector.
   * It will have the same direction but a magnitude of exactly 1.0
   *
   * @return        The normalized unit vector
   */
  public Vector3 normalized () {
    if ( magnitude == 1.0 )
      return this;

    return new Vector3( x / magnitude, y / magnitude, z / magnitude );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Vector3 vector3 = (Vector3) o;

    if (Double.compare(vector3.x, x) != 0) return false;
    if (Double.compare(vector3.y, y) != 0) return false;
    return Double.compare(vector3.z, z) == 0;
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
    return "Vector3{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        ", magnitude=" + magnitude +
        '}';
  }
}