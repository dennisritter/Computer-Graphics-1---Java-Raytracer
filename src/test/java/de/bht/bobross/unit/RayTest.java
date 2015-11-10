package de.bht.bobross.unit;

import de.bht.bobross.Ray;
import de.bht.bobross.math.Helpers;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import org.junit.Assert;
import org.junit.Test;

public class RayTest {

  @Test
  public void testConstructor () {
    final Point3 o = new Point3( 1, 2, 3 );
    final Vector3 d = new Vector3( 2, 3, 4 );
    final Ray r = new Ray( o, d );
    Assert.assertEquals(o, r.o);
    Assert.assertEquals(d, r.d);
  }

  @Test
  public void testAt () {
    final Ray ray = new Ray( new Point3( 1, 2, 3 ), new Vector3( 2, 3, 4 ) );
    final Point3 e = new Point3( 5, 8, 11 );
    Assert.assertEquals( e, ray.at( 2 ) );
  }

  @Test
  public void testAt2 () {
    final Ray ray = new Ray( new Point3( 1, 2, 3 ), new Vector3( 2, 3, 4 ) );
    final Point3 e = new Point3( 5.4, 8.6, 11.8 );
    Assert.assertEquals( e, ray.at( 2.2 ) );
  }

  @Test
  public void testTOf () {
    final Ray ray = new Ray( new Point3( 1, 2, 3 ), new Vector3( 2, 3, 4 ) );
    final Point3 p = new Point3( 5, 8, 11 );
    Assert.assertEquals( 2, ray.tOf( p ), Helpers.EPSILON );
  }

  @Test
  public void testTOf2 () {
    final Ray ray = new Ray( new Point3( 1, 2, 3 ), new Vector3( 2, 3, 4 ) );
    final Point3 p = new Point3( 5.4, 8.6, 11.8 );
    Assert.assertEquals( 2.2, ray.tOf( p ), Helpers.EPSILON );
  }

}