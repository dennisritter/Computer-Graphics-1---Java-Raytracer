package de.bht.bobross.geometry;

import de.bht.bobross.Ray;

/**
 * Represents a intersection between a Ray and Geometry
 * @author Dennis Ritter
 */
public class Hit {

  /**
   * The scalar when the direction Vector hit the Geometry
   */
  public final double t;

  /**
   * The Ray that hit the Geometry
   */
  public final Ray ray;

  /**
   * The Geometry that has been hit
   */
  public final Geometry geo;

  /**
   * The constructor
   * @param   t     The scalar when the direction Vector hit the Geometry
   * @param   ray   The Ray that hit the Geometry
   * @param   geo   The Geometry that has been hit
   */
  public Hit(final double t, final Ray ray, final Geometry geo){
    this.t = t;
    this.ray = ray;
    this.geo = geo;
  }


}
