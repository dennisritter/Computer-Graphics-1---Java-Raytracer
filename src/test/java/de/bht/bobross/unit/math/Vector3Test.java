package de.bht.bobross.unit.math;

import de.bht.bobross.math.Vector3;
import junit.framework.Assert;
import org.junit.Test;

public class Vector3Test {

  public static final double EPSILON = 0.001;

  public static void compareVectors ( final Vector3 v1, final Vector3 v2 ) {
    Assert.assertEquals( v1.x, v2.x, EPSILON );
    Assert.assertEquals( v1.y, v2.y, EPSILON );
    Assert.assertEquals( v1.z, v2.z, EPSILON );
  }

  @Test
  public void testMagnitude () {
    final Vector3 v = new Vector3( 1, 2, 3 );
    Assert.assertEquals( v.magnitude, Math.sqrt( 14 ), EPSILON );
  }

  @Test
  public void testAdd () {
    final Vector3 v1 = new Vector3( 1, 2, 3 );
    final Vector3 v2 = new Vector3( 4, 5, 6 );

    Assert.assertEquals( v1.add(v2), new Vector3( 5, 7, 9 ) );
  }

  @Test
  public void testMul () {
    final Vector3 v1 = new Vector3( 1, 2, 3 );
    Assert.assertEquals( v1.mul( 2.0 ), new Vector3( 2, 4, 6 ) );
  }

  @Test
  public void testDot () {
    final Vector3 v1 = new Vector3( 1, 2, 3 );
    final Vector3 v2 = new Vector3( 4, 5, 6 );

    Assert.assertEquals( v1.dot(v2), 5.656854249, EPSILON );
  }

  @Test
  public void testNormalized () {
    final Vector3 v = new Vector3( 1, 2, 3 );
    compareVectors( v.normalized(), new Vector3( 0.267, 0.534, 0.801 ) );
  }

}