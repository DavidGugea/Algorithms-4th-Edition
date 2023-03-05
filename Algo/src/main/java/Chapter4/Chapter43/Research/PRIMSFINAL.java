package Chapter4.Chapter43.Research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PRIMSFINAL {
    HashMap<Integer, List<KeyValuePair<Integer, Integer>>> map = new HashMap<>();

    public void addVertex(int v) {
        map.put(v, new ArrayList<>());
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        map.get(vertex1).add(new KeyValuePair<>(vertex2, weight));
        map.get(vertex2).add(new KeyValuePair<>(vertex1, weight));
    }

    public void printGraph(HashMap<Integer, List<KeyValuePair<Integer, Integer>>> graphMap) {
        for (int v : graphMap.keySet()) {
            System.out.print(v + " -> ");

            for (KeyValuePair<Integer, Integer> kvp : graphMap.get(v)) {
                System.out.print("( " + kvp.key + " - " + kvp.value + " ) - ");
            }

            System.out.print("\n");
        }
    }

    public HashMap<Integer, List<KeyValuePair<Integer, Integer>>> primsMST(int startVertex) {
        HashMap<Integer, List<KeyValuePair<Integer, Integer>>> mst = new HashMap<>();
        HashSet<Integer> unvisited = new HashSet<>(this.map.keySet());

        unvisited.remove(startVertex);
        mst.put(startVertex, new ArrayList<>());

        while (!unvisited.isEmpty()) {
            int minWeight = Integer.MAX_VALUE;
            int nextVertex = -1;
            int connectionVertex = -1;

            for (int v : mst.keySet()) {
                for (KeyValuePair<Integer, Integer> kvp : this.map.get(v)) {
                    if (!mst.containsKey(kvp.getKey())) {
                        if (kvp.value < minWeight) {
                            minWeight = kvp.value;
                            nextVertex = kvp.key;
                            connectionVertex = v;
                        }
                    }
                }
            }

            if (!mst.containsKey(nextVertex)) {
                mst.put(nextVertex, new ArrayList<>());
            }
            if (!mst.containsKey(connectionVertex)) {
                mst.put(connectionVertex, new ArrayList<>());
            }
            mst.get(nextVertex).add(new KeyValuePair<>(connectionVertex, minWeight));
            mst.get(connectionVertex).add(new KeyValuePair<>(nextVertex, minWeight));

            unvisited.remove(nextVertex);
        }

        return mst;
    }

    public static void main(String[] args) {
        PRIMSFINAL graph = new PRIMSFINAL();

        int[] vertices = new int[]{0, 1, 7, 2, 8, 6, 3, 5, 4};
        for (int i : vertices) {
            graph.addVertex(i);
        }

        int[][] connections = new int[][]{
                {0, 1, 1},
                {0, 7, 2},
                {1, 7, 3},
                {1, 2, 4},
                {7, 8, 5},
                {7, 6, 6},
                {6, 8, 7},
                {8, 2, 8},
                {2, 3, 9},
                {2, 5, 10},
                {6, 5, 11},
                {5, 4, 12},
                {3, 4, 13},
        };

        for (int[] i : connections) {
            graph.addEdge(i[0], i[1], i[2]);
        }

        graph.printGraph(graph.map);
        System.out.println("-----------------------------------------");
        graph.printGraph(graph.primsMST(0));
    }
}
