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

    this.u = (g.x(t)).normalized();
    this.w = g.normalized();
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
}
