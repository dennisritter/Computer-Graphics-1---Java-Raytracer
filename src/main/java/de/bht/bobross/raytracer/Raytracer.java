package de.bht.bobross.raytracer;

import de.bht.bobross.Color;
import de.bht.bobross.Ray;
import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

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
   * Spawn a new Thread for every available processor on the machine and dedicates a line range.
   * Sends a RaytracerProgressEvent when all Threads have finished with action command FINISHED
   */
  public void loadImage () {
    started = System.currentTimeMillis();

    final int height = image.getHeight();
    final int totalPixels = height * image.getWidth();
    final int numberProcessors = Runtime.getRuntime().availableProcessors();
    final int linesPerThread = height / numberProcessors;
    final AtomicInteger c = new AtomicInteger( 0 );

    final Thread[] threads = new Thread[ numberProcessors ];

    for ( int i = 0; i < numberProcessors; ++i ) {
      final int minY = i * linesPerThread;
      final int maxY = (i+1) * linesPerThread;
      final RaytracerRunnable runnable = new RaytracerRunnable( minY, Math.min( maxY, height ), c );
      final Thread t = new Thread( runnable );
      t.start();
      threads[ i ] = t;
    }

    for ( Thread t : threads ) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
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
   * Enumeration representing raytracing states
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

  /**
   * Runnable raytracing a specific part of the image
   */
  protected class RaytracerRunnable implements Runnable {
    /** Index of the first line to trace (inclusive) */
    public final int minY;

    /** Index of the last line to trace (exclusive) */
    public final int maxY;

    /** Shared counter representing the number of pixels that have been drawn in the whole image */
    public final AtomicInteger counter;

    /** Tracer used to trace the rays */
    public final Tracer tracer;

    /**
     * Constructs a new RaytracerRunnable
     *
     * @param     minY      Index of the first line to trace (inclusive)
     * @param     maxY      Index of the last line to trace (exclusive)
     * @param     counter   Shared counter representing the number of pixels that have been drawn in the whole image
     */
    public RaytracerRunnable ( final int minY, final int maxY, final AtomicInteger counter ) {
      this.minY = minY;
      this.maxY = maxY;
      this.counter = counter;
      this.tracer = new Tracer( world );
    }

    /**
     * Iterates through all pixels in the dedicated line range and saves them in the image's DataBuffer
     */
    public void run () {
      final DataBufferInt dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
      final int[] pixels = dataBuffer.getData();
      final int w = image.getWidth();
      final int h = image.getHeight();
      final int totalPixels = w * h;

      for ( int y = minY; y < maxY; y++ ) {
        for ( int x = 0; x < w; x++ ) {
          pixels[ (h-y-1) * w + x ] = tracePixel( x, y ).asInt();
          counter.incrementAndGet();
          if ( counter.get() % progressInterval == 0 ) {
            notifyActionListeners( new RaytracerProgress( counter.get(), totalPixels ), RaytracerCommands.PROGRESS );
          }
        }
      }
    }

    /**
     * Traces a ray from the specified pixel in the camera
     *
     * @param   x       The pixel's x coordinate
     * @param   y       The pixel's y coordinate
     * @return          The color for the pixel
     */
    protected Color tracePixel ( final int x, final int y ) {
      tracer.resetRecursionCounter();
      final Ray ray = camera.rayFor( image.getWidth(), image.getHeight(), x, y );
      return tracer.traceRay( ray );
    }
  }
}