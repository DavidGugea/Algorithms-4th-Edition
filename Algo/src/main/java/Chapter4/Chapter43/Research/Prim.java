package Chapter4.Chapter43.Research;

import java.util.Arrays;

public class Prim {
    public void Prim(int[][] G, int V) {
        int INF = Integer.MAX_VALUE;

        int no_edge; // number of edge

        boolean[] selected = new boolean[V];

        Arrays.fill(selected, false);

        // Set number of edge to 0
        no_edge = 0;

        // the number of edge in mst will be always less than (V-1), where V is the number of vertices in the graph.
        selected[0] = true;

        System.out.println("Edge : Weight");

        while (no_edge < V - 1) {
            // For every vertex in the set S, find all adjacent vertices
            // calculate the distance from the vertex selected at step 1.
            // if the vertex is already in the set S, discard it otherwise
            // choose another vertex nearest to selected vertex at step 1

            int min = INF;
            int x = 0; // row
            int y = 0; // col

            for (int i = 0 ; i < V; ++i) {
                if (selected[i]) {
                    for(int j = 0; j < V; ++j) {
                        if (!selected[j] && G[i][j] != 0) {
                            if (min > G[i][j]) {
                                min = G[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }

            System.out.println(x + " - " + y + " : " + G[x][y]);
            selected[y] = true;
            ++no_edge;
        }
    }
}
