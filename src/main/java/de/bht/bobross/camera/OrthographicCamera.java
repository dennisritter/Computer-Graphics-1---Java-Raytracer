package de.bht.bobross.camera;

import de.bht.bobross.Ray;
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
   * @param     e   The camera's eye-position
   * @param     g   The camera's gaze direction
   * @param     t   The camera's up-vector
   * @param     s   The camera's scaling factor
   */
  public OrthographicCamera (final Point3 e, final Vector3 g, final Vector3 t, final double s) {
    super(e, g, t);
    this.s = s;
  }

  @Override
  public Ray rayFor (int w, int h, int x, int y) {
    final Vector3 d = this.w.mul(-1);
    final int a = w / h;

    double d1 = (x-(w-1)/2)/(w-1);
    double d2 = (y-(h-1)/2)/(h-1);
    double p1 = d1*s*a;
    double p2 = d2*s;

    Vector3 vec1 = u.mul(p1);
    Vector3 vec2 = v.mul(p2);

    final Point3 o = (e.add(vec1)).add(vec2);

    return new Ray(o, d);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    OrthographicCamera that = (OrthographicCamera) o;

    return Double.compare(that.s, s) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
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
