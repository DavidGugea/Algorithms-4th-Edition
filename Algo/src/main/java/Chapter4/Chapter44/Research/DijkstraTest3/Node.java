package Chapter4.Chapter44.Research.DijkstraTest3;

import com.sun.source.tree.UsesTree;

import java.util.*;
import java.util.stream.Stream;

public class Node {
    public String name;
    public int distance = Integer.MAX_VALUE;
    public List<Node> shortestPath = new LinkedList<>();
    public Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    public void dijkstra(Node source) {
        source.distance = 0;
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>();

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();

            currentNode.adjacentNodes
                    .entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        int newDistance = sourceNode.distance + edgeWeight;

        if (newDistance < adjacentNode.distance) {
            adjacentNode.distance = newDistance;
            adjacentNode.shortestPath = Stream.concat(sourceNode.shortestPath.stream(), Stream.of(sourceNode)).toList();
        }
    }

    /*
    public void dijkstra(int[][] graph, int source) {
        int N = graph[0].length;
        int[] d = new int[N];

        for (int i = 0 ; i < N; ++i)
            d[i] = Integer.MAX_VALUE;

        d[source] = 0;
        Set<Integer> visited = new HashSet<>();

        while (visited.size() < N) {
            int u = (!visited).min(x -> d[x]);
            visited.add(u);

            for (v -> vertex adjacent to u) {
                id (d(v) > d(u) + d[u][v]) {
                    d(v) = d(u) + graph[u][v];
                }
            }
        }
    }
    */
}
