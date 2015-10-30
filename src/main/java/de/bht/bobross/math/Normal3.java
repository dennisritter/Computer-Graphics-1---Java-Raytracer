package de.bht.bobross.math;

public class Normal3 {

  public final double x;
  public final double y;
  public final double z;

  public Normal3 ( final double x, final double y, final double z ) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Normal3 mul ( final double n ) {
    return new Normal3( x * n, y * n, z * n );
  }

  public Normal3 add ( final Normal3 n ) { return new Normal3( x + n.x, y + n.y, z + n.z ); }

  public double dot ( final Vector3 v ) {
    return x * v.x + y * v.y + z * v.z;
  }

}