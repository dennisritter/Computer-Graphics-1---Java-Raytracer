package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point2;
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

  /** The pattern to use for generating sampling points */
  public final SamplingPattern pattern;

  /**
   * Initializes the camera with 3 vectors e, g, t.
   *
   * @param     e   the camera's position (eye-position)
   * @param     g   the camera's gaze direction
   * @param     t   the camera's up-vector.
   */
  public Camera ( final Point3 e, final Vector3 g, final Vector3 t, final SamplingPattern pattern ) {
    this.e = e;
    this.g = g;
    this.t = t;
    this.pattern = pattern;

    this.w = g.normalized().mul(-1);
    this.u = t.x(w).normalized();
    this.v = w.x(u);
  }

  /**
   * Generates a Ray for a specific pixel with the provided sampling point
   * @param     w   the width of the image.
   * @param     h   the height of the image.
   * @param     x   the x-coordinate of the pixel.
   * @param     y   the y-coordinate of the pixel.
   * @param     samplingPoint The sampling point for the ray
   * @return        A ray for the specified pixel with the provided sampling point
   */
  protected abstract Ray rayFor ( int w, int h, int x, int y, Point2 samplingPoint );

  /**
   * Returns an array of Rays for the specified pixel.
   * Each ray in the array represents a sampling ray
   *
   * @param     w   the width of the image.
   * @param     h   the height of the image.
   * @param     x   the x-coordinate of the pixel.
   * @param     y   the y-coordinate of the pixel.
   *
   * @return        array of rays for one specific pixel
   */
  public Ray[] raysFor (int w, int h, int x, int y) {
    final Point2[] samplingPoints = pattern.generatePoints();
    final Ray[] rays = new Ray[ samplingPoints.length ];

    for ( int i = 0; i < samplingPoints.length; ++i ) {
      rays[i] = rayFor( w, h, x, y, samplingPoints[i] );
    }

    return rays;
  }

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

  @Override
  public String toString() {
    return "Camera{" +
        "e=" + e +
        ", g=" + g +
        ", t=" + t +
        ", u=" + u +
        ", v=" + v +
        ", w=" + w +
        '}';
  }
}
