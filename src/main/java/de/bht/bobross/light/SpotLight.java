package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a spotlight
 *
 * @author        Dennis RitteR
 * */

public class SpotLight extends Light {

   /** The position of this spotlight */
  final public Point3 position;

  /** The direction this spotlight points to */
  final public Vector3 direction;

  /** The half opening angle of this spotlight */
  final public double halfAngle;

  /**
   * The constructor
   * @param color           The color of this spotlight
   * @param position        The position of this spotlight
   * @param direction       The direction this spotlight points to
   * @param halfAngle       The half opening angle of this spotlight
   */
  public SpotLight(final Color color, final boolean castsShadows, final Point3 position, final Vector3 direction, final double halfAngle){
    super(color, castsShadows);
    this.position = position;
    this.direction = direction;
    this.halfAngle = halfAngle;
  }

  @Override
  public boolean illuminates(Point3 point) {
    double angle = Math.acos(point.sub(position).normalized().dot(direction));
    if (angle > halfAngle) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return position.sub(point).normalized();
  }
}
