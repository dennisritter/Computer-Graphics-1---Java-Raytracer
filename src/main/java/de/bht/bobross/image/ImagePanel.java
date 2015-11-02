package de.bht.bobross.image;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * The ImagePanel creates a new panel
 *
 * @author      nathalie
 */

public class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(Image img){
    super();
    this.img = img;
  }

  /**
   * Draws the image.
   * @param g . the Graphic component
   */
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this);
  }

  /**
   * Returns a Dimension Object based on the width and height of the image.
   * @param img . the image
   * @return returns the size of the image.
   */
  public Dimension getImgSize(Image img){
    return new Dimension(img.getWidth(this), img.getHeight(this));
  }
}
