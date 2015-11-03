package de.bht.bobross.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;

/**
 * Creates a new ChooseImageFrame and reads the selected image.
 *
 * @author      nathalie
*/
public class ChooseImageFrame extends JFrame{

  /** The container*/
  final Container container;

  /** The file dialog */
  final JFileChooser chooser;

  /** The file dialog's filter */
  final ImageFileFilter filter;

  /** The image to be displayed */
  Image img;


  public ChooseImageFrame(){
    container = this.getContentPane();
    chooser = new JFileChooser();
    filter = new ImageFileFilter();

    container.setLayout(new BorderLayout());

    chooser.setFileFilter(filter);
    chooser.showOpenDialog(null);
    try {
      img = ImageIO.read(chooser.getSelectedFile());
    } catch (IOException ex){
      ex.printStackTrace();
    }
    displayImage(img);
  }

  /** Reads the selected file, stores it in variable img and adds it to the ImagePanel. */
  public void displayImage(Image img){
    final ImagePanel panel = new ImagePanel(img);
    container.add(panel);
    this.setSize(panel.getImgSize(img));
  }
}
