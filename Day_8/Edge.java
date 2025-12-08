import java.io.*;
import java.util.*;

public class Edge implements Comparable<Edge> {
    double dist;
    JunctionBox first_box, second_box;

    public Edge(JunctionBox first_box, JunctionBox second_box, double dist) {
        this.first_box = first_box;
        this.second_box = second_box;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.dist, o.dist);
    }
}