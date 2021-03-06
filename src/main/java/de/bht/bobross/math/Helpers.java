package de.bht.bobross.math;

/**
 * Helper class for Math-Library
 *
 * @author Dennis Ritter
 */
public class Helpers {

  /**
   * Represents the allowed variance for results of type double
   */
  public static final double EPSILON = 1e-5;

  /**
   * Compares the two specified double values with a precision of three decimal places
   *
   * @param   d1    The first double to compare
   * @param   d2    The second double to compare
   * @return        Whether the two doubles are equal with a precision of three decimal places
   */
  public static boolean compareDouble ( final double d1, final double d2 ) {
    return Math.abs( d1 - d2 ) < EPSILON;
  }
}
