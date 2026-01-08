import java.io.*;
import java.util.*;

public class DSU {
    int[] parent;
    int[] size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA == rootB) 
            return;
        
        if (size[rootA] < size[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }
        
        parent[rootB] = rootA;
        size[rootA] += size[rootB];
    }

    public int getSize(int x) {
        return size[find(x)];
    }
}
