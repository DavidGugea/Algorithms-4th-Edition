package Chapter4.Chapter44.Research;


import java.util.*;

public class EdgeWeightedDigraph {
    public HashMap<Integer, List<KeyValuePair<Integer, Integer>>> graph = new HashMap<>();

    public void addVertex(int v) {
        graph.put(v, new ArrayList<>());
    }

    public void addWeightedEdge(int sourceVertex, int targetVertex, int weight) {
        this.graph.get(sourceVertex).add(new KeyValuePair<>(targetVertex, weight));
    }

    public void printGraph() {
        for (int v : this.graph.keySet()) {
            System.out.print(v + " -> ");

            for (KeyValuePair<Integer, Integer> kvp : this.graph.get(v)) {
                System.out.print("( " + kvp.key + " - " + kvp.value + " ) - ");
            }

            System.out.println();
        }
    }

    public void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (!visited.contains(v)) {
                System.out.print(v + " - ");
                visited.add(v);
            }

            for (KeyValuePair<Integer, Integer> kvp : this.graph.get(v)) {
                if (!visited.contains(kvp.key)) {
                    queue.add(kvp.key);
                }
            }
        }
    }

    public void dfs(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited.contains(v)) {
                System.out.print(v + " - ");
                visited.add(v);
            }

            for (KeyValuePair<Integer, Integer> kvp : this.graph.get(v)) {
                if (!visited.contains(kvp.key)) {
                    stack.push(kvp.key);
                }
            }
        }
    }

    public void topologicalSort(int startVertex) {
        Stack<Integer> output = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        topologicalSortRecursiveDFS(startVertex, output, visited);

        while (output.size() != this.graph.keySet().size()) {
            for (int c : this.graph.keySet()) {
                if (!visited.contains(c)) {
                    this.topologicalSortRecursiveDFS(c, output, visited);
                }
            }
        }

        for (int i : output) {
            System.out.print(i + " - ");
        }
    }

    public void topologicalSortRecursiveDFS(int vertex, Stack<Integer> output, HashSet<Integer> visited) {
        for (KeyValuePair<Integer, Integer> kvp : this.graph.get(vertex)) {
            if (!visited.contains(kvp.key)) {
                this.topologicalSortRecursiveDFS(kvp.key, output, visited);
            }
        }

        if (!visited.contains(vertex)) {
            output.push(vertex);
            visited.add(vertex);
        }
    }
}
