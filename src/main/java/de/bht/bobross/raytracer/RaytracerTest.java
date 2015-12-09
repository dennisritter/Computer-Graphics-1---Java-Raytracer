package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.AxisAlignedBox;
import de.bht.bobross.geometry.Geometry;
import de.bht.bobross.geometry.Plane;
import de.bht.bobross.geometry.Sphere;
import de.bht.bobross.light.Light;
import de.bht.bobross.light.PointLight;
import de.bht.bobross.material.LambertMaterial;
import de.bht.bobross.material.ReflectiveMaterial;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

public class RaytracerTest {

  /** The initial width and height of the window */
  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  public static final Color BLACK = new Color( 0, 0, 0 );
  public static final Color RED = new Color( 1, 0, 0 );
  public static final Color WHITE = new Color( 1, 1, 1 );
  public static final Color BLUE = new Color( 0, 0, 1 );
  public static final Color GREEN = new Color( 0, 1, 0 );
  public static final Color AMBIENT = new Color( .25, .25, .25 );

  public static void main ( final String[] args ) {
//    illuminatesTest();
//    reflectiveBox();
    scene1();
    scene2();
  }

  public static void illuminatesTest () {
    final Geometry[] geometries = new Geometry[]{
        new Plane( new Point3(0,0,0), new Normal3(0,1,0), new LambertMaterial( new Color(.8,.8,.8) ) ),
        new Sphere(new Point3(-3, 1, 0), 1, new LambertMaterial(BLUE)),
        new Sphere(new Point3(0, 1, 0), 1, new LambertMaterial(RED)),
        new Sphere(new Point3(3, 1, 0), 1, new LambertMaterial(GREEN))
    };

    final Camera cam = new PerspectiveCamera( new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4 );

    final Light[] lights = new Light[]{
        new PointLight( WHITE, true, new Point3(8,6,0) )
    };

    final World world = new World( geometries, lights, BLACK, AMBIENT );
    createFrame( cam, world );
  }

  public static void reflectiveBox () {
    final Geometry[] geometries = new Geometry[]{
        new Plane( new Point3(0,0,0), new Normal3(0,1,0), new ReflectiveMaterial( WHITE, BLACK, 64, new Color(.5,.5,.5) ) ),
        new AxisAlignedBox( new Point3(-.5,0,-.5), new Point3(.5,1,.5), new ReflectiveMaterial( RED, BLACK, 64, new Color(.5,.5,.5) ) )
    };

    final Camera cam = new PerspectiveCamera( new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4 );

    final Light[] lights = new Light[]{
        new PointLight( WHITE, true, new Point3(8,8,0) )
    };

    final World world = new World( geometries, lights, BLACK, AMBIENT );
    createFrame( cam, world );
  }

  public static void scene1 () {
    final Geometry[] geometries = new Geometry[]{
      new Plane( new Point3(0,0,0), new Normal3(0,1,0), new ReflectiveMaterial( new Color(.1,.1,.1), BLACK, 64, new Color(.5,.5,.5) ) ),
      new Sphere( new Point3(-3,1,0), 1, new ReflectiveMaterial( RED, WHITE, 64, new Color(.5,.5,.5) ) ),
      new Sphere( new Point3(0,1,0), 1, new ReflectiveMaterial( GREEN, WHITE, 64, new Color(.5,.5,.5) ) ),
      new Sphere( new Point3(3,1,0), 1, new ReflectiveMaterial( BLUE, WHITE, 64, new Color(.5,.5,.5) ) )
    };

    final Camera cam = new PerspectiveCamera( new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4 );

    final Light[] lights = new Light[]{
        new PointLight( WHITE, true, new Point3(8,8,8) )
    };

    final World world = new World( geometries, lights, BLACK, AMBIENT );
    createFrame( cam, world );
  }

  public static void scene2 () {
    final Geometry[] geometries = new Geometry[]{
      new Plane( new Point3(0,0,0), new Normal3(0,1,0), new LambertMaterial( new Color(.8,.8,.8) ) ),
      new AxisAlignedBox( new Point3(-.5,0,-.5), new Point3(.5,1,.5), new LambertMaterial( RED ) )
    };

    final Camera cam = new PerspectiveCamera( new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4 );

    final Light[] lights = new Light[]{
        new PointLight( WHITE, true, new Point3(8,8,0) )
    };

    final World world = new World( geometries, lights, BLACK, AMBIENT );
    createFrame( cam, world );
  }

  public static RaytracerFrame createFrame ( final Camera c, final World w ) {
    final RaytracerFrame frame = new RaytracerFrame( new Raytracer( c, w, WIDTH, HEIGHT ) );
    frame.setVisible( true );
    frame.drawImage();
    return frame;
  }
}