/*
 */
public class Square extends PlaneFigureEnclosableInRectangle {

  public Square(Point center, float requiredAttributeLength) throws IllegalArgumentException{
    super(center, requiredAttributeLength);
  }

  public float areaOfEnclosingRectangle() {
    return (float) (Math.pow(this.getRequiredAttributeLength()*2,2));
  }

}