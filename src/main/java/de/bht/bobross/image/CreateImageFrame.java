package de.bht.bobross.image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author      Nathalie Junker
 */
public class CreateImageFrame extends JFrame{

  private BufferedImage img;

  /**
   * The constructor.
   */
  public CreateImageFrame(){
    final Container container = this.getContentPane();
    final CreateImageCanvas canvas = new CreateImageCanvas();

    container.setLayout(new BorderLayout());
    container.add(canvas);

    canvas.repaint();
    System.out.println(canvas.getWidth());

    setSize(this.getWidth(), this.getHeight());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);

    createMenu();

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

        try {
          final File outfile = chooser.getSelectedFile();
          ImageIO.write(img, "png", outfile);
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
