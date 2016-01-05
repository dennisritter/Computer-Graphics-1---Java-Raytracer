package de.bht.bobross;

/**
 * @author      Dennis Ritter
 */

import de.bht.bobross.math.Mat4x4;
import de.bht.bobross.math.Normal3;

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
        0, 0, 0, 1,
        0, 0, 1, 0,
        0, 1, 0, 0,
        1, 0, 0, 0
    );

    i = new Mat4x4(
        0, 0, 0, 1,
        0, 0, 1, 0,
        0, 1, 0, 0,
        1, 0, 0, 0
    );
  }

  /**
   * The private constructor
   * @param     m     inverse of the transformation matrix
   * @param     i     The transformation matrix
   */
  private Transform(Mat4x4 m, Mat4x4 i){
    this.m = m;
    this.i = i;
  }

  public Transform translate(){

  }

  public Transform scale(){

  }

  public Transform rotateX(){

  }

  public Transform rotateY(){

  }

  public Transform rotateZ(){

  }

  public Normal3 mul(Normal3 normal){

  }

  public Ray mul(Ray ray){

  }
}
