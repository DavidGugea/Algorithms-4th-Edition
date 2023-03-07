package Chapter4.Chapter44.Research.Test;

import java.util.*;

public class Digraph {
    public HashMap<Integer, List<Integer>> graph = new HashMap<>();

    public void addVertex(int v) {
        this.graph.put(v, new ArrayList<>());
    }

    public void addEdge(int v, int w) {
        this.graph.get(v).add(w);
    }

    public void printGraph() {
        for (int v : this.graph.keySet()) {
            System.out.print(v + " -> ");

            for (int i : this.graph.get(v)) {
                System.out.print(i + " - ");
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
            dfsrecur(c, visited);
        }
    }

    public void topologicalSort(int startVertex) {
        Stack<Integer> output = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        topologicalSortRecursiveDFS(startVertex, output, visited);

        while (output.size() != this.graph.keySet().size()) {
            for (int w : this.graph.keySet()) {
                if (!visited.contains(w)) {
                    topologicalSortRecursiveDFS(w, output, visited);
                }
            }
        }

        for (int i : output) {
            System.out.print(i + " - ");
        }
    }

    public void topologicalSortRecursiveDFS(int vertex, Stack<Integer> output, HashSet<Integer> visited) {
        for (int c : this.graph.get(vertex)) {
            if (!visited.contains(c)) {
                topologicalSortRecursiveDFS(c, output, visited);
            }
        }

        if (!visited.contains(vertex)) {
            output.push(vertex);
            visited.add(vertex);
        }
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph();

        int[] vertices = new int[]{0, 1, 2, 3, 4, 5};
        for (int i : vertices) {
            graph.addVertex(i);
        }

        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(3, 1);
        graph.addEdge(2, 3);

        graph.printGraph();
        System.out.println();
        System.out.println("-----------------");
        System.out.println();
        graph.topologicalSort(0);
    }
}
