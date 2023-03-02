package Chapter4.Chapter43;

import java.util.HashMap;
import java.util.List;

public class EdgeWeightedGraph {
    private final int V; // number of vertices
    private int E; // number of edges
    private HashMap<Integer, List<Edge>> map;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;

        map = new HashMap<>();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);

        map.get(v).add(e);
        map.get(w).add(e);

        ++E;
    }

    public Iterable<Edge> adj(int v) {
        return map.get(v);
    }
}
