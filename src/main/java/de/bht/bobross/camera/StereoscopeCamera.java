package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point2;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Implementation for a Camera that renders two different images from two cameras on each side of the frame
 * @author      Jannik Portz
 */
public class StereoscopeCamera extends Camera {

  /** The camera for the left partial image */
  public final PerspectiveCamera leftCam;

  /** The camera fpr the right partial image */
  public final PerspectiveCamera rightCam;

  /**
   * Constructs a new stereoscope camera with all attributes
   *
   * @param     e         The camera's eye-position
   * @param     g         The camera's gaze direction
   * @param     t         The camera's up-vector
   * @param     angle     The angle for the cameras
   * @param     distance  The distance between the two cameras
   * @param     pattern   The sampling pattern to use
   */
  public StereoscopeCamera ( final Point3 e, final Vector3 g, final Vector3 t, final double angle, final double distance, final SamplingPattern pattern ) {
    super( e, g, t, pattern );

    leftCam = new PerspectiveCamera( e.add( u.normalized().mul( distance / 2 ) ), g, t, angle, pattern );
    rightCam = new PerspectiveCamera( e.sub( u.normalized().mul( distance / 2 ) ), g, t, angle, pattern );
  }

  @Override
  public Ray rayFor ( final int w, final int h, final int x, final int y, final Point2 samplingPoint ) {
    if ( x < w / 2 ) {
      return leftCam.rayFor( w / 2, h, x, y, samplingPoint );
    }

    return rightCam.rayFor( w / 2, h, x - (w/2), y, samplingPoint );
  }
}