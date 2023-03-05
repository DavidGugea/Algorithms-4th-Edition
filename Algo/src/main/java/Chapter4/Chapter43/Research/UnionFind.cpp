class UnionFind {
private:
    int *sets;
    int *size;
    int N;
public:
    UnionFind(int n) {
        // Set up a union-find data structure with n elements.
        N = n;
        sets = new int[N];
        size = new int[N];
        for (int i = 0 ; i < N; ++i) {
            sets[i] = i;
            size[i] = 1;
        }
    }

    int find(int x) {
        int root = x;

        while (parent[root] != root) {
            parent[root] = parent[parent[root]]; // PATH COMPRESSION
            root = parent[root];
        }

        return root;
    }

    void merge(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);

        if (size[root_y] > size[root_x]) {
            parent[root_x] = root_y;
            size[root_y] += size[root_x];
        } else {
            parent[root_y] = root_x;
            size[root_x] += size[root_y];
        }
    }
}
