/**
 * Class resembling a coordinate pair.
 */
public class Point {

  private float x;

  private float y;

  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public String toString(){
    return "(" + x + ", " + y + ")";
  }
}