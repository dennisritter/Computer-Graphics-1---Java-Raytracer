package de.bht.bobross.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 *
 * @author      Nathalie Junker
 */
public class CreateImage {

  final static int WIDTH = 640;
  final static int HEIGHT = 480;
  final static Color RED = Color.red;

  static BufferedImage image;

  public static void main (String[] args){

    JFrame frame = new JFrame();
    Container c = frame.getContentPane();
    JPanel panel = new JPanel();

    c.add(panel);
    
    BufferedImage image = createImage();
    panel.add(image);
  }

  public static BufferedImage createImage(){
    image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

    for (int i = 0; i < WIDTH; i++){
      for(int j = 0; j < HEIGHT; j++) {
        image.setRGB(i, j, 0x000000);
      }
    }

    return image;

  }

}
