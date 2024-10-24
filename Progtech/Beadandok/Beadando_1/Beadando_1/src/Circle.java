/*
 */
public class Circle extends PlaneFigureEnclosableInRectangle {

  public Circle(Point center, float requiredAttributeLength) throws IllegalArgumentException {
    super(center, requiredAttributeLength);
  }

  public float areaOfEnclosingRectangle() {
    return (float) Math.pow(this.getRequiredAttributeLength()*2,2);
  }
}