package Chapter4.Chapter44.Research.Test;

import java.util.*;

public class UndirectedGraph {
    public HashMap<Integer, List<Integer>> graph = new HashMap<>();

    public void addVertex(int v) {
        graph.put(v, new ArrayList<>());
    }

    public void addEdge(int v, int w) {
        graph.get(v).add(w);
        graph.get(w).add(v);
    }

    public void printGraph() {
        for (int v : this.graph.keySet()) {
            System.out.print(v + " -> ");

            for (int w : this.graph.get(v)) {
                System.out.print(w + " - ");
            }

            System.out.println();
        }
    }

    public void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(v);

        while (!queue.isEmpty()) {
            int w = queue.poll();

            if (!visited.contains(w)) {
                visited.add(w);
                System.out.print(w + " - ");
            }

            for (int c : this.graph.get(w)) {
                if (!visited.contains(c)) {
                    queue.add(c);
                }
            }
        }

        System.out.println();
    }

    public void dfs(int v) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(v);

        while (!stack.isEmpty()) {
            int w = stack.pop();

            if (!visited.contains(w)) {
                visited.add(w);
                System.out.print(w + " - ");
            }

            for (int c : this.graph.get(w)) {
                if (!visited.contains(c)) {
                    stack.push(c);
                }
            }
        }

        System.out.println();
    }

    public void dfsrecur(int v, HashSet<Integer> visited) {
        if (visited == null) {
            visited = new HashSet<>();
        }

        if (!visited.contains(v)) {
            visited.add(v);
            System.out.print(v + " - ");
        }

        for (int c : this.graph.get(v)) {
            if (!visited.contains(c)) {
                dfsrecur(c, visited);
            }
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();

        int[] vertices = new int[]{1, 2, 3, 4, 5, 6, 7};

        for (int i : vertices) {
            graph.addVertex(i);
        }

        int[][] connections = new int[][]{
                {1, 4},
                {1, 5},
                {1, 2},
                {2, 7},
                {2, 6},
                {2, 3}
        };

        for (int[] edge : connections) {
            graph.addEdge(edge[0], edge[1]);
        }

        // graph.printGraph();
        graph.bfs(1);
        graph.dfs(1);
        graph.dfsrecur(1, null);
    }
}
