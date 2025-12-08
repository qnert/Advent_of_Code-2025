package Day_7;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Day6{
    static long counter_first = 0;
    static long counter_second = 0;

    private static int doOperationFirst(ArrayList<String> map) {
        int rows = map.size();
        int cols = map.get(0).length();

        int startCol = map.get(0).indexOf('S');

        LinkedList<int[]> beams = new LinkedList<>();
        beams.add(new int[]{1, startCol});

        int splits = 0;

        HashSet<String> visited = new HashSet<>();

        while (!beams.isEmpty()) {
            int[] b = beams.poll();
            int r = b[0];
            int c = b[1];

            if (r < 0 || r >= rows || c < 0 || c >= cols)
                continue;

            String key = r + "," + c;

            if (visited.contains(key))
                continue;

            visited.add(key);

            char ch = map.get(r).charAt(c);

            if (ch == '.') {
                beams.add(new int[]{r + 1, c});
            } 
            else if (ch == '^') {
                splits++;

                if (c - 1 >= 0)
                    beams.add(new int[]{r, c - 1});

                if (c + 1 < cols)
                    beams.add(new int[]{r, c + 1});
            }
        }

        return splits;
    }

    private static long countTimelines(int r, int c, ArrayList<String> map, HashMap<String, Long> memo, HashSet<String> visiting) {
        if (r >= map.size() || c < 0 || c >= map.get(0).length()) 
            return 1L;
        
        String key = r + "," + c;
        
        if (memo.containsKey(key)) return memo.get(key);
        if (visiting.contains(key)) 
            throw new IllegalStateException("Cycle at " + key);
        
        visiting.add(key);
        
        long total = map.get(r).charAt(c) == '^' 
            ? countTimelines(r, c - 1, map, memo, visiting) + countTimelines(r, c + 1, map, memo, visiting)
            : countTimelines(r + 1, c, map, memo, visiting);
        
        visiting.remove(key);
        memo.put(key, total);
        return total;
    }

    private static long doOperationSecond(ArrayList<String> map) {
                int startRow = 0;
        int startCol = map.get(startRow).indexOf('S');

        HashSet<String> visiting = new HashSet<>();
        HashMap<String, Long> memo = new HashMap<>();

        return countTimelines(startRow + 1, startCol, map, memo, visiting);
    }

    private static void parseAndOperate(Scanner scan){
        int i = 0;
        ArrayList<String> map = new ArrayList<>();

        while (scan.hasNextLine()) {
            map.add(scan.nextLine());
        }
        counter_first = doOperationFirst(map);
        counter_second = doOperationSecond(map);
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
