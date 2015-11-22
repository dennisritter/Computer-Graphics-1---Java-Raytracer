package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.Geometry;
import de.bht.bobross.geometry.Sphere;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;

public class RaytracerTest {

  public static void main ( final String[] args ) {

  }

  public static void testSphere () {
    final World world = new World( new Geometry[] {
        new Sphere( new Point3(0,0,-3), 0.5, new Color(1,0,0))
    }, new Color( 0,0,0 ) );

    final Camera camera = new PerspectiveCamera( new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI / 4.0);
    final JFrame jf = createRaytracerFrame( world, camera, 640, 480 );

    jf.setVisible( true );
  }

  public static void testBox () {
    final World world = new World( new Geometry[] {
        new Sphere( new Point3(0,0,-3), 0.5, new Color(1,0,0))
    }, new Color( 0,0,0 ) );

    final Camera camera = new PerspectiveCamera( new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI / 4.0);
    final JFrame jf = createRaytracerFrame( world, camera, 640, 480 );

    jf.setVisible( true );
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
