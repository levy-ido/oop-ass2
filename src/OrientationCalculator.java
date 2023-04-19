/**
 * This class calculates the orientation of an ordered triplet of points in the plane.
 */
public class OrientationCalculator {
    public static final int COUNTERCLOCKWISE = -1;
    public static final int COLLINEAR = 0;
    public static final int CLOCKWISE = 1;

    /**
     * Calculates the orientation of an ordered triplet of points in the plane.
     *
     * @param p1 A Point object representing the first point
     * @param p2 A Point object representing the second point
     * @param p3 A Point object representing the third point
     * @return COUNTERCLOCKWISE if the three points make a counterclockwise turn,
     * COLLINEAR if the three points are collinear,
     * CLOCKWISE if the three points make a clockwise turn
     */
    public int calculate(Point p1, Point p2, Point p3) {
        int cmpRes = Double.compare(new Line(p1, p2).slope(), new Line(p2, p3).slope());
        if (cmpRes == 0) {
            return COLLINEAR;
        }
        if (cmpRes < 0) {
            return COUNTERCLOCKWISE;
        }
        return CLOCKWISE;
    }

}
