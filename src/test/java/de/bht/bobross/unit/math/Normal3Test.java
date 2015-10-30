package de.bht.bobross.unit.math;

import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Vector3;
import junit.framework.Assert;
import org.junit.Test;

public class Normal3Test {

  public static void compareNormals( Normal3 n1, Normal3 n2 ) {
    Assert.assertEquals( n2.x, n1.x );
    Assert.assertEquals( n2.y, n1.y );
    Assert.assertEquals( n2.z, n1.z );
  }

  @Test
  public void testConstructor() {
    final Normal3 n = new Normal3(1, 2, 3);

    Assert.assertEquals( 1.0, n.x );
    Assert.assertEquals( 2.0, n.y );
    Assert.assertEquals( 3.0, n.z );
  }

  @Test
  public void testMul() {
    final Normal3 n1 = new Normal3( 1, 2, 3 );

    compareNormals(new Normal3( 0.5, 1.0, 1.5 ), n1.mul( 0.5 ));
  }

  @Test
  public void testAdd() {
    final Normal3 n1 = new Normal3( 1, 2, 3 );
    final Normal3 n2 = new Normal3( 3, 2, 1 );

    compareNormals(new Normal3(4, 4, 4), n1.add(n2));
  }

  @Test
  public void testDot() {
    final Normal3 n1 = new Normal3( 1, 0, 0 );
    final Vector3 v1 = new Vector3( 1, 0, 0 );
    final Vector3 v2 = new Vector3( 0, 1, 0 );

    Assert.assertEquals(1.0, n1.dot(v1));
    Assert.assertEquals(0.0, n1.dot(v2));
  }
}