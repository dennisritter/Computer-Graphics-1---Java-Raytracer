package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a perspective camera
 *
 * @author      Nathalie Junker
 */
public class PerspectiveCamera extends Camera {

  /** The camera's angular aperture */
  public final double angle;

  /**
   * Constructs a new perspective camera with all attributes
   *
   * @param     e     The camera's eye-position
   * @param     g     The camera's gaze direction
   * @param     t     The camera's up-vector
   * @param     angle The camera's angular aperture
   */
  public PerspectiveCamera (Point3 e, Vector3 g, Vector3 t, double angle) {
    super(e, g, t);
    this.angle = angle;
  }

  @Override
  public Ray rayFor(int w, int h, int x, int y) {
    final Point3 o = this.e;
    final double tan = Math.tan(angle);

    double d1 = x-((w-1)/2);
    double d2 = y-((h-1)/2);
    Vector3 vec1 = u.mul(d1);
    Vector3 vec2 = v.mul(d2);
    Vector3 vec3 = this.w.mul((-1)*(h/2)/tan);

    final Vector3 d = vec1.add(vec2).add(vec3);
    return new Ray(o, d);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    PerspectiveCamera that = (PerspectiveCamera) o;

    return Double.compare(that.angle, angle) == 0;

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(angle);
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
        "angle=" + angle +
        "} ";
  }
}
