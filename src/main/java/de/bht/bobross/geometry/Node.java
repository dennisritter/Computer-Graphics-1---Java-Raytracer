package de.bht.bobross.geometry;

import de.bht.bobross.Ray;
import de.bht.bobross.Transform;
import de.bht.bobross.material.Material;
import de.bht.bobross.math.Helpers;
import de.bht.bobross.math.Normal3;

/**
 *
 * Represents a Node with a transform object and a list of geometries.
 *
 * @author      Nathalie Junker
 */
public class Node extends Geometry{

  /** The transformation */
  final Transform transform;

  /** The geometries to be transformed */
  final Geometry[] geometries;

  /**
   * The constructor
   * @param transform    The transformation
   * @param geometries   The geometries to be transformed
   * @param material     The material
   */
  public Node ( final Transform transform, final Geometry[] geometries, final Material material ){
    super(material);
    this.transform = transform;
    this.geometries = geometries;
  }

  @Override
  public Hit hit(Ray r) {

    Ray ray = transform.mul(r);
    Hit minHit = null;

    for (Geometry geo : geometries){
      final Hit hit = geo.hit(ray);
      if ( hit != null && (minHit == null || hit.t < minHit.t) ) {
        minHit = hit;
      }
    }
    if( minHit == null ) { return null; }
    return new Hit( minHit.t, r, minHit.geo, transform.mul( minHit.normal ) );
  }

  @Override
  public String toString(){
    return "Node{" +
        "transform=" + transform +
        ", geometries=" + geometries +
        ", material=" + material +
        "} " + super.toString();
  }
}
