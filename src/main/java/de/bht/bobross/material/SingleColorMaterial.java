package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;

/**
 * Represents a Material that always has the same color
 *
 * @author      Jannik Portz
 */
public class SingleColorMaterial extends Material {
  
  @Override
  public Color colorFor( final Hit hit, final World world) {
    return hit.geo.color;
  }
}