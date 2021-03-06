package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.light.Light;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import de.bht.bobross.raytracer.Tracer;

/**
 * Represents a Lambert Material for a geometry
 *
 * @author      Jannik Portz
 */
public class LambertMaterial extends Material {

  /** The material's color */
  public final Color color;

  /**
   * Constructs a new LambertMaterial with its color
   *
   * @param     color     The material's color
   */
  public LambertMaterial ( final Color color ) {
    this.color = color;
  }
  
  @Override
  public Color colorFor ( final Hit hit, final World world, final Tracer tracer ) {

    Color c = color.mul( world.ambientLightColor );
    final Point3 p = hit.getPoint();

    for ( Light light : world.lights ) {
      final Vector3 l = light.directionFrom(p);

      if(light.illuminates(p, world)) {
        final Color cTemp = color.mul(light.color).mul(Math.max(0, hit.normal.dot(l)));
        c = c.add(cTemp);
      }
    }
    return c.limitComponents();
  }

}