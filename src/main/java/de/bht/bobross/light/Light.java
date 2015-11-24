package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

abstract public class Light {

  public final Color color;

  public Light ( final Color color ) {
    this.color = color;
  }

  abstract public boolean illuminates ( final Point3 point );

  abstract public Vector3 directionFrom ( final Point3 point );

}