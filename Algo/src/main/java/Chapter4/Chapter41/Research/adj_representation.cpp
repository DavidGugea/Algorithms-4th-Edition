#include <iostream>

using namespace std;

int main() {
    // n is the number of vertices
    // m is the number of edges
    int n, m;
    cin >> n >> m;

    /*
    3 Vertices
    9 combinations

    [
        0 1 2
        2 3 4
        5 6 7
    ]
    */

    int adjMat[n+1][n+1];
    for (int i = 0 ; i < m ; ++i) {
        int u, v;
        cin >> u >> v;
        adjMat[u][v] = 1;
        adjMat[v][u] = 1;
    }

    return 0;
}