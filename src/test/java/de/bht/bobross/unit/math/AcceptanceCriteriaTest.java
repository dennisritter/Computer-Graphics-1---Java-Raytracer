package de.bht.bobross.unit.math;

import de.bht.bobross.math.Mat3x3;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test case for all acceptance criteria stated in assignment 1
 */
public class AcceptanceCriteriaTest {

  public static final double EPSILON = 10e-3;

  @Test
  public void testCriterion1 () {
    final Normal3 n = new Normal3( 1, 2, 3 );
    final Normal3 e = new Normal3( 0.5, 1, 1.5 );
    Assert.assertEquals( e, n.mul(0.5) );
  }

  @Test
  public void testCriterion2 () {
    final Normal3 n1 = new Normal3( 1, 2, 3 );
    final Normal3 n2 = new Normal3( 3, 2, 1 );
    final Normal3 e = new Normal3( 4, 4, 4 );
    Assert.assertEquals( e, n1.add(n2) );
  }

  @Test
  public void testCriterion3NormalVector () {
    final Normal3 n1 = new Normal3( 1, 0, 0 );
    final Vector3 v1 = new Vector3( 1, 0, 0 );
    Assert.assertEquals( 1, n1.dot( v1 ), EPSILON );

    final Vector3 v2 = new Vector3( 0, 1, 0 );
    Assert.assertEquals( 0, n1.dot( v2 ), EPSILON );
  }

  @Test
  public void testCriterion3VectorNormal () {
    final Vector3 v1 = new Vector3( 1, 0, 0 );
    final Normal3 n1 = new Normal3( 1, 0, 0 );
    Assert.assertEquals( 1, v1.dot( n1 ), EPSILON );

    final Normal3 n2 = new Normal3( 0, 1, 0 );
    Assert.assertEquals( 0, v1.dot( n2 ), EPSILON );
  }

  @Test
  public void testCriterion3VectorVector () {
    final Vector3 v1 = new Vector3( 1, 0, 0 );
    Assert.assertEquals( 1, v1.dot( v1 ), EPSILON );

    final Vector3 v2 = new Vector3( 0, 1, 0 );
    Assert.assertEquals( 0, v1.dot( v2 ), EPSILON );
  }

  @Test
  public void testCriterion4 () {
    final Point3 p1 = new Point3( 1, 1, 1 );
    final Point3 p2 = new Point3( 2, 2, 0 );
    final Vector3 e = new Vector3( -1, -1, 1 );
    Assert.assertEquals( e, p1.sub( p2 ) );
  }

  @Test
  public void testCriterion5 () {
    final Point3 p1 = new Point3( 1, 1, 1 );
    final Vector3 v1 = new Vector3( 4, 3, 2 );
    final Point3 e = new Point3( -3, -2, -1 );
    Assert.assertEquals( e, p1.sub(v1) );
  }

  @Test
  public void testCriterion6 () {
    final Point3 p1 = new Point3( 1, 1, 1 );
    final Vector3 v1 = new Vector3( 4, 3, 2 );
    final Point3 e = new Point3( 5, 4, 3 );
    Assert.assertEquals( e, p1.add(v1) );
  }

  @Test
  public void testCriterion7 () {
    final Vector3 v = new Vector3( 1, 1, 1 );
    Assert.assertEquals( Math.sqrt( 3 ), v.magnitude, EPSILON );
  }

  /**
   * TODO: criterion 8
   */

  // TODO: add Test Annotation
  public void testCriterion9 () {
    final Vector3 v = new Vector3( -.707, .707, 0 );
    final Normal3 n = new Normal3( 0, 1, 0 );
    final Vector3 e = new Vector3( .707, .707, 0 );
    Assert.assertEquals( e, v.reflectedOn( n ) );
  }

  // TODO: add Test Annotation
  public void testCriterion10 () {
    final Vector3 v = new Vector3( .707, .707, 0 );
    final Normal3 n = new Normal3( 1, 0, 0 );
    final Vector3 e = new Vector3( .707, -.707, 0 );
    Assert.assertEquals( e, v.reflectedOn( n ) );
  }

  @Test
  public void testCriterion11 () {
    final Mat3x3 m = new Mat3x3(
        1, 0, 0,
        0, 1, 0,
        0, 0, 1 );

    final Point3 p = new Point3( 3, 2, 1 );
    Assert.assertEquals( p, m.mul( p ) );

    final Vector3 v = new Vector3( 3, 2, 1 );
    Assert.assertEquals( v, m.mul(v) );
  }

  @Test
  public void testCriterion12 () {
    final Mat3x3 m1 = new Mat3x3(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9 );

    final Mat3x3 m2 = new Mat3x3(
        1, 0, 0,
        0, 1, 0,
        0, 0, 1 );

    Assert.assertEquals( m1, m1.mul( m2 ) );
  }

  @Test
  public void testCriterion13 () {
    final Mat3x3 m1 = new Mat3x3(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9 );

    final Mat3x3 m2 = new Mat3x3(
        0, 0, 1,
        0, 1, 0,
        1, 0, 0 );

    final Mat3x3 e = new Mat3x3(
        3, 2, 1,
        6, 5, 4,
        9, 8, 7 );

    Assert.assertEquals( e, m1.mul( m2 ) );
  }

  @Test
  public void testCriterion14 () {
    final Mat3x3 m = new Mat3x3(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9 );

    final Vector3 v = new Vector3( 8, 8, 8 );

    final Mat3x3 e1 = new Mat3x3(
        8, 2, 3,
        8, 5, 6,
        8, 8, 9 );
    Assert.assertEquals( e1, m.changeCol1( v ) );

    final Mat3x3 e2 = new Mat3x3(
        1, 8, 3,
        4, 8, 6,
        7, 8, 9 );
    Assert.assertEquals( e2, m.changeCol2( v ) );

    final Mat3x3 e3 = new Mat3x3(
        1, 2, 8,
        4, 5, 8,
        7, 8, 8 );
    Assert.assertEquals( e3, m.changeCol3( v ) );
  }

}
