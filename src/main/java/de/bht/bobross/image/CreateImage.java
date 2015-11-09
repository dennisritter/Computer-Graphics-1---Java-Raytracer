package de.bht.bobross.image;

/**
 * Creates an image with a black background, a diagonal red line
 * and the size of WIDTH and HEIGHT
 *
 * @author      Nathalie Junker
 */
public class CreateImage {

  /** The initial width of the image */
  public final static int WIDTH = 640;

  /** The initial height of the image */
  public final static int HEIGHT = 480;
  
  public static void main (String[] args){
    final CreateImageFrame frame = new CreateImageFrame();
    frame.setSize(WIDTH,HEIGHT);
    frame.setVisible(true);
  }

}
