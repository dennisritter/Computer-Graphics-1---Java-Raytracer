package de.bht.bobross.raytracer;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
    setSize( 350, 200 );
    setLayout( new BoxLayout( getContentPane(), BoxLayout.Y_AXIS ) );

    progressBar = new JProgressBar();
    progressBar.setIndeterminate( true );
    progressBar.setMinimum( 0 );
    progressBar.setMaximum( 100 );
    progressBar.setOrientation( SwingConstants.HORIZONTAL );

    elapsed = Duration.ofMillis( 0 );
    estimated = Duration.ofMillis( 0 );

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

    if ( cmd == Raytracer.RaytracerCommands.FINISHED )
      dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );

    handleProgress( e.progress );
  }

  protected class DurationPanel extends JPanel {

    protected final JLabel elapsedLabel;
    protected final JLabel estimatedLabel;

    public DurationPanel () {
      setLayout( new GridLayout( 2, 2 ) );

      elapsedLabel = new JLabel( "-" );
      estimatedLabel = new JLabel( "-" );

      add( new JLabel( "Elapsed Time:" ) );
      add( new JLabel( "Estimated Time remaining:" ) );
      add( elapsedLabel );
      add( estimatedLabel );
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
}