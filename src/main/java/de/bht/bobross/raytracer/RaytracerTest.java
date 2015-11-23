package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.OrthographicCamera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.AxisAlignedBox;
import de.bht.bobross.geometry.Geometry;
import de.bht.bobross.geometry.Plane;
import de.bht.bobross.geometry.Sphere;
import de.bht.bobross.geometry.Triangle;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;

public class RaytracerTest {

  public static void main ( final String[] args ) {
    //showBox();
    showSpheresSmallBigOrthographic();
  }

  public static void showPlane(){
    final World w = new World( new Geometry[] {
        new Plane( new Point3(0, -1, 0), new Normal3(0, 1, 0), new Color(0, 1, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = createRaytracerFrame( w, c, 640, 480 );
    frame.setVisible( true );
  }

  public static void showSphere(){
    final World w = new World( new Geometry[] {
        new Sphere( new Point3(0,0,-3), 0.5, new Color(1, 0, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = createRaytracerFrame( w, c, 640, 480 );
    frame.setVisible( true );
  }

  public static void showBox(){
    final World w = new World( new Geometry[] {
        new AxisAlignedBox( new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), new Color(0, 0, 1) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(3, 3, 3), new Vector3(-3, -3, -3), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = createRaytracerFrame( w, c, 640, 480 );
    frame.setVisible( true );
  }

  public static void showTriangle(){
    final World w = new World( new Geometry[] {
        new Triangle( new Point3(-.5, .5, -3), new Point3(.5, .5, -3), new Point3(.5, -.5, -3), new Color(1, 0, 1) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = createRaytracerFrame( w, c, 640, 480 );
    frame.setVisible( true );
  }

  public static void showSpheresSmallBig(){
    final World w = new World( new Geometry[] {
        new Sphere( new Point3(-1, 0, -3), 0.5, new Color(1, 0, 0) ), new Sphere( new Point3(1, 0, -6), 0.5, new Color(1, 0, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = createRaytracerFrame( w, c, 640,480 );
    frame.setVisible( true );
  }

  public static void showSpheresSmallBigOrthographic () {
    final World w = new World( new Geometry[] {
        new Sphere( new Point3(-1, 0, -3), 0.5, new Color(1, 0, 0) ), new Sphere( new Point3(1, 0, -6), 0.5, new Color(1, 0, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new OrthographicCamera( new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), 3 );
    final Frame frame = createRaytracerFrame( w, c, 640, 480 );
    frame.setVisible( true );
  }

  public static JFrame createRaytracerFrame (final World w, final Camera c, final int width, final int height ) {
    final JFrame f = new JFrame();
    f.setSize( width, height );
    f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    f.setTitle( "Bob Ross Raytracer" );
    final Container container = f.getContentPane();
    container.setLayout( new BorderLayout() );

    final JPanel panel = new RaytracerPanel( new Raytracer( c, w ), width, height );
    container.add( panel, BorderLayout.CENTER );

    return f;
  }

}
