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

  /** Brechungsindex */
  public double indexOfRefraction;

  public TransparentMaterial(double indexOfRefraction){

    this.indexOfRefraction = indexOfRefraction;
  }

  @Override
  public Color colorFor(Hit hit, World world, Tracer tracer) {
    final Vector3 d = hit.ray.d;
    Normal3 normal = hit.normal;

    double eta = 1;                                       //Brechungsindex des Ausgangsmediums

    final double cosPhiOne = (d.mul(-1.0)).dot(normal);
    final double cosPhiTwo = Math.sqrt(1.0-Math.pow((eta/indexOfRefraction),2.0)*(1.0-Math.pow(cosPhiOne,2.0)));


    if (cosPhiOne < 0){

      /** Brechungsindex des Ausgangsmediums und Brechungsindex des Eintrittsmediums werden vertauscht*/
      eta = indexOfRefraction;
      indexOfRefraction = 1;

      /** Normale wird umgedreht*/
      normal = normal.mul(-1);

      final Vector3 r_d = d.add(normal.mul(cosPhiOne * 2.0));     //reflektierter Vektor d
      final Vector3 rt = d.mul(eta/indexOfRefraction).sub(normal.mul((cosPhiTwo - (eta / indexOfRefraction)*cosPhiOne)));

      final double rNull = Math.pow((eta-indexOfRefraction)/(eta+indexOfRefraction),2.0);
      final double R = rNull + (1.0-rNull)*Math.pow(1.0-cosPhiOne,5.0);
      final double T = 1.0 - R;

      final Point3 p = hit.getPoint();

      //  R * f[pr, rd] + T * [pr, rt]
      final Color c1 = tracer.traceRay(new Ray(p, r_d)).mul(R);
      final Color c2 = tracer.traceRay(new Ray(p, rt)).mul(T);
      final Color c3 = c1.add(c2);

      return c3.limitComponents();
    }

    final Vector3 r_d = d.add(normal.mul(cosPhiOne * 2.0));     //reflektierter Vektor d
    final Vector3 rt = d.mul(eta/indexOfRefraction).sub(normal.mul((cosPhiTwo - (eta / indexOfRefraction)*cosPhiOne)));

    final double rNull = Math.pow((eta-indexOfRefraction)/(eta+indexOfRefraction),2.0);
    final double R = rNull + (1.0-rNull)*Math.pow(1.0-cosPhiOne,5.0);
    final double T = 1.0 - R;

    final Point3 p = hit.getPoint();

    //  R * f[pr, rd] + T * [pr, rt]
    final Color c1 = tracer.traceRay(new Ray(p, r_d)).mul(R);
    final Color c2 = tracer.traceRay(new Ray(p, rt)).mul(T);
    final Color c3 = c1.add(c2);

    return c3.limitComponents();
  }

}
