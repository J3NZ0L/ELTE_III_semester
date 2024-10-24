/**
 * Class resembling the super class of all plane figures which can be enclosed in a rectangle, such as the ones needed
 * for this assignment, like circle, square, regular hexagon, and regular triangle.
 * All of these can be described by their centre, being a point made up of x and y coordinates, and the length of either
 * their sides, or their radius (in case of circles).
 *
 * @author Zoltán Jeney
 */
public abstract class PlaneFigureEnclosableInRectangle{
  /**
   * Either the side or the radius length of the plane figure, by which it can be described easily.
   */
  protected float requiredAttributeLength;
  /**
   * The center of the plane figure on the coordinate system, enclosed in a Point object.
   */
  private final Point center;

  /**
   * Initializes the instance with the length value (radius or side length), and the Point object resembling the center
   * @param center the x and y coordinates of the center of the plane figure, encapsulated into a Point object
   * @param requiredAttributeLength either the side or the radius length of the plane figure, by which it can be
   *                                described easily
   * @throws IllegalArgumentException if given attribute length is not greater than zero
   * @see PlaneFigureEnclosableInRectangle#center
   * @see PlaneFigureEnclosableInRectangle#requiredAttributeLength
   */
  public PlaneFigureEnclosableInRectangle(Point center, float requiredAttributeLength) throws IllegalArgumentException
  {
    if (requiredAttributeLength<0){
      throw new IllegalArgumentException("Required attribute length must be greater than zero");
    }
    this.center = center;
    this.requiredAttributeLength = requiredAttributeLength;
  }

  /**
   *  Calculates the area of the enclosing rectangle, based off on the plane figure's required attribute, keeping in
   *  mind the custom logic of the specific type and calculation method of it.
   * @return the calculated area
   * @see PlaneFigureEnclosableInRectangle#requiredAttributeLength
   */
  public abstract float areaOfEnclosingRectangle();

  /**
   * Getter for the required attribute length member.
   * @return value of member requiredAttributeLength
   * @see PlaneFigureEnclosableInRectangle#requiredAttributeLength
   */
  public float getRequiredAttributeLength() {
    return this.requiredAttributeLength;
  }

  public String toString(){
    //return this.getClass().getName()+", center: "+this.center.toString() + ", required attr. len.: " + this.requiredAttributeLength;
    return this.getClass().getName()+", attr. len.: " + this.requiredAttributeLength;
  }
}