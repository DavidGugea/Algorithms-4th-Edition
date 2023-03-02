package Chapter4.Chapter42.Research;

import java.util.*;

public class DigraphTopologicalSort2 {
    public HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    public void addVertex(int v) {
        map.put(v, new ArrayList<>());
    }

    public void addEdge(int v, int w) {
        map.get(v).add(w);
    }

    public void printGraph() {
        for (int v : this.map.keySet()) {
            System.out.print(v + " - ");

            for (int w : this.map.get(v)) {
                System.out.print(w + " ");
            }

            System.out.print("\n");
        }
    }

    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.offer(vertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (!visited.contains(v)) {
                System.out.print(v + " ");
                visited.add(v);
            }

            for (int w : map.get(v)) {
                if (!visited.contains(w)) {
                    queue.offer(w);
                }
            }
        }
    }

    public void dfs(int vertex) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(vertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited.contains(v)) {
                System.out.print(v + " ");
                visited.add(v);
            }

            for (int w : map.get(v)) {
                if (!visited.contains(w)) {
                    stack.push(w);
                }
            }
        }
    }

    public void dfsRecur(int vertex, HashSet<Integer> visited) {
        if (visited == null) {
            visited = new HashSet<>();
        }

        if (!visited.contains(vertex)) {
            System.out.print(vertex + " ");
            visited.add(vertex);
        }

        for (int w : map.get(vertex)) {
            if (!visited.contains(w)) {
                this.dfsRecur(w, visited);
            }
        }
    }

    public void topologicalSort(int vertex) {
        Stack<Integer> output = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        topologicalSortRecursiveDFS(vertex, output, visited);

        while (output.size() != this.map.keySet().size()) {
            for (int w : map.keySet()) {
                if (!output.contains(w)) {
                    topologicalSortRecursiveDFS(w, output, visited);
                }
            }
        }

        for (int w : output) {
            System.out.println(w);
        }
    }

    private void topologicalSortRecursiveDFS(int vertex, Stack<Integer> output, HashSet<Integer> visited) {
        for (int w : map.get(vertex)) {
            if (!visited.contains(w)) {
                topologicalSortRecursiveDFS(w, output, visited);
            }
        }

        if (!visited.contains(vertex)) {
            visited.add(vertex);
            output.push(vertex);
        }
    }

    public static void main(String[] args) {
        DigraphTopologicalSort2 graph = new DigraphTopologicalSort2();


        int[] vertices = new int[]{0, 1, 2, 3, 4};
        for (int i : vertices) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        // graph.printGraph();
        for (int i : vertices) {
            graph.topologicalSort(i);
            System.out.println(" --------------- ");
        }
    }
}
