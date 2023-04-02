/**
 *
 */
public class Main {
    /**
     *
     * @param args x1 y1 x2 y2
     */
    public static void main(String[] args) {
        Double[] arr = new Double[4];
        for (int i = 0; i < 4; ++i) {
            arr[i] = Double.parseDouble(args[i]);
        }
        Point p1 = new Point(arr[0], arr[1]);
        Point p2 = new Point(arr[2], arr[3]);
        System.out.println("Distance: " + p1.distance(p2));
        System.out.println("Are the two points equal? " + p1.equals(p2));
    }
}
