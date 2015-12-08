package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.light.Light;
import de.bht.bobross.math.Point3;

/**
 * Represents a Material that always has the same static color regardless of the lighting in the scene
 *
 * @author      Jannik Portz
 */
public class SingleColorMaterial extends Material {

  /** The static color */
  public final Color color;

  /**
   * Constructs a new SingleColorMaterial with a static color
   *
   * @param     color     The static color
   */
  public SingleColorMaterial ( final Color color ) {
    this.color = color;
  }

  @Override
  public Color colorFor( final Hit hit, final World world ) {
    final Point3 p = hit.getPoint();
    Color c = color.mul(world.ambientLightColor);

    for ( Light light : world.lights ) {
      if (light.illuminates(p)) {
        c.add(light.color);
      }
    }
    return c.limitComponents();
  }
}