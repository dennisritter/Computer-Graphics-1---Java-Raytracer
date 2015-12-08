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

  /** Represents the light's main direction */
  final Vector3 direction;

  /**
   * The constructor
   * @param color           Represents the color of this spotlight
   * @param direction       Represents the light's main direction
   */
  public DirectionalLight(final Color color, final boolean castsShadows, final Vector3 direction){
    super(color, castsShadows);
    this.direction = direction;
  }

  @Override
  public boolean illuminates(Point3 point) {
    return true;
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return direction.mul(-1).normalized();
  }
}
