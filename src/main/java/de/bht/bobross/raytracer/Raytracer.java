package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;
import de.bht.bobross.geometry.Hit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Collection;
import java.util.HashSet;

/**
 * A Raytracer traces rays coming from a camera and returns the closest hit with a Geometry.
 *
 * @author      Jannik Portz
 */
public class Raytracer {

  /** The camera whose rays to hit against the geometries */
  public final Camera camera;

  /** The world containing all geometries in the scene */
  public final World world;

  /** The image to draw on */
  public final BufferedImage image;

  /** The image's width */
  public final int width;

  /** The image's height */
  public final int height;

  /** The ActionListeners to notify on progress */
  public final Collection<ActionListener> actionListeners;

  /** The interval in pixels when a RaytracerProgressEvent should be sent */
  protected final int progressInterval;

  /** Time when rendering process started in milliseconds */
  protected long started;

  /**
   * Constructs a new Raytracer with a camera and a world
   *
   * @param     camera    The camera whose rays to hit against the geometries
   * @param     world     The world containing all geometries in the scene
   * @param     width     The image's width
   * @param     height    The image's height
   * @param     progressInterval The interval in pixels when a RaytracerProgressEvent should be sent
   */
  public Raytracer ( final Camera camera, final World world, final int width, final int height, final int progressInterval ) {
    this.camera = camera;
    this.world = world;
    this.width = width;
    this.height = height;
    this.progressInterval = progressInterval;
    this.image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
    this.actionListeners = new HashSet<ActionListener>();
  }

  /**
   * Constructs a new Raytracer with a camera and a world
   * Sets the progressInterval to 1000
   *
   * @param     camera    The camera whose rays to hit against the geometries
   * @param     world     The world containing all geometries in the scene
   * @param     width     The image's width
   * @param     height    The image's height
   */
  public Raytracer ( final Camera camera, final World world, final int width, final int height ) {
    this( camera, world, width, height, 1000 );
  }

