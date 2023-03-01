#include <iostream>

using namespace std;

int main() {
    int v, e;
    cin >> v >> e;

    int adjMat[v][v];

    for (int i = 0 ; i < e; ++i) {
        int u, v;
        cin >> u >> v;

        adjMat[u][v] = 1;
        adjMat[v][u] = 1;
    }

    for(int i = 0 ; i < v; ++i) {
        for(int j = 0 ; j < v; ++j) {
            cout << adjMat[i][j] << " - ";
        }

        cout << " " << endl;
    }

    return 0;
}