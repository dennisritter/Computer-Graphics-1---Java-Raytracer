package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;

abstract public class Material {

  abstract public Color colorFor ( final Hit hit, final World world );

}