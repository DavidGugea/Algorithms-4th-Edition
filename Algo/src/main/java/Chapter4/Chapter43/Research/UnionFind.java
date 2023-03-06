package Chapter4.Chapter43.Research;

public class UnionFind {
    public int[] parent;
    public int[] size;

    public UnionFind(int N) {
        this.parent = new int[N];
        this.size = new int[N];

        for (int i = 0; i < N; ++i) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    public int getRoot(int x) {
        while (this.parent[x] != x) {
            this.parent[x] = this.parent[this.parent[x]]; // path compression
            x = this.parent[x];
        }

        return x;
    }

    public boolean sameGroup(int a, int b) {
        // Returns true if a and b are in the same group, false otherwise.
        return this.getRoot(a) == this.getRoot(b);
    }

    public void union(int a, int b) {
        // Smaller tree goes into larger tree.
        int root_a = this.getRoot(a);
        int root_b = this.getRoot(b);

        if (size[root_a] > size[root_b]) {
            parent[root_b] = root_a;
            size[root_a] += size[root_b];
        } else {
            parent[root_a] = root_b;
            size[root_b] += size[root_a];
        }
    }

    private void print() {
        for (int i : this.parent) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : this.size) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);

        uf.print();
        System.out.println();
        uf.union(0, 1);
        uf.union(2, 1);
        uf.union(3, 4);
        uf.print();

        System.out.println(uf.sameGroup(0, 1));
        System.out.println(uf.sameGroup(2, 1));
        System.out.println(uf.sameGroup(3, 4));
        System.out.println(uf.sameGroup(3, 0));
    }
}
