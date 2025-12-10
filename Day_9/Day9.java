import java.io.*;
import java.util.*;

public class Day9{
    static long min_coord_x = Long.MAX_VALUE;
    static long max_coord_x = 0;
    static long min_coord_y = Long.MAX_VALUE;
    static long max_coord_y = 0;
    static long counter_first = 0;
    static long counter_second = 0;

    private static boolean betweenGreenPoints(ArrayList<Point> green_points, Point point){
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;

        for (Point p : green_points) {
            if (p.y == point.y) {
                if (p.x < minX) minX = p.x;
                if (p.x > maxX) maxX = p.x;
            }
        }
        return point.x >= minX && point.x <= maxX;
    }

    private static boolean checkValidSquare(ArrayList<Point> green_points, Point first_corner, Point second_corner) {
        Point corner3 = new Point(first_corner.x, second_corner.y);
        Point corner4 = new Point(second_corner.x, first_corner.y);

        boolean first_valid = green_points.contains(corner3) || betweenGreenPoints(green_points, corner3);
        boolean second_valid = green_points.contains(corner4) || betweenGreenPoints(green_points, corner4);

        return first_valid && second_valid;
    }

    private static ArrayList<Point> getGreenPoints(ArrayList<Point> red_points){
        ArrayList<Point> green_points = new ArrayList<>();

        for (int i = 0; i < red_points.size(); i++){
            for (int j = i + 1; j < red_points.size(); j++){
                if (red_points.get(i).sameXAxis(red_points.get(j)))
                    for (long y = Math.min(red_points.get(i).y, red_points.get(j).y); y <= Math.max(red_points.get(i).y, red_points.get(j).y); y++)
                        green_points.add(new Point(red_points.get(i).x, y));
                else if (red_points.get(i).sameYAxis(red_points.get(j)))
                    for (long x = Math.min(red_points.get(i).x, red_points.get(j).x); x <= Math.max(red_points.get(i).x, red_points.get(j).x); x++)
                        green_points.add(new Point(x, red_points.get(i).y));
            }
        }
        return green_points;
    }

    private static long doOperationFirst(ArrayList<Point> red_points){
        long maxArea = 0;
        long tmp_area = 0;
        for (int i = 0; i < red_points.size(); i++){
            for (int j = i + 1; j < red_points.size(); j++){
                if (red_points.get(i).possibleCorner(red_points.get(j))){
                    tmp_area = red_points.get(i).area(red_points.get(j));
                    if (tmp_area > maxArea)
                        maxArea = tmp_area;
                }
            }
        }
        return maxArea;
    }

    private static long doOperationSecond(ArrayList<Point> red_points){
        long maxArea = 0;
        long tmp_area = 0;
        ArrayList<Point> green_points = getGreenPoints(red_points);

        for (int i = 0; i < red_points.size(); i++){
            for (int j = i + 1; j < red_points.size(); j++){
                Point p1 = red_points.get(i);
                Point p2 = red_points.get(j);
                if (p1.possibleCorner(p2) && checkValidSquare(green_points,  p1, p2)){
                    tmp_area = p1.area(p2);
                    if (tmp_area > maxArea)
                        maxArea = tmp_area;
                }
            }
        }
        return maxArea;
    }

    private static void parseAndOperate(Scanner scan){
        long x = 0;
        long y = 0;
        String[] splitted;
        ArrayList<Point> red_points = new ArrayList<>();

        while (scan.hasNextLine()) {
            splitted = scan.nextLine().split(",");
            x = Long.parseLong(splitted[0]);
            y = Long.parseLong(splitted[1]);
            if (x > max_coord_x)
                max_coord_x = x;
            if (x < min_coord_x)
                min_coord_x = x;
            if (y > max_coord_y)
                max_coord_y = y;
            if (y < min_coord_y)
                min_coord_y = y;
            red_points.add(new Point(x, y));
        }
        counter_first = doOperationFirst(red_points);
        counter_second = doOperationSecond(red_points);
    }

    private static void getInputAndOperate() {
        Scanner scan = null;

        try {
            scan = new Scanner(new File("test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        parseAndOperate(scan);
        scan.close();

        System.out.println(counter_first);
        System.out.println(counter_second);
    }

    public static void main(String[] argv){
        getInputAndOperate();
        return;
    }
}
