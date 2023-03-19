package Chapter4.Chapter44.Research.DijkstraFinal;

import java.sql.Array;
import java.util.*;

public class Dijkstra {
    private HashMap<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();

    public void addVertex(int vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    public void addEdge(int v, int u, int weight) {
        graph.get(v).add(new Edge(u, weight));
        graph.get(u).add(new Edge(v, weight));
    }

    public void dijkstra(int startVertex) {
        // Create a priority queue to store vertices and their distances from the start vertex
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Create a hash set to store visited vertices
        Set<Integer> visited = new HashSet<>();

        // Create an array to store the distances from the start vertex to each vertex
        HashMap<Integer, Integer> distances = new HashMap<>();

        for (int v : this.graph.keySet()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        // Add the start vertex to the priority queue
        pq.add(new Edge(startVertex, 0));

        // Loop until all vertices have been visited
        while (!pq.isEmpty()) {
            // Get teh vertex with the smallest distance from the start vertex.
            Edge currentEdge = pq.poll();
            int currentVertex = currentEdge.to;

            // If the vertex has already been visited, continue to the next vertex.
            if (visited.contains(currentVertex)) {
                continue;
            }

            // Mark the vertex as visited
            visited.add(currentVertex);

            // Update the distances of the neighboring vertices
            for (Edge edge: graph.get(currentVertex)) {
                int neighbourVertex = edge.to;
                int neighbourDistance = edge.weight;
                int newDistance = distances.get(currentVertex) + neighbourDistance;

                if (newDistance < distances.get(neighbourVertex)) {
                    pq.add(new Edge(neighbourVertex, newDistance));
                }
            }
        }
    }

    public void dijkstra2(int startVertex) {
        HashMap<Integer, Integer> distances = new HashMap<>();
        for (int v : this.graph.keySet()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.offer(new Edge(startVertex, 0));

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int currentVertex = currentEdge.to;

            if (visited.contains(currentVertex)) {
                continue;
            } else {
                visited.add(currentVertex);
            }

            for (Edge edge : this.graph.get(currentVertex)) {
                int travelVertex = edge.to;
                int travelWeight = edge.weight;
                int newDistance = distances.get(currentVertex) + travelWeight;

                if (visited.contains(travelVertex)) {
                    continue;
                }

                if (newDistance < distances.get(travelVertex)) {
                    distances.put(travelVertex, newDistance);
                    pq.add(new Edge(travelVertex, newDistance));
                }
            }
        }
    }

    public void printGraph() {
        for (int vertex : graph.keySet()) {
            System.out.print(vertex + " -> ");

            for (Edge edge : graph.get(vertex)) {
                System.out.print(" ( " +  edge.to + " - " + edge.weight + " ) - ");
            }

            System.out.println(" ");
        }
    }

    public void BFS(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            if (!visited.contains(vertex)) {
                System.out.print(vertex + " - ");
                visited.add(vertex);
            }

            for (Edge edge : this.graph.get(vertex)) {
                if (!visited.contains(edge.to)) {
                    queue.add(edge.to);
                }
            }
        }
    }

    public void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited.contains(vertex)) {
                System.out.print(vertex + " - ");
                visited.add(vertex);
            }

            for (Edge edge : this.graph.get(vertex)) {
                if (!visited.contains(edge.to)) {
                    stack.push(edge.to);
                }
            }
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

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra();
        int[] vertices = new int[] {0, 1, 2, 3, 4};
        int[][] connections = new int[][] {
                {0, 1, 4},
                {0, 2, 2},
                {1, 2, 1},
                {1, 3, 1},
                {2, 3, 5},
                {3, 4, 3},
        };

        for (int v : vertices) {
            graph.addVertex(v);
        }

        for (int[] connection : connections) {
            graph.addEdge(connection[0], connection[1], connection[2]);
        }

        /*
        graph.printGraph();
        System.out.println("BFS"); // breadth first search
        graph.BFS(3);
        System.out.println("\nDFS"); // depth first search
        graph.DFS(3);
        System.out.println("\nRecursive DFS"); // depth first search
        graph.recursiveDFS(3, null);
        */

        System.out.println("\nDijkstra"); // depth first search
        // graph.dijkstra(0);
    }
}