package de.bht.bobross.light;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

/**
 * @author        Jannik Portz
 *
 * TODO: Documentation
 */
abstract public class Light {

  /** The light's color */
  public final Color color;

  public final boolean castsShadows;

  /**
   * Sets the color of the light as its base indication.
   * @param     color     The light's color.
   * @param     castsShadows  Whether the light casts shadows
   */
  public Light ( final Color color, final boolean castsShadows ) {
    this.color = color;
    this.castsShadows = castsShadows;
  }

  /**
   * Checks whether a specific point is illuminated by this light or not.
   * @param point     The point to be checked.
   * @return          True if the point is illuminated, false if it's not.
   */
  abstract public boolean illuminates ( final Point3 point, final World world );

  /**
   * Calculates the the vector that points at the direction of the light source for a specific Point.
   * @param point     The point
   * @return          The normalized Vector that points towards the light source.
   */
  abstract public Vector3 directionFrom ( final Point3 point );

}