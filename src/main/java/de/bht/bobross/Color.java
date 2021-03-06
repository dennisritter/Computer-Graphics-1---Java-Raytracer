package de.bht.bobross;

import de.bht.bobross.math.Helpers;

/**
 * Represents a Color in the RGB color model
 * @author Dennis Ritter
 */
public class Color {

  /** Represents the amount of red light */
  public final double r;

  /** Represents the amount of green light */
  public final double g;

  /** Represents the amount of blue light */
  public final double b;

  /**
   * The Constructor
   * @param   r   The amount of red light the color will contain
   * @param   g   The amount of green light the color will contain
   * @param   b   The amount of blue light the color will contain
   */
  public Color(final double r, final double g, final double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Adds a Color to the current Color
   * @param   c    The Color to add to this Color
   * @return       The new Color representing the sum of this Color and the Color c
   */
  public Color add(final Color c){
    return new Color(r + c.r, g + c.g, b + c.b);
  }

  /**
   * Subtracts a Color from the current Color
   * @param   c     The Color to subtract from this Color
   * @return        The new Color representing the difference of this Color and the Color c
   */
  public Color sub(final Color c){
    return new Color(r - c.r, g - c.g, b - c.b);
  }

  /**
   * Multiplies a Color with the current Color
   * @param    c    The Color to multiply this Color with
   * @return        The new Color representing the product of this Color and the Color c
   */
  public Color mul(final Color c){
    return new Color(r * c.r, g * c.g, b * c.b);
  }

  /**
   * Multiplies a Scalar with the current Color
   * @param   v     The Scalar to multiply this Color with
   * @return        The new Color representing the product of this Color and the Scalar v
   */
  public Color mul(final double v){
    return new Color(r * v, g * v, b * v);
  }

  /**
   * Returns the integer representation of the color
   *
   * @return        The color's integer representation
   */
  public int asInt () {
    int i = 0;
    i += (int) ( r * 0xff ) << 16;
    i += (int) ( g * 0xff ) << 8;
    i += (int) ( b * 0xff );
    return i;
  }


  /**
   * Limits the colors components to a maximum of 1.0
   * @return    c         The Color with it´s components limited to 1.0
   */
  public Color limitComponents (){
    return new Color(Math.min(r, 1.0), Math.min(g, 1.0), Math.min(b, 1.0));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Color color = (Color) o;

    if (!Helpers.compareDouble(color.r, r)) return false;
    if (!Helpers.compareDouble(color.g, g)) return false;
    return Helpers.compareDouble(color.b, b);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(r);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(g);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(b);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Color{" +
        "r=" + r +
        ", g=" + g +
        ", b=" + b +
        '}';
  }
}

