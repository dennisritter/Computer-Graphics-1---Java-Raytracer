package de.bht.bobross.image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author      Nathalie Junker
 */
public class CreateImageFrame extends JFrame{

  private CreateImageCanvas canvas;

  /**
   * The constructor. Initializes the Frame, the CreateImageCanvas and a container.
   */
  public CreateImageFrame(){

    final Container container = this.getContentPane();

    this.canvas = new CreateImageCanvas();

    container.setLayout(new BorderLayout());
    container.add(canvas);

    canvas.repaint();
    createMenu();

    setSize(this.getWidth(), this.getHeight());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);

  }

  /**
   * Creates a JMenuBar with the JMenu "Datei" and the JMenuItem "Speichern"
   */
  public void createMenu(){
    final JMenuBar menu = new JMenuBar();
    final JMenu file = new JMenu ("Datei");
    final JMenuItem save = new JMenuItem("Speichern");

    save.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        final JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);

        final BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);

        try {
          final File outfile = chooser.getSelectedFile();
          ImageIO.write(image, "png", new File (outfile.toString() + ".png"));
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
