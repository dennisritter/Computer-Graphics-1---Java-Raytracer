package de.bht.bobross.raytracer;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * Dialog displaying the current raytracing progress
 *
 * @author      Jannik Portz
 */
public class RaytracerProgressDialog extends JDialog implements ActionListener {

  protected final JProgressBar progressBar;
  protected Duration elapsed;
  protected Duration estimated;

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

  protected void handleProgress ( final Raytracer.RaytracerProgress progress ) {
    progressBar.setValue( (int) progress.percentage );
    elapsed = Duration.ofMillis( progress.elapsed );
    estimated = Duration.ofMillis( progress.estimated );

    if ( progressBar.isIndeterminate() )
      progressBar.setIndeterminate( false );

    repaint();
  }

  public void actionPerformed( final ActionEvent ae ) {
    if ( !(ae instanceof Raytracer.RaytracerProgressEvent) )
      return;

    final Raytracer.RaytracerProgressEvent e = (Raytracer.RaytracerProgressEvent) ae;
    final Raytracer.RaytracerCommands cmd = Raytracer.RaytracerCommands.valueOf( e.getActionCommand() );

    //if ( cmd == Raytracer.RaytracerCommands.FINISHED )
    //  dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );

    handleProgress( e.progress );
  }

  protected class DurationPanel extends JPanel {

    protected final JLabel elapsedLabel;
    protected final JLabel estimatedLabel;

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

    public String createDurationString ( Duration duration ) {
      return String.format( "%02d:%02d:%02d", duration.toHours(), duration.toMinutes(), duration.toMillis() / 1000 );
    }
  }

  protected class ImagePanel extends JPanel {

    protected BufferedImage image;
    public static final String BOBROSS_IMAGE = "/images/bobross.jpg";

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