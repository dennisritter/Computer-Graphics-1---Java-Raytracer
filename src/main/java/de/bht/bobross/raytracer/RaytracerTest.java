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
import de.bht.bobross.light.DirectionalLight;
import de.bht.bobross.light.Light;
import de.bht.bobross.light.PointLight;
import de.bht.bobross.light.SpotLight;
import de.bht.bobross.material.LambertMaterial;
import de.bht.bobross.material.PhongMaterial;
import de.bht.bobross.material.SingleColorMaterial;
import de.bht.bobross.math.Normal3;
import de.bht.bobross.math.Point3;
import de.bht.bobross.math.Vector3;

public class RaytracerTest {

  static final int WIDTH = 640;
  static final int HEIGHT = 480;

  static final Color RED = new Color (1, 0, 0);
  static final Color BLUE = new Color (0, 0, 1);
  static final Color GREEN = new Color (0, 1, 0);
  static final Color YELLOW = new Color (1, 1, 0);
  static final Color WHITE = new Color (1, 1, 1);

  static final Point3 CAM_POSITION = new Point3(4,4,4);
  static final Vector3 CAM_DIRECTION = new Vector3(-1,-1,-1).normalized();

  static final PointLight POINTLIGHT = new PointLight(WHITE, CAM_POSITION);
  static final DirectionalLight DIRLIGHT = new DirectionalLight(WHITE, CAM_DIRECTION);
  static final SpotLight SPOTLIGHT = new SpotLight(WHITE, CAM_POSITION, CAM_DIRECTION, Math.PI/14);

  public static void main ( final String[] args ) {
//    singleColor();
//    pointLightLambert();
//    pointLightPhong();
//    dirLightPhong();
//    SpotLightPhong();
//    SpotLightLambert();
  }

  public static void singleColor(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new SingleColorMaterial(BLUE) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new SingleColorMaterial(RED));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new SingleColorMaterial(GREEN) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new SingleColorMaterial(YELLOW));

    final Camera c = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { POINTLIGHT }, WHITE, WHITE);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static void pointLightLambert(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(BLUE) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new LambertMaterial (RED));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new LambertMaterial (GREEN) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new LambertMaterial (YELLOW) );

    final Camera c = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { POINTLIGHT }, WHITE, WHITE);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static void pointLightPhong(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new PhongMaterial(GREEN, WHITE, 64) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new PhongMaterial(YELLOW, WHITE, 64) );

    final Camera c = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { POINTLIGHT }, WHITE, WHITE);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static void dirLightPhong(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new PhongMaterial(GREEN, WHITE, 64) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new PhongMaterial(YELLOW, WHITE, 64) );

    final Camera c = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { DIRLIGHT }, WHITE, WHITE);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static void SpotLightPhong(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new PhongMaterial(GREEN, WHITE, 64) );
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new PhongMaterial(YELLOW, WHITE, 64) );

    final Camera c = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { SPOTLIGHT }, WHITE, WHITE);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static void SpotLightLambert(){

    final AxisAlignedBox aab = new AxisAlignedBox( new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(BLUE) );
    final Plane plane =  new Plane( new Point3(0, 0, 0), new Normal3(0, 1, 0), new LambertMaterial(RED));
    final Sphere sphere = new Sphere( new Point3(1,1,1), 0.5, new LambertMaterial(GREEN));
    final Triangle triangle = new Triangle( new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), new LambertMaterial(YELLOW) );

    final Camera c = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( new Geometry[] {aab, plane, sphere, triangle}, new Light[] { SPOTLIGHT }, WHITE, WHITE);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static RaytracerFrame createRaytracerFrame (final World w, final Camera c, final int width, final int height ) {
    return new RaytracerFrame( new Raytracer( c, w, width, height ) );
  }
}