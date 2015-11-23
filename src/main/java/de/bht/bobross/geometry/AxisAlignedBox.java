package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;

/**
 * Represents an axis-aligned-box geometry
 *
 * @author      Jannik Portz
 */
public class AxisAlignedBox extends Geometry {

  /** The low-bottom-far corner of the box */
  public final Point3 lbf;

  /** The right-upper-near corner of the box */
  public final Point3 run;

  /** Each plane represents a side of the box */
  protected final Plane[] planes;

  /**
   * Constructs a new axis-aligned-box
   * @param     lbf     The low-bottom-far corner of the box
   * @param     run     The right-upper-near corner of the box
   * @param     color   The box' color
   */
  public AxisAlignedBox ( Point3 lbf, Point3 run, Color color ) {
    super( color );
    this.lbf = lbf;
    this.run = run;

    final Color c = new Color( 0, 0, 0 );

    this.planes = new Plane[] {
        new Plane( run, new Normal3( 0, 1, 0 ), c ),
        new Plane( run, new Normal3( 1, 0, 0 ), c ),
        new Plane( lbf, new Normal3( 0, -1, 0 ), c ),
        new Plane( lbf, new Normal3( -1, 0, 0 ), c ),
        new Plane( run, new Normal3( 0, 0, 1 ), c ),
        new Plane( lbf, new Normal3( 0, 0, -1 ), c ),
    };
  }

  @Override
  public Hit hit(Ray r) {
    double minT = -1;
    for ( Plane p : planes ) {
      if ( r.d.dot( p.n ) >= 0 ) {
        final Hit hit = p.hit( r );
        if ( hit != null && hit.t > 0 ) {
          if ( minT > 0 ) {
            minT = Math.min( minT, hit.t );
          } else {
            minT = hit.t;
          }
        }
      }
    }

    if ( minT < 0 )
      return null;

    final Point3 p = r.at( minT );

    if ( lbf.x > p.x || p.x > run.x )
      return null;

    if ( lbf.y > p.y || p.y > run.y )
      return null;

    if ( lbf.z > p.z || p.z > run.z )
      return null;

    return new Hit ( minT, r, this );
  }
}
