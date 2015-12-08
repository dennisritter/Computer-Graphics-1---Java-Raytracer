package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;

/**
 * Represents the reflective material.
 *
 * @author      Nathalie Junker
 */
public class ReflectiveMaterial extends Material{

  /** The material's diffuse color */
  public final Color diffuse;

  /** The material's specular color */
  public final Color specular;

  /** The material's Phong exponent */
  public final int exponent;

  /** The material's reflection color */
  public final Color reflection;

  public ReflectiveMaterial(Color diffuse, Color specular, int exponent, Color reflection){
    this.diffuse = diffuse;
    this.specular = specular;
    this.exponent = exponent;
    this.reflection = reflection;
  }

  @Override
  public Color colorFor(Hit hit, World world) {
    return null;
  }
}
