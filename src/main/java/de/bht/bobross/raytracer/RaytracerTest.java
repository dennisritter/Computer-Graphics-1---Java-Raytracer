package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Transform;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.*;
import de.bht.bobross.light.DirectionalLight;
import de.bht.bobross.light.Light;
import de.bht.bobross.light.PointLight;
import de.bht.bobross.light.SpotLight;
import de.bht.bobross.material.LambertMaterial;
import de.bht.bobross.material.PhongMaterial;
import de.bht.bobross.material.ReflectiveMaterial;
import de.bht.bobross.material.TransparentMaterial;
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
//    reflectiveBox();
//    transparentScene();
//    transformScene1();
    transparentScene();
  }

  public static void transformScene1() {
    final Geometry[] geometries = new Geometry[]{
        new Sphere(new PhongMaterial( RED, WHITE, 64 ) )
    };

    final Node[] nodes = new Node[]{ new Node( new Transform().scale(1,.2,1).rotateX( Math.PI/8  ).rotateZ( -Math.PI/5 ), geometries, new PhongMaterial( RED, WHITE, 64 ) ) };
    final Light[] lights = new Light[]{
        new PointLight( WHITE, true, new Point3(0,0,4) )
    };

    final World world = new World( nodes, lights, BLACK, AMBIENT );
    final Camera cam = new PerspectiveCamera( new Point3(0,0,4), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI / 4 );

    createFrame(cam, world);
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

  public static void transparentScene() {
    final Geometry[] geometries = {
        new Plane( new Point3(0,0,0), new Normal3(0,1,0), new ReflectiveMaterial( new Color (1,1,1), new Color(1,1,1), 10, new Color (1,1,1))),

        new Sphere( new Point3(0,1,0), 0.5, new ReflectiveMaterial( new Color (1,0,0), new Color (1,1,1), 10, new Color (1, .5, .5)) ),
        new Sphere( new Point3(-1.5, 1, 0), 0.5, new ReflectiveMaterial( new Color (0,1,0), new Color (1,1,1), 10, new Color (1, .5, .5)) ),
        new Sphere( new Point3(1.5,1,0), 0.5, new ReflectiveMaterial( new Color (0,0,1), new Color (1,1,1), 10, new Color (1, .5, .5)) ),
        new Sphere( new Point3(0,1,-1.5), 0.5, new ReflectiveMaterial( new Color (0,1,1), new Color (1,1,1), 10, new Color (1, .5, .5)) ),
        new Sphere( new Point3(-1.5,1,-1.5), 0.5, new ReflectiveMaterial( new Color (1,0,1), new Color (1,1,1), 10, new Color (1, .5, .5)) ),
        new Sphere( new Point3(1.5,1,-1.5), 0.5, new ReflectiveMaterial( new Color (1,1,0), new Color (1,1,1), 10, new Color (1, .5, .5)) ),

        /** transparent spheres */
        new Sphere( new Point3( 0, 2, 1.5 ), 0.5, new TransparentMaterial( 1.33 ) ),
        new Sphere( new Point3( -1.5, 2, 1.5 ), 0.5, new TransparentMaterial( 1.33 ) ),
        new Sphere( new Point3( 1.5, 2, 1.5 ), 0.5, new TransparentMaterial( 1.33 ) ),

        /** transparent box */
        new AxisAlignedBox( new Point3(-.5, 0, 3), new Point3 ( .5, 1, 4), new TransparentMaterial( 1.33 )),

        new Triangle( new Point3(.7, .5, 3), new Point3(1.3, .5, 3), new Point3(.7, .5, 4),
            new Normal3(0,1,0), new Normal3(0,1,0), new Normal3(0,1,0), new PhongMaterial( new Color(0,1,0), new Color(0,1,0), 20) )
    };

    final Color ambient = new Color (.1,.1,.1);

    final Light[] lights = {
        new SpotLight(new Color(.3, .3, .3), true, new Point3(0,5,-10), new Vector3(0,-1,0), Math.PI/8),
        new PointLight(new Color (.3,.3,.3), true, new Point3(5,5,-10)),
        new DirectionalLight(new Color(.3,.3,.3), true, new Vector3 (1,-1,0))
    };

    final PerspectiveCamera cam = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World world = new World(geometries, lights, BLACK, ambient);
    createFrame(cam, world);
  }

  public static RaytracerFrame createFrame ( final Camera c, final World w ) {
    final RaytracerFrame frame = new RaytracerFrame( new Raytracer( c, w, WIDTH, HEIGHT ) );
    frame.setVisible( true );
    frame.drawImage();
    return frame;
  }

}