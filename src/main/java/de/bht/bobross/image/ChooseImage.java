package de.bht.bobross.image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.awt.Container;
import java.awt.Image;
import java.awt.BorderLayout;
import java.io.IOException;

/**
 * Opens a file dialog and displays the selected PNG or JPEG.
 *
 * @author      nathalie
 */

public class ChooseImage {

  public static void main (String[] args) {

    /** The frame */
    final JFrame frame = new JFrame();

    /** The container*/
    final Container container = frame.getContentPane();

    /** The file dialog */
    final JFileChooser chooser = new JFileChooser();

    /** The file dialog's filter */
    final ImageFileFilter filter = new ImageFileFilter();

    /** The image to be displayed */
    final Image img;

    /** The image panel */
    final ImagePanel panel;

    frame.setVisible(true);
    container.setLayout(new BorderLayout());

    chooser.setFileFilter(filter);
    chooser.showOpenDialog(container);

   /**tries to read the selected file, stores it in variable img and adds it to the ImagePanel. */
    try {
      img = ImageIO.read(chooser.getSelectedFile());
      panel = new ImagePanel(img);
      container.add(panel);
      frame.setSize(panel.getImgSize(img));
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

}
