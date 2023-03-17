#include <bits/stc++.h>
using namespace std;

#define V 6 // No of vertices

int selectMinVertex(vector<int>& values, vector<bool>& processed) {
    int minimum = INT_MAX;
    int vertex;

    for (int i = 0 ; i < V; ++i) {
        if(processed[i] == false && value[i] < minimum) {
            vertex = i;
            minimum = value[i];
        }
    }

    return vertex;
}

void dijkstra(int graph[V][V]) {
    int parent[V];  // Stores shortes path structure
    vector<int> value(V, INT_MAX); // Keeps shortest path value to each vertex from source
    vector<bool> processed(V, false); // TRUE->Vertex is processed

    // Assuming start point as Node - 0
    parent[0] = -1;
    value[0] = 0;

    for (int i = 0 ; i < V - 1; ++i) {
        // Select best vertex by applying greedy method
        int U = selectMinVertex(value, processed);
        processed[U] = true;  // include new vertex in shortest path graph

        // relax adjacent vertices (not yet included in shortest path graph)
        for (int j = 0 ; j < V; ++j) {
            if (graph[U][j] != 0 && processed[j] == false && value[U] != INT_MAX && (value[U] + graph[U][j] < value[j])) {
                value[j] = value[U] + graph[U][j];
                parent[j] = U;
            }
        }
    }

    // Print shortest path graph
    for (int i =1 ; i < V; ++i) {
        cout << "U->V : " << parent[i] << " -> " << i << " wt = " << graph[parent[i]][i] << "\n";
    }
}