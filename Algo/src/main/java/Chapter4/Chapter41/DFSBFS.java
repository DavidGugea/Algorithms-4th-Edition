package Chapter4.Chapter41;

import java.util.*;

public class DFSBFS {
    private HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    public void addVertex(int x) {
        map.put(x, new ArrayList<>());
    }

    public void addEdge(int v1, int v2) {
        map.get(v1).add(v2);
        map.get(v2).add(v1);
    }

    public void printGraph() {
        for (int v : map.keySet()) {
            System.out.print(v + " - ");

            for (int w : map.get(v)) {
                System.out.print(w + " ");
            }

            System.out.print("\n");
        }
    }

    public void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (!visited.contains(v)) {
                System.out.print(v + " ");
                visited.add(v);
            }

            for (int w : this.map.get(v)) {
                if (!visited.contains(w)) {
                    queue.offer(w);
                }
            }
        }

        System.out.println(" ");
    }

    public void dfs(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited.contains(v)) {
                visited.add(v);
                System.out.print(v + " - ");
            }

            for (int w : map.get(v)) {
                if (!visited.contains(w)) {
                    stack.push(w);
                }
            }
        }
    }

    public void dfsrecur(int vertex, HashSet<Integer> visited) {
        if (visited == null) {
            visited = new HashSet<>();
        }

        if (!visited.contains(vertex)) {
            System.out.print(vertex + " - ");
            visited.add(vertex);
        }

        for (int w : map.get(vertex)) {
            if (!visited.contains(w)) {
                dfsrecur(w, visited);
            }
        }
    }

    public static void main(String[] args) {
        DFSBFS graph = new DFSBFS();
        /*
        int[] vertices = new int[]{1, 5, 4, 2, 7, 6, 3};

        for (int i : vertices) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 5);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 7);
        graph.addEdge(2, 6);
        graph.addEdge(2, 3);

        // graph.printGraph();
        */

        int[] vertices = new int[]{1, 2, 4, 3, 10, 9, 5, 8, 7, 6};

        for (int i : vertices) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(4, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 10);
        graph.addEdge(3, 9);
        graph.addEdge(2, 5);
        graph.addEdge(2, 8);
        graph.addEdge(2, 7);
        graph.addEdge(5, 8);
        graph.addEdge(5, 6);
        graph.addEdge(7, 5);
        graph.addEdge(7, 8);

        // graph.printGraph();

        System.out.println(" ------- ");

        // graph.bfs(1);
        System.out.println("Iterative DFS:");
        graph.dfs(1);
        System.out.print("\n");
        System.out.println("RECURSIVE DFS:");
        graph.dfsrecur(1, null);
    }
}
