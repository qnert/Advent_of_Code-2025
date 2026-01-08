import java.io.*;
import java.util.*;

public class JunctionBox{
    final long x;
    final long y;
    final long z;
    final int id;

    public JunctionBox(int id, long x, long y, long z){
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double euclideanDistance(JunctionBox vector){
        double x = Math.pow((vector.x - this.x), 2);
        double y = Math.pow((vector.y - this.y), 2);
        double z = Math.pow((vector.z - this.z), 2);

        return (Math.sqrt(x + y + z));
    }

    @Override
    public String toString() {
        return "JunctionBox{id=" + id + ", (" + x + "," + y + "," + z + ")}";
    }
}