package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 *@author      Dennis Ritter
 * Represents a Light that illuminates the whole space around it
 */
public class PointLight extends Light {
  final public Point3 position;

  public PointLight(final Color color, final Point3 position){
    super(color);
    this.position = position;
  }

  @Override
  public boolean illuminates(Point3 point) {
    return true;
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return position.sub(point).normalized();
  }
}
