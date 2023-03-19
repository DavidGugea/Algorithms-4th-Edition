package Chapter4.Chapter44;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new Edge(1, 4), new Edge(2, 2)));
        graph.put(1, Arrays.asList(new Edge(3, 1)));
        graph.put(2, Arrays.asList(new Edge(1, 1), new Edge(3, 5)));
        graph.put(3, Arrays.asList(new Edge(4, 3)));
        graph.put(4, Arrays.asList());

        int start = 0;
        int dist[] = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Edge e : graph.get(current.id)) {
                int newDist = dist[current.id] + e.weight;
                if (newDist < dist[e.to]) {
                    dist[e.to] = newDist;
                    pq.offer(new Node(e.to, newDist));
                }
            }
        }

        // Print out the shortest distances
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Shortest distance to node " + i + ": " + dist[i]);
        }
    }

    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Node implements Comparable<Node> {
        int id;
        int dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
}
