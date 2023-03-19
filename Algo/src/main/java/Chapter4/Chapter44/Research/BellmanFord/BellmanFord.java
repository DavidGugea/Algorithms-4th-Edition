package Chapter4.Chapter44.Research.BellmanFord;

import java.util.*;

public class BellmanFord {
    private HashMap<Integer, List<Edge>> graph = new HashMap<>();
    private HashMap<Integer, Integer> distances = new HashMap<>();
    private HashMap<Integer, Integer> previous = new HashMap<>();

    public void addVertex(int vertex) {
        graph.put(vertex, new ArrayList<>());

        distances.put(vertex, Integer.MAX_VALUE);
        previous.put(vertex, null);
    }

    public void addEdge(int v, int u, int weight) {
        graph.get(v).add(new Edge(u, weight));
        graph.get(u).add(new Edge(v, weight));
    }

    public void bellmanFord(int startVertex) {
        distances.put(startVertex, 0);

        for (int i = 1; i < graph.size(); ++i) {
            for (int vertex : graph.keySet()) {
                if (distances.get(vertex) == Integer.MAX_VALUE) {
                    continue;
                }

                for (Edge edge : graph.get(vertex)) {
                    if (distances.get(vertex) + edge.weight < distances.get(edge.to)) {
                        distances.put(edge.to, distances.get(vertex) + edge.weight);
                        previous.put(edge.to, vertex);
                    }
                }
            }
        }

        for (int vertex : graph.keySet()) {
            if (distances.get(vertex) == Integer.MAX_VALUE) {
                continue;
            }

            for (Edge edge : graph.get(vertex)) {
                if (distances.get(vertex) + edge.weight < distances.get(edge.to)) {
                    System.out.println("Negative cycle detected.");
                    return;
                }
            }
        }

        for (int vertex : distances.keySet()) {
            System.out.println("Vertex " + vertex + ": distance = " + distances.get(vertex) + ", previous vertex = " + previous.get(vertex));
        }
    }

    public void recursiveDFS(int vertex, Set<Integer> visited) {
        if (visited == null) {
            visited = new HashSet<>();
        }

        if (!visited.contains(vertex)) {
            System.out.print(vertex + " - ");
            visited.add(vertex);
        }

        for (Edge edge : this.graph.get(vertex)) {
            if (!visited.contains(edge.to)) {
                this.recursiveDFS(edge.to, visited);
            }
        }
    }
}
