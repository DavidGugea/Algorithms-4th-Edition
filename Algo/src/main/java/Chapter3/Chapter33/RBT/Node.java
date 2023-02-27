package Chapter3.Chapter33.RBT;

public class Node<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = true;

    public Key key; // key
    public Value value; // associated data
    Node left, right; // subtrees

    int N; // # nodes in this subtree
    boolean color;

    Node(Key key, Value value, int N, boolean color) {
        this.key = key;
        this.value = value;
        this.N = N;
        this.color = color;
    }

}
