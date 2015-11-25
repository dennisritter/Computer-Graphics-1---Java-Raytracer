package de.bht.bobross.raytracer;

import de.bht.bobross.World;
import de.bht.bobross.camera.Camera;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * A Panel which has the raytracer draw the image
 *
 * @author    Jannik Portz
 */
public class RaytracerPanel extends JPanel implements ActionListener {

  /** The raytracer to use for the pixels */
  protected final Raytracer raytracer;

  /**
   * Constructs a new RaytracerPanel with a raytracer
   *
   * @param     raytracer The raytracer containing the scene and camera
   */
  public RaytracerPanel ( final Raytracer raytracer ) {
    this.raytracer = raytracer;
    this.raytracer.addActionListener( this );
    setSize( raytracer.width, raytracer.height );
  }

  /**
   * Constructs a new RaytracerPanel with the components for a raytracer
   *
   * @param     camera    The camera used to capture the scene
   * @param     world     The world representing the scene
   * @param     width     The image's width
   * @param     height    The image's height
   */
  public RaytracerPanel ( final Camera camera, final World world, final int width, final int height ) {
    this( new Raytracer( camera, world, width, height ) );
  }

  /**
   * Iterates through all pixels and draws the colors onto the image
   *
   * @param     g       The graphics instance to use for drawing the image
   */
  @Override
  protected void paintComponent( final Graphics g ) {
    super.paintComponent(g);
    g.drawImage( raytracer.image, 0, 0, this );
  }

  /**
   * Initiates the raytracer to draw the image
   */
  public void drawImage () {
    raytracer.loadImage();
  }

  /**
   * Callback for RaytracerProgressEvents
   * Continuously paints the image inside the panel
   *
   * @param     ae        The RaytracerProgressEvent representing the raytracing progress
   */
  public void actionPerformed ( final ActionEvent ae ) {
    if ( ae instanceof Raytracer.RaytracerProgressEvent )
      repaint();
  }

  public BufferedImage getImage () {
    return raytracer.image;
  }

  /**
   * Listener for RaytracerProgressEvents which prints the progress to the console
   */
  public class RaytracerProgressPrinter implements ActionListener {

    /**
     * Prints the progress details to the console
     *
     * @param     ae    The RaytracerProgressEvent representing the raytracing progress
     */
    public void actionPerformed( ActionEvent ae ) {
      if ( !(ae instanceof Raytracer.RaytracerProgressEvent) )
        return;

      final Raytracer.RaytracerProgressEvent e = (Raytracer.RaytracerProgressEvent) ae;
      final Raytracer.RaytracerProgress p = e.progress;

      System.out.printf( "%6.2f%% %10d %10d %n", p.percentage, p.elapsed, p.estimated );
    }
  }
}