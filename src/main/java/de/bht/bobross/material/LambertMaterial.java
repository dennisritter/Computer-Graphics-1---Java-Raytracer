package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.light.Light;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

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
  public Color colorFor( final Hit hit, final World world ) {
    Color c = color.mul( world.ambientLightColor );

    final Point3 p = hit.getPoint();
    final Color black = new Color(0, 0, 0);

    for ( Light light : world.lights ) {
      if(light.illuminates(p)) {
        final Vector3 l = light.directionFrom(p);
        final Color nc = color.mul(light.color).mul(Math.max(0, hit.normal.dot(l)));
        c = c.add(nc);
      }else{
        c = black;
      }
    }
    return c;
  }
}