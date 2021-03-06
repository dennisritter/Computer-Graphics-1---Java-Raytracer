package de.bht.bobross.image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
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

/**
 * Frame containing the drawn image and the menu bar
 *
 * @author      Nathalie Junker
 */
public class CreateImageFrame extends JFrame {

  /** The canvas to draw the image in */
  private ImageCanvas canvas;

  /**
   * Initializes the Frame, the ImageCanvas and a container.
   */
  public CreateImageFrame(){
    final Container container = this.getContentPane();

    this.canvas = new ImageCanvas();

    container.setLayout(new BorderLayout());
    container.add(canvas);

    canvas.repaint();
    createMenu();

    setSize(this.getWidth(), this.getHeight());
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
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

        try {
          final File outfile = chooser.getSelectedFile();
          ImageIO.write( canvas.getImage() , "png", new File (outfile.toString() + ".png"));
        } catch (IOException ex){
          System.out.println("Ungültiges Dateiformat.");
        }
      }
    });

    file.add(save);
    menu.add(file);

    setJMenuBar(menu);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreateImageFrame that = (CreateImageFrame) o;

    return !(canvas != null ? !canvas.equals(that.canvas) : that.canvas != null);

  }

  @Override
  public int hashCode() {
    return canvas != null ? canvas.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "CreateImageFrame{" +
        "canvas=" + canvas +
        '}';
  }
}
