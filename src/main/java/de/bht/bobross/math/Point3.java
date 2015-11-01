package de.bht.bobross.math;

/**
 * Represents a point in a three-dimensional room with its x, y and z coordinates
 *
 * @author      Jannik Portz
 */
public class Point3 {

  /** The point's x coordinate */
  public final double x;

  /** The point's y coordinate */
  public final double y;

  /** The point's z coordinate */
  public final double z;

  /**
   * Constructs a new point with all coordinate values
   *
   * @param     x   The point's x coordinate
   * @param     y   The point's y coordinate
   * @param     z   The point's z coordinate
   */
  public Point3 ( final double x, final double y, final double z ) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Returns the vector between this point and the provided point p
   *
   * @param     p   The point to subtract from this point
   * @return        The vector which represents the distance between this point and the provided point p
   */
  public Vector3 sub ( final Point3 p ) {
    return new Vector3( x - p.x, y - p.y, z - p.z );
  }

  /**
   * Subtracts the provided vector v from this point and returns a new point.
   *
   * @param     v   The vector to subtract from this point.
   * @return        The point representing the difference between this point and the provided vector v
   */
  public Point3 sub ( final Vector3 v ) {
    return new Point3( x - v.x, y - v.y, z - v.z );
  }

  /**
   * Adds the provided vector v to this point and returns a new point
   *
   * @param     v   The vector to add to this point
   * @return        A new point that represents the sum of this point and the provided vecotr v
   */
  public Point3 add ( final Vector3 v ) {
    return new Point3( x + v.x, y + v.y, z + v.z );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Point3 point3 = (Point3) o;

    if (Double.compare(point3.x, x) != 0) return false;
    if (Double.compare(point3.y, y) != 0) return false;
    return Double.compare(point3.z, z) == 0;

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
    return "Point3{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }
}
