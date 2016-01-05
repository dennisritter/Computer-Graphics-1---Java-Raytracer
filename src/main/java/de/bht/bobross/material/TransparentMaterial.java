package de.bht.bobross.material;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.geometry.Hit;
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
  public final double indexOfRefraction;

  public TransparentMaterial(double indexOfRefraction){

    this.indexOfRefraction = indexOfRefraction;
  }

  @Override
  public Color colorFor(Hit hit, World world, Tracer tracer) {
    final Vector3 d = hit.ray.d;
    final Normal3 normal = hit.normal;
    final Vector3 rd = d.reflectedOn(normal);                   //reflektierter Vektor d (unsere Implementierung)

    final double eta = 1;                                       //Brechungsindex des Ausgangsmediums

    final double cosPhiOne = (d.mul(-1.0)).dot(normal);
    final double cosPhiTwo = Math.sqrt(1.0-Math.pow((eta/indexOfRefraction),2.0)*(1.0-Math.pow(cosPhiOne,2.0)));

    final Vector3 r_d = d.add(normal.mul(cosPhiOne * 2.0));     //reflektierter Vektor d (Stephans Folie mit cosPhiOne)
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
/*
  public Color colorFor(Hit hit, World world, Tracer tracer) {
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
    final double cosPhiOne = (hit.ray.d.mul(-1.0)).dot(hit.normal);
    final Vector3 r_d = hit.ray.d.mul( -1 ).reflectedOn( hit.normal );
    final Color cReflection = reflection.mul(tracer.traceRay(new Ray(p, r_d)));
    c = c.add(cReflection);

    return c.limitComponents();
  }
  */
}
