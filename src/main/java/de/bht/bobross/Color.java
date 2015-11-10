package de.bht.bobross;

/**
 * Created by dennisritter on 10.11.15.
 */

/**
 * Represents a Color in the RGB color model
 */
public class Color {

  /**
   * Represents the amount of red light
   */
  public final double r;
  /**
   * Represents the amount of green light
   */
  public final double g;
  /**
   * Represents the amount of blue light
   */
  public final double b;

  /**
   * The Constructor
   * @param   r   The amount of red light the color will contain
   * @param   g   The amount of green light the color will contain
   * @param   b   The amount of blue light the color will contain
   */
  public Color(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   *
   * @param   c    The Color to add to this Color
   * @return       The new Color representing the sum of this Color and the Color c
   */
  public Color add(Color c){
    return new Color(r + c.r, g + c.g, b + c.b);
  }

  /**
   *
   * @param   c     The Color to subtract from this Color
   * @return        The new Color representing the difference of this Color and the Color c
   */
  public Color sub(Color c){
    return new Color(r - c.r, g - c.g, b - c.b);
  }

  /**
   *
   * @param    c    The Color to multiply this Color with
   * @return        The new Color representing the product of this Color and the Color c
   */
  public Color mul(Color c){
    return new Color(r * c.r, g * c.g, b * c.b);
  }

  /**
   *
   * @param   v     The Scalar to multiply this Color with
   * @return        The new Color representing the product of this Color and the Scalar v
   */
  public Color mul(Double v){
    return new Color(r * v, g * v, b * v);
  }
}

