import java.io.*;
import java.util.*;

public class Day8{
    static long counter_first = 0;
    static long counter_second = 0;

    private static long doOperationFirst(ArrayList<JunctionBox> coordinates){
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < coordinates.size(); i++){
            for (int j = i + 1; j < coordinates.size(); j++){
                double d = coordinates.get(i).euclideanDistance(coordinates.get(j));
                edges.add(new Edge(coordinates.get(i), coordinates.get(j), d));
            }
        }
        Collections.sort(edges);

        DSU dsu = new DSU(coordinates.size());

        int connections = Math.min(1000, edges.size());
        for (int i = 0; i < connections; i++) {
            Edge e = edges.get(i);
            dsu.union(e.first_box.id, e.second_box.id);
        }

        Map<Integer, Integer> groups = new HashMap<>();

        for (int i = 0; i < coordinates.size(); i++) {
            int root = dsu.find(i);
            groups.put(root, groups.getOrDefault(root, 0) + 1);
        }

        List<Integer> sizes = new ArrayList<>(groups.values());
        sizes.sort(Collections.reverseOrder());

        long result = (long)sizes.get(0) * sizes.get(1) * sizes.get(2);
        return result;
    }

    private static long doOperationSecond(ArrayList<JunctionBox> coordinates) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < coordinates.size(); i++){
            for (int j = i + 1; j < coordinates.size(); j++){
                double d = coordinates.get(i).euclideanDistance(coordinates.get(j));
                edges.add(new Edge(coordinates.get(i), coordinates.get(j), d));
            }
        }

        Collections.sort(edges);

        DSU dsu = new DSU(coordinates.size());
        int components = coordinates.size();
        long lastX1 = 0, lastX2 = 0;

        for (Edge e : edges) {
            int rootA = dsu.find(e.first_box.id);
            int rootB = dsu.find(e.second_box.id);
            if (rootA != rootB) {
                dsu.union(rootA, rootB);
                lastX1 = e.first_box.x;
                lastX2 = e.second_box.x;
                components--;
                if (components == 1) 
                    break;
            }
        }
        return lastX1 * lastX2;
    }

    private static void parseAndOperate(Scanner scan){
        int i = 0;
        int[] temp_coord;
        String[] splitted;
        ArrayList<JunctionBox> coordinates = new ArrayList<>();

        while (scan.hasNextLine()) {
            splitted = scan.nextLine().split(",");
            coordinates.add(new JunctionBox(i++, Long.parseLong(splitted[0]), Long.parseLong(splitted[1]), Long.parseLong(splitted[2])));
        }
        counter_first = doOperationFirst(coordinates);
        counter_second = doOperationSecond(coordinates);
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
