package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
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
   * @param castsShadows    Whether the light casts shadows
   */
  public DirectionalLight(final Color color, final boolean castsShadows, final Vector3 direction){
    super(color, castsShadows);
    this.direction = direction;
  }

  @Override
  public boolean illuminates(Point3 point, World world) {
    if ( !castsShadows ){
      return true;
    }
    Vector3 l = directionFrom(point);
    Ray ray = new Ray( point, l );
    Hit hitGeo = world.hit( ray );

    if ( hitGeo == null ){
      return true;
    }
    return false;
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return direction.mul(-1).normalized();
  }
}
