package de.bht.bobross.geometry;

import de.bht.bobross.Ray;
import de.bht.bobross.material.Material;
import de.bht.bobross.math.Helpers;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

public class Disc extends Geometry {

  public final Point3 c;
  public final Normal3 n;
  public final double radius;

  public Disc ( final Point3 c, final Normal3 n, final double radius, final Material material ) {
    super( material );
    this.c = c;
    this.n = n;
    this.radius = radius;
  }

  @Override
  public Hit hit(Ray r) {
    if ( n.dot(r.d) == 0 ) {
      return null;
    }

    final double t = n.dot(c.sub(r.o))/n.dot(r.d);
    final Point3 i = r.at( t );
    final Vector3 v = i.sub( c );

    if ( v.magnitude > radius )
      return null;

    if ( t < Helpers.EPSILON )
      return null;

    return new Hit( t, r, this, this.n );
  }
}
