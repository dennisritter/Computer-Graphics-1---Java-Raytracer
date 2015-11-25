package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.AxisAlignedBox;
import de.bht.bobross.geometry.Geometry;
import de.bht.bobross.geometry.Plane;
import de.bht.bobross.geometry.Sphere;
import de.bht.bobross.geometry.Triangle;
import de.bht.bobross.light.Light;
import de.bht.bobross.material.SingleColorMaterial;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

public class RaytracerTest {

  public static void main ( final String[] args ) {
    firstScene();
  }

  public static void firstScene () {
    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new SingleColorMaterial(new Color(0, 0, 1)) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new SingleColorMaterial(new Color(0, 1, 0)));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new SingleColorMaterial( new Color(1, 0, 0) ) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new SingleColorMaterial (new Color(1, 0, 1)) );

    final Camera c = new PerspectiveCamera( new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[]{}, new Color(0,0,0), new Color(0.15, 0.15, 0.15) );

    final RaytracerFrame frame = createRaytracerFrame(w, c, 640, 480);
    frame.setVisible( true );
    frame.drawImage();
  }

  public static RaytracerFrame createRaytracerFrame (final World w, final Camera c, final int width, final int height ) {
    return new RaytracerFrame( new Raytracer( c, w, width, height ) );
  }
}