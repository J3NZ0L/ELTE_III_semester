/*
 */
public class RegularHexagon extends PlaneFigureEnclosableInRectangle {

  public RegularHexagon(Point center, float requiredAttributeLength) throws IllegalArgumentException{
    super(center, requiredAttributeLength);
  }

  public float areaOfEnclosingRectangle() {
    return (float) (2*Math.pow(this.getRequiredAttributeLength(),2)*Math.pow(3,(0.5)));
  }

}