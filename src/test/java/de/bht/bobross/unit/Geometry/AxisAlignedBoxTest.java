package de.bht.bobross.unit.geometry;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.geometry.AxisAlignedBox;
import de.bht.bobross.geometry.Hit;
import de.bht.bobross.material.SingleColorMaterial;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;
import org.junit.Assert;
import org.junit.Test;

public class AxisAlignedBoxTest {

  public static final AxisAlignedBox BOX = new AxisAlignedBox( new SingleColorMaterial( new Color( 1, 0, 0 ) ) );

  @Test
  public void testRayDirectNear () {
    final Ray r = new Ray( new Point3(0,0,1), new Vector3(0,0,-1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,0,1) );
  }

  @Test
  public void testRayDirectFar () {
    final Ray r = new Ray( new Point3(0,0,-1), new Vector3(0,0,1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,0,-1) );
  }

  @Test
  public void testRayDirectTop () {
    final Ray r = new Ray( new Point3(0,1,0), new Vector3(0,-1,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,1,0) );
  }

  @Test
  public void testRayDirectBottom () {
    final Ray r = new Ray( new Point3(0,-1,0), new Vector3(0,1,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,-1,0) );
  }

  @Test
  public void testRayDirectLeft () {
    final Ray r = new Ray( new Point3(-1,0,0), new Vector3(1,0,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(-1,0,0) );
  }

  @Test
  public void testRayDirectRight () {
    final Ray r = new Ray( new Point3(1,0,0), new Vector3(-1,0,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(1,0,0) );
  }

  @Test
  public void testRayAngularLeft () {
    final Ray r = new Ray( new Point3(-1,.8,.8), new Vector3(1,-1,-1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( h.normal, new Normal3(-1,0,0) );
  }

  @Test
  public void testRayAngularInsideLeft () {
    final Ray r = new Ray( new Point3(0,-.5,.5), new Vector3(-1,1,-1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( h.normal, new Normal3(1,0,0) );
  }

  @Test
  public void testRayInsideDirectNear () {
    final Ray r = new Ray( new Point3(0,0,0), new Vector3(0,0,1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,0,-1) );
  }

  @Test
  public void testRayInsideDirectFar () {
    final Ray r = new Ray( new Point3(0,0,0), new Vector3(0,0,-1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,0,1) );
  }

  @Test
  public void testRayInsideDirectTop () {
    final Ray r = new Ray( new Point3(0,0,0), new Vector3(0,1,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,-1,0) );
  }

  @Test
  public void testRayInsideDirectBottom () {
    final Ray r = new Ray( new Point3(0,0,0), new Vector3(0,-1,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,1,0) );
  }

  @Test
  public void testRayInsideDirectLeft () {
    final Ray r = new Ray( new Point3(0,0,0), new Vector3(-1,0,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(1,0,0) );
  }

  @Test
  public void testRayInsideDirectRight () {
    final Ray r = new Ray( new Point3(0,0,0), new Vector3(1,0,0) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( .5, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(-1,0,0) );
  }

  @Test
  public void testRayOppositeDirectNear () {
    final Ray r = new Ray( new Point3(0,0,.5), new Vector3(0,0,-1) );
    final Hit h = BOX.hit( r );
    Assert.assertNotNull( h );
    Assert.assertEquals( 1, h.t, .001 );
    Assert.assertEquals( h.normal, new Normal3(0,0,1) );
  }

}
