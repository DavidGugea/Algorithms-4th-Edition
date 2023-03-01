package Chapter4.Chapter42.Research;

import java.util.*;

public class Digraph {
    public HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    public void addVertex(int vertex) {
        map.put(vertex, new ArrayList<>());
    }

    public void addEdge(int v1, int v2) {
        map.get(v1).add(v2);
    }

    public void printGraph() {
        for (int v : map.keySet()) {
            System.out.print(v + " - ");

            for (int w : map.get(v)) {
                System.out.print(w + " ");
            }

            System.out.print("\n");
        }

        System.out.println(" ");
    }

    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.offer(vertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (!visited.contains(v)) {
                System.out.print(v + " - ");
                visited.add(v);
            }

            for (int w : map.get(v)) {
                if (!visited.contains(w)) {
                    queue.offer(w);
                }
            }
        }

        System.out.print("\n");
    }

    public void dfs(int vertex) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.add(vertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited.contains(v)) {
                System.out.print(v + " - ");
                visited.add(v);
            }

            for (int w : map.get(v)) {
                if (!visited.contains(w)) {
                    stack.push(w);
                }
            }
        }

        System.out.print("\n");
    }

    public void topologicalSort(int vertex) {
        Stack<Integer> output = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        topologicalSortRecursiveDFS(vertex, output, visited);

        while (output.size() != map.keySet().size()) {
            for (int w : map.keySet()) {
                if (!output.contains(w)) {
                    topologicalSortRecursiveDFS(w, output, visited);
                }
            }
        }

        for (int i : output) {
            System.out.println(i);
        }
    }

    public void topologicalSortRecursiveDFS(int vertex, Stack<Integer> output, HashSet<Integer> visited) {
        for (int w : map.get(vertex)) {
            if (!visited.contains(w)) {
                this.topologicalSortRecursiveDFS(w, output, visited);
            }
        }

        if (!visited.contains(vertex)) {
            visited.add(vertex);
            output.push(vertex);
        }
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph();

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

        graph.topologicalSort(2);
    }
}
