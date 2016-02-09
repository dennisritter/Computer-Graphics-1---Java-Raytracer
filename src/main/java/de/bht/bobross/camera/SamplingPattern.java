package de.bht.bobross.camera;

import de.bht.bobross.math.Point2;

/**
 * Pattern for sampling points
 * @author      Jannik Portz
 */
public class SamplingPattern {

  /** The number of rows per pixel */
  public final int rows;

  /** The number of columns per pixel */
  public final int columns;

  /** The height of one row in a pixel */
  public final double rowHeight;

  /** The width of one column in a pixel */
  public final double columnWidth;

  /**
   * The constructor
   * @param rows    The number of rows per pixel
   * @param columns The number of columns per pixel
   */
  public SamplingPattern ( final int rows, final int columns ) {
    this.rows = rows;
    this.columns = columns;
    this.rowHeight = 1.0 / rows;
    this.columnWidth = 1.0 / columns;
  }

  /**
   * Generates points for a sampling pattern
   * @return    array of Point2 objects with coordinates between -0.5 and 0.5
   */
  public Point2[] generatePoints () {
    final Point2[] points = new Point2[ rows * columns ];

    int i = 0;
    for ( int x = 0; x < columns; ++x ) {
      for ( int y = 0; y < rows; ++y ) {
        final double xc = x * columnWidth + columnWidth * Math.random();
        final double yc = y * rowHeight + rowHeight * Math.random();
        points[i] = new Point2( xc - 0.5, yc - 0.5 );
        ++i;
      }
    }

    return points;
  }
}