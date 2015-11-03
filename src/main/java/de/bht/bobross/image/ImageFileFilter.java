package de.bht.bobross.image;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * FileFilter for a JFileChooser, which accepts JPG and PNG files and directories.
 *
 * @author      Nathalie Junker
 */
public class ImageFileFilter extends FileFilter {

  /**
   * Accept file with .png or .jpg extensions and directories
   *
   * @param     f       the file to be checked.
   */
  public boolean accept ( File f ) {
    final String name = f.getName();
    return f.isDirectory() || name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".JPG") || name.endsWith(".PNG");
  }

  @Override
  public String getDescription() {
    return "JPG and PNG";
  }

}