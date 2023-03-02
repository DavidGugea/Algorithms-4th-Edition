package Chapter4.Chapter43.Research;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EdgeWeightedGraph2 {
    public HashMap<Integer, List<AbstractMap.SimpleEntry<Integer, Integer>>> map = new HashMap<>();

    public void addVertex(int v) {
        map.put(v, new ArrayList<>());
    }

    public void addEdge(int v, int w, int weight) {
        map.get(v).add(new AbstractMap.SimpleEntry<Integer, Integer>(w, weight));
        map.get(w).add(new AbstractMap.SimpleEntry<Integer, Integer>(v, weight));
    }

    public void printGraph() {
        for (int v : map.keySet()) {
            System.out.print(v + " - ");

            for (AbstractMap.SimpleEntry<Integer, Integer> kvp : map.get(v)) {
                System.out.print("( " + kvp.getKey() + " " + kvp.getValue() + " ) ");
            }

            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        EdgeWeightedGraph2 graph = new EdgeWeightedGraph2();

        int[] vertices = new int[]{0, 1, 2, 3, 4};
        for (int i : vertices) {
            graph.addVertex(i);
        }

        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 3, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 4, 9);
        graph.addEdge(3, 4, 15);

        graph.printGraph();
    }
}
