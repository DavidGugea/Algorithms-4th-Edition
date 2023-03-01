package Chapter4.Chapter42;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Digraph {
    private final int V;
    private int E;

    public HashMap<Integer, List<Integer>> map;

    public Digraph(int V) {
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
        E++;
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);

        for (int v = 0; v < V; ++v) {
            for (int w : map.get(v)) {
                R.addEdge(w, v);
            }
        }

        return R;
    }
}
