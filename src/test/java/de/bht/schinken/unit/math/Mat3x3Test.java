package de.bht.schinken.unit.math;

import de.bht.schinken.math.Mat3x3;
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

    Assert.assertEquals( m.determinant, 0.0 );
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
    System.err.println( result );

    Assert.assertEquals( result, expected );
  }

}
