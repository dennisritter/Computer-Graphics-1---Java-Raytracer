package de.bht.bobross.unit.math;

import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Vector3;
import junit.framework.Assert;
import org.junit.Test;

public class Vector3Test {

  public static final double EPSILON = 0.001;

  public static void compareVectors ( final Vector3 v1, final Vector3 v2 ) {
    Assert.assertEquals( v2.x, v1.x, EPSILON );
    Assert.assertEquals( v2.y, v1.y, EPSILON );
    Assert.assertEquals( v2.z, v1.z, EPSILON );
  }

  @Test
  public void testMagnitude () {
    final Vector3 v = new Vector3( 1, 1, 1 );
    Assert.assertEquals( Math.sqrt( 3 ), v.magnitude, EPSILON );
  }

  @Test
  public void testAdd () {
    final Vector3 v1 = new Vector3( 1, 1, 1 );
    final Vector3 v2 = new Vector3( 4, 3, 2 );
    final Normal3 n1 = new Normal3( 4, 3, 2 );

    Assert.assertEquals( new Vector3( 5, 4, 3 ), v1.add(v2) );
    Assert.assertEquals( new Vector3( 5, 4, 3 ), v1.add(n1) );
  }

  @Test
  public void testMul () {
    final Vector3 v1 = new Vector3( 1, 2, 3 );
    Assert.assertEquals( new Vector3( 0.5, 1.0, 1.5 ), v1.mul( 0.5 ) );
  }

  @Test
  public void testDot () {
    final Vector3 v1 = new Vector3( 1, 0, 0 );
    final Vector3 v2 = new Vector3( 1, 0, 0 );
    final Vector3 v3 = new Vector3( 0, 1, 0 );
    final Normal3 n1 = new Normal3( 1, 0, 0 );
    final Normal3 n2 = new Normal3( 0, 1, 0 );

    Assert.assertEquals( 1.0, v1.dot(v2) );
    Assert.assertEquals( 0.0, v1.dot(v3) );
    Assert.assertEquals( 1.0, v1.dot(n1) );
    Assert.assertEquals( 0.0, v1.dot(n2) );
  }

  @Test
  public void testNormalized () {
    final Vector3 v = new Vector3( 1, 2, 3 );
    compareVectors( v.normalized(), new Vector3( 0.267, 0.534, 0.801 ) );
  }

  @Test
  public void testReflectOn () {
    final Vector3 v1 = new Vector3( -0.707, 0.707, 0 );
    final Normal3 n1 = new Normal3( 0, 1, 0 );

    Assert.assertEquals( new Vector3( 0.707, 0.707, 0 ), v1.reflectOn( n1 ) );
  }

}