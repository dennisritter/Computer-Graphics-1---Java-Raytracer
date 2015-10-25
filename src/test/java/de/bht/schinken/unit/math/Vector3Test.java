package de.bht.schinken.unit.math;

import de.bht.schinken.math.Vector3;
import junit.framework.Assert;
import org.junit.Test;

public class Vector3Test {

  @Test
  public void testAdd () {
    final Vector3 v1 = new Vector3( 1, 2, 3 );
    final Vector3 v2 = new Vector3( 4, 5, 6 );

    Assert.assertEquals( v1.add( v2 ), new Vector3( 5, 7, 9 ) );
  }

  @Test
  public void testMul () {
    final Vector3 v1 = new Vector3( 1, 2, 3 );
    final Vector3 v2 = new Vector3( 4, 5, 6 );

    Assert.assertEquals( v1.mul( v2 ), new Vector3( 4, 10, 18 ) );
  }

}