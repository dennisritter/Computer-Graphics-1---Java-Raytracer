package de.bht.bobross.unit.math;

import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import junit.framework.Assert;
import org.junit.Test;

public class Point3Test {

  public static void comparePoints( Point3 p1, Point3 p2 ) {
    Assert.assertEquals( p2.x, p1.x );
    Assert.assertEquals( p2.y, p1.y );
    Assert.assertEquals( p2.z, p1.z );
  }

  @Test
  public void testConstructor() {
    final Point3 p = new Point3( 1, 2, 3 );

    Assert.assertEquals( 1.0, p.x );
    Assert.assertEquals( 2.0, p.y );
    Assert.assertEquals( 3.0, p.z );
  }

  @Test
  public void testSubPoint() {
    final Point3 p1 = new Point3( 1, 1, 1 );
    final Point3 p2 = new Point3( 2, 2, 0 );

    Assert.assertEquals( new Vector3( -1, -1, 1 ), p1.sub(p2) );
  }

  @Test
  public void testSubVector() {
    final Point3 p1 = new Point3( 1, 1, 1 );
    final Vector3 v1 = new Vector3( 4, 3, 2 );

    comparePoints(new Point3(-3, -2, -1), p1.sub(v1));
  }

  public void testAddVector() {
    final Point3 p1 = new Point3( 1, 1, 1 );
    final Vector3 v1 = new Vector3( 4, 3, 2 );

    comparePoints( new Point3( 5, 4, 3 ), p1.add(v1) );
  }
}