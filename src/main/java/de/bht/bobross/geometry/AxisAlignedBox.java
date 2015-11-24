package de.bht.bobross.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;

import java.util.Arrays;

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
  public AxisAlignedBox ( final Point3 lbf, final Point3 run, final Color color ) {
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
    Plane mp = null;
    for ( Plane p : planes ) {
      if ( r.d.dot( p.n ) < 0 )
        continue;

      final Hit hit = p.hit( r );

      if ( hit == null || hit.t <= 0 )
        continue;

      if ( minT <= 0 || minT > 0 && hit.t < minT ) {
        minT = hit.t;
        mp = p;
      }
    }

    if ( minT < 0 )
      return null;

    final Point3 p = r.at( minT );

    if ( mp.n.x == 0 && lbf.x > p.x || p.x > run.x )
      return null;

    if ( mp.n.y == 0 && lbf.y > p.y || p.y > run.y )
      return null;

    if ( mp.n.z == 0 && lbf.z > p.z || p.z > run.z )
      return null;

    return new Hit ( minT, r, this, mp.n );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    AxisAlignedBox that = (AxisAlignedBox) o;

    if (lbf != null ? !lbf.equals(that.lbf) : that.lbf != null) return false;
    if (run != null ? !run.equals(that.run) : that.run != null) return false;
    // Probably incorrect - comparing Object[] arrays with Arrays.equals
    return Arrays.equals(planes, that.planes);

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (lbf != null ? lbf.hashCode() : 0);
    result = 31 * result + (run != null ? run.hashCode() : 0);
    result = 31 * result + (planes != null ? Arrays.hashCode(planes) : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AxisAlignedBox{" +
        "lbf=" + lbf +
        ", run=" + run +
        ", planes=" + Arrays.toString(planes) +
        '}';
  }
}

