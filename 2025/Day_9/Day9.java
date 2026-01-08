import java.io.*;
import java.util.*;

public class Day9 {

    static long counterFirst = 0;
    static long counterSecond = 0;
    private static final int OUTSIDE = 0;
    private static final int INSIDE = 1;
    private static final int UNKNOWN = 2;
    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    private static long doOperationFirst(List<Point> points) {
        long maxArea = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point a = points.get(i);
                Point b = points.get(j);

                if (a.possibleCorner(b)) {
                    long area = a.area(b);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static Map<Long, Integer> shrinkCoordinates(List<Point> points, boolean useX) {
        int index = 0;
        Set<Long> axisValues = new TreeSet<>();
        Map<Long, Integer> compressed = new HashMap<>();

        for (Point p : points) {
            axisValues.add(useX ? p.x : p.y);
        }

        axisValues.add(Long.MIN_VALUE);
        axisValues.add(Long.MAX_VALUE);

        for (Long value : axisValues) {
            compressed.put(value, index++);
        }

        return compressed;
    }

    private static int[] getMinMax(GridPoint a, GridPoint b) {
        int x1 = Math.min(a.x, b.x);
        int y1 = Math.min(a.y, b.y);
        int x2 = Math.max(a.x, b.x);
        int y2 = Math.max(a.y, b.y);

        return new int[]{x1, y1, x2, y2};
    }

    private static void floodFillOutside(int[][] grid) {
        int width = grid.length;
        int height = grid[0].length;
        Deque<GridPoint> queue = new ArrayDeque<>();

        queue.add(new GridPoint(0, 0));

        if (grid[0][0] == UNKNOWN) {
            grid[0][0] = OUTSIDE;
        }

        while (!queue.isEmpty()) {
            GridPoint cur = queue.poll();

            for (int[] d : DIRECTIONS) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx >= 0 && nx < width &&
                    ny >= 0 && ny < height &&
                    grid[nx][ny] == UNKNOWN) {

                    grid[nx][ny] = OUTSIDE;
                    queue.add(new GridPoint(nx, ny));
                }
            }
        }
    }

    private static int[][] buildPrefixSum(int[][] grid) {
        int width = grid.length;
        int height = grid[0].length;
        int[][] prefix = new int[width][height];

        for (int y = 1; y < height; y++) {
            for (int x = 1; x < width; x++) {
                int value = (grid[x][y] != OUTSIDE ? 1 : 0);
                prefix[x][y] = value + prefix[x - 1][y] + prefix[x][y - 1] - prefix[x - 1][y - 1];
            }
        }

        return prefix;
    }

    private static long doOperationSecond(List<Point> points) {

        Map<Long, Integer> shrinkX = shrinkCoordinates(points, true);
        Map<Long, Integer> shrinkY = shrinkCoordinates(points, false);

        List<GridPoint> compressed = new ArrayList<>();
        for (Point p : points) {
            compressed.add(new GridPoint(shrinkX.get(p.x), shrinkY.get(p.y)));
        }

        int width = shrinkX.size();
        int height = shrinkY.size();
        int[][] grid = new int[width][height];

        for (int[] row : grid) {
            Arrays.fill(row, UNKNOWN);
        }

        int size = points.size();

        for (int i = 0; i < size; i++) {
            GridPoint a = compressed.get(i);
            GridPoint b = compressed.get((i + 1) % size);

            int[] bounds = getMinMax(a, b);
            for (int x = bounds[0]; x <= bounds[2]; x++) {
                for (int y = bounds[1]; y <= bounds[3]; y++) {
                    grid[x][y] = INSIDE;
                }
            }
        }

        floodFillOutside(grid);
        int[][] prefix = buildPrefixSum(grid);
        long maxArea = 0;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {

                Point A = points.get(i);
                Point B = points.get(j);

                if (A.x == B.x || A.y == B.y)
                    continue;

                GridPoint a = compressed.get(i);
                GridPoint b = compressed.get(j);

                int[] bounds = getMinMax(a, b);
                int x1 = bounds[0], y1 = bounds[1], x2 = bounds[2], y2 = bounds[3];

                int expected = (x2 - x1 + 1) * (y2 - y1 + 1);

                int actual = prefix[x2][y2] - (x1 > 0 ? prefix[x1 - 1][y2] : 0) - (y1 > 0 ? prefix[x2][y1 - 1] : 0) + (x1 > 0 && y1 > 0 ? prefix[x1 - 1][y1 - 1] : 0);

                if (expected == actual) {
                    long dx = Math.abs(A.x - B.x) + 1;
                    long dy = Math.abs(A.y - B.y) + 1;
                    maxArea = Math.max(maxArea, dx * dy);
                }
            }
        }

        return maxArea;
    }

    private static void parseAndOperate(Scanner scan) {
        List<Point> points = new ArrayList<>();

        while (scan.hasNextLine()) {
            String[] s = scan.nextLine().split(",");
            long x = Long.parseLong(s[1]);
            long y = Long.parseLong(s[0]);
            points.add(new Point(x, y));
        }

        counterFirst = doOperationFirst(points);
        counterSecond = doOperationSecond(points);
    }

    private static void getInputAndOperate() {
        try (Scanner scan = new Scanner(new File("input.txt"))) {
            parseAndOperate(scan);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(counterFirst);
        System.out.println(counterSecond);
    }

    public static void main(String[] args) {
        getInputAndOperate();
    }
}
