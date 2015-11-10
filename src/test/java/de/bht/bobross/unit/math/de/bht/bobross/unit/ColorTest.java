package de.bht.bobross.unit.math.de.bht.bobross.unit;

import de.bht.bobross.Color;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Dennis Ritter
 */
public class ColorTest {

  @Test
  public void testConstructor(){
    final Color c1 = new Color(0.5, 0.6, 0.7);

    Assert.assertEquals(0.5, c1.r);
    Assert.assertEquals(0.6, c1.g);
    Assert.assertEquals(0.7, c1.b);
  }

  @Test
  public void testAdd() {
    final Color c1 = new Color(0.5, 0.6, 0.7);
    final Color c2 = new Color(0.3, 0.2, 0.1);
    final Color expected = new Color(0.8, 0.8, 0.8);

    Assert.assertEquals(expected, c1.add(c2));
  }

  @Test
  public void testSub(){
    final Color c1 = new Color(0.5, 0.6, 0.7);
    final Color c2 = new Color(0.3, 0.2, 0.1);
    final Color expected = new Color(0.2, 0.4, 0.6);

    Assert.assertEquals(expected, c1.sub(c2));
  }

  @Test
  public void testMul(){
    final Color c1 = new Color(0.5, 0.6, 0.7);
    final Color c2 = new Color(0.5, 0.6, 0.7);
    final Color expected1 = new Color(0.25, 0.36, 0.49);
    final Color expected2 = new Color(0.25, 0.30, 0.35);

    Assert.assertEquals(expected1, c1.mul(c2));

    final double v1 = 0.5;
    Assert.assertEquals(expected2, c1.mul(v1));
  }
}
