package Chapter3.Chapter33.Research.BTree;

public class BTree {
    private int T;
    private Node root;

    public BTree(int t) {
        T = t;

        root = new Node(T);
        root.n = 0;
        root.leaf = true;
    }

    public Node Search(Node x, int key) {
        int i = 0;
        if (x == null)
            return x;

        for (i = 0; i < x.n; ++i) {
            if (key < x.key[i]) {
                break;
            }

            if (key == x.key[i]) {
                return x;
            }
        }

        if (x.leaf) {
            return null;
        } else {
            return Search(x.child[i], key);
        }
    }

    public void Split(Node x, int pos, Node y) {

    }
}
