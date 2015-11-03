package de.bht.bobross.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * Creates a new ChooseImageFrame and reads the selected image.
 *
 * @author      Nathalie Junker
 */
public class ChooseImageFrame extends JFrame{

  /**
   * Constructs a new ChooseImageFrame and automatically displays a JFileChooser.
   * When a file has been selected, it loads the image into the content container.
   */
  public ChooseImageFrame () {
    final Container container = this.getContentPane();
    final JFileChooser chooser = new JFileChooser();

    container.setLayout(new BorderLayout());

    chooser.setFileFilter( new ImageFileFilter() );
    chooser.showOpenDialog( null );
    try {
      final Image img = ImageIO.read(chooser.getSelectedFile());
      displayImage(img);
    } catch (IOException ex){
      ex.printStackTrace();
    }
  }

  /**
   * Reads the selected file, stores it in variable img and adds it to the ImagePanel.
   *
   * @param     img       The image to display in the frame
   */
  protected void displayImage ( final Image img ) {
    final ImagePanel panel = new ImagePanel(img);
    getContentPane().add(panel);
    setSize(panel.getImgSize(img));
  }

  /**
   * FileFilter for a JFileChooser, which accepts JPG and PNG files and directories.
   */
  protected class ImageFileFilter extends FileFilter {

    /**
     * Accept file with .png or .jpg extensions and directories
     *
     * @param     f       The file to be checked.
     */
    public boolean accept ( final File f ) {
      final String name = f.getName();
      return f.isDirectory() || name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".JPG") || name.endsWith(".PNG");
    }

    @Override
    public String getDescription() {
      return "JPG and PNG";
    }
  }
}
