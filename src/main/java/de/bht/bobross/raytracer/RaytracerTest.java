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
import de.bht.bobross.light.PointLight;
import de.bht.bobross.material.LambertMaterial;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

import javax.swing.JFrame;
import java.awt.Frame;

/**
 * Test class displaying various geometries in a raytracer
 *
 * @author    Jannik Portz
 */
public class RaytracerTest {

  static final int WIDTH = 640;
  static final int HEIGHT = 480;

  static final Color RED = new Color (1, 0, 0);
  static final Color BLUE = new Color (0, 0, 1);
  static final Color GREEN = new Color (0, 1, 0);
  static final Color BLACK = new Color (0, 0, 0);
  static final Color WHITE = new Color (1, 1, 1);

  public static void main ( final String[] args ) {
    firstScene();
  }

  public static void firstScene(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(new Color(0, 0, 1)) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new LambertMaterial (new Color(1, 0, 0)));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new LambertMaterial (new Color(0, 1, 0)) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new LambertMaterial (new Color(0, 1, 1)) );

    final Point3 camPosition = new Point3(4,4,4);

    final Camera c = new PerspectiveCamera( camPosition, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { new PointLight(WHITE, camPosition) }, RED, BLUE);

    final Frame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
  }

  public static JFrame createRaytracerFrame (final World w, final Camera c, final int width, final int height ) {
    return new RaytracerFrame( new Raytracer( c, w ), width, height );
  }

}
