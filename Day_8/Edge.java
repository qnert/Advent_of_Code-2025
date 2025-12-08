import java.io.*;
import java.util.*;

public class Edge implements Comparable<Edge> {
    double dist;
    JunctionBox a, b;

    public Edge(JunctionBox a, JunctionBox b, double dist) {
        this.a = a;
        this.b = b;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.dist, o.dist);
    }
}