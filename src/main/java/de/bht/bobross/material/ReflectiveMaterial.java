package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.light.Light;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import de.bht.bobross.raytracer.Tracer;

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

  /** The material's exponent */
  public final int exponent;

  /** The material's reflection color */
  public final Color reflection;

  /**
   * Constructs a new ReflectiveMaterial with all attributes
   * @param diffuse    The material's diffuse color.
   * @param specular   The material's specular color.
   * @param exponent   The material's exponent.
   * @param reflection The material's reflection.
   */
  public ReflectiveMaterial(Color diffuse, Color specular, int exponent, Color reflection){
    this.diffuse = diffuse;
    this.specular = specular;
    this.exponent = exponent;
    this.reflection = reflection;
  }

  @Override
  public Color colorFor(Hit hit, World world, Tracer tracer) {

    Tracer tracer1 = new Tracer(world);

    Color c = diffuse.mul( world.ambientLightColor );
    final Point3 p = hit.getPoint();

    for (Light light : world.lights){
      final Vector3 l = light.directionFrom(p).normalized();
      final Vector3 r = l.reflectedOn(hit.normal);
      final Vector3 e = hit.ray.d.mul(-1);

      if(light.illuminates(p, world)) {
        final Color cTemp = diffuse.mul(light.color).mul(Math.max(0, (hit.normal.dot(l))))
            .add(specular.mul(light.color).mul(Math.pow(Math.max(0, e.dot(r)), exponent)));
        c = c.add(cTemp);
      }

    }
    //final double cosPhiOne = (hit.ray.d.mul(-1.0)).dot(hit.normal);
    final Vector3 r_d = hit.ray.d.mul( -1 ).reflectedOn( hit.normal );
    final Color cReflection = reflection.mul(tracer.traceRay(new Ray(p, r_d)));
    c = c.add(cReflection);

    return c.limitComponents();
  }



}
