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

    private static int[] createTuple(int x, int y){
        int[] tuple = new int[2];

        tuple[0] = x;
        tuple[1] = y;

        return tuple;
    }

    private static double euklidischeDistanz(String[] first_point, String[] second_point){
        double x = Math.pow((Integer.parseInt(second_point[X]) - Integer.parseInt(first_point[X])), 2);
        double y = Math.pow((Integer.parseInt(second_point[Y]) - Integer.parseInt(first_point[Y])), 2);
        double z = Math.pow((Integer.parseInt(second_point[Z]) - Integer.parseInt(first_point[Z])), 2);

        return (Math.sqrt(x + y + z));
    }

    private static int doOperationFirst(ArrayList<String> coordinates){
        int first_vec = 0;
        int second_vec = 0;
        double temp_dist = 0;
        double minDistance = 0;
        HashMap<String, ArrayList<String>> circuits = new HashMap<>();

        for (int i = 0; i < 10; i++){
            minDistance = Double.MAX_VALUE;
            for (int j = 0; j < coordinates.size(); j++){
                for (int k = j + 1; k < coordinates.size(); k++){
                    String key = coordinates.get(j);
                    String value = coordinates.get(k);

                    temp_dist = euklidischeDistanz(coordinates.get(j).split(","), coordinates.get(k).split(","));
                    if (temp_dist < minDistance){
                        first_vec = j;
                        second_vec = k;
                        minDistance = temp_dist;
                    }
                }
            }
            System.out.println(coordinates.get(first_vec) + " | " + coordinates.get(second_vec));
        }
        return 0;
    }

    private static void parseAndOperate(Scanner scan){
        int[] temp_coord;
        ArrayList<String> coordinates = new ArrayList<>();

        while (scan.hasNextLine()) {
            coordinates.add(scan.nextLine());
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
