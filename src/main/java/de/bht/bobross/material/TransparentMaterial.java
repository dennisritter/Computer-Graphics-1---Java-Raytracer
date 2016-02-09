package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.geometry.Sphere;
import de.bht.bobross.math.Helpers;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import de.bht.bobross.raytracer.Tracer;

/**
 * This class creates a transparent material for a geometry.
 *
 * @author      Nathalie Junker
 */
public class TransparentMaterial extends Material {

  /** The refractive index of the medium the light is entering */
  public double indexOfRefraction;

  /**
   * The constructor
   * @param indexOfRefraction The refractive index of the medium the light is entering
   */
  public TransparentMaterial(double indexOfRefraction){
    this.indexOfRefraction = indexOfRefraction;
  }

  @Override
  public Color colorFor(Hit hit, World world, Tracer tracer){

    final Point3 p = hit.getPoint();
    final Vector3 d = hit.ray.d;

    double eta = 1.0 / indexOfRefraction;
    Normal3 normal = hit.normal;

    final double cosPhiOne = normal.dot( d.mul(-1) );
    if (cosPhiOne < 0) {
      eta = indexOfRefraction/1.0;
      normal = normal.mul(-1);
    }

    final double cosPhiTwo =  Math.sqrt( 1 - ( Math.pow(eta,2) ) * ( 1-Math.pow(cosPhiOne, 2) ) );

    final Vector3 r_d = d.add( normal.mul(cosPhiOne * 2) );
    final Vector3 t = ( d.mul(eta) ).sub( normal.mul(cosPhiTwo - eta * cosPhiOne) );

    double r0 = Math.pow( (1 - indexOfRefraction)/(1 + indexOfRefraction), 2 );
    double rr = r0 + ( 1-r0 ) * Math.pow(1-cosPhiOne, 5);
    double tt = 1 - rr;

    //  R * f[pr, rd] + T * [pr, rt]
    final Color c1 = tracer.traceRay(new Ray(p, r_d)).mul(rr);
    final Color c2 = tracer.traceRay(new Ray(p, t)).mul(tt);

    return ( c1.add(c2) ).limitComponents();

  }


}
