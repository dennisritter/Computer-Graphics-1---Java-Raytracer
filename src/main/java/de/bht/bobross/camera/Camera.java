package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Abstraction of a camera.
 *
 * @author      Nathalie Junker
 */
public abstract class Camera {

  /** The camera's eye position */
  public final Point3 e;

  /** The camera's gaze direction */
  public final Vector3 g;

  /** The camera's up-vector */
  public final Vector3 t;

  /** The x-Axis of the camera's orthonormal coordinate system */
  public final Vector3 u;

  /** The y-Axis of the camera's orthonormal coordinate system */
  public final Vector3 v;

  /** The z-Axis of the camera's orthonormal coordinate system */
  public final Vector3 w;

  /**
   * Initializes the camera with 3 vectors e, g, t.
   *
   * @param     e   the camera's position (eye-position)
   * @param     g   the camera's gaze direction
   * @param     t   the camera's up-vector.
   */
  public Camera (final Point3 e, final Vector3 g, final Vector3 t) {
    this.e = e;
    this.g = g;
    this.t = t;

    this.w = g.normalized().mul(-1);
    this.u = t.x(w).normalized();
    this.v = w.x(u);
  }

  /**
   * Returns the ray of a precise pixel.
   * @param     w   the width of the image.
   * @param     h   the height of the image.
   * @param     x   the x-coordinate of the pixel.
   * @param     y   the y-coordinate of the pixel.
   *
   * @return        the ray for the pixel
   */
  public abstract Ray rayFor (final int w, final int h, final int x, final int y);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Camera camera = (Camera) o;

    if (!e.equals(camera.e)) return false;
    if (!g.equals(camera.g)) return false;
    if (!t.equals(camera.t)) return false;
    if (!u.equals(camera.u)) return false;
    if (!v.equals(camera.v)) return false;
    return w.equals(camera.w);

  }

  @Override
  public int hashCode() {
    int result = e.hashCode();
    result = 31 * result + g.hashCode();
    result = 31 * result + t.hashCode();
    result = 31 * result + u.hashCode();
    result = 31 * result + v.hashCode();
    result = 31 * result + w.hashCode();
    return result;
  }
}
