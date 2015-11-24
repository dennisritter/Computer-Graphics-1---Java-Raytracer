package de.bht.bobross.raytracer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RaytracerFrame extends JFrame {

  protected final RaytracerPanel panel;

  public RaytracerFrame ( final Raytracer r, final int width, final int height ) {
    this.panel = new RaytracerPanel( r, width, height );

    setSize( width, height );
    setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
    setTitle( "Bob Ross Raytracer" );
    final Container container = getContentPane();
    container.setLayout( new BorderLayout() );
    container.add( panel, BorderLayout.CENTER );

    createMenu();
  }

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

}
