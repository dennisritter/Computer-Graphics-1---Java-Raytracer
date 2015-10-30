package de.bht.bobross.unit.math;

import de.bht.bobross.math.Mat3x3;
import de.bht.bobross.math.Vector3;
import junit.framework.Assert;
import org.junit.Test;

public class Mat3x3Test {

  @Test
  public void testDeterminant () {
    final Mat3x3 m = new Mat3x3(
        1, 2, 1,
        3, 2, 2,
        1, 2, 1
    );

    Assert.assertEquals( 0.0, m.determinant );
  }

  @Test
  public void testMultiplyWithMatrix () {
    final Mat3x3 m1 = new Mat3x3(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9
    );

    final Mat3x3 m2 = new Mat3x3(
        14, 2, 27,
        12, 25, 12,
        29, -34, 45
    );

    final Mat3x3 expected = new Mat3x3(
        125, -50, 186,
        290, -71, 438,
        455, -92, 690
    );

    final Mat3x3 result = m1.mul( m2 );

    Assert.assertEquals( expected , result );
  }

  @Test
  public void testMultiplyWithVector () {
    final Mat3x3 m = new Mat3x3(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9
    );

    final Vector3 v = new Vector3( 1, 2, 3 );

    Assert.assertEquals( new Vector3( 14, 32, 50 ), m.mul( v ) );
  }

  @Test
  public void testChangeCol1 () {
    final Mat3x3 m = new Mat3x3(
        1, 1, 1,
        1, 1, 1,
        1, 1, 1
    );

    final Vector3 v = new Vector3( 2, 2, 2 );

    final Mat3x3 expected = new Mat3x3(
        2, 1, 1,
        2, 1, 1,
        2, 1, 1
    );

    Assert.assertEquals( expected, m.changeCol1( v ) );
  }

  @Test
  public void testChangeCol2 () {
    final Mat3x3 m = new Mat3x3(
        1, 1, 1,
        1, 1, 1,
        1, 1, 1
    );

    final Vector3 v = new Vector3( 2, 2, 2 );

    final Mat3x3 expected = new Mat3x3(
        1, 2, 1,
        1, 2, 1,
        1, 2, 1
    );

    Assert.assertEquals( expected, m.changeCol2(v) );
  }

  @Test
  public void testChangeCol3 () {
    final Mat3x3 m = new Mat3x3(
        1, 1, 1,
        1, 1, 1,
        1, 1, 1
    );

    final Vector3 v = new Vector3( 2, 2, 2 );

    final Mat3x3 expected = new Mat3x3(
        1, 1, 2,
        1, 1, 2,
        1, 1, 2
    );

    Assert.assertEquals( expected, m.changeCol3(v) );
  }

}
