package Chapter4.Chapter43.Research;

import java.util.*;

public class KruskalsAlgorithm {
    private final HashMap<Integer, List<KeyValuePair<Integer, Integer>>> map = new HashMap<>();

    public void addVertex(int v) {
        map.put(v, new ArrayList<>());
    }

    public void addEdge(int v1, int v2, int weight) {
        map.get(v1).add(new KeyValuePair<>(v2, weight));
        map.get(v2).add(new KeyValuePair<>(v1, weight));
    }

    public void printGraph(HashMap<Integer, List<KeyValuePair<Integer, Integer>>> map) {
        for (int v : map.keySet()) {
            System.out.print(v + " -> ");

            for (KeyValuePair<Integer, Integer> kvp : map.get(v)) {
                System.out.print("( " + kvp.key + " - " + kvp.value + " ) - ");
            }

            System.out.println();
        }
    }

    private boolean inTrack(KeyValuePair<Integer, Integer> targetKvp, List<KeyValuePair<Integer, Integer>> track) {
        for (KeyValuePair<Integer, Integer> kvp : track) {
            if (Objects.equals(kvp.key, targetKvp.key) && Objects.equals(kvp.value, targetKvp.value)) {
                return true;
            }
        }

        return false;
    }

    public List<int[]> KruskalMST(int[][] connections) {
        List<int[]> output = new ArrayList<>();

        // Sort by weight
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        /*
        for (int[] i : connections) {
            System.out.println(i[0] + " - " + i[1] + " - " + i[2]);
        }
        */

        // Get the number of vertices
        int numOfVertices = 0;
        List<Integer> track = new ArrayList<>();

        for (int[] i : connections) {
            if (!track.contains(i[0])) {
                numOfVertices += 1;
                track.add(i[0]);
            }

            if (!track.contains(i[1])) {
                numOfVertices += 1;
                track.add(i[1]);
            }
        }

        UnionFind uf = new UnionFind(numOfVertices);

        for (int[] connection : connections) {
            if (!uf.sameGroup(connection[0], connection[1])) {
                uf.union(connection[0], connection[1]);
                output.add(connection);
            }
        }

        return output;
    }

    public static void main(String[] args) {
        KruskalsAlgorithm graph = new KruskalsAlgorithm();

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

        // graph.printGraph(graph.map);
        List<int[]> output = graph.KruskalMST(connections);
        for (int[] i : output) {
            System.out.println(i[0] + " - " + i[1] + " - " + i[2]);
        }
    }
}
