package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;

/**
 * Abstraction of a material covering a geometry
 *
 * @author      Jannik Portz
 */
abstract public class Material {

  /**
   * Determines the color of the geometry on a specific hit.
   * Also takes the world's lights in consideration.
   *
   * @param     hit       The hit
   * @param     world     The world containing all lights
   * @return              The color of the geometry on the specified hit
   */
  abstract public Color colorFor ( final Hit hit, final World world );

  /**
   * Limits the colors components to a maximum of 1.0
   * @param     c         The color
   * @return    c         The Color with it´s components limited to 1.0
   */
  private Color limitColorComponentsTo1(final Color c){
    return new Color(Math.min(c.r, 1.0), Math.min(c.g, 1.0), Math.min(c.b, 1.0));
  }
}