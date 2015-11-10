package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 *
 * @author      Nathalie Junker
 */
public class PerspectiveCamera extends Camera {

  public final double angle;

  public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle){
    super(e, g, t);
    this.angle = angle;
  }

  @Override
  public Ray rayFor(int w, int h, int x, int y) {
    final Point3 o = this.e;
    final double tan = Math.tan(angle);

    double doub1 = x-((w-1)/2);
    double doub2 = y-((h-1)/2);
    Vector3 vec1 = u.mul(doub1);
    Vector3 vec2 = v.mul(doub2);
    Vector3 vec3 = this.w.mul((-1)*(h/2)/tan);

    final Vector3 d = vec1.add(vec2).add(vec3);
    return new Ray(o, d);
  }
}
