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

  //TODO: Fix black sphere center when using ambient light
  @Override
  public Color colorFor( final Hit hit, final World world ) {

    Color c = color.mul( world.ambientLightColor );
    final Point3 p = hit.getPoint();

    for ( Light light : world.lights ) {
      final Vector3 l = light.directionFrom(p);

      if(light.illuminates(p)) {
        //Sobald zu c ambientes Licht hinzumultipliziert wird die Mitte der Sphere dunkel.
        //(Zum Zentrum hin wird es aber noch heller)
        //Lässt man das ambiente Licht weg ist alles wie es sein soll.
        final Color cTemp = color.mul(light.color).mul(Math.max(0, hit.normal.dot(l)));
        c = c.add(cTemp);
      }else {
        final Color cTemp = color.mul(world.ambientLightColor).mul(Math.max(0, hit.normal.dot(l)));
        c = c.add(cTemp);
      }
    }
    return limitColorComponentsTo1(c);
  }

}