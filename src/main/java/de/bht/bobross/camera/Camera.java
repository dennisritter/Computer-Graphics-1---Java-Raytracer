package de.bht.bobross.camera;

import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * @author      Nathalie Junker
 */
public abstract class Camera {

  public final Point3 e;
  public final Vector3 g;
  public final Vector3 t;

  public final Vector3 u;
  public final Vector3 v;
  public final Vector3 w;

  /**
   * The constructor. Initializes the 3 vectors e, g, t.
   * @param e the camera's position (eyeposition)
   * @param g the camera's gaze direction
   * @param t the upvector.
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
   * @param w the width of the image.
   * @param h the height of the image.
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return
   */
  public Ray rayFor(final int w, final int h, final int x, final int y){

  }
}
