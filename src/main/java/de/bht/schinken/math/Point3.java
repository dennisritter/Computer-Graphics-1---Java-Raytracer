package de.bht.schinken.math;

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
   * @return        The vector which represents the distance between the two points
   */
  public Vector3 sub ( final Point3 p ) {
    return new Vector3( x - p.x, y - p.y, z - p.z );
  }

  /**
   * TODO: better documentation
   *
   * Subtracts the provided vector v from this point and returns the point
   * @param     v   The vector
   * @return        The point
   */
  public Point3 sub ( final Vector3 v ) {
    return new Point3( x - v.x, y - v.y, z - v.z );
  }

  /**
   * TODO: add documentation
   */
  public Point3 add ( final Vector3 v ) {
    return new Point3( x + v.x, y + v.y, z + v.z );
  }

}
