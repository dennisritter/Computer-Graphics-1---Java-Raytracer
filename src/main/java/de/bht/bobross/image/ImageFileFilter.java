package de.bht.bobross.image;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 *
 * @author      nathalie
 */

public class ImageFileFilter extends FileFilter {

  /**
   * Filters directories, jpg and png files in a JFileChooser.
   * @param f : the file to be checked.
   */
  public boolean accept(File f) {
    String name = f.getName();
    if (f.isDirectory() || name.endsWith(".jpg") ||
        name.endsWith(".png") || name.endsWith(".JPG") ||
        name.endsWith(".PNG")){
      return true;
    }
    return false;
  }

  @Override
  public String getDescription() {
    return "JPG and PNG";
  }

}
