package Chapter4.Chapter44.Research.BellmanFord;

public class Edge implements Comparable<Edge> {
    public int to;
    public int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return Integer.compare(this.weight, edge.weight);
    }
}
