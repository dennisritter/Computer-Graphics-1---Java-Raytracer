package de.bht.bobross.unit.math.de.bht.bobross.unit;

import de.bht.bobross.Color;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by dennisritter on 10.11.15.
 */
public class ColorTest {

  public static final double EPSILON = 0.001;

  @Test
  public void testConstructor(){
    Color c1 = new Color(0.5, 0.6, 0.7);

    Assert.assertEquals(0.5, c1.r);
    Assert.assertEquals(0.6, c1.g);
    Assert.assertEquals(0.7, c1.b);
  }

  @Test
  public void testAdd(){
    Color c1 = new Color(0.5, 0.6, 0.7);
    Color c2 = new Color(0.3, 0.2, 0.1);

    Assert.assertEquals(0.8, c1.add(c2).r, EPSILON);
    Assert.assertEquals(0.8, c1.add(c2).g, EPSILON);
    Assert.assertEquals(0.8, c1.add(c2).b, EPSILON);
  }

  @Test
  public void testSub(){
    Color c1 = new Color(0.5, 0.6, 0.7);
    Color c2 = new Color(0.3, 0.2, 0.1);

    Assert.assertEquals(0.2, c1.sub(c2).r, EPSILON);
    Assert.assertEquals(0.4, c1.sub(c2).g, EPSILON);
    Assert.assertEquals(0.6, c1.sub(c2).b, EPSILON);
  }

  @Test
  public void testMul(){
    Color c1 = new Color(0.5, 0.6, 0.7);
    Color c2 = new Color(0.5, 0.6, 0.7);

    Assert.assertEquals(0.25, c1.mul(c2).r, EPSILON);
    Assert.assertEquals(0.36, c1.mul(c2).g, EPSILON);
    Assert.assertEquals(0.49, c1.mul(c2).b, EPSILON);

    double v1 = 0.5;
    Assert.assertEquals(0.25, c1.mul(v1).r, EPSILON);
    Assert.assertEquals(0.30, c1.mul(v1).g, EPSILON);
    Assert.assertEquals(0.35, c1.mul(v1).b, EPSILON);
  }
}
