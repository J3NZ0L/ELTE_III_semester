/*
 */
public class RegularTriangle extends PlaneFigureEnclosableInRectangle {

  public RegularTriangle(Point center, float requiredAttributeLength) throws IllegalArgumentException{
    super(center, requiredAttributeLength);
  }

  public float areaOfEnclosingRectangle() {
    return (float) (Math.pow(this.getRequiredAttributeLength(),2)*Math.pow(3,(0.5))/2);
  }
}