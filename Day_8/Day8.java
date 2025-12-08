package Day_7;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Day8{
    static final int X = 0;
    static final int Y = 1;
    static final int Z = 2;
    static int counter_first = 0;
    static int counter_second = 0;

    private static double euklidischeDistanz(int[] first_point, int[] second_point){
        double x = Math.pow((second_point[X] - first_point[X]), 2);
        double y = Math.pow((second_point[Y] - first_point[Y]), 2);
        double z = Math.pow((second_point[Z] - first_point[Z]), 2);

        return (Math.sqrt(x + y + z));
    }

    private static int doOperationFirst(ArrayList<int[]> coordinates){
        int first_vec = 0;
        int second_vec = 0;
        double temp_dist = 0;
        double minDistance = euklidischeDistanz(coordinates.get(0), coordinates.get(1));
        ArrayList<int[]> circuits = new ArrayList<int[]>();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < coordinates.size(); j++){
                for (int k = j + 1; k < coordinates.size(); k++){
                    temp_dist = euklidischeDistanz(coordinates.get(j), coordinates.get(k));
                    if (temp_dist < minDistance){
                        first_vec = j;
                        second_vec = k;
                        minDistance = temp_dist;
                    }
                }
            }
            System.out.println(coordinates.get(first_vec)[X] + " | " + coordinates.get(second_vec)[X]);
            break;
        }
        return 0;
    }

    private static void parseAndOperate(Scanner scan){
        int[] temp_coord;
        String[] splitted;
        ArrayList<int[]> coordinates = new ArrayList<>();

        while (scan.hasNextLine()) {
            temp_coord = new int[3];
            splitted = scan.nextLine().split(",");
            for (int i = 0; i < 3; i++){
                temp_coord[i] = Integer.parseInt(splitted[i]);
            }
            coordinates.add(temp_coord);
        }
        counter_first = doOperationFirst(coordinates);
        // counter_second = doOperationSecond(map);
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
