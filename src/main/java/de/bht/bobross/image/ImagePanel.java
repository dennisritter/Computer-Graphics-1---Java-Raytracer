package de.bht.bobross.image;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * The ImagePanel creates a new panel which draws the provided Image
 *
 * @author      nathalie
 */

public class ImagePanel extends JPanel {

  /**
   * The Image to draw inside the panel
   */
  private Image img;

  /**
   * Constructs a new panel with an Image object to draw
   *
   * @param     img     The image to draw in the panel
   */
  public ImagePanel(Image img){
    super();
    this.img = img;
  }

  /**
   * Draws the image.
   * @param     g         the Graphic component
   */
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this);
  }

  /**
   * Returns a Dimension Object based on the width and height of the image.
   *
   * @param     img       the image whose dimensions to adopt
   * @return              Dimension representing the image width and height
   */
  public Dimension getImgSize(Image img){
    return new Dimension(img.getWidth(this), img.getHeight(this));
  }
}
