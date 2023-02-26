package Chapter3.Chapter33.Research.BTree;

public class Node {
    public int T = 1;
    public int n;

    public int[] key = new int[2 * T - 1];
    public Node[] child = new Node[2*T];

    boolean leaf = true;

    public int find(int k) {
        for (int i = 0 ; i < this.n; ++i) {
            if (this.key[i] == k) {
                return i;
            }
        }

        return -1;
    }

    public Node(int T) {
        this.T = T;
    }
}