  /**
   * Iterates through all pixels in the image and fills the image's DataBuffer.
   * Sends a RaytracerProgressEvent every 1000 pixels.
   */
  public void loadImage () {
    started = System.currentTimeMillis();

    final DataBufferInt dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
    final int[] pixels = dataBuffer.getData();
    final int w = image.getWidth();
    final int h = image.getHeight();
    final int totalPixels = w * h;

    int i = 0;
    for ( int y = 0; y < h; y++ ) {
      for ( int x = 0; x < w; x++ ) {
        pixels[ (h-y-1) * w + x ] = traceRay( x, y, image.getWidth(), image.getHeight() ).asInt();
        ++i;
        if ( i % progressInterval == 0 ) {
          notifyActionListeners( new RaytracerProgress( i, totalPixels ), RaytracerCommands.PROGRESS );
        }
        try {
          if ( i % 50 == 0 ) {
            Thread.sleep(1);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    notifyActionListeners( new RaytracerProgress( totalPixels, totalPixels ), RaytracerCommands.FINISHED );
    started = -1;
  }

  /**
   * Adds the specified ActionListener to the set of ActionListeners
   *
   * @param     actionListener  The ActionListener to be added
   */
  public void addActionListener ( final ActionListener actionListener ) {
    actionListeners.add( actionListener );
  }

  /**
   * Removes the specified ActionListener from the set of ActionListeners
   *
   * @param     actionListener  The ActionListener to be removed
   */
  public void removeActionListener ( final ActionListener actionListener ) {
    actionListeners.remove( actionListener );
  }

  /**
   * Sends an RaytracerProgressEvent to all registered ActionListeners
   *
   * @param     progress  The current raytracing progress
   * @param     cmd       The ActionCommand as an element of the RaytracerCommands enum
   */
  protected void notifyActionListeners ( final RaytracerProgress progress, RaytracerCommands cmd ) {
    for ( ActionListener l : actionListeners ) {
      l.actionPerformed( new RaytracerProgressEvent( progress, cmd, this ) );
    }
  }

  /**
   * Traces a ray representing the specified pixel and determines the color of the pixel
   *
   * @param     x         The pixel's x coordinate
   * @param     y         The pixel's y coordinate
   * @param     width     The image's width
   * @param     height    The image's height
   * @return              The color in which the specified pixel shall be displayed
   */
  public Color traceRay ( final int x, final int y, final int width, final int height ) {
    if ( x < 0 || x >= width )
      throw new IllegalArgumentException( "Parameter x must be in the range of the image's width." );

    if ( y < 0 || y >= height )
      throw new IllegalArgumentException( "Parameter y must be in the range of the image's height" );

    final Ray r = camera.rayFor( width, height, x, y );
    final Hit h = world.hit( r );

    if ( h == null || h.t <= 0 )
      return world.backgroundColor;

    return h.geo.material.colorFor(h, world);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Raytracer raytracer = (Raytracer) o;

    if (width != raytracer.width) return false;
    if (height != raytracer.height) return false;
    if (progressInterval != raytracer.progressInterval) return false;
    if (started != raytracer.started) return false;
    if (!camera.equals(raytracer.camera)) return false;
    if (!world.equals(raytracer.world)) return false;
    if (!image.equals(raytracer.image)) return false;
    return actionListeners.equals(raytracer.actionListeners);

  }

  @Override
  public int hashCode() {
    int result = camera.hashCode();
    result = 31 * result + world.hashCode();
    result = 31 * result + image.hashCode();
    result = 31 * result + width;
    result = 31 * result + height;
    result = 31 * result + actionListeners.hashCode();
    result = 31 * result + progressInterval;
    result = 31 * result + (int) (started ^ (started >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Raytracer{" +
        "camera=" + camera +
        ", world=" + world +
        ", image=" + image +
        ", width=" + width +
        ", height=" + height +
        ", actionListeners=" + actionListeners +
        ", progressInterval=" + progressInterval +
        ", started=" + started +
        '}';
  }

  /**
   * Enumeration representing ratracing states
   */
  public enum RaytracerCommands {
    /** Raytracing is still in progress */
    PROGRESS,
    /** Raytracing has finished */
    FINISHED
  }

  /**
   * Represents a raytracing progress
   */
  protected class RaytracerProgress {
    /** The percentage of the pixels that have already been drawn relative to all pixels to draw */
    public final double percentage;

    /** The elapsed time spent on the raytracing of the image in milliseconds */
    public final long elapsed;

    /** The estimated time to draw all remaining pixels in milliseconds */
    public final long estimated;

    /**
     * Constructs a new RaytracerProgress
     *
     * @param     currentPixel  The number of pixels that have been drawn
     * @param     totalPixels   The total number of pixels in the image
     */
    public RaytracerProgress ( final int currentPixel, final int totalPixels ) {
      this.percentage = (currentPixel * 100.0) / totalPixels;
      this.elapsed = System.currentTimeMillis() - started;

      this.estimated = (long) (this.elapsed * ((100.0 / this.percentage) - 1));
    }
  }

  /**
   * Represents an event containing a RaytracerProgress that can be sent to ActionListeners to propagate
   * the current raytracing progress
   */
  public static class RaytracerProgressEvent extends ActionEvent {
    /** The current raytracing progress */
    public final RaytracerProgress progress;

    /**
     * Constructs a new RaytracerProgressEvent
     *
     * @param     progress  The current raytracing progress
     * @param     cmd       The action command
     * @param     raytracer The raytracer instance
     */
    public RaytracerProgressEvent ( RaytracerProgress progress, RaytracerCommands cmd, Raytracer raytracer ) {
      super( raytracer, (int) (Math.random() * Integer.MAX_VALUE), cmd.toString() );
      this.progress = progress;
    }

    /**
     * Returns the source as Raytracer
     *
     * @return    The Raytracer painting the image
     */
    public Raytracer getRaytracer () {
      return (Raytracer) source;
    }
  }
}