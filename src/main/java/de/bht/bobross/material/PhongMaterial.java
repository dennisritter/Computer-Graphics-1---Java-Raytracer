package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.light.Light;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a PhongMaterial for a geometry
 *
 * @author      Jannik Portz
 */
public class PhongMaterial extends Material {

  /** The material's diffuse color */
  public final Color diffuse;

  /** The material's specular color */
  public final Color specular;

  /** The material's Phong exponent */
  public final int exponent;

  /**
   * Constructs a new PhongMaterial with all attributes
   *
   * @param     diffuse   The material's diffuse color
   * @param     specular  The material's specular color
   * @param     exponent  The material's Phong exponent
   */
  public PhongMaterial ( final Color diffuse, final Color specular, final int exponent ) {
    this.diffuse = diffuse;
    this.specular = specular;
    this.exponent = exponent;
  }

  @Override
  public Color colorFor( final Hit hit, final World world ) {
    Color c = diffuse.mul( world.ambientLightColor );
    final Point3 p = hit.getPoint();
    for ( Light light : world.lights ) {
      final Vector3 l = light.directionFrom(p);
      final Vector3 r = l.reflectedOn(hit.normal);
      final Vector3 e = hit.ray.d.mul(-1);



      if(light.illuminates(p)) {
        final Color cTemp = diffuse.mul(light.color).mul(Math.max(0, (hit.normal.dot(l))))
            .add(specular.mul(light.color).mul(Math.pow(Math.max(0, e.dot(r)), exponent)));
        c = c.add(cTemp);
      }else {
        final Color cTemp = diffuse.mul(world.ambientLightColor).mul(Math.max(0, hit.normal.dot(l)))
            .add(specular.mul(light.color).mul(Math.pow(Math.max(0, e.dot(r)), exponent)));
        c = c.add(cTemp);
      }
    }
    return limitColorComponentsTo1(c);
  }

}
