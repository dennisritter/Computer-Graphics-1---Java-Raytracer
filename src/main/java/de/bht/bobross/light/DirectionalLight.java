package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents the directional light
 *
 * @author      Nathalie Junker
 */
public class DirectionalLight extends Light {

  Vector3 direction;

  public DirectionalLight(Color color, Vector3 direction){
    super(color);
    this.direction = direction.mul(-1);
  }

  @Override
  public boolean illuminates(Point3 point) {
    return true;
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return direction;
  }
}
