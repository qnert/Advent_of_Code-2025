import java.io.*;
import java.util.*;

class Point{
    final long x;
    final long y;

    public Point(long x, long y){
        this.x = x;
        this.y = y;
    }

    public boolean poissibleCorner(Point point){
        return !(this.x == point.x || this.y == point.y);
    }

    public long area(Point point) {
        long width  = Math.abs(this.x - point.x) + 1;
        long height = Math.abs(this.y - point.y) + 1;
        return width * height;
    }

    @Override
    public String toString(){
        return this.x + "," + this.y;
    }
}