package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

public class SpotLight extends Light {

  final public Point3 position;
  final public Vector3 direction;
  final public double halfAngle;

  public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle){
    super(color);
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
