package de.bht.bobross;

import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.*;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RaytracerFrame extends JFrame {

  protected Raytracer raytracer;

  public RaytracerFrame(Raytracer raytracer) {
    this.raytracer = raytracer;

    setSize(raytracer.image.getWidth(), raytracer.image.getHeight());
    setLayout(new BorderLayout());
    add(new RaytracerCanvas(), BorderLayout.CENTER);
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
  }

  public RaytracerFrame(int width, int height, World world, Camera camera) {
    this(new Raytracer(camera, world, new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)));
  }

  public static void main ( String[] args ) {
    showSphere();
  }

  public static void showPlane(){
    final World w = new World( new Geometry[] {
        new Plane( new Point3(0, -1, 0), new Normal3(0, 1, 0), new Color(0, 1, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = new RaytracerFrame( 640, 480, w, c );
    frame.setVisible( true );
  }

  public static void showSphere(){
    final World w = new World( new Geometry[] {
        new Sphere( new Point3(-1,-1,-3), 1, new Color(1, 0, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = new RaytracerFrame( 500, 500, w, c );
    frame.setVisible( true );
  }

  public static void showBox(){
    final World w = new World( new Geometry[] {
        new AxisAlignedBox( new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), new Color(0, 0, 1) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(3, 3, 3), new Vector3(-3, -3, -3), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = new RaytracerFrame( 640, 480, w, c );
    frame.setVisible( true );
  }

  public static void showTriangle(){
    final World w = new World( new Geometry[] {
        new Triangle( new Point3(-.5, .5, -3), new Point3(.5, .5, -3), new Point3(.5, -.5, -3), new Color(1, 0, 1) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = new RaytracerFrame( 640, 480, w, c );
    frame.setVisible( true );
  }

  public static void showSpheresSmallBig(){
    final World w = new World( new Geometry[] {
        new Sphere( new Point3(-1, 0, -3), 0.5, new Color(1, 0, 0) ), new Sphere( new Point3(1, 0, -6), 0.5, new Color(1, 0, 0) )
    }, new Color(0, 0, 0) );
    final Camera c = new PerspectiveCamera( new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4 );
    final Frame frame = new RaytracerFrame( 640, 480, w, c );
    frame.setVisible( true );
  }

  protected class RaytracerCanvas extends Canvas {

    @Override
    public void paint ( Graphics g ) {
      super.paint(g);
      raytracer.fillImage();
      g.drawImage( raytracer.image, 0, 0, this );
    }
  }

}
