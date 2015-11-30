package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * @author        Jannik Portz
 */

abstract public class Light {

  /** The light's color */
  public final Color color;

  /**
   * Sets the color of the light as its base indication.
   * @param color     The light's color.
   */
  public Light ( final Color color ) {
    this.color = color;
  }

  /**
   * Checks whether a specific point is illuminated by this light or not.
   * @param point     The point to be checked.
   * @return          True if the point is illuminated, false if it's not.
   */
  abstract public boolean illuminates ( final Point3 point );

  /**
   * Calculates the the vector that points at the direction of the light source for a specific Point.
   * @param point     The point
   * @return          The normalized Vector that points towards the light source.
   */
  abstract public Vector3 directionFrom ( final Point3 point );

}