import java.util.*;
import java.io.*;

public class Day7{
    static long counter_first = 0;
    static long counter_second = 0;

    private static int doOperationFirst(ArrayList<String> map) {
        int totalRows = map.size();
        int totalCols = map.get(0).length();
        int startColumn = map.get(0).indexOf('S');

        LinkedList<int[]> activeBeams = new LinkedList<>();
        activeBeams.add(new int[]{1, startColumn});

        int splitCount = 0;
        HashSet<String> visitedPositions = new HashSet<>();

        while (!activeBeams.isEmpty()) {
            int[] currentBeam = activeBeams.poll();
            int currentRow = currentBeam[0];
            int currentCol = currentBeam[1];

            if (currentRow < 0 || currentRow >= totalRows || currentCol < 0 || currentCol >= totalCols)
                continue;

            String positionKey = createPositionKey(currentRow, currentCol);
            if (visitedPositions.contains(positionKey))
                continue;

            visitedPositions.add(positionKey);

            char currentCell = map.get(currentRow).charAt(currentCol);

            if (currentCell == '.')
                activeBeams.add(new int[]{currentRow + 1, currentCol});
            else if (currentCell == '^') {
                splitCount++;
                if (currentCol - 1 >= 0) {
                    activeBeams.add(new int[]{currentRow, currentCol - 1});
                }
                if (currentCol + 1 < totalCols) {
                    activeBeams.add(new int[]{currentRow, currentCol + 1});
                }
            }
        }

        return splitCount;
    }

    private static String createPositionKey(int row, int col) {
        return row + "," + col;
    }

    private static long countPossibleTimelines(int currentRow, int currentCol, ArrayList<String> map, 
                                            HashMap<String, Long> memoization, 
                                            HashSet<String> currentPath) {
        
        if (currentRow >= map.size() || currentCol < 0 || currentCol >= map.get(0).length()) {
            return 1L;
        }
        
        String positionKey = createPositionKey(currentRow, currentCol);
        
        if (memoization.containsKey(positionKey)) {
            return memoization.get(positionKey);
        }
        
        if (currentPath.contains(positionKey)) {
            throw new IllegalStateException("Zyklus entdeckt an Position: " + positionKey);
        }
        
        currentPath.add(positionKey);
        
        long timelineCount;
        char currentCell = map.get(currentRow).charAt(currentCol);
        
        if (currentCell == '^') {
            long leftPaths = countPossibleTimelines(currentRow, currentCol - 1, map, memoization, currentPath);
            long rightPaths = countPossibleTimelines(currentRow, currentCol + 1, map, memoization, currentPath);
            timelineCount = leftPaths + rightPaths;
        } else {
            timelineCount = countPossibleTimelines(currentRow + 1, currentCol, map, memoization, currentPath);
        }
        
        currentPath.remove(positionKey);
        memoization.put(positionKey, timelineCount);
        
        return timelineCount;
    }

    private static long doOperationSecond(ArrayList<String> map) {
        int startRow = 0;
        int startCol = map.get(startRow).indexOf('S');

        HashSet<String> visiting = new HashSet<>();
        HashMap<String, Long> memo = new HashMap<>();

        return countPossibleTimelines(startRow + 1, startCol, map, memo, visiting);
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
