package de.bht.bobross.image;

<<<<<<< HEAD
=======
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

>>>>>>> a4be4ff1c991f107a0b72a8925aef5abb8f8ca70
/**
 * Creates an image with a black background, a diagonal red line
 * and the size of WIDTH and HEIGHT
 *
 * @author      Nathalie Junker
 */
public class CreateImage {

  /** The width of the image   */
  final static int WIDTH = 640;

  /** The height of the image   */
  final static int HEIGHT = 480;


  public static void main (String[] args){

    final CreateImageFrame frame = new CreateImageFrame();
    frame.setSize(WIDTH,HEIGHT);
    frame.setVisible(true);

  }



}
