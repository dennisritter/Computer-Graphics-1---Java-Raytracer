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
    //wenn vec l ein Objekt, vor der Lichtquelle schneidet wirft das objekt einen Schatten auf den zu prüfenden Punkt
    Vector3 l = directionFrom(point);
    Ray rayGeo = new Ray( point, l );
    Ray rayLight = new Ray ( position, point.sub(position).normalized() );
    Hit hitGeo = world.hit( rayGeo );
    Hit hitLight = world.hit( rayLight );
    if( hitGeo.t <= hitLight.t ){
      System.out.println("shadow");
      return false;
    }else {
      System.out.println("light");
      return true;
    }
  }

  @Override
  public Vector3 directionFrom(Point3 point) {
    return position.sub(point).normalized();
  }
}
