package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;

/**
 * @author      Nathalie Junker
 */
public class Plane extends Geometry {

  public final Point3 a;
  public final Normal3 n;


  public Plane(final Point3 a, final Normal3 n, final Color color){
    super(color);
    this.a = a;
    this.n = n;
  }

  @Override
  public Hit hit(Ray r) {

    double t = n.dot(a.sub(r.o))/n.dot(r.d);
    if(t == 0){
      return null;
    }

    return new Hit(t, r, this);
  }
}
