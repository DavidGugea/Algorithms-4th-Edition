package Chapter4.Chapter41;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Graph {
    private final int V;  // number of vertices
    private int E;  // number of edges
    private HashMap<Integer, List<Integer>> map;

    public Graph(int V) {
        this.V = V;
        this.E = 0;

        map = new HashMap<>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        map.get(v).add(w);
        map.get(w).add(v);
        E++;
    }

    public List<Integer> adj(int v) {
        return map.get(v);
    }
}
