package de.bht.bobross;

/**
 * @author      Dennis Ritter
 */

import de.bht.bobross.math.Mat4x4;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Vector3;

/**
 * Represents a Transformation
 */
public class Transform {

  /** The transformation matrix */
  public final Mat4x4 m;
  /** inverse of the transformation matrix */
  public final Mat4x4 i;

  /**
   * The public constructor.
   * Initialises the transformation matrix and it´s inverse with the 4x4 unit matrix
   */
  public Transform(){
    m = new Mat4x4(
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1
    );

    i = new Mat4x4(
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1
    );
  }

  /**
   * The private constructor
   * @param     m     inverse of the transformation matrix
   * @param     i     The transformation matrix
   */
  private Transform(final Mat4x4 m, final Mat4x4 i){
    this.m = m;
    this.i = i;
  }

  /**
   * Translates the Transform-object
   * @param     x   translation for x-axis
   * @param     y   translation for y-axis
   * @param     z   translation for z-axis
   * @return    Transform   A new Transform-Object representing the translation
   *                        Transformation of the previous Transform-object
   */
  public Transform translate(final double x, final double y, final double z){
    //transformation matrix
    final Mat4x4 tm = new Mat4x4(
        1, 0, 0, x,
        0, 1, 0, y,
        0, 0, 1, z,
        0, 0, 0, 1
    );
    //inverse transformation matrix
    final Mat4x4 ti = new Mat4x4(
        1, 0, 0, -x,
        0, 1, 0, -y,
        0, 0, 1, -z,
        0, 0, 0, 1
    );

    return new Transform( m.mul(tm), i.mul(ti) );
  }

  /**
   * Scales the transform-object
   * @param     sx    Scaling factor for the x-axis
   * @param     sy    Scaling factor for the y-axis
   * @param     sz    Scaling factor for the z-axis
   * @return    @return    Transform    A new Transform-Object representing the scaling
   *                                    Transformation of the previous Transform-object
   */
  public Transform scale(final double sx, final double sy, final double sz){
    //transformation matrix
    final Mat4x4 tm = new Mat4x4(
        sx, 0, 0, 0,
        0, sy, 0, 0,
        0, 0, sz, 0,
        0, 0,  0, 1
    );
    //inverse transformation matrix
    final Mat4x4 ti = new Mat4x4(
        1/sx,    0,    0, 0,
           0, 1/sy,    0, 0,
           0,    0, 1/sz, 0,
           0,    0,    0, 1
    );

    return new Transform( m.mul(tm), i.mul(ti) );
  }


  /**
   * Rotates the transform-object around the x-axis
   * @param     alpha     the degree for the rotation
   * @return    @return    Transform    A new Transform-Object representing the rotation around the x-axis
   *                                    of the previous Transform-object
   */
  public Transform rotateX(final double alpha){
    //transformation matrix
    final Mat4x4 tm = new Mat4x4(
        1,                0,                0, 0,
        0,  Math.cos(alpha), -Math.sin(alpha), 0,
        0,  Math.sin(alpha),  Math.cos(alpha), 0,
        0,                0,                0, 1
    );
    //inverse transformation matrix
    final Mat4x4 ti = new Mat4x4(
        1,                 0,                0, 0,
        0,   Math.cos(alpha),  Math.sin(alpha), 0,
        0,  -Math.sin(alpha),  Math.cos(alpha), 0,
        0,                 0,                0, 1
    );

    return new Transform( m.mul(tm), i.mul(ti) );
  }

  /**
   * Rotates the transform-object around the y-axis
   * @param     alpha     the degree for the rotation
   * @return    @return    Transform    A new Transform-Object representing the rotation around the y-axis
   *                                    of the previous Transform-object
   */
  public Transform rotateY(final double alpha){
    //transformation matrix
    final Mat4x4 tm = new Mat4x4(
        Math.cos(alpha), 0, -Math.sin(alpha), 0,
                      0, 1,                0, 0,
        Math.sin(alpha), 0,  Math.cos(alpha), 0,
                      0, 0,                0, 1
    );
    //inverse transformation matrix
    final Mat4x4 ti = new Mat4x4(
         Math.cos(alpha), 0, Math.sin(alpha), 0,
                       0, 1,               0, 0,
        -Math.sin(alpha), 0, Math.cos(alpha), 0,
                       0, 0,               0, 1
    );

    return new Transform( m.mul(tm), i.mul(ti) );
  }

  /**
   * Rotates the transform-object around the z-axis
   * @param     alpha     the degree for the rotation
   * @return    @return    Transform    A new Transform-Object representing the rotation around the z-axis
   *                                    of the previous Transform-object
   */
  public Transform rotateZ(final double alpha){
    //transformation matrix
    final Mat4x4 tm = new Mat4x4(
        Math.cos(alpha), -Math.sin(alpha), 0, 0,
        Math.sin(alpha),  Math.cos(alpha), 0, 0,
                      0,                0, 1, 0,
                      0,                0, 0, 1
    );
    //inverse transformation matrix
    final Mat4x4 ti = new Mat4x4(
         Math.cos(alpha),  Math.sin(alpha), 0, 0,
        -Math.sin(alpha),  Math.cos(alpha), 0, 0,
                       0,                0, 1, 0,
                       0,                0, 0, 1
    );

    return new Transform( m.mul(tm), i.mul(ti) );
  }

  public Normal3 mul(final Normal3 normal){
    Vector3 normalVec = new Vector3(normal.x, normal.y, normal.z);
    return i.transpose().mul(normalVec).asNormal();
  }

  public Ray mul(final Ray ray){
    return new Ray( i.mul(ray.o), i.mul(ray.d) );
  }
}
