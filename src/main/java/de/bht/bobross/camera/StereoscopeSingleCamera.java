package de.bht.bobross.camera;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Point2;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * Implementation of a camera that renders two partial images of two cameras in one image
 * @author      Jannik Portz
 */
public class StereoscopeSingleCamera extends StereoscopeCamera {

  /**
   * Constructs a new stereoscope single camera with all attributes
   *
   * @param     e         The camera's eye-position
   * @param     g         The camera's gaze direction
   * @param     t         The camera's up-vector
   * @param     angle     The angle for the cameras
   * @param     distance  The distance between the two cameras
   * @param     pattern   The sampling pattern to use
   */
  public StereoscopeSingleCamera( final Point3 e, final Vector3 g, final Vector3 t, final double angle, final double distance, final SamplingPattern pattern) {
    super( e, g, t, angle, distance, pattern );
  }

  @Override
  public Ray[] raysFor(int w, int h, int x, int y) {
    final Point2[] samplingPoints = pattern.generatePoints();
    final Ray[] rays = new Ray[ samplingPoints.length * 2 ];

    for ( int i = 0; i < samplingPoints.length; i += 2 ) {
      rays[i] = leftCam.rayFor( w, h, x, y, samplingPoints[i] );
      rays[i+1] = rightCam.rayFor( w, h, x, y, samplingPoints[i] );
    }

    return rays;
  }

}
