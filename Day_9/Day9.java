import java.io.*;
import java.util.*;

public class Day9{
    static long counter_first = 0;
    static long counter_second = 0;

    private static long doOperationFirst(ArrayList<Point> points){
        long maxArea = 0;
        long tmp_area = 0;
        for (int i = 0; i < points.size(); i++){
            for (int j = i + 1; j < points.size(); j++){
                if (points.get(i).poissibleCorner(points.get(j))){
                    tmp_area = points.get(i).area(points.get(j));
                    if (tmp_area > maxArea)
                        maxArea = tmp_area;
                }
            }
        }
        return maxArea;
    }

    private static void parseAndOperate(Scanner scan){
        int[] temp_coord;
        String[] splitted;
        ArrayList<Point> points = new ArrayList<>();

        while (scan.hasNextLine()) {
            splitted = scan.nextLine().split(",");
            points.add(new Point(Long.parseLong(splitted[0]), Long.parseLong(splitted[1])));
        }
        counter_first = doOperationFirst(points);
        // counter_second = doOperationSecond();
    }

    private static void getInputAndOperate() {
        Scanner scan = null;

        try {
            scan = new Scanner(new File("input.txt"));
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
