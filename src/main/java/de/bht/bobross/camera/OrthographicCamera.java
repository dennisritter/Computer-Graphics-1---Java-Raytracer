package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 *
 *
 * @author      Nathalie Junker
 */
public class OrthographicCamera extends Camera {

  public final double s;

  public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s){
    super(e, g, t);
    this.s = s;
  }

  @Override
  public Ray rayFor(int w, int h, int x, int y) {
    final Vector3 d = this.w.mul(-1);
    final int a = w / h;

    double doub1 = (x-(w-1)/2)/(w-1);
    double doub2 = (y-(h-1)/2)/(h-1);
    double produkt1 = doub1*s*a;
    double produkt2 = doub2*s;

    Vector3 vec1 = u.mul(produkt1);
    Vector3 vec2 = v.mul(produkt2);

    final Point3 o = (e.add(vec1)).add(vec2);

    return new Ray(o, d);
  }
}
