package de.bht.bobross.raytracer;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * Dialog displaying the current raytracing progress.
 *
 * @author      Jannik Portz
 */
public class RaytracerProgressDialog extends JDialog implements ActionListener {

  /** The progress bar indicating how many pixels have already been traces */
  protected final JProgressBar progressBar;

  /** Duration representing the elapsed time */
  protected Duration elapsed;

  /** Duration representing the estimated time */
  protected Duration estimated;

  /**
   * Constructs a new RaytracerProgressDialog
   */
  public RaytracerProgressDialog () {
    setSize( 350, 400 );
    setLayout( new BoxLayout( getContentPane(), BoxLayout.Y_AXIS ) );

    progressBar = new JProgressBar();
    progressBar.setIndeterminate( true );
    progressBar.setMinimum( 0 );
    progressBar.setMaximum( 100 );
    progressBar.setOrientation( SwingConstants.HORIZONTAL );

    elapsed = Duration.ofMillis( 0 );
    estimated = Duration.ofMillis( 0 );

    try {
      add( new ImagePanel() );
    } catch ( Exception e ) {
      e.printStackTrace();
    }

    add( progressBar );
    add( new DurationPanel() );

    setDefaultCloseOperation( HIDE_ON_CLOSE );
  }

  /**
   * Updates the attributes based on the provided progress object
   *
   * @param     progress  The object representing the current progress
   */
  protected void handleProgress ( final Raytracer.RaytracerProgress progress ) {
    progressBar.setValue( (int) progress.percentage );
    elapsed = Duration.ofMillis( progress.elapsed );
    estimated = Duration.ofMillis( progress.estimated );

    if ( progressBar.isIndeterminate() )
      progressBar.setIndeterminate( false );

    repaint();
  }

  /**
   * Handles the RaytracerProgressEvents.
   * Closes the Dialog when the image has fully been rendered
   *
   * @param     ae        The RaytracerProgressEvent containing the progress information
   */
  public void actionPerformed( final ActionEvent ae ) {
    if ( !(ae instanceof Raytracer.RaytracerProgressEvent) )
      return;

    final Raytracer.RaytracerProgressEvent e = (Raytracer.RaytracerProgressEvent) ae;
    final Raytracer.RaytracerCommands cmd = Raytracer.RaytracerCommands.valueOf( e.getActionCommand() );

    if ( cmd == Raytracer.RaytracerCommands.FINISHED )
      dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );

    handleProgress( e.progress );
  }

  /**
   * Panel displaying the durations
   */
  protected class DurationPanel extends JPanel {

    /** Label containing the elapsed time */
    protected final JLabel elapsedLabel;

    /** Label containing the estimated time */
    protected final JLabel estimatedLabel;

    /**
     * Constructs a new DurationPanel
     */
    public DurationPanel () {
      setLayout( new FlowLayout() );

      elapsedLabel = new JLabel( "-" );
      estimatedLabel = new JLabel( "-" );

      final JPanel elapsedPanel = new JPanel();
      elapsedPanel.setLayout( new FlowLayout() );
      elapsedPanel.add( new JLabel( "Elapsed Time: " ) );
      elapsedPanel.add( elapsedLabel );

      final JPanel estimatedPanel = new JPanel();
      estimatedPanel.setLayout( new FlowLayout() );
      estimatedPanel.add( new JLabel( "Estimated Time Remaining: " ) );
      estimatedPanel.add( estimatedLabel );

      add( elapsedPanel );
      add( estimatedPanel );
    }

    @Override
    protected void paintComponent(Graphics g) {
      elapsedLabel.setText( createDurationString( elapsed ) );
      estimatedLabel.setText( createDurationString( estimated ) );
      super.paintComponent(g);
    }

    /**
     * Creates a String representing the specified Duration object with hours, minutes and seconds
     *
     * @param     duration    The Duration to convert to a string
     * @return                The String representing the duration
     */
    public String createDurationString ( Duration duration ) {
      return String.format( "%02d:%02d:%02d", duration.toHours(), duration.toMinutes(), duration.toMillis() / 1000 );
    }
  }

  /**
   * Panel containing the image of our idol.
   */
  protected class ImagePanel extends JPanel {

    /** The Bob Ross image */
    protected BufferedImage image;

    /** The resource path to the image */
    public static final String BOBROSS_IMAGE = "/images/bobross.jpg";

    /**
     * Constructs a new ImagePanel and loads the image resource
     *
     * @throws    IOException     If the file could not be read
     * @throws    URISyntaxException
     */
    public ImagePanel () throws IOException, URISyntaxException {
      final File imageFile = new File( this.getClass().getResource( BOBROSS_IMAGE ).toURI() );
      final ImageInputStream stream = new FileImageInputStream( imageFile );
      image = ImageIO.read( stream );
      setSize( image.getWidth(), image.getHeight() );
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage( image, 0, 0, this );
    }
  }
}