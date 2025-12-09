import java.io.*;
import java.util.*;

public class Day9{
    static long counter_first = 0;
    static long counter_second = 0;
    private static ArrayList<Point> getGreenPoints(ArrayList<Point> red_points){
        ArrayList<Point> green_points = new ArrayList<>();

        for (int i = 0; i < red_points.size(); i++){
            for (int j = i + 1; j < red_points.size(); j++){
                if (red_points.get(i).sameXAxis(red_points.get(j)))
                    for (long y = Math.min(red_points.get(i).y, red_points.get(j).y) + 1; y < Math.max(red_points.get(i).y, red_points.get(j).y); y++)
                        green_points.add(new Point(red_points.get(i).x, y));
                else if (red_points.get(i).sameYAxis(red_points.get(j)))
                    for (long x = Math.min(red_points.get(i).x, red_points.get(j).x) + 1; x < Math.max(red_points.get(i).x, red_points.get(j).x); x++)
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
                if (red_points.get(i).poissibleCorner(red_points.get(j))){
                    tmp_area = red_points.get(i).area(red_points.get(j));
                    if (tmp_area > maxArea)
                        maxArea = tmp_area;
                }
            }
        }
        return maxArea;
    }

    private static long doOperationSecond(ArrayList<Point> red_points){
        ArrayList<Point> green_points = getGreenPoints(red_points);
        // for (int i = 0; i < green_points.size(); i++)
        //     System.out.println(green_points.get(i));
        return 0;
    }

    private static void parseAndOperate(Scanner scan){
        int[] temp_coord;
        String[] splitted;
        ArrayList<Point> red_points = new ArrayList<>();

        while (scan.hasNextLine()) {
            splitted = scan.nextLine().split(",");
            red_points.add(new Point(Long.parseLong(splitted[0]), Long.parseLong(splitted[1])));
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
