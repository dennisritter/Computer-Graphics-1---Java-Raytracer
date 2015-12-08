package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 *@author      Dennis Ritter
 * Represents a Light that illuminates the whole space around it
 */
public class PointLight extends Light {
  /**
   * The position of this PointLight;
   */
  final public Point3 position;

  /**
   * The constructor
   * @param color         the color of the light;
   * @param position      the position of this PointLight;
   * @param castsShadows  Whether the light casts shadows
   */
  public PointLight(final Color color, final boolean castsShadows, final Point3 position){
    super(color, castsShadows);
    this.position = position;
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
    if( hitGeo.t > 0.0 && hitGeo.t <= ray.tOf( this.position ) ){
      return false;
    }
    return true;
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return position.sub(point).normalized();
  }
}
