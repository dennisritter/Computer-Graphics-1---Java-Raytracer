package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point2;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents an orthographic camera
 *
 * @author      Nathalie Junker
 */
public class OrthographicCamera extends Camera {

  /** The camera's scaling factor */
  public final double s;

  /**
   * Constructs a new orthographic camera with all attributes
   *
   * @param     e         The camera's eye-position
   * @param     g         The camera's gaze direction
   * @param     t         The camera's up-vector
   * @param     s         The camera's scaling factor
   * @param     pattern   The sampling pattern to use
   */
  public OrthographicCamera ( final Point3 e, final Vector3 g, final Vector3 t, final double s, final SamplingPattern pattern ) {
    super(e, g, t, pattern);
    this.s = s;
  }

  @Override
  public Ray rayFor ( final int w, final int h, final int x, final int y, Point2 samplingPoint ) {
    final Vector3 d = this.w.mul(-1);

    final double dw = w*1.0;
    final double dh = h*1.0;
    final double dx = x*1.0 + samplingPoint.x;
    final double dy = y*1.0 + samplingPoint.y;
    final double a = dw / dh;

    final double f1 = (dx-((dw-1.0)/2.0))/(dw-1.0);
    final double f2 = (dy-((dh-1.0)/2.0))/(dh-1.0);

    final double p1 = f1*s*a;
    final double p2 = f2*s;

    final Vector3 vec1 = u.mul(p1);
    final Vector3 vec2 = v.mul(p2);

    final Point3 o = (e.add(vec1)).add(vec2);

    return new Ray(o, d);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    OrthographicCamera that = (OrthographicCamera) o;

    return Double.compare(that.s, s) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    final long temp;
    temp = Double.doubleToLongBits(s);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "PerspectiveCamera{" +
        "e=" + e +
        "g=" + g +
        "t=" + t +
        "u=" + u +
        "v=" + v +
        "w=" + w +
        "s=" + s +
        "} ";
  }
}
