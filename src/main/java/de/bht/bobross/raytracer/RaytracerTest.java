package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.camera.PerspectiveCamera;
import de.bht.bobross.geometry.AxisAlignedBox;
import de.bht.bobross.geometry.Disc;
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

  /** The initial width and height of the window */
  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  /** The used colors */
  static final Color RED = new Color (1, 0, 0);
  static final Color BLUE = new Color (0, 0, 1);
  static final Color GREEN = new Color (0, 1, 0);
  static final Color YELLOW = new Color (1, 1, 0);
  static final Color WHITE = new Color (1, 1, 1);
  static final Color BLACK = new Color (0, 0, 0);
  static final Color GOLD = new Color (0.7,0.5,0);
  static final Color DARK_GREEN = new Color (0.1,0.5,0);

  /** The camera's eye-position */
  static final Point3 CAM_POSITION = new Point3(4,4,4);

  /** The directional light's main direction */
  static final Vector3 LIGHT_DIRECTION = new Vector3(-1,-1,-1).normalized();

  /** The perspective camera */
  static final Camera PERSPECTIVE_CAMERA = new PerspectiveCamera( CAM_POSITION, new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4 );

  /** The different lights */
  static final PointLight POINTLIGHT = new PointLight(WHITE, CAM_POSITION);
  static final DirectionalLight DIR_LIGHT = new DirectionalLight(WHITE, LIGHT_DIRECTION);
  static final SpotLight SPOTLIGHT = new SpotLight(WHITE, CAM_POSITION, LIGHT_DIRECTION, Math.PI/14);


  /** Displays the selected scenes, each in it's own window. */
  public static void main ( final String[] args ) {
        singleColor();
        pointLightLambert();
        pointLightPhong();
        dirLightPhong();
        spotLightPhong();
        spotLightPhongAmbient();
        pointLightPhongDisc();
        customDemoScene();
  }


  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the SingleColorMaterial and a point light.
   */
  public static void singleColor(){

    /** Initialize each geometry */
    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new SingleColorMaterial(BLUE)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new SingleColorMaterial(RED)),
        new Sphere(new Point3(1, 1, 1), 0.5, new SingleColorMaterial(GREEN)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new SingleColorMaterial(YELLOW))
    };

    final World w = new World( geometries, new Light[] { POINTLIGHT }, BLACK, WHITE);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Lambert material and a point light.
   */
  public static void pointLightLambert(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(BLUE)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new LambertMaterial(RED)),
        new Sphere(new Point3(1, 1, 1), 0.5, new LambertMaterial(GREEN)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new LambertMaterial(YELLOW))
    };

    final World w = new World( geometries, new Light[] { POINTLIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Phong material and a point light.
   */
  public static void pointLightPhong(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64)),
        new Sphere(new Point3(1, 1, 1), 0.5, new PhongMaterial(GREEN, WHITE, 64)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new PhongMaterial(YELLOW, WHITE, 64))
    };

    final World w = new World( geometries, new Light[] { POINTLIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow disc.
   * Uses the Phong material and a point light.
   */
  public static void pointLightPhongDisc(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64)),
        new Sphere(new Point3(1, 1, 1), 0.5, new PhongMaterial(GREEN, WHITE, 64)),
        new Disc(new Point3(0, 2, -1), new Normal3(0, 0, 1), 0.5, new PhongMaterial(YELLOW, WHITE, 64))
    };

    final World w = new World( geometries, new Light[] { POINTLIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Phong material and a directional light.
   */
  public static void dirLightPhong(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64)),
        new Sphere(new Point3(1, 1, 1), 0.5, new PhongMaterial(GREEN, WHITE, 64)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new PhongMaterial(YELLOW, WHITE, 64))
    };

    final World w = new World( geometries, new Light[] { DIR_LIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Phong material and a spotlight.
   */
  public static void spotLightPhong(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64)),
        new Sphere(new Point3(1, 1, 1), 0.5, new PhongMaterial(GREEN, WHITE, 64)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new PhongMaterial(YELLOW, WHITE, 64))
    };

    final World w = new World( geometries, new Light[] { SPOTLIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Phong material, a spotlight and a specific ambient light.
   */
  public static void spotLightPhongAmbient () {

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(BLUE, WHITE, 64)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(RED, WHITE, 64)),
        new Sphere(new Point3(1, 1, 1), 0.5, new PhongMaterial(GREEN, WHITE, 64)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new PhongMaterial(YELLOW, WHITE, 64))
    };

    final World w = new World( geometries, new Light[] { SPOTLIGHT }, BLACK, new Color(.25, .25, .25));
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Lambert material, a spotlight and a specific ambient light.
   *
   */
  public static void spotLightLambertAmbient25(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(BLUE)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new LambertMaterial(RED)),
        new Sphere(new Point3(1, 1, 1), 0.5, new LambertMaterial(GREEN)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new LambertMaterial(YELLOW))
    };

    final World w = new World( geometries, new Light[] { SPOTLIGHT }, BLACK,  new Color(0.25, 0.25, 0.25));
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the Lambert material and a spotlight.
   */
  public static void spotLightLambert(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(BLUE)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new LambertMaterial(RED)),
        new Sphere(new Point3(1, 1, 1), 0.5, new LambertMaterial(GREEN)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new LambertMaterial(YELLOW))
    };

    final World w = new World( geometries, new Light[] { SPOTLIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  /**
   * Creates a scene with a blue AxisAlignedBox, a red plane, a green sphere and a yellow triangle.
   * Uses the single color material and a spotlight.
   */
  public static void spotLightSingleColor(){

    Geometry[] geometries = {
        new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new SingleColorMaterial(BLUE)),
        new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new SingleColorMaterial(RED)),
        new Sphere(new Point3(1, 1, 1), 0.5, new SingleColorMaterial(GREEN)),
        new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new SingleColorMaterial(YELLOW))
    };

    final World w = new World( geometries, new Light[] { SPOTLIGHT }, BLACK, BLACK);
    final RaytracerFrame frame = createRaytracerFrame(w, PERSPECTIVE_CAMERA, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }


  /**
   * Creates a custom demo scene with one dark-green plane and eleven gold and red spheres with an radius of 1.
   * Uses the Phong Material, a point light and a directional light.
   */
  public static void customDemoScene(){

    /** Initializes the point light and the directional light*/
    Light[] lights = {
      new PointLight(WHITE, new Point3(4, 4, 0)),
      new DirectionalLight(BLACK, new Vector3(1, 1, 1).normalized())
    };

    Geometry[] geometries = {
        new Plane(new Point3(-20, -20, -20), new Normal3(0, 1, 0), new PhongMaterial(DARK_GREEN, DARK_GREEN, 10)),
        new Sphere(new Point3(7, 1, 4), 1, new PhongMaterial(GOLD, WHITE, 10)),     // 1
        new Sphere(new Point3(5, 3, 7), 1, new PhongMaterial(RED, WHITE, 10)),      // 2
        new Sphere(new Point3(1, 3, 6), 1, new PhongMaterial(RED, WHITE, 10)),      // 3
        new Sphere(new Point3(-5, 1, 0), 1, new PhongMaterial(GOLD, WHITE, 10)),    // 4
        new Sphere(new Point3(-5, 1, -4), 1, new PhongMaterial(GOLD, WHITE, 10)),   // 5
        new Sphere(new Point3(-3, 0, -8), 1, new PhongMaterial(RED, WHITE, 10)),    // 6
        new Sphere(new Point3(0, 0, 0), 1, new PhongMaterial(RED, WHITE, 10)),      // 7
        new Sphere(new Point3(0, 0, 4), 1, new PhongMaterial(GOLD, WHITE, 10)),     // 8
        new Sphere(new Point3(4, 0, 4), 1, new PhongMaterial(RED, WHITE, 10)),      // 9
        new Sphere(new Point3(1, 0, -4), 1, new PhongMaterial(GOLD, WHITE, 10)),    // 10
        new Sphere(new Point3(5, 1, -2), 1, new PhongMaterial(RED, WHITE, 10))      // 11
    };

    final Camera c = new PerspectiveCamera( new Point3(8,8,8) , new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
    final World w = new World( geometries, lights, BLACK, BLACK);

    final RaytracerFrame frame = createRaytracerFrame(w, c, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.drawImage();
  }

  public static RaytracerFrame createRaytracerFrame (final World w, final Camera c, final int width, final int height ) {
    return new RaytracerFrame( new Raytracer( c, w, width, height ) );
  }
}