package de.bht.bobross.raytracer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * JFrame containing a RaytracerPanel and save functionality
 *
 * @author      Jannik Portz
 */
public class RaytracerFrame extends JFrame {

  /** The RaytracerPanel containing the raytraced image */
  protected final RaytracerPanel panel;

  /**
   * Constructs a new RaytracerFrame with a raytracer and image dimensions
   *
   * @param     r         The raytracer used to trace the scene
   */
  public RaytracerFrame ( final Raytracer r ) {
    this.panel = new RaytracerPanel( r );

    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setTitle( "Bob Ross Raytracer" );
    final Container container = getContentPane();
    createMenu();
    container.setPreferredSize( new Dimension( r.width, r.height ) );
    container.setLayout( new BorderLayout() );
    container.add( panel, BorderLayout.CENTER );
    this.pack();
  }

  /**
   * Creates a JMenu providing save functionality
   */
  public void createMenu(){
    final JMenuBar menu = new JMenuBar();
    final JMenu file = new JMenu ("Datei");
    final JMenuItem save = new JMenuItem("Speichern");

    save.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        final JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);

        try {
          final File outfile = chooser.getSelectedFile();
          ImageIO.write( panel.getImage() , "png", new File (outfile.toString() + ".png"));
        } catch (IOException ex){
          System.out.println("Ungültiges Dateiformat.");
        }
      }
    });

    file.add(save);
    menu.add(file);

    setJMenuBar(menu);
  }

  public void drawImage () {
    panel.drawImage();
  }
}